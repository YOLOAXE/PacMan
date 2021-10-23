package fr.upec.PacMan.MODEL;

import fr.upec.PacMan.VUE.Rendering;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
 * Nom de classe : Consumable
 *
 * Description   : Les objects qui peuvent etre utiliser par les acteurs exemple player
 */

public class Consumable extends Entity implements Rendering
{
	protected int m_consomeScore;
	protected Vector2 m_currentPos = new Vector2();
	protected CarteCollider m_carteCollider;

	Consumable(byte id, Vector2 pos, Color color, CarteCollider carteCollider)
	{
		super(id, pos);
		super.m_color = color;
		this.m_carteCollider = carteCollider;
		m_currentPos = pos;
		m_consomeScore = 0;
	}

	Consumable(byte id, Vector2 pos, Color color, CarteCollider carteCollider, int score)
	{
		super(id, pos);
		super.m_color = color;
		this.m_carteCollider = carteCollider;
		m_currentPos = pos;
		m_consomeScore = score;
	}

	int getScore()
	{
		return m_consomeScore;
	}

	@Override
	public void render(Graphics p,int width,int height)
	{
		p.setColor(super.m_color);
		p.fillOval((int)(super.m_pos.x*width+width/4), (int)(super.m_pos.y*height+height/4), width/2, height/2);
	}
}