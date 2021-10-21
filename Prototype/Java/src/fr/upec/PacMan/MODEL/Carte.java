package fr.upec.PacMan.MODEL;
import java.util.*;

import fr.upec.PacMan.VUE.Rendering;
import fr.upec.PacMan.CONTROLEUR.Behaviour;
import javax.swing.*;
import java.awt.*;

import java.io.*;
/*
 * Nom de classe : Carte
 *
 * Description   : Carte dynamique des elements
 */

public class Carte implements Rendering
{
    private int m_spawnCarte[][];
    private Consumable m_consumables[][];	
    private int m_hauteur;
    private int m_largeur;
	private ClassLoader loader;
	private final String CHEMIN_SIMPLE_RUN = "./res/";
	private InputStream inputStream;
	private int fs = 0;

	private java.util.List<Behaviour> m_behaviours = new ArrayList<Behaviour>();
	private java.util.List<Rendering> m_entitys = new ArrayList<Rendering>();

    public Carte(String path)
    {
		this.loader = Thread.currentThread().getContextClassLoader();
		try 
		{
			if(this.loader.getResource(path) == null)
			{
				this.inputStream = new FileInputStream(CHEMIN_SIMPLE_RUN+path);
			}
			else
			{
				this.inputStream = this.loader.getResourceAsStream(path);
			}
			this.m_hauteur = this.inputStream.read();
			this.m_largeur = this.inputStream.read();
			this.m_spawnCarte = new int[m_largeur][m_hauteur];
			this.m_consumables = new Consumable[m_largeur][m_hauteur];
			for(int y = 0; y < m_hauteur;y++)
			{
				for(int x = 0; x < m_largeur;x++)
				{
					this.m_spawnCarte[x][y] = this.inputStream.read();
					createEntity(x,y,this.m_spawnCarte[x][y]);
				}
			}
			this.inputStream.close();
		} 
		catch (IOException ex) 
		{
            ex.printStackTrace();
        }
    }

	private void createEntity(int x,int y,int id)
	{
		switch(id)
		{
			case Constant.PLAYER_ID:
				Player p = new Player(new Vector2(x,y));		
				m_behaviours.add((Behaviour)p);
				m_entitys.add((Rendering)p);
				break;
			case Constant.FANTOME_ID:
				Ghost g = new Ghost(new Vector2(x,y),Constant.FANTOME_COLOR[fs++%Constant.FANTOME_COLOR.length]);		
				m_behaviours.add((Behaviour)g);
				m_entitys.add((Rendering)g);
				break;
			default:
				break;
		}
	}

	public java.util.List<Behaviour> getBehaviours()
	{
		return this.m_behaviours;
	}

	public Consumable getConsumableCarte(Vector2 vec)
	{
		return this.m_consumables[vec.getIntX()][vec.getIntY()];
	}

    public int getSpawnCarte(Vector2 vec)
    {
        return this.m_spawnCarte[vec.getIntX()][vec.getIntY()];
    }

    public int getH()
    {
        return this.m_hauteur;
    }

    public int getL()
    {
        return this.m_largeur;
    }

	@Override
	public void render(Graphics p,int width,int height)
	{		
		p.setColor(new Color(20,20,220));
		int sizeXCase = width/this.m_largeur;
		int sizeYCase = height/this.m_hauteur;
		for(int y = 0; y < m_hauteur;y++)
		{
			for(int x = 0; x < m_largeur;x++)
			{
				if(this.m_spawnCarte[x][y] == Constant.WALL_ID)
				{
					p.fillRect(x*sizeXCase, y*sizeYCase, sizeXCase, sizeYCase);				 
				}
			}
		}		
		for(Rendering r : m_entitys)
		{
			r.render(p,sizeXCase,sizeYCase);
		}
	}
}
