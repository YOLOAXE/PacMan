package fr.upec.PacMan.MODEL;
import java.awt.*;
import java.util.*;
/*
 * Nom de classe : Ghost
 *
 * Description   : Deplacement aleatoire des fantomes avec du path finding car il retourne a leur point d'aparition
 */

public class Ghost extends Actor
{
	private java.util.List<Direction> m_pathDir = new ArrayList<Direction>();
	private Node[][] m_pathCarte;
	private int m_indicePathDir = 0;
	private Random random = new Random();
	private java.util.List<Vector2> allCorrectNoWallPos = new ArrayList<Vector2>();
	private Player m_player = null;

	public Ghost(Vector2 pos,Color c,CarteCollider carteCollider,Player p)
	{
		super(Constant.GHOST_ID,pos,c,carteCollider);	
		this.m_player = p;
		m_pathCarte = new Node[carteCollider.getL()][carteCollider.getH()];
		for (int i = 0; i < carteCollider.getL(); i++)
		{
			for (int j = 0; j < carteCollider.getH(); j++)
			{
				m_pathCarte[i][j] = new Node(new Vector2(i, j),carteCollider.getSpawnCarte(new Vector2(i,j)) == Constant.WALL_ID);
				//TODO MODULO DEUX DIR 				
				if (i - 1 >= 0)
				{
					if (!m_pathCarte[i - 1][j].isWall())
					{
						m_pathCarte[i][j].addNeighbourDirection(m_pathCarte[i - 1][j],Direction.EAST);					
					}
					if (!m_pathCarte[i][j].isWall())
					{
						m_pathCarte[i - 1][j].addNeighbourDirection(m_pathCarte[i][j],Direction.WEST);
					}
				}
				if (j - 1 >= 0)
				{
					if (!m_pathCarte[i][j - 1].isWall())
					{
						m_pathCarte[i][j].addNeighbourDirection(m_pathCarte[i][j - 1],Direction.SOUTH);						
					}
					if (!m_pathCarte[i][j].isWall())
					{
						m_pathCarte[i][j - 1].addNeighbourDirection(m_pathCarte[i][j],Direction.NORTH);	
						allCorrectNoWallPos.add(new Vector2(i,j));
					}
				}
			}
		}
		m_pathDir.add(Direction.NORTH);
	}

	Node findlowestFCost(java.util.List<Node> nodes)
	{
		float cost = 20000000.0f;
		Node choise = null;
		for (Node n : nodes)
		{
			if (n.getFCost() < cost)
			{
				cost = n.getFCost();
				choise = n;
			}
		}
		return choise;
	}

	private Direction recursiveAddPath(Node n)
	{
		if (n.getParent() == null)
		{
			return Direction.NORTH;
		}
		if (n.getParent().getParent() == null)
		{
			return n.getParentDirection();
		}		
		m_pathDir.add(recursiveAddPath(n.getParent()));
		return n.getParentDirection();
	}

	private void pathFinding(Vector2 targetPos)
	{
		m_indicePathDir = 0;
		m_pathDir.clear();
		java.util.List<Node> closePath = new ArrayList<Node>();
		java.util.List<Node> openPath = new ArrayList<Node>();
		Node start = m_pathCarte[super.m_pos.getIntX()][super.m_pos.getIntY()];
		Node target = m_pathCarte[targetPos.getIntX()][targetPos.getIntY()];
		Node current = start;
		current.calculeCost(start,0, target);
		openPath.add(current);		
		boolean end = false;
		for (int y = 0; y < super.m_carteCollider.getH(); y++)
		{
			for (int x = 0; x < super.m_carteCollider.getL(); x++)
			{
				m_pathCarte[x][y].clear();
			}
		}		
		while (!end)
		{
			current = findlowestFCost(openPath);
			if (current == null)
			{				
				System.out.println("Warn openPath : " + openPath.size());
				return;
			}
			openPath.remove(current);
			closePath.add(current);
			if (current == target)
			{
				recursiveAddPath(target);
				m_pathDir.add(current.getParentDirection());
				end = true;
				return;
			}
			for (Node neighbour : current.getNeighbour())
			{
				if (!closePath.contains(neighbour) && !openPath.contains(neighbour))
				{
					neighbour.calculeCost(current, current.getGCost(), target);
					openPath.add(neighbour);
				}
			}
		}
	}

    @Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);

		if(m_player.getPosition().equalsInt(super.m_pos))
		{
			m_player.died();
		}

		// PATH finding RANDOM
		if (m_indicePathDir == m_pathDir.size() || !m_move)
		{			
			pathFinding(allCorrectNoWallPos.get(RandomRange(0,allCorrectNoWallPos.size())));//pathFinding(this.m_player.getPosition()); //Fonctionne
			if (!m_move) { m_timerDeplacement = m_actorSpeed; }
		}

		if (!m_nextState && m_indicePathDir < m_pathDir.size())
		{
			m_nextState = true;
			m_nextDir = m_pathDir.get(m_indicePathDir++);
		}
		// PATH finding RANDOM

		// RANDOM Simple
		/*if(!m_nextState)
		{
			m_nextState = true;
			super.m_nextDir = this.nextRandomDirection();
		}*/
		// RANDOM Simple
	}

	public int RandomRange(int min, int max) {		
		return random.nextInt(max - min) + min;
	}

	public Direction nextRandomDirection()
	{
		Vector2 result = m_nextPos.additionModuloDeuxDirection(super.m_nextDir.getPosDirection(),super.m_carteCollider.getSize());	
		boolean collide = false;
		java.util.List<Direction> choix = new ArrayList<Direction>();
		for (int i = 0; i < m_idWallCollide.size() && !collide; i++)
		{
			collide = super.m_carteCollider.getSpawnCarte(result) == m_idWallCollide.get(i);
		}
		if(collide)
		{
			for(int j =  0; j < 4;j++)
			{
				result = m_nextPos.additionModuloDeuxDirection(Constant.allDirection[j].getPosDirection(),super.m_carteCollider.getSize());
				for (int i = 0; i < m_idWallCollide.size() && collide; i++)
				{
					collide = super.m_carteCollider.getSpawnCarte(result) == m_idWallCollide.get(i);
				}
				if(!collide)
				{
					choix.add(Constant.allDirection[j]);
					collide = true;
				}
			}
			if(choix.size() > 0)
			{
				return choix.get(RandomRange(0,choix.size()));
			}
		}
		else
		{
			return super.m_nextDir;
		}
		return super.m_nextDir; 
	}
}
