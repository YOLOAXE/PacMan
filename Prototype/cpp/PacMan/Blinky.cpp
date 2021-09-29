#include "Blinky.h"

Blinky::Blinky(uint8_t ** cc, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec2 indicePos) : Fantome(BLINKY_ROUGE_ID,cc,p,shapeBuffers,pipeline,textureFantome, glm::vec3(BLINKY_COLOR[0], BLINKY_COLOR[1], BLINKY_COLOR[2]),indicePos)
{

}

void Blinky::update()
{
	if (!m_boost)
	{
		if (m_indicePathDir == m_pathDir.size() || !m_move)
		{
			pathFinding(m_player->getIndicePos());
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
	}

	Fantome::update();
}

void Blinky::fixedUpdate()
{

}

void Blinky::onGUI()
{

}