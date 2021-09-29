package fr.upec.PacMan.MODEL;

/*
 * Nom de classe : Vector2
 *
 * Description   : Pour manipuler les objects en Jeux
 */

 public class Entity
 {
	protected byte m_id;
	protected Vector2 m_pos;

	Entity(byte id,Vector2 pos)
	{
		m_id = id;
		m_pos = pos;
	}
 }