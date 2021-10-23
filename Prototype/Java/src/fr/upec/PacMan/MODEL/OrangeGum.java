package fr.upec.PacMan.MODEL;

import java.awt.Color;

public class OrangeGum extends Consumable{

	OrangeGum(Vector2 pos, CarteCollider carteCollider) {
		super(Constant.GOME_ID, pos, new Color(255, 215, 0), carteCollider);
	}

}