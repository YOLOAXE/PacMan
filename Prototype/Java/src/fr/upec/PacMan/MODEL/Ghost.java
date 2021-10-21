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

	public Ghost(Vector2 pos,Color c,CarteCollider carteCollider)
	{
		super(Constant.GHOST_ID,pos,c,carteCollider);	
		m_pathCarte = new Node[carteCollider.getL()][carteCollider.getH()];
		for (int i = 0; i < carteCollider.getL(); i++)
		{
			for (int j = 0; j < carteCollider.getH(); j++)
			{
				m_pathCarte[i][j] = new Node();
				m_pathCarte[i][j].setWall(carteCollider.getSpawnCarte(new Vector2(i,j)) == Constant.WALL_ID);//TODO MODULO DEUX DIR 
				m_pathCarte[i][j].setPos(new Vector2(i, j));				
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
					}
				}
			}
		}
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
				m_pathCarte[x][y].setFCost(10000000.0f);
				m_pathCarte[x][y].setParent(null);
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
	}
}
