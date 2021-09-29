#include "GameManager.h"

void GameManager::start()
{
	player = nullptr;
	pc = GameEngine::getPtrClass();
	packGome_ShapeBuffer = pc.modelManager->allocateBuffer("../data/bonus/packGome.obj");
	bigPackGome_ShapeBuffer = pc.modelManager->allocateBuffer("../data/bonus/bigPackGome.obj");

	packGome_Material = pc.materialManager->createMaterial();	
	packGome_Material->setColor(glm::vec3(PACK_GOME_COLOR[0], PACK_GOME_COLOR[1], PACK_GOME_COLOR[2]));
	m_colliderCarte = (uint8_t**)malloc(sizeof(uint8_t*)*CARTE_HAUTEUR);
	m_consomableCarte = (Consomable***)malloc(sizeof(Consomable**)*CARTE_HAUTEUR);
	for (int i = 0; i < CARTE_HAUTEUR; i++)
	{
		m_colliderCarte[i] = (uint8_t*)malloc(sizeof(uint8_t)*CARTE_LARGEUR);
		m_consomableCarte[i] = (Consomable**)malloc(sizeof(Consomable*)*CARTE_LARGEUR);
	}
	m_pacManDataUI = new PacManDataUI();
	//m_debugCarte = new DebugCarte(m_colliderCarte);

	
	m_fantomeShapeBuffers.push_back(pc.modelManager->allocateBuffer("../data/fantome/fantome0.obj"));
	m_fantomeShapeBuffers.push_back(pc.modelManager->allocateBuffer("../data/fantome/fantome1.obj"));
	m_fantomePipeline = pc.graphiquePipelineManager->createPipeline("../Shader/fantome.spv","../Shader/vert.spv");
	for (int i = 0; i < 6; i++)
	{
		std::string value = "../data/fantome/f";
		value += std::to_string(i) + ".png";
		m_fantomeTextures.push_back(pc.textureManager->createTexture(value.c_str()));
	}
	for (int i = 0; i < CARTE_LARGEUR; i++)
	{		
		for (int j = 0; j < CARTE_HAUTEUR; j++)
		{
			m_colliderCarte[j][i] = COLIDER_CARTE_BASE[j][i];
			m_consomableCarte[j][i] = nullptr;
			createEntityByID(i,j);
		}
	}
	pc.hud->addBlockUI(m_pacManDataUI);
	//pc.hud->addBlockUI(m_debugCarte);
}

glm::vec3 GameManager::IndiceToWorldPos(glm::vec2 p)
{
	return glm::vec3(p.x*ESPACEMENT + OFFSET_H, 1.0f, p.y*ESPACEMENT + OFFSET_L);
}

void GameManager::createEntityByID(int i,int j)
{
	switch (m_colliderCarte[j][i])
	{
	case NULL_ID:
		break;
	case WALL_ID:
		break;
	case WALL_SPEC_ID:
		break;
	case TP_ID:
		break;
	case PAC_GOME_ID:
		m_consomableCarte[j][i] = new PacObject(PAC_GOME_ID, PAC_GOME_PT, packGome_ShapeBuffer, packGome_Material, glm::vec2(i, j), "PacGome");
		break;
	case BIG_PAC_GOME_ID:
		m_consomableCarte[j][i] = new BigPacGome(bigPackGome_ShapeBuffer,packGome_Material, glm::vec2(i, j));
		break;
	case PLAYER_ID:
		player = new Player(m_colliderCarte, m_consomableCarte, m_pacManDataUI,glm::vec2(i,j));
		pc.behaviourManager->addBehaviour(player);
		for (Fantome * f : m_fantomes) 
		{
			f->setPlayer(player);
		}
		break;
	case BLINKY_ROUGE_ID:
		m_fantomes.push_back(new Blinky(m_colliderCarte,player,m_fantomeShapeBuffers, m_fantomePipeline,m_fantomeTextures, glm::vec2(i, j)));
		m_blinky = m_fantomes[m_fantomes.size() - 1];
		if (m_inky != nullptr)
		{
			m_inky->setBlinky(m_blinky);
		}
		pc.behaviourManager->addBehaviour(m_fantomes[m_fantomes.size()-1]);
		break;
	case PINKY_ROSE_ID:
		m_fantomes.push_back(new Pinky(m_colliderCarte, player, m_fantomeShapeBuffers, m_fantomePipeline, m_fantomeTextures, glm::vec2(i, j)));
		pc.behaviourManager->addBehaviour(m_fantomes[m_fantomes.size()-1]);
		break;
	case INKY_BLEU_ID:
		m_inky = new Inky(m_colliderCarte, m_blinky, player, m_fantomeShapeBuffers, m_fantomePipeline, m_fantomeTextures, glm::vec2(i, j));
		m_fantomes.push_back(m_inky);
		pc.behaviourManager->addBehaviour(m_inky);
		break;
	case CLYDE_ORANGE_ID:
		m_fantomes.push_back(new Clyde(m_colliderCarte, player, m_fantomeShapeBuffers, m_fantomePipeline, m_fantomeTextures, glm::vec2(i, j)));
		pc.behaviourManager->addBehaviour(m_fantomes[m_fantomes.size()-1]);
		break;
	default:
		Debug::Warn("id not found");
	}
}

void GameManager::fixedUpdate()
{

}

void GameManager::update()
{

}

void GameManager::stop()
{
	for (int i = 0; i < m_fantomeShapeBuffers.size(); i++)
	{
		pc.modelManager->destroyBuffer(m_fantomeShapeBuffers[i]);
	}
	for (int i = 0; i < m_fantomeTextures.size(); i++)
	{
		pc.textureManager->destroyTexture(m_fantomeTextures[i]);
	}
	pc.behaviourManager->removeBehaviour(player);
	for (int i = 0; i < CARTE_LARGEUR; i++)
	{
		for (int j = 0; j < CARTE_HAUTEUR; j++)
		{
			m_consomableCarte[j][i]->consome();
		}
	}
	for (int i = 0; i < CARTE_HAUTEUR; i++)
	{
		free(m_consomableCarte[i]);
		free(m_colliderCarte[i]);
	}
	pc.graphiquePipelineManager->destroyPipeline(m_fantomePipeline);
	free(m_colliderCarte);
	free(m_consomableCarte);
	pc.hud->removeBlockUI(m_pacManDataUI);
}


void GameManager::onGUI()
{
	
}