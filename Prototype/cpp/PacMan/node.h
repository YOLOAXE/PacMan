#pragma once
#include <vector>
#include "def.h"

struct node
{
	float f_cost;
	float h_cost;
	float g_cost;
	glm::vec2 pos;
	bool mur;
	node * parent;
	Direction parentDir;
	std::vector<node *> neighbour;	
	std::vector<Direction> neighbourDir;

	void calculeCost(node * start,float gp_cost, node * target)
	{
		float g = glm::distance(start->pos, pos)+ gp_cost;
		float h = glm::distance(target->pos, pos);
		if ((g + h) < f_cost)
		{
			g_cost = g;
			h_cost = h;
			f_cost = g_cost + h_cost;
			parent = start;
			for (int i = 0; i < neighbour.size();i++)
			{
				if (neighbour[i] == parent)
				{
					parentDir = neighbourDir[i];
					return;
				}
			}
		}
	}
};

