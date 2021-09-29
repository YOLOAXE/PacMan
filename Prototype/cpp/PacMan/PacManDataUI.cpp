#include "PacManDataUI.h"

void PacManDataUI::preRender(VulkanMisc* vM)
{
	m_fontBase = GameEngine::getPtrClass().hud->createFont("../data/font/Joystix.ttf", 26.0f);
}

void PacManDataUI::render(VulkanMisc* vM)
{
	ImGuiIO& io = ImGui::GetIO();
	ImGuiWindowFlags window_flags = ImGuiWindowFlags_NoDecoration | ImGuiWindowFlags_AlwaysAutoResize | ImGuiWindowFlags_NoSavedSettings | ImGuiWindowFlags_NoFocusOnAppearing | ImGuiWindowFlags_NoNav;

	window_flags |= ImGuiWindowFlags_NoMove;
	ImGui::SetNextWindowPos(ImVec2(vM->str_VulkanSwapChainMisc->str_swapChainExtent.width - 250.0f, 0), ImGuiCond_Always);

	ImGui::SetNextWindowBgAlpha(0.0f);
	ImGui::PushFont(m_fontBase);	
	if (ImGui::Begin("PacMan HS", &m_open, window_flags))
	{
		ImGui::TextColored(ImVec4(1.0f, 0.2f, 0.2f, 1.0f), "HIGH SCORE");
		ImGui::TextColored(ImVec4(1.0f, 1.0f, 1.0f, 1.0f), "%d", m_highScore);
	}
	ImGui::End();
	ImGui::SetNextWindowPos(ImVec2(50.0f, 0), ImGuiCond_Always);
	if (ImGui::Begin("PacMan S", &m_open, window_flags))
	{
		ImGui::TextColored(ImVec4(1.0f, 1.0f, 1.0f, 1.0f), "1UP", m_score);
		ImGui::TextColored(ImVec4(1.0f, 1.0f, 1.0f, 1.0f), "%d", m_score);
	}	
	ImGui::End();
	ImGui::PopFont();
}

void PacManDataUI::addScore(unsigned long score)
{
	m_score += score;
}

void PacManDataUI::setHighScore(unsigned long score)
{
	m_highScore = score;
}

void PacManDataUI::setScore(unsigned long score)
{
	m_score = score;
}

unsigned long PacManDataUI::getHighScore()
{
	return m_highScore;
}

unsigned long PacManDataUI::getScore()
{
	return m_score;
}