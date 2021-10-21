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

	public CarteCollider(int hauteur,int largeur,int spawnCarte[][],Consumable consumables[][])
	{
	    m_hauteur = hauteur;
		m_largeur = largeur;
		m_spawnCarte = spawnCarte;
		m_consumables = consumables;
	}

	public void setConsumableCarte(Vector2 vec,Consumable c)
	{
		this.m_consumables[vec.getIntX()][vec.getIntY()] = c;
	}

	public void setSpawnCarte(Vector2 vec,int id)
	{
		this.m_spawnCarte[vec.getIntX()][vec.getIntY()] = id;
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
