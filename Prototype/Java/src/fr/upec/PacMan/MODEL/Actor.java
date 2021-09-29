package fr.upec.PacMan.MODEL;
import fr.upec.PacMan.CONTROLEUR;

/*
 * Nom de classe : Actor
 *
 * Description   : Le conportement des Entites en mouvement
 */

public abstract class Actor extends Entity implements Behaviour
{
	Actor(byte id,Vector2 pos)
	{
		super(id,pos);
	}

	Vector2 move(Direction dir)
	{
		return new Vector2();
	}

	@Override
	void update(float deltaTime)
	{
	
	}
}
