#include "PathReader.h"

PathReader::PathReader(std::vector<std::vector<node>> * pathCarte)
{
	m_pathCarte = pathCarte;
}

void PathReader::render(VulkanMisc* vM)
{
	ImGuiIO& io = ImGui::GetIO();
	ImGuiWindowFlags window_flags = ImGuiWindowFlags_NoDecoration | ImGuiWindowFlags_AlwaysAutoResize | ImGuiWindowFlags_NoSavedSettings | ImGuiWindowFlags_NoFocusOnAppearing | ImGuiWindowFlags_NoNav;

	window_flags |= ImGuiWindowFlags_NoMove;
	ImGui::SetNextWindowPos(ImVec2(0, 300), ImGuiCond_Always);

	ImGui::SetNextWindowBgAlpha(0.0f);
	if (ImGui::Begin("PacMan Debug Fantome", &m_open, window_flags))
	{
		for (int i = 0; i < CARTE_HAUTEUR; i++)
		{
			std::string value = "";
			for (int j = 0; j < CARTE_LARGEUR; j++)
			{
				value += m_pathCarte[0][i][j].mur ? "m " : _Floating_to_string("%.0f",m_pathCarte[0][i][j].f_cost > 10000.0f ? 0 : m_pathCarte[0][i][j].f_cost) + " ";
				//value += (m_pathCarte[0][i][j].neighbourDir.size() > 0 ? std::to_string(m_pathCarte[0][i][j].neighbourDir[0]) : " ") + " ";
			}
			ImGui::Text(value.c_str());
		}
	}
	ImGui::End();
}

void PathReader::preRender(VulkanMisc* vM)
{

}