#include "Entity.h"


Entity::Entity(unsigned char id, glm::vec2 indicePos)
{
	m_id = id;
	m_indicePos = indicePos;
}

glm::vec2 Entity::getIndicePos()
{
	return m_indicePos;
}

unsigned char Entity::getID()
{
	return m_id;
}