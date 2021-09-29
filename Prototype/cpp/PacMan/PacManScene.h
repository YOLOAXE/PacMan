#ifndef __PAC_MAN_SCENE_0_
#define __PAC_MAN_SCENE_0_

#include "def.h"
#include "Scene.hpp"
#include "GameEngine.hpp"
#include "GameManager.h"
#include "PacCam.h"

class PacManScene : public Scene
{
public:
	void load();
	void unload();
private:
	PacCam pCam;
	GameManager gameManager;
	ShapeBuffer * map_ShapeBuffer;
	Model * map_Model;
	Materials * map_Material;
	Textures * map_Texture;
	Lights * scenePointLight;
};

#endif //__PAC_MAN_SCENE_0_

