#include "Inky.h"

Inky::Inky(uint8_t ** cc, Fantome * b, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec2 indicePos) : Fantome(INKY_BLEU_ID, cc, p, shapeBuffers, pipeline, textureFantome, glm::vec3(INKY_COLOR[0], INKY_COLOR[1], INKY_COLOR[2]), indicePos)
{
	m_blinky = b;
}

void Inky::setBlinky(Fantome * b)
{
	m_blinky = b;
}

void Inky::update()
{
	if (!m_boost)
	{
		if (m_indicePathDir == m_pathDir.size() || !m_move)
		{
			glm::vec2 pos = m_blinky->getIndicePos();
			glm::vec2 playerPos = m_player->getIndicePos();
			glm::vec2 finalPos;
			finalPos.x = (int)(pos.x > playerPos.x ? (pos.x - playerPos.x) + pos.x : (playerPos.x - pos.x) + playerPos.x) % CARTE_LARGEUR;
			finalPos.y = (int)(pos.y > playerPos.y ? (pos.y - playerPos.y) + pos.y : (playerPos.y - pos.y) + playerPos.y) % CARTE_HAUTEUR;

			if (finalPos.x <= 0)
			{
				finalPos.x = 1;
			}
			if (finalPos.y <= 0)
			{
				finalPos.y = 1;
			}
			if (COLIDER_CARTE_BASE[(int)finalPos.y][(int)finalPos.x] != WALL_ID)
			{
				pathFinding(finalPos);
			}
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}
	}
	Fantome::update();
}

void Inky::fixedUpdate()
{

}

void Inky::onGUI()
{

}