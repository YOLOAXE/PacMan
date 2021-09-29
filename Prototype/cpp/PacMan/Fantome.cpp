#include "Fantome.h"
#include "GameManager.h"

Fantome::Fantome(uint8_t id, uint8_t ** cc, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec3 color_base, glm::vec2 indicePos) : Actor(id, cc, indicePos)
{
	m_player = p;
	m_spawnPoint.x = indicePos.x;
	m_spawnPoint.y = indicePos.y;
	glm::vec3 pos = GameManager::IndiceToWorldPos(indicePos);
	m_maxModel = shapeBuffers.size();
	m_colorBase = color_base;
	m_matcount = textureFantome.size();
	m_material = (Materials**)malloc(sizeof(Materials*)*m_matcount);
	for (int i = 0; i < m_matcount; i++)
	{
		m_material[i] = pc.materialManager->createMaterial();
		m_material[i]->setColor(m_colorBase);
		m_material[i]->setAlbedoTexture(textureFantome[i]);
		m_material[i]->setPipeline(pipeline);
	}
	m_model = (Model**)malloc(sizeof(Model*) * m_maxModel);
	for (int i = 0; i < m_maxModel; i++)
	{
		m_model[i] = pc.modelManager->createModel(shapeBuffers[i], "Fantome");
		m_model[i]->addComponent(this);
		m_model[i]->setPosition(pos);
		m_model[i]->setMaterial(m_material[Direction::East]);
		m_model[i]->setEulerAngles(glm::vec3(20.0f, 0, 0));
	}
	m_model[1]->setScale(glm::vec3(base_scale));
	m_model[0]->setScale(glm::vec3(0.0f));
	m_light = pc.lightManager->createPointLight(GameManager::IndiceToWorldPos(indicePos) + glm::vec3(0.0f, 5.0f, 0.0f), m_colorBase+glm::vec3(0.2f), "FantomeLight");
	m_light->setRange(8.0f);

	m_pathCarte.resize(CARTE_HAUTEUR);
	for (int j = 0; j < CARTE_HAUTEUR; j++)
	{
		m_pathCarte[j].resize(CARTE_LARGEUR);
	}
	for (int i = 0; i < CARTE_LARGEUR; i++)
	{
		for (int j = 0; j < CARTE_HAUTEUR; j++)
		{
			m_pathCarte[j][i].mur = COLIDER_CARTE_BASE[j][i] == WALL_ID;
			m_pathCarte[j][i].pos = glm::vec2(i, j);
			m_pathCarte[j][i].f_cost = 10000000.0f;
			m_pathCarte[j][i].parent = nullptr;
			
			if (j - 1 >= 0)
			{
				if (!m_pathCarte[j - 1][i].mur)
				{
					m_pathCarte[j][i].neighbour.push_back(&m_pathCarte[j - 1][i]);
					m_pathCarte[j][i].neighbourDir.push_back(Direction::South);
				}
				if (!m_pathCarte[j][i].mur)
				{
					m_pathCarte[j - 1][i].neighbour.push_back(&m_pathCarte[j][i]);
					m_pathCarte[j - 1][i].neighbourDir.push_back(Direction::North);
				}
			}
			if (i - 1 >= 0)
			{
				if (!m_pathCarte[j][i - 1].mur)
				{
					m_pathCarte[j][i].neighbour.push_back(&m_pathCarte[j][i - 1]);
					m_pathCarte[j][i].neighbourDir.push_back(Direction::East);
				}
				if (!m_pathCarte[j][i].mur)
				{
					m_pathCarte[j][i - 1].neighbour.push_back(&m_pathCarte[j][i]);
					m_pathCarte[j][i - 1].neighbourDir.push_back(Direction::West);
				}
			}
		}
	}
	//m_pathReader = new PathReader(&m_pathCarte);
	//pc.hud->addBlockUI(m_pathReader);
}

node * findlowestFCost(std::vector<node *> nodes)
{
	float cost = 20000000.0f;
	node * choise = nullptr;
	for (node * n : nodes)
	{
		if (n->f_cost < cost)
		{
			cost = n->f_cost;
			choise = n;			
		}
	}
	return choise;
}

Direction Fantome::recursiveAddPath(node * n)
{
	if (n->parent == nullptr)
	{
		return Direction::North;
	}
	if (n->parent->parent == nullptr)
	{
		return n->parentDir;
	}		
	m_pathDir.push_back(recursiveAddPath(n->parent));
	return n->parentDir;
}

void Fantome::pathFinding(glm::vec2 targetPos)
{
	m_indicePathDir = 0;
	m_pathDir.clear();
	std::vector<node *> closePath;
	std::vector<node *> openPath;
	node * start = &m_pathCarte[(int)m_indicePos.y][(int)m_indicePos.x];
	node * target = &m_pathCarte[(int)targetPos.y][(int)targetPos.x];
	node * current = start;
	current->calculeCost(start,0, target);
	openPath.push_back(current);
	bool end = false;
	for (int i = 0; i < CARTE_HAUTEUR; i++)
	{
		for (int j = 0; j < CARTE_LARGEUR; j++)
		{
			m_pathCarte[i][j].f_cost = 10000000.0f;
			m_pathCarte[i][j].parent = nullptr;
		}
	}

	while (!end)
	{
		current = findlowestFCost(openPath);
		if (current == nullptr)
		{
			Debug::Warn("Dont work %d", openPath.size());
			return;
		}
		openPath.erase(std::remove(openPath.begin(), openPath.end(), current), openPath.end());
		closePath.push_back(current);
		if (current == target)
		{
			recursiveAddPath(target);
			m_pathDir.push_back(current->parentDir);
			end = true;
			return;
		}

		for (node * neighbour : current->neighbour)
		{
			if (std::find(closePath.begin(), closePath.end(), neighbour) == closePath.end() && std::find(openPath.begin(), openPath.end(), neighbour) == openPath.end())
			{
				neighbour->calculeCost(current, current->g_cost, target);
				openPath.push_back(neighbour);
			}
		}

	}
}

