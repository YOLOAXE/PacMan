#include "PacObject.h"
#include "GameManager.h"

PacObject::PacObject(unsigned char id, unsigned int consomeScore, ShapeBuffer * buffer, Materials * material, glm::vec2 indicePos, std::string name) : Entity(id, indicePos), Consomable(consomeScore)
{
	pc = GameEngine::getPtrClass();
	m_model = pc.modelManager->createModel(buffer, name);
	m_model->setMaterial(material);
	m_model->setPosition(GameManager::IndiceToWorldPos(indicePos));
}

void PacObject::consome()
{
	pc.modelManager->destroyModel(m_model);
	delete(this);
}