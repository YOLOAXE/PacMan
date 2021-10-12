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

public class Consumable implements Rendering
{
	protected int m_consomeScore;

	Consumable()
	{
		m_consomeScore = 0;
	}

	Consumable(int score)
	{
		m_consomeScore = score;
	}

	int getScore()
	{
		return m_consomeScore;
	}

	@Override
	public void render(Graphics p)
	{

	}
}