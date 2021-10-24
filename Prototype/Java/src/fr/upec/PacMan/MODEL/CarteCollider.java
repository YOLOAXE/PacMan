package fr.upec.PacMan.MODEL;
import java.util.*;

import fr.upec.PacMan.VUE.Rendering;
import fr.upec.PacMan.CONTROLEUR.Behaviour;
import javax.swing.*;
import java.awt.*;

/*
 * Nom de classe : CarteCollider
 *
 * Description   : Carte des Collisions
 */

public class CarteCollider
{
    private int m_hauteur;
    private int m_largeur;
	private int m_spawnCarte[][];
    private Consumable m_consumables[][];	
	private int consumableMax = 0;

	public CarteCollider(int hauteur,int largeur)
	{
	    this.m_hauteur = hauteur;
		this.m_largeur = largeur;
	}

	public void initCarte(int spawnCarte[][],Consumable consumables[][])
	{
		this.m_spawnCarte = spawnCarte;
		this.m_consumables = consumables;
		for(int y = 0; y < m_hauteur;y++)
		{
			for(int x = 0; x < m_largeur;x++)
			{
				if(consumables[x][y] != null)
				{
					consumableMax++;
				}
			}
		}
	}

	public int getMaxConsumable()
	{
		return consumableMax;
	}

	public void setSpawnCarte(Vector2 vec,int id)
	{
		this.m_spawnCarte[vec.getIntX()][vec.getIntY()] = id;
	}

	public void removeRandomWall()
	{
		Random random = new Random();
		java.util.List<Vector2> allWall = new ArrayList<Vector2>();
		for(int y = 0; y < m_hauteur;y++)
		{
			for(int x = 0; x < m_largeur;x++)
			{
				if(m_spawnCarte[x][y] == Constant.WALL_ID)
				{
					allWall.add(new Vector2(x,y));
				}
			}
		}
		if(allWall.size() > 0)
		{
			Vector2 vec = allWall.get(random.nextInt(allWall.size()));
			m_spawnCarte[vec.getIntX()][vec.getIntY()] = 0;
		}
	}

	public Consumable getConsumableCarte(Vector2 vec)
	{
		return this.m_consumables[vec.getIntX()][vec.getIntY()];
	}

    public int getSpawnCarte(Vector2 vec)
    {
        return this.m_spawnCarte[vec.getIntX()][vec.getIntY()];
    }

	public Vector2 getSize()
	{
		return new Vector2(this.m_largeur,this.m_hauteur);
	}

	public int getH()
    {
        return this.m_hauteur;
    }

    public int getL()
    {
        return this.m_largeur;
    }
}
