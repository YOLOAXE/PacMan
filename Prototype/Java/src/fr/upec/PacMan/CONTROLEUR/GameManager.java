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

public class GameManager implements Behaviour
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
	private boolean m_endGame = false;
	private float m_timerRestart;

	public GameManager()
	{
		this.m_carte = new Carte("Data/1.carte",m_infoGraphics);
		this.m_gameGraphics.add(this.m_carte);
		this.m_window = new Window(m_gameGraphics,m_infoGraphics);
		this.m_behaviours = new ArrayList<Behaviour>(this.m_carte.getBehaviours());
		this.m_window.setVisible(true);
		this.m_window.addKeyListener(this.m_carte.getPlayer());
		this.m_behaviours.add(this);
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

	@Override
	public void update(float deltaTime)//Game management
	{
		boolean cosumeAll = this.m_carte.getPlayer().consomeCount() == this.m_carte.getCarteCollider().getMaxConsumable();
		if(this.m_carte.getPlayer().isDead() || cosumeAll && !m_endGame)
		{
			if(this.m_carte.getPlayer().endGame() || cosumeAll)
			{
				this.m_gameGraphics.endGame(cosumeAll);
				this.m_endGame = true;
				this.m_timerRestart = 5.0f;
			}
			for(Actor a : this.m_carte.getActors())
			{
				a.resetSpawnPoint();
			}
		}
		if(m_endGame)
		{
			this.m_timerRestart -= deltaTime;
			this.m_gameGraphics.setTime(Math.round(this.m_timerRestart));
			if(this.m_timerRestart < 0.0f)
			{
				this.m_endGame = false;				
				for(Entity e : this.m_carte.getEntitys())
				{
					e.reset();
				}
				this.m_gameGraphics.reset();
			}
		}
	}
}