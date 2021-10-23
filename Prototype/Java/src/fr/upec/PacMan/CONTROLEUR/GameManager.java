package fr.upec.PacMan.CONTROLEUR;
import fr.upec.PacMan.VUE.*;
import fr.upec.PacMan.MODEL.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Nom de classe : GameManager
 *
 * Description   : Cree et suprime tous les elements liers au jeux et si besoin redemare la partie en cas de fin de jeux
 */

public class GameManager
{
	private List<Behaviour> m_behaviours;
	private long m_lastTime = System.nanoTime();
	private long m_fixedLastTime = System.nanoTime();
	private float m_deltaTime;
	private float m_fixedTime;	
	private float m_lag = 0.0f;
	private float suprimer = 0.0f;
	private Window m_window;
	private Carte m_carte;
	private GameGraphics m_gameGraphics = new GameGraphics(); 
	private InfoGraphics m_infoGraphics = new InfoGraphics(); 

	public GameManager()
	{
		this.m_carte = new Carte("Data/1.carte",m_infoGraphics);
		this.m_gameGraphics.add(this.m_carte);
		this.m_window = new Window(m_gameGraphics,m_infoGraphics);
		this.m_behaviours = new ArrayList<Behaviour>(this.m_carte.getBehaviours());
		this.m_window.setVisible(true);
		this.m_window.addKeyListener(this.m_carte.getPlayer());
	}

	public void start()
	{
		this.update();
		this.m_window.dispose();
	}

	private void update()
	{
	    while (!this.m_window.isClosing())
		{
			m_fixedTime = (System.nanoTime() - m_fixedLastTime) / 1000000000.0f;
    		m_fixedLastTime = System.nanoTime();
			m_lag += m_fixedTime;			
			if (m_lag >= Constant.FRAME_PAR_SECONDE)
			{
				m_deltaTime = (System.nanoTime() - m_lastTime) / 1000000000.0f;
    			m_lastTime = System.nanoTime();
				for (Behaviour behaviour : m_behaviours) 
				{
					behaviour.update(m_deltaTime);
				}
				m_gameGraphics.repaint();
				m_lag -= Constant.FRAME_PAR_SECONDE;
			}			
		}
	}
}