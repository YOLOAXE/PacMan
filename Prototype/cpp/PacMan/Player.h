#ifndef __PLAYER_PACMAN__
#define __PLAYER_PACMAN__

#include <iostream>
#include <sstream>
#include "Actor.h"
#include "PacManDataUI.h"

class Player : public Actor
{
public:
	Player(uint8_t ** cc, Consomable *** consomable, PacManDataUI * pacManDataUI, glm::vec2 indicePos);
	void start();
	void fixedUpdate();
	void update();
	void stop();
	void onGUI();
	void Died();
	bool isDead();
	static Player * GetInstance();
private:
	static Player * Instance;
	Consomable *** m_consomableCarte;
	ShapeBuffer ** m_shapeBuffer;
	PacManDataUI * m_pacManDataUI;
	bool m_dead = false;
	float timer = 0;
	float timerDeathAnimation = 0;
	float animationSpeed = 0.1f;
	float base_scale = 1.4f;
	int anim[4] = { 0,1,2,1 };
};

#endif //__PLAYER_PACMAN__

