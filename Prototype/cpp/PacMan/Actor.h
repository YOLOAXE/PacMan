#ifndef __ACTOR__
#define __ACTOR__

#include "Entity.h"
#include "Consomable.h"

class Actor : public Entity , public Behaviour
{
public:
	Actor(unsigned char id,uint8_t ** cc, glm::vec2 indicePos);
	virtual void turn(Direction dir);
	virtual glm::vec2 move(Direction dir);
	virtual void update();
	virtual void Boost();
	virtual bool isBoosted();
	Direction getDirection();
protected:
	ptrClass pc;
	float m_actorSpeed = BASE_SPEED;
	Direction m_dir = Direction::North;
	Direction m_nextDir = Direction::North;
	glm::vec2 m_nextPos;
	glm::vec3 m_worldPos;
	glm::vec3 m_nextWorldPos;
	bool m_move = false;
	bool m_nextState = false;
	float m_timerboost = 0;
	bool m_boost = false;
	Model ** m_model;
	Materials ** m_material;
	int m_maxModel = 0;
	float m_offsetAngles = 0.0f;
	unsigned char currentAnimatedFrame = 0;	
	uint8_t ** m_colliderCarte;
	std::vector<uint8_t> m_idWallCollide;
	float m_timerDeplacement = 0;
};

#endif//__ACTOR__
