package fr.upec.PacMan.MODEL;

import java.awt.Color;

public class VioletGum extends Consumable{

	VioletGum(Vector2 pos, CarteCollider carteCollider) {
		super(Constant.GOME_ID, pos, new Color(255, 0, 127), carteCollider);
	}

}