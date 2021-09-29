#ifndef __GAME_MANAGER__
#define __GAME_MANAGER__

#include "GameEngine.hpp"
#include "imgui-cmake/Header/imconfig.h"
#include "def.h"
#include "PacObject.h"
#include <map>
#include "BigPacGome.h"
#include "Player.h"
#include "PacManDataUI.h"
#include "DebugCarte.h"
#include "Blinky.h"
#include "Pinky.h"
#include "Inky.h"
#include "Clyde.h"

class GameManager : public Behaviour
{
public:
	void start();
	void fixedUpdate();
	void update();
	void stop();
	void onGUI();
	void createEntityByID(int i, int j);
	static glm::vec3 IndiceToWorldPos(glm::vec2 p);
private:
	ptrClass pc;
	unsigned int m_levels;
	unsigned char m_playerLife;
	ShapeBuffer * packGome_ShapeBuffer;
	ShapeBuffer * bigPackGome_ShapeBuffer;
	Materials * packGome_Material;	
	Player * player;
	std::vector<Fantome *> m_fantomes;
	std::vector<Textures *> m_fantomeTextures;
	std::vector<ShapeBuffer *> m_fantomeShapeBuffers;
	GraphiquePipeline * m_fantomePipeline;
	uint8_t ** m_colliderCarte;
	Consomable *** m_consomableCarte;
	PacManDataUI * m_pacManDataUI;
	DebugCarte * m_debugCarte;
	Fantome * m_blinky;
	Inky * m_inky;
};

#endif //__GAME_MANAGER__

