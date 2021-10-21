package fr.upec.PacMan.MODEL;
import java.awt.*;

/*
 * Nom de classe : Ghost
 *
 * Description   : Deplacement aleatoire des fantomes avec du path finding car il retourne a leur point d'aparition
 */

public class Ghost extends Actor
{
	public Ghost(Vector2 pos,Color c,CarteCollider carteCollider)
	{
		super(Constant.GHOST_ID,pos,c,carteCollider);	
	}

    @Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
	}
}
