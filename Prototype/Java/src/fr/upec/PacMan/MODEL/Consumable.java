package fr.upec.PacMan.MODEL;
import fr.upec.PacMan.CONTROLEUR.*;
import fr.upec.PacMan.VUE.Rendering;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
 * Nom de classe : Consumable
 *
 * Description   : Les objects qui peuvent etre utiliser par les acteurs exemple player
 */

public class Consumable extends Entity implements Rendering, EffetObject
{
	protected int m_consomeScore;
	protected boolean m_isEaten = false;
	protected CarteCollider m_carteCollider;
	protected float m_baseSize = 1.0f;
	protected float m_timerEffect = 0.0f;

	public Consumable(byte id, Vector2 pos, Color color, CarteCollider carteCollider)
	{
		super(id, pos);
		super.m_color = color;
		this.m_carteCollider = carteCollider;
		this.m_consomeScore = 0;
	}

	public Consumable(byte id, Vector2 pos, Color color, CarteCollider carteCollider, int score)
	{
		super(id, pos);
		super.m_color = color;
		this.m_carteCollider = carteCollider;
		this.m_consomeScore = score;
	}

	@Override
	public void reset()
	{
		this.m_isEaten = false;
	}

	@Override
	public boolean hasEffect()
	{
		return true;
	}

	@Override
	public void initEffect(java.util.List<Actor> users){}

	@Override
	public boolean effet(java.util.List<Actor> users,float deltaTime)
	{		
		if(m_timerEffect > 0)
		{
			m_timerEffect -= deltaTime;
			return true;
		}
		return false;
	}

	@Override
	public void endEffect(java.util.List<Actor> users)
	{
		for(Actor a : users)
		{
			a.action(Constant.STATE_ACTION_NORMAL);
		}
	}

	public int consume()
	{
		this.m_isEaten = true;
		return this.m_consomeScore;
	}

	public boolean isEaten()
	{
		return m_isEaten;
	}

	@Override
	public void render(Graphics p,int width,int height)
	{
		if(!m_isEaten) 
		{
			p.setColor(super.m_color);
			p.fillOval((int)(super.m_pos.x*width+width/4), (int)(super.m_pos.y*height+height/4), (int)((width/2)*m_baseSize), (int)((height/2)*m_baseSize));
		}
	}
}