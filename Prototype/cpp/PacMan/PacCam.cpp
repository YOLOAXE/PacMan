#include "PacCam.h"

void PacCam::start()
{
	pc = GameEngine::getPtrClass();
	flyCam = pc.cameraManager->getCurrentCamera();
	flyCam->setPosition(glm::vec3(0.0f, -105.0f, -25.0f));
	flyCam->setEulerAngles(glm::vec3(79.0f, 0.0f, 0.0f));
	cam = pc.cameraManager->createCamera("PacCam");
	cam->addComponent(this);
	cam->setPosition(glm::vec3(0.0f ,-105.0f, -25.0f));
	cam->setEulerAngles(glm::vec3(79.0f, 0.0f, 0.0f));
	cam->setFieldOfView(60.0f);

}

void PacCam::fixedUpdate()
{

}

void PacCam::update()
{
#ifdef DEBUG_PACMAN
	if (pc.inputManager->getKeyDown(GLFW_KEY_P))
	{
		state = !state;
		cam->setPriority(state ? 2.0f : -1.0f);		
	}
#endif
}

void PacCam::stop()
{
	pc.cameraManager->releaseCamera(cam);
}

void PacCam::onGUI()
{

}