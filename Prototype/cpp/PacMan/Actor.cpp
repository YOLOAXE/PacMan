#include "Actor.h"
#include "GameManager.h"

Actor::Actor(unsigned char id,uint8_t ** cc,glm::vec2 indicePos) : Entity(id, indicePos)
{
	pc = GameEngine::getPtrClass();
	m_colliderCarte = cc;
	m_idWallCollide.push_back(WALL_ID);
}

glm::vec2 Actor::move(Direction dir)
{
	m_timerDeplacement = 0.0f;
	int x = m_indicePos.x + DTOP[dir][0];
	int y = m_indicePos.y + DTOP[dir][1];
	bool collide = false;
	for (int i = 0; i < m_idWallCollide.size() && !collide; i++)
	{
		collide = m_colliderCarte[y][x] == m_idWallCollide[i];
	}
	if (collide)
	{
		m_move = false;
		return m_indicePos;
	}
	else
	{
		turn(dir);
		m_move = true; 		
		return glm::vec2(x, y);
	}	
}

Direction Actor::getDirection()
{
	return m_dir;
}

void Actor::Boost()
{
	m_boost = true;
	m_timerboost = FANTOME_TEMPS_FUITE;
}

bool Actor::isBoosted()
{
	return m_boost;
}

void Actor::update()
{
	m_timerDeplacement += pc.time->getDeltaTime();

	if (m_timerDeplacement > m_actorSpeed)
	{
		m_indicePos = m_nextPos;
		m_nextPos = move(m_nextDir);
		m_nextState = false;
		m_worldPos = GameManager::IndiceToWorldPos(m_indicePos);
		m_nextWorldPos = GameManager::IndiceToWorldPos(m_nextPos);
	}

	for (int i = 0; i < m_maxModel; i++)
	{
		m_model[i]->setPosition(glm::lerp(m_worldPos, m_nextWorldPos, m_timerDeplacement / m_actorSpeed));
	}
}

void Actor::turn(Direction dir)
{
	m_dir = dir;
	for (int i = 0; i < m_maxModel; i++)
	{
		m_model[i]->setEulerAngles(glm::vec3(0,ANGLES[dir]+m_offsetAngles,0));
	}
}