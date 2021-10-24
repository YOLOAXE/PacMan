package fr.upec.PacMan.MODEL;
import fr.upec.PacMan.VUE.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
/*
 * Nom de classe : Player
 *
 * Description   : Le joueur controle pacman avec un les touches z,q,s,d
 */

public class Player extends Actor implements KeyListener
{
	private int m_life = Constant.PACMAN_BASE_LIFE;
	private int m_score = 0;
	private int m_additionalLife = 0;
	private InfoGraphics infoGraphics = null;
	private boolean m_dead = false;
	private int m_consomeCount = 0;
	private java.util.List<Actor> m_actors;
	private EffetObject currentEffect = null;
	private boolean initEffect = false;

	public Player(Vector2 pos,CarteCollider carteCollider,InfoGraphics ig)
	{
		super(Constant.PLAYER_ID,pos,Constant.PACMAN_COLOR,carteCollider);	
		super.m_idWallCollide.add((int)Constant.WALL_SPEC_ID);
		this.infoGraphics = ig;		
	}

	public void setActors(java.util.List<Actor> actors)
	{
		this.m_actors = actors;
	}

	@Override
	public void action(int state)
	{
		super.action(state);
		switch(state)
		{
			case Constant.STATE_ACTION_NORMAL:
				super.m_color = Constant.PACMAN_COLOR;
				break;	
			case Constant.STATE_ACTION_INVISIBLE:
				super.m_color = new Color(255,250,155);
				break;	
			case Constant.STATE_ACTION_SUPER:
				super.m_color = new Color(255,106,0);
				break;	
			case Constant.STATE_ACTION_LABYRINTHE:
				super.m_carteCollider.removeRandomWall();
				break;	
			default:
				break;
		}
	}

	public int consomeCount()
	{
		return this.m_consomeCount;
	}

	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		if(!super.m_nextState && m_move)
		{
			super.m_nextState = true;
			Consumable cons = super.m_carteCollider.getConsumableCarte(this.getPosition());
			if(cons != null && !cons.isEaten())
			{
				m_score += cons.consume();
				m_additionalLife += cons.consume();
				m_consomeCount++;
				EffetObject e = (EffetObject)cons;
				if(e.hasEffect())
				{
					if(currentEffect != null)
					{
						currentEffect.endEffect(m_actors);
					}		
					currentEffect = e;
					initEffect = false;
				}
				infoGraphics.setPoint(m_score);
				if(m_additionalLife > Constant.ADDITIONAL_LIFE)
				{
					m_additionalLife = 0;
					m_life++;
					infoGraphics.setVie(m_life);
				}
			}
		}
		if(currentEffect != null)
		{
			if(!initEffect)
			{
				currentEffect.initEffect(m_actors);
				initEffect = true;
			}
			if(currentEffect.effet(m_actors,deltaTime))
			{
				return;
			}
			currentEffect.endEffect(m_actors);
			currentEffect = null;
		}
	}

	@Override
	public void reset()
	{
		super.reset();
		m_score = 0;
		m_life = Constant.PACMAN_BASE_LIFE;
		m_additionalLife = 0;
		m_consomeCount = 0;
		initEffect = false;
		currentEffect = null;
		infoGraphics.setVie(m_life);		
		infoGraphics.setPoint(m_score);
	}

	@Override
	public void resetSpawnPoint()
	{
		super.resetSpawnPoint();
		m_dead = false;
	}

	public boolean endGame()
	{
		return m_dead && m_life == 0; 
	}

	public boolean isDead()
	{
		return m_dead;
	}

	public void died()
	{		
		if(!m_dead)
		{
			m_dead = true;
			initEffect = false;
			if(currentEffect != null)
			{
				currentEffect.endEffect(m_actors);
				currentEffect = null;
			}			
			if(m_life > 0)
			{
				m_life--;
			}
			infoGraphics.setVie(m_life);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_UP:
			case KeyEvent.VK_Z:
				super.m_nextDir = Direction.NORTH;
				if (!super.m_move) { super.m_timerDeplacement = super.m_actorSpeed; }
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_Q:
				super.m_nextDir = Direction.WEST;
				if (!super.m_move) { super.m_timerDeplacement = super.m_actorSpeed; }
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				super.m_nextDir = Direction.SOUTH;
				if (!super.m_move) { super.m_timerDeplacement = super.m_actorSpeed; }
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				super.m_nextDir = Direction.EAST;
				if (!super.m_move) { super.m_timerDeplacement = super.m_actorSpeed; }
				break;
			default:
				break;
		}
	}

    @Override
    public void keyReleased(KeyEvent e) {}
}