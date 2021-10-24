package fr.upec.PacMan.MODEL;
import fr.upec.PacMan.CONTROLEUR.*;
import fr.upec.PacMan.VUE.Rendering;
import javax.swing.*;
import java.awt.*;
import java.util.*;
/*
 * Nom de classe : Actor
 *
 * Description   : Le conportement des Entites en mouvement
 */

public abstract class Actor extends Entity implements Behaviour,Rendering,StateEffect
{
	protected float m_actorSpeed = Constant.BASE_SPEED;
	protected Direction m_dir = Direction.NORTH;
	protected Direction m_nextDir = Direction.NORTH;
	protected Vector2 m_spawnPoint = new Vector2();
	protected Vector2 m_currentPos = new Vector2();
	protected Vector2 m_nextPos = new Vector2();
	protected boolean m_move = false;
	protected boolean m_nextState = false;
	protected float m_timerDeplacement = 0;
	protected CarteCollider m_carteCollider;
	protected java.util.List<Integer> m_idWallCollide = new ArrayList<Integer>();
	protected int m_state = 0;
	private boolean wraparound = false;

	Actor(byte id,Vector2 pos,Color color,CarteCollider carteCollider)
	{
		super(id,pos);
		m_spawnPoint = pos;
		super.m_color = color;
		this.m_carteCollider = carteCollider;
		m_idWallCollide.add((int)Constant.WALL_ID);
		m_currentPos = pos;
		m_nextPos = pos;
	}

	public void action(int state)
	{
		this.m_state = state;
	}

	public void resetSpawnPoint()
	{
		m_currentPos = m_spawnPoint;
		m_nextPos = m_spawnPoint;
	}

	Vector2 move(Direction dir)
	{
		m_timerDeplacement = 0.0f;	
		Vector2 result = m_currentPos.additionModuloDeuxDirection(dir.getPosDirection(),this.m_carteCollider.getSize());
		this.wraparound = !result.equalsInt(m_currentPos.addition(dir.getPosDirection()));
		boolean collide = false;
		for (int i = 0; i < m_idWallCollide.size() && !collide; i++)
		{
			collide = this.m_carteCollider.getSpawnCarte(result) == m_idWallCollide.get(i);
		}
		if (collide)
		{
			m_move = false;
			return m_currentPos;
		}
		else
		{			
			m_move = true; 	
			if(this.wraparound)
			{
				m_timerDeplacement = m_actorSpeed;
				this.wraparound = false;
			}
			return result;
		}	
	}

	@Override
	public void reset()
	{
		this.resetSpawnPoint();
		this.m_dir = Direction.NORTH;
		this.m_nextDir = Direction.NORTH;
		this.m_actorSpeed = Constant.BASE_SPEED;
	}

	@Override
	public void update(float deltaTime)
	{
		m_timerDeplacement += deltaTime;

		if (m_timerDeplacement > m_actorSpeed)
		{
			m_currentPos = m_nextPos;
			m_nextPos = move(m_nextDir);
			m_nextState = false;			
		}

		super.m_pos = Vector2.Lerp(m_currentPos, m_nextPos, m_timerDeplacement / m_actorSpeed);
	}

	@Override
	public void render(Graphics p,int width,int height)
	{
		p.setColor(super.m_color);
		p.fillOval((int)(super.m_pos.x*width), (int)(super.m_pos.y*height), width, height);
	}
}
