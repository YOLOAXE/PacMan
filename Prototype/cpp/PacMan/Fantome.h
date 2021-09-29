#pragma once

#include <iostream>
#include <sstream>
#include "Actor.h"
#include "PacManDataUI.h"
#include "Player.h"
#include <vector>
#include "node.h"
#include <limits>
#include <algorithm>
#include "PathReader.h"

class Fantome : public Actor
{
public:
	Fantome(uint8_t id, uint8_t ** cc, Player * p, std::vector<ShapeBuffer *> shapeBuffers, GraphiquePipeline * pipeline, std::vector<Textures *> textureFantome, glm::vec3 color_base, glm::vec2 indicePos);
	virtual void update();
	void start();
	void stop();
	void pathFinding(glm::vec2 targetPos);	
	void turn(Direction dir);
	void setPlayer(Player * p);
private:
	Direction recursiveAddPath(node * n);
protected:
	Player * m_player;
	Lights * m_light;
	std::vector<std::vector<node>> m_pathCarte;
	float timer = 0;	
	float animationSpeed = 0.1f;
	float base_scale = 17.0f;
	glm::vec3 m_colorBase;
	int m_matcount = 0;
	std::vector<Direction> m_pathDir;
	int m_indicePathDir = 0;
	bool m_fuite = false;
	bool m_endFuite = false;
	glm::vec2 m_spawnPoint;
	PathReader * m_pathReader;
};

