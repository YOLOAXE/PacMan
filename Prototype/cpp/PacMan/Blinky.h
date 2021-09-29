#pragma once
#include "Fantome.h"

class Blinky : public Fantome
{
public:
	Blinky(uint8_t ** cc, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec2 indicePos);
	void fixedUpdate();
	void onGUI();
	void update();
};

