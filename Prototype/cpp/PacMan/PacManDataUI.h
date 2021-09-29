#pragma once
#include  "ImguiBlock.hpp"
#include "GameEngine.hpp"
using namespace Ge;

class PacManDataUI : public ImguiBlock
{
public:	
	void render(VulkanMisc* vM);
	void preRender(VulkanMisc* vM);
	void setHighScore(unsigned long score);
	void setScore(unsigned long score);
	unsigned long getHighScore();
	unsigned long getScore();
	void addScore(unsigned long score);
private:
	ImFont* m_fontBase;
	bool m_open = true;
	unsigned long m_highScore = 0;
	unsigned long m_score = 0;
};


