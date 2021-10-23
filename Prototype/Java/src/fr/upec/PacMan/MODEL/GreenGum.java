package fr.upec.PacMan.MODEL;

import java.awt.Color;

public class GreenGum extends Consumable{

	GreenGum(Vector2 pos, CarteCollider carteCollider) {
		super(Constant.GOME_ID, pos, new Color(0, 255, 0), carteCollider);
	}

}