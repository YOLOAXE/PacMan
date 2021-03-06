package fr.upec.PacMan.MODEL;
import javax.swing.*;
import java.awt.*;

/*
 * Nom de classe : Vector2
 *
 * Description   : Pour manipuler les objects en Jeux
 */

 public abstract class Entity
 {
	protected byte m_id;
	protected Vector2 m_pos;
	protected Color m_color;

	Entity(byte id,Vector2 pos)
	{
		m_id = id;
		m_pos = pos;
	}

	public Vector2 getPosition()
	{
		return m_pos;
	}

	public abstract void reset();
 }