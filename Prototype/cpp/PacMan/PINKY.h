#pragma once
#include "Fantome.h"
class Pinky : public Fantome
{
public:
	Pinky(uint8_t ** cc, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec2 indicePos);
	glm::vec2 getPlayerViewPoint();
	void fixedUpdate();
	void update();
	void onGUI();
};

