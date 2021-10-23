package fr.upec.PacMan.MODEL;
import java.util.*;
/*
 * Nom de classe : Node
 *
 * Description   : Node pour effectuer des calcule de path Finding A*
 */

public class Node
{
	private float f_cost = 10000000.0f;
	private float h_cost;
	private float g_cost;
	private Vector2 pos = new Vector2();
	private boolean mur = false;
	private Node parent = null;
	private Direction parentDir;
	private List<Node> neighbour = new ArrayList<Node>();	
	private List<Direction> neighbourDir = new ArrayList<Direction>();

	public Node(Vector2 p,boolean m)
	{
		this.pos = p;
		this.mur = m;
	}

	public void clear()
	{
		f_cost= 10000000.0f;
		parent = null;
	}

	void calculeCost(Node start,float gp_cost, Node target)
	{
		float g = start.getPos().Distance(pos)+ gp_cost;
		float h = target.getPos().Distance(pos);
		if ((g + h) < f_cost)
		{
			g_cost = g;
			h_cost = h;
			f_cost = g_cost + h_cost;
			parent = start;
			for (int i = 0; i < neighbour.size();i++)
			{
				if (neighbour.get(i) == parent)
				{
					parentDir = neighbourDir.get(i);
					return;
				}
			}
		}
	}

	public Vector2 getPos()
	{
		return pos;
	}

	public float getFCost()
	{
		return f_cost;
	}

	public void setFCost(float cost)
	{
		this.f_cost = cost;
	}

	public float getGCost()
	{
		return g_cost;
	}

	public boolean isWall()
	{
		return mur;
	}

	public Node getParent()
	{
		return parent;
	}

	public void setParent(Node p)
	{
		this.parent = p;
	}

	public Direction getParentDirection()
	{
		return parentDir;
	}

	public List<Node> getNeighbour()
	{
		return neighbour;
	}

	public void addNeighbourDirection(Node n,Direction d)
	{
		this.neighbour.add(n);
		this.neighbourDir.add(d);
	}

	public List<Direction> getNeighbourDirection()
	{
		return neighbourDir;
	}
}