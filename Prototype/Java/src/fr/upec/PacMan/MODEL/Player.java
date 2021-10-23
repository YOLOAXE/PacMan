package fr.upec.PacMan.MODEL;
import fr.upec.PacMan.VUE.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Nom de classe : Player
 *
 * Description   : Le joueur controle pacman avec un les touches z,q,s,d
 */

public class Player extends Actor implements KeyListener
{
	private int m_vie = Constant.PACMAN_BASE_LIFE;
	private int m_score = 0;
	private InfoGraphics infoGraphics = null;
	private boolean m_dead = false;

	public Player(Vector2 pos,CarteCollider carteCollider,InfoGraphics ig)
	{
		super(Constant.PLAYER_ID,pos,new Color(255,255,0),carteCollider);	
		super.m_idWallCollide.add((int)Constant.WALL_SPEC_ID);
		this.infoGraphics = ig;
	}

	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		if(m_timerDeplacement > m_actorSpeed/2.0f && !m_nextState && m_move)
		{
			m_nextState = true;
		}
	}

	@Override
	public void resetSpawnPoint()
	{
		super.resetSpawnPoint();
		m_dead = false;
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
			System.out.println("Player Dead");
			if(m_vie > 0)
			{
				m_vie--;
			}
			infoGraphics.setVie(m_vie);
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