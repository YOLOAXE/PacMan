#pragma once
#include "PacObject.h"
#include "BoostObject.h"

class BigPacGome : public PacObject, public BoostObject
{
public:
	BigPacGome(ShapeBuffer * buffer, Materials * material, glm::vec2 indicePos);
	void boostPlayer(Player * p);
	void consome();
private:
	Lights * m_light;
};

