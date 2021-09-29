#ifndef __ENTITY__
#define __ENTITY__

#include "GameEngine.hpp"
#include "def.h"

class Entity
{
public:
	Entity(unsigned char id, glm::vec2 indicePos);
	unsigned char getID();
	glm::vec2 getIndicePos();
protected:
	unsigned char m_id;
	glm::vec2 m_indicePos;
};

#endif//__ENTITY__
