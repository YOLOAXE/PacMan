#include "Player.h"
#include "GameManager.h"

Player * Player::Instance = nullptr;

Player::Player(uint8_t ** cc, Consomable *** consomable, PacManDataUI * pacManDataUI, glm::vec2 indicePos) : Actor(PLAYER_ID, cc, indicePos)
{
	m_consomableCarte = consomable;
	glm::vec3 pos = GameManager::IndiceToWorldPos(indicePos);
	m_maxModel = 3;
	Instance = this;
	m_shapeBuffer = (ShapeBuffer**)malloc(sizeof(ShapeBuffer*)*m_maxModel);
	m_model = (Model**)malloc(sizeof(Model*) * m_maxModel);
	m_material = (Materials**)malloc(sizeof(Materials*));
	m_material[0] = pc.materialManager->createMaterial();
	m_material[0]->setColor(glm::vec3(PACK_MAN_COLOR[0], PACK_MAN_COLOR[1], PACK_MAN_COLOR[2]));
	for (int i = 0; i < m_maxModel; i++)
	{
		std::stringstream stream;
		stream << "../data/pacman/p" << i << ".obj";
		m_shapeBuffer[i] = pc.modelManager->allocateBuffer(stream.str().c_str());
		m_model[i] = pc.modelManager->createModel(m_shapeBuffer[i], "PlayerA");
		m_model[i]->addComponent(this);
		m_model[i]->setPosition(pos);
		m_model[i]->setMaterial(m_material[0]);
	}
	m_model[1]->setScale(glm::vec3(base_scale));
	m_model[2]->setScale(glm::vec3(0.0f));
	m_model[0]->setScale(glm::vec3(0.0f));
	currentAnimatedFrame++;
	m_offsetAngles = 90.0f;
	m_idWallCollide.push_back(WALL_SPEC_ID);
	m_pacManDataUI = pacManDataUI;
}

Player * Player::GetInstance()
{
	return Player::Instance;
}

void Player::start()
{
	m_nextPos = m_indicePos;
	m_worldPos = GameManager::IndiceToWorldPos(m_indicePos);
	m_nextWorldPos = GameManager::IndiceToWorldPos(m_nextPos);
}

void Player::fixedUpdate()
{

}

void Player::Died()
{
	m_dead = true;
	m_move = false;
}

bool Player::isDead()
{
	return m_dead;
}


void Player::update()
{
	if (m_move)
	{
		timer += pc.time->getDeltaTime();
	}
	if (timer > animationSpeed)
	{
		timer = 0.0f;
		m_model[anim[currentAnimatedFrame]]->setScale(glm::vec3(0.0f));
		currentAnimatedFrame = ++currentAnimatedFrame % 4;
		m_model[anim[currentAnimatedFrame]]->setScale(glm::vec3(base_scale));
	}	

	if(m_timerDeplacement > m_actorSpeed/2.0f && !m_nextState && m_move)
	{
		m_nextState = true;
		int x = m_nextPos.x;
		int y = m_nextPos.y;
		if (m_consomableCarte[y][x] != nullptr)
		{
			m_pacManDataUI->addScore(m_consomableCarte[y][x]->getConsomeScore());
			m_consomableCarte[y][x]->consome();
			m_consomableCarte[y][x] = nullptr;
		}
		m_colliderCarte[(int)m_indicePos.y][(int)m_indicePos.x] = NULL_ID;
		if (m_colliderCarte[y][x] == TP_ID)
		{
			int tp = (TP_POINT[0][0] == x && TP_POINT[0][1] == y) ? 1 : 0;
			int tpx = TP_POINT[tp][0] + DTOP[m_dir][0];
			int tpy = TP_POINT[tp][1] + DTOP[m_dir][1];
			m_colliderCarte[tpx][tpy] = PLAYER_ID;
			m_indicePos.x = tpx;
			m_indicePos.y = tpy;
			m_nextPos = m_indicePos;
		}
		else
		{
			m_colliderCarte[y][x] = PLAYER_ID;
		}
	}	

	if (m_timerboost > 0)
	{
		m_timerboost -= pc.time->getDeltaTime();
		if (m_timerboost <= 0)
		{
			m_boost = false;
		}
	}

	if (!m_dead)
	{
		Actor::update();
		if (pc.inputManager->getKeyDown(GLFW_KEY_W) || pc.inputManager->getKeyDown(GLFW_KEY_UP))
		{
			m_nextDir = Direction::North;
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
		if (pc.inputManager->getKeyDown(GLFW_KEY_S) || pc.inputManager->getKeyDown(GLFW_KEY_DOWN))
		{
			m_nextDir = Direction::South;
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
		if (pc.inputManager->getKeyDown(GLFW_KEY_A) || pc.inputManager->getKeyDown(GLFW_KEY_LEFT))
		{
			m_nextDir = Direction::West;
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
		if (pc.inputManager->getKeyDown(GLFW_KEY_D) || pc.inputManager->getKeyDown(GLFW_KEY_RIGHT))
		{
			m_nextDir = Direction::East;
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
	}
	else
	{
		if (timerDeathAnimation > 1.5f)
		{
			//Restart
		}
		else
		{
			timerDeathAnimation += pc.time->getDeltaTime();
		}
		m_model[anim[currentAnimatedFrame]]->setScale(glm::vec3((1-(timerDeathAnimation/1.5f))*base_scale));
	}
}

void Player::stop()
{
	pc.materialManager->destroyMaterial(m_material[0]);
	free(m_material);
	for (int i = 0; i < m_maxModel; i++)
	{
		pc.modelManager->destroyModel(m_model[i]);
		pc.modelManager->destroyBuffer(m_shapeBuffer[i]);
	}
	free(m_shapeBuffer);
	free(m_model);	
}

void Player::onGUI()
{

}