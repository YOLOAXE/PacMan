package fr.upec.PacMan.MODEL;

/*
 * Nom de classe : Consumable
 *
 * Description   : Les objects qui peuvent etre utiliser par les acteurs exemple player
 */

public class Consumable
{
	protected int m_consomeScore;

	Consumable(int score = 0)
	{
		m_consomeScore = score;
	}

	int getScore()
	{
		return m_consomeScore;
	}
}