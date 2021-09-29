#pragma once
#include "Entity.h"
#include "Consomable.h"
#include "GameEngine.hpp"

class PacObject : public Entity, public Consomable
{
public:
	PacObject(unsigned char id, unsigned int consomeScore,ShapeBuffer * buffer,Materials * material,glm::vec2 indicePos, std::string name);
	virtual void consome();
protected:
	ptrClass pc;
	Model * m_model;
};

