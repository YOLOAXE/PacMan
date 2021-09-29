#pragma once
#include "Fantome.h"
#include "Blinky.h"

class Inky : public Fantome
{
public:
	Inky(uint8_t ** cc, Fantome * b, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec2 indicePos);
	void fixedUpdate();
	void onGUI();
	void update();
	void setBlinky(Fantome * b);
private:
	Fantome * m_blinky;
};

