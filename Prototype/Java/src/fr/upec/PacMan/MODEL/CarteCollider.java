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
    private Consumable m_consumables[][], cons;	

	public CarteCollider(int hauteur,int largeur,int spawnCarte[][],Consumable consumables[][])
	{
	    m_hauteur = hauteur;
		m_largeur = largeur;
		m_spawnCarte = spawnCarte;
		m_consumables = consumables;
	}

	public void setConsumableCarte(Vector2 vec,int c)
	{
		switch (c) {
		case Constant.GOME_ID:
			cons = new Gum(vec, this);
			break;
		case Constant.PAC_GOME_ORANGE_ID:
			cons = new OrangeGum(vec, this);
			break;
		case Constant.PAC_GOME_VERT_ID:
			cons = new GreenGum(vec, this);
			break;
		case Constant.PAC_GOME_VIOLET_ID:
			cons = new VioletGum(vec, this);
			break;
		default:
			break;
		}
		this.m_consumables[vec.getIntX()][vec.getIntY()] = cons;
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
