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
    private CarteCollider m_carteCollider;	
	private ClassLoader loader;
	private final String CHEMIN_SIMPLE_RUN = "./res/";
	private InputStream inputStream;
	private int fs = 0;
	private Player player = null;

	private java.util.List<Behaviour> m_behaviours = new ArrayList<Behaviour>();
	private java.util.List<Rendering> m_entitys = new ArrayList<Rendering>();

    public Carte(String path)
    {
		this.loader = Thread.currentThread().getContextClassLoader();
		int m_spawnCarte[][];
		Consumable m_consumables[][];
		int m_hauteur;
		int m_largeur;
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
			m_hauteur = this.inputStream.read();
			m_largeur = this.inputStream.read();
			m_spawnCarte = new int[m_largeur][m_hauteur];
			m_consumables = new Consumable[m_largeur][m_hauteur];
			m_carteCollider = new CarteCollider(m_hauteur,m_largeur,m_spawnCarte,m_consumables);
			for(int y = 0; y < m_hauteur;y++)
			{
				for(int x = 0; x < m_largeur;x++)
				{
					m_spawnCarte[x][y] = this.inputStream.read();
					m_carteCollider.setSpawnCarte(new Vector2(x,y),m_spawnCarte[x][y]);
					createEntity(x,y,m_spawnCarte[x][y]);
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
				player = new Player(new Vector2(x,y),m_carteCollider);		
				m_behaviours.add((Behaviour)player);
				m_entitys.add((Rendering)player);
				break;
			case Constant.GHOST_ID:
				Ghost g = new Ghost(new Vector2(x,y),Constant.GHOST_COLOR[fs++%Constant.GHOST_COLOR.length],m_carteCollider);
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

	public Player getPlayer()
	{
		return player;
	}

	@Override
	public void render(Graphics p,int width,int height)
	{		
		p.setColor(new Color(20,20,220));
		int sizeXCase = width/this.m_carteCollider.getL();
		int sizeYCase = height/this.m_carteCollider.getH();
		for(int y = 0; y < this.m_carteCollider.getH();y++)
		{
			for(int x = 0; x < this.m_carteCollider.getL();x++)
			{
				if(this.m_carteCollider.getSpawnCarte(new Vector2(x,y)) == Constant.WALL_ID)
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
