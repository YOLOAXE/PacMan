#pragma once
#include "Fantome.h"

class Clyde : public Fantome
{
public:
	Clyde(uint8_t ** cc, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec2 indicePos);
	glm::vec2 randomPos();
	void fixedUpdate();
	void onGUI();
	void update();
private:
	int countRandom = 0;
	std::vector<glm::vec2> randomCorrectPos;
};

