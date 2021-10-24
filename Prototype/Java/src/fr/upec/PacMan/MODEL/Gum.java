package fr.upec.PacMan.MODEL;

import java.awt.Color;

public class Gum extends Consumable
{
	public Gum(Vector2 pos, CarteCollider carteCollider) 
	{
		super(Constant.GOME_ID, pos, Constant.PAC_GOME_COLOR[0], carteCollider,Constant.PAC_GOME_SCORE[0]);
	}

	@Override
	public boolean hasEffect()
	{
		return false;
	}
}