void Fantome::start()
{
	m_nextPos = m_indicePos;
	m_worldPos = GameManager::IndiceToWorldPos(m_indicePos);
	m_nextWorldPos = GameManager::IndiceToWorldPos(m_nextPos);
}

void Fantome::update()
{
	if (m_boost)
	{
		if (m_indicePathDir == m_pathDir.size() || !m_move)
		{
			pathFinding(m_spawnPoint);
			if (m_fuite)
			{
				m_fuite = false;
				m_boost = false;
				m_endFuite = true;
				m_actorSpeed = BASE_SPEED;
			}
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
	}

	if (m_timerDeplacement > m_actorSpeed / 2.0f && !m_nextState && m_indicePathDir < m_pathDir.size())
	{
		m_nextState = true;
		m_nextDir = m_pathDir[m_indicePathDir++];
	}
	if (m_move)
	{
		timer += pc.time->getDeltaTime();
	}
	if (timer > animationSpeed)
	{
		timer = 0.0f;
		m_model[currentAnimatedFrame]->setScale(glm::vec3(0.0f));
		currentAnimatedFrame = ++currentAnimatedFrame % 2;
		m_model[currentAnimatedFrame]->setScale(glm::vec3(base_scale));
	}

	if (m_boost && !m_fuite && m_timerboost < 3.0f)
	{
		if ((int)(m_timerboost*2) % 2 == 0)
		{
			m_material[4]->setColor(glm::vec3(FUITE_COLOR[0], FUITE_COLOR[1], FUITE_COLOR[2]));
			for (int i = 0; i < m_maxModel; i++)
			{
				m_model[i]->setMaterial(m_material[4]);
			}
		}
		else
		{
			m_material[5]->setColor(glm::vec3(FUITE_END_COLOR[0], FUITE_END_COLOR[1], FUITE_END_COLOR[2]));
			for (int i = 0; i < m_maxModel; i++)
			{
				m_model[i]->setMaterial(m_material[5]);
			}
		}
	}

	if (glm::distance(m_player->getIndicePos(), m_indicePos) == 0.0f)
	{		
		if (m_boost)
		{
			m_fuite = true;		
			m_actorSpeed = FUITE_SPEED;
			m_material[m_dir]->setColor(glm::vec3(DEAD_FANTOME_COLOR[0], DEAD_FANTOME_COLOR[1], DEAD_FANTOME_COLOR[2]));
			for (int i = 0; i < m_maxModel; i++)
			{
				m_model[i]->setMaterial(m_material[m_dir]);
			}
		}
		else
		{
			m_player->Died();
		}
	}

	if (m_player->isBoosted() && !m_boost && !m_endFuite)
	{
		Actor::Boost();
		m_actorSpeed = MALUS_SPEED;
		m_indicePathDir = m_pathDir.size();
		m_material[4]->setColor(glm::vec3(FUITE_COLOR[0], FUITE_COLOR[1], FUITE_COLOR[2]));
		for (int i = 0; i < m_maxModel; i++)
		{			
			m_model[i]->setMaterial(m_material[4]);			
		}
	}

	if (m_timerboost > 0)
	{
		m_timerboost -= pc.time->getDeltaTime();
		if (m_timerboost <= 0)
		{
			m_boost = false;
			m_endFuite = false;
			m_actorSpeed = BASE_SPEED;			
		}
	}

	if (!m_player->isDead())
	{
		Actor::update();
	}
	else
	{
		m_move = false;
	}
	m_light->setPosition(glm::lerp(m_worldPos, m_nextWorldPos, m_timerDeplacement / m_actorSpeed) + glm::vec3(0.0f, 7.0f, 0.0f));
}

void Fantome::turn(Direction dir)
{
	m_dir = dir;
	if (!m_boost)
	{
		if (!m_fuite)
		{
			m_material[m_dir]->setColor(m_colorBase);
		}
		else
		{
			m_material[m_dir]->setColor(glm::vec3(DEAD_FANTOME_COLOR[0], DEAD_FANTOME_COLOR[1], DEAD_FANTOME_COLOR[2]));
		}
		for (int i = 0; i < m_maxModel; i++)
		{			
			m_model[i]->setMaterial(m_material[m_dir]);			
		}
	}
}

void Fantome::setPlayer(Player * p)
{
	m_player = p;
}

void Fantome::stop()
{
	pc.lightManager->destroyLight(m_light);
	for (int i = 0; i < m_matcount; i++)
	{
		pc.materialManager->destroyMaterial(m_material[i]);
	}
	free(m_material);
	for (int i = 0; i < m_maxModel; i++)
	{
		pc.modelManager->destroyModel(m_model[i]);
	}
	free(m_model);
	//pc.hud->removeBlockUI(m_pathReader);
	//delete(m_pathReader);
}

