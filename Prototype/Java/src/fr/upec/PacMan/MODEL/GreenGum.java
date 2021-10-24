package fr.upec.PacMan.MODEL;

import java.awt.Color;
import java.util.*;

public class GreenGum extends Consumable
{
	public GreenGum(Vector2 pos, CarteCollider carteCollider) 
	{
		super(Constant.GOME_ID, pos, Constant.PAC_GOME_COLOR[1], carteCollider,Constant.PAC_GOME_SCORE[1]);
		super.m_baseSize = 1.4f;
	}

	@Override
	public void initEffect(java.util.List<Actor> users)
	{
		for(Actor a : users)
		{
			a.action(Constant.STATE_ACTION_LABYRINTHE);
		}
	}
}