#include "Clyde.h"

Clyde::Clyde(uint8_t ** cc, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec2 indicePos) : Fantome(CLYDE_ORANGE_ID, cc, p, shapeBuffers, pipeline, textureFantome, glm::vec3(CLYDE_COLOR[0], CLYDE_COLOR[1], CLYDE_COLOR[2]), indicePos)
{
	for (int i = 0; i < CARTE_HAUTEUR; i++)
	{
		for (int j = 0; j < CARTE_LARGEUR; j++)
		{
			if (COLIDER_CARTE_BASE[i][j] != WALL_ID && COLIDER_CARTE_BASE[i][j] != TP_ID)
			{
				randomCorrectPos.push_back(glm::vec2(j,i));
			}
		}
	}
	countRandom = rand();
}

void Clyde::fixedUpdate()
{

}

inline int fastrand(unsigned long g_seed) {
	g_seed = (214013 * g_seed + 2531011);
	return (g_seed >> 16) & 0x7FFF;
}

glm::vec2 Clyde::randomPos()
{
	return randomCorrectPos[fastrand(pc.time->GetTime() + countRandom++)% randomCorrectPos.size()];
}

void Clyde::update()
{
	if (!m_boost)
	{
		if (m_indicePathDir == m_pathDir.size() || !m_move)
		{
			pathFinding(randomPos());
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
	}

	Fantome::update();
}

void Clyde::onGUI()
{

}