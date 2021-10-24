package fr.upec.PacMan.MODEL;

import java.awt.Color;
import java.util.*;

public class OrangeGum extends Consumable
{
	public OrangeGum(Vector2 pos, CarteCollider carteCollider) 
	{
		super(Constant.GOME_ID, pos, Constant.PAC_GOME_COLOR[3], carteCollider,Constant.PAC_GOME_SCORE[3]);
		super.m_baseSize = 1.4f;
	}

	@Override
	public void initEffect(java.util.List<Actor> users)
	{
		super.m_timerEffect = 7.0f;
		for(Actor a : users)
		{
			a.action(Constant.STATE_ACTION_SUPER);
		}
	}
}