package fr.upec.PacMan.CONTROLEUR;
import fr.upec.PacMan.VUE.*;
import java.util.*;

/*
 * Nom de classe : GameManager
 *
 * Description   : Cr�e et suprime tous les �lement liers au jeux et si besoin redemare la partie en cas de fin de jeux
 */

public class GameManager
{
	private List<Behaviour> m_behaviours = new ArrayList<Behaviour>();
	private Window m_window;

	public GameManager()
	{
		m_window = new Window();
		m_window.setVisible(true);
		Update();
	}

	public void Update()
	{
		
	}
}