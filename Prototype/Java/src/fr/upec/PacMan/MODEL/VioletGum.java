package fr.upec.PacMan.MODEL;

import java.awt.Color;
import java.util.*;

public class VioletGum extends Consumable
{
	public VioletGum(Vector2 pos, CarteCollider carteCollider) 
	{
		super(Constant.GOME_ID, pos, Constant.PAC_GOME_COLOR[2], carteCollider,Constant.PAC_GOME_SCORE[2]);
		super.m_baseSize = 1.4f;
	}

	@Override
	public void initEffect(java.util.List<Actor> users)
	{
		super.m_timerEffect = 7.0f;
		for(Actor a : users)
		{
			a.action(Constant.STATE_ACTION_INVISIBLE);
		}
	}
}