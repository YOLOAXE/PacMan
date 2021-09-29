#ifndef __PAC_CAM__
#define __PAC_CAM__

#include "def.h"
#include "GameEngine.hpp"

class PacCam : public Behaviour
{
public:
	void start();
	void fixedUpdate();
	void update();
	void stop();
	void onGUI();
private:
	Camera * cam;
	Camera * flyCam;
	bool state = false;
	ptrClass pc;
};

#endif

