#include <iostream>
#include "GameEngine.hpp"
#include "PacManScene.h"
using namespace Ge;

int main()
{
	Debug::Info("Moteur Graphique");
	GameEngine engine;
	ptrClass e = engine.getPtrClass();
	PacManScene pms;

	e.settingManager->setName("Pac-Man");
	e.settingManager->setIconPath("../data/icon/pacmanIcon.png");
	e.sceneManager->addScene("Scene0",&pms);
	e.settingManager->setClearColor(glm::vec4(0.0f,0.0f,0.0f,1.0f));

	if (!engine.initialize())
	{
		Debug::Error("Erreur d'intialisation du moteur graphique");
		return -1;
	}
	
	try
	{
		engine.start();
	}
	catch (std::runtime_error& e)
	{
		Debug::Error("Exception : %s", e.what());
		return -1;
	}
	catch (std::bad_alloc& e)
	{
		Debug::Error("Exception : %s", e.what());
		return -1;
	}
	catch (const std::exception& e)
	{
		Debug::Error("Exception : %s", e.what());
		return -1;
	}

	engine.release();
	std::cin.ignore();
	return 0;
}
