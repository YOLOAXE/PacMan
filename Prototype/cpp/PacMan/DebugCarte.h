#pragma once
#include  "ImguiBlock.hpp"
#include "GameEngine.hpp"
#include "def.h"
using namespace Ge;

class DebugCarte : public ImguiBlock
{
public:
	DebugCarte(uint8_t ** cc);
	void render(VulkanMisc* vM);
	void preRender(VulkanMisc* vM);
private:
	bool m_open = true;
	uint8_t ** m_collisionCarte;
};

