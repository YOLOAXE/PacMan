#pragma once

#include  "ImguiBlock.hpp"
#include "GameEngine.hpp"
#include "def.h"
using namespace Ge;

#include "node.h"
#include <vector>

class PathReader : public ImguiBlock
{
public:
	PathReader(std::vector<std::vector<node>> * pathCarte);
	void render(VulkanMisc* vM);
	void preRender(VulkanMisc* vM);
private:
	bool m_open = true;
	std::vector<std::vector<node>> * m_pathCarte;
};

