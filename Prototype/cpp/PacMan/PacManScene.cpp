#include "PacManScene.h"

void PacManScene::load()
{
	ptrClass e = GameEngine::getPtrClass();	
#ifndef DEBUG_PACMAN
	e.hud->setHudActive(false);
#endif // DEBUG_PACMAN
	e.behaviourManager->addBehaviour(&pCam);
	e.behaviourManager->addBehaviour(&gameManager);
	map_ShapeBuffer = e.modelManager->allocateBuffer("../data/plateaux.obj");
	map_Texture = e.textureManager->createTexture("../data/base.png");	
	map_Material = e.materialManager->createMaterial();
	map_Material->setAlbedoTexture(map_Texture);
	map_Model = e.modelManager->createModel(map_ShapeBuffer,"Plateaux_Maze");
	map_Model->setMaterial(map_Material);
	map_Model->setScale(glm::vec3(1.0f,0.6f,1.0f));
	map_Model->addComponent(&gameManager);
	scenePointLight = e.lightManager->createPointLight(glm::vec3(0,7,0),glm::vec3(0.186f,0.234f,1.0f),"Global_Point_Light_Scene");
	scenePointLight->setRange(100.0f);
}

void PacManScene::unload()
{
	ptrClass e = GameEngine::getPtrClass();
	e.lightManager->destroyLight(scenePointLight);	
	e.behaviourManager->removeBehaviour(&pCam);
	e.behaviourManager->removeBehaviour(&gameManager);
	e.modelManager->destroyModel(map_Model);
	e.materialManager->destroyMaterial(map_Material);
	e.textureManager->destroyTexture(map_Texture);
	e.modelManager->destroyBuffer(map_ShapeBuffer);
}