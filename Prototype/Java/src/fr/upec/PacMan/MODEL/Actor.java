package fr.upec.PacMan.MODEL;
import fr.upec.PacMan.CONTROLEUR.*;
import fr.upec.PacMan.VUE.Rendering;
import javax.swing.*;
import java.awt.*;

/*
 * Nom de classe : Actor
 *
 * Description   : Le conportement des Entites en mouvement
 */

public abstract class Actor extends Entity implements Behaviour,Rendering
{
	Actor(byte id,Vector2 pos,Color c)
	{
		super(id,pos);
		super.m_color = c;
	}

	Vector2 move(Direction dir)
	{
		return new Vector2();
	}

	@Override
	public void update(float deltaTime)
	{
	
	}

	@Override
	public void render(Graphics p,int width,int height)
	{
		p.setColor(super.m_color);
		p.fillOval((int)(super.m_pos.x*width), (int)(super.m_pos.y*height), width, height);
	}
}
