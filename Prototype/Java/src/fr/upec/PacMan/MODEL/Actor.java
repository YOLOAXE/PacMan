package fr.upec.PacMan.MODEL;
import fr.upec.PacMan.CONTROLEUR;
import fr.upec.PacMan.VUE.Rendering;

/*
 * Nom de classe : Actor
 *
 * Description   : Le conportement des Entites en mouvement
 */

public abstract class Actor extends Entity implements Behaviour,Rendering
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

	@Override
	public void render(Graphics p,JPanel panel)
	{
		
	}
}
