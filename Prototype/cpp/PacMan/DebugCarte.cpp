#include "DebugCarte.h"

DebugCarte::DebugCarte(uint8_t ** cc)
{
	m_collisionCarte = cc;
}

void DebugCarte::preRender(VulkanMisc* vM){}

void DebugCarte::render(VulkanMisc* vM)
{
	ImGuiIO& io = ImGui::GetIO();
	ImGuiWindowFlags window_flags = ImGuiWindowFlags_NoDecoration | ImGuiWindowFlags_AlwaysAutoResize | ImGuiWindowFlags_NoSavedSettings | ImGuiWindowFlags_NoFocusOnAppearing | ImGuiWindowFlags_NoNav;

	window_flags |= ImGuiWindowFlags_NoMove;
	ImGui::SetNextWindowPos(ImVec2(0, 300), ImGuiCond_Always);

	ImGui::SetNextWindowBgAlpha(0.0f);
	if (ImGui::Begin("PacMan Debug", &m_open, window_flags))
	{
		for (int i = 0; i < CARTE_HAUTEUR; i++)
		{
			std::string value = "";
			for (int j = 0; j < CARTE_LARGEUR; j++)
			{
				value += std::to_string(m_collisionCarte[i][j])+" ";
			}
			ImGui::Text(value.c_str());
		}
	}
	ImGui::End();
}