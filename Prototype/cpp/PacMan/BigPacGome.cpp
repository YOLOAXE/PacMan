#include "BigPacGome.h"
#include "GameManager.h"
#include "Player.h"

BigPacGome::BigPacGome(ShapeBuffer * buffer, Materials * material, glm::vec2 indicePos) : PacObject(BIG_PAC_GOME_ID,BIG_PAC_GOME_PT,buffer,material, indicePos,"BigPacGome")
{	
	m_light = pc.lightManager->createPointLight(GameManager::IndiceToWorldPos(indicePos)+glm::vec3(0.0f,7.0f,0.0f), material->getColor(), "BigPacGomeLight");
	m_light->setRange(6.0f);
}

void BigPacGome::boostPlayer(Player * p)
{
	p->Boost();
}

void BigPacGome::consome()
{
	boostPlayer(Player::GetInstance());
	pc.lightManager->destroyLight(m_light);
	pc.modelManager->destroyModel(m_model);
	delete(this);
}