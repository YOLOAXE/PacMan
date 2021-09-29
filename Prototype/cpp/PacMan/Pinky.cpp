#include "Pinky.h"

Pinky::Pinky(uint8_t ** cc, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec2 indicePos) : Fantome(PINKY_ROSE_ID, cc, p, shapeBuffers, pipeline, textureFantome, glm::vec3(PINKY_COLOR[0], PINKY_COLOR[1], PINKY_COLOR[2]), indicePos)
{}

void Pinky::fixedUpdate()
{

}

glm::vec2 Pinky::getPlayerViewPoint()
{
	glm::vec2 pp = m_player->getIndicePos();
	Direction pd = m_player->getDirection();
	uint8_t result = 0;
	
	int x = 0;
	int y = 0;
	while (true)
	{
		x = pp.x + DTOP[pd][0];
		y = pp.y + DTOP[pd][1];
		result = COLIDER_CARTE_BASE[y][x];		
		if (result != WALL_ID && result != TP_ID)
		{
			pp.x += DTOP[pd][0];
			pp.y += DTOP[pd][1];
		}
		else
		{
			return pp;
		}
	}
	return pp;
}

void Pinky::update()
{
	if (!m_boost)
	{
		if (m_indicePathDir == m_pathDir.size() || !m_move)
		{
			pathFinding(getPlayerViewPoint());
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
	}
	Fantome::update();
}

void Pinky::onGUI()
{

}