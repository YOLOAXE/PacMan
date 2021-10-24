package fr.upec.PacMan.MODEL;
import java.util.*;

import fr.upec.PacMan.VUE.*;
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
	private InfoGraphics m_infoGraphics = null;
	private java.util.List<Behaviour> m_behaviours = new ArrayList<Behaviour>();
	private java.util.List<Rendering> m_renderings = new ArrayList<Rendering>();
	private java.util.List<Entity> m_entitys = new ArrayList<Entity>();
	private java.util.List<Vector2> m_ghostPos = new ArrayList<Vector2>();
	private java.util.List<Actor> m_actor = new ArrayList<Actor>();
	private Consumable m_consumables[][];

    public Carte(String path,InfoGraphics ig)
    {
		this.loader = Thread.currentThread().getContextClassLoader();
		this.m_infoGraphics = ig;
		int m_spawnCarte[][];
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
			m_carteCollider = new CarteCollider(m_hauteur,m_largeur);
			for(int y = 0; y < m_hauteur;y++)
			{
				for(int x = 0; x < m_largeur;x++)
				{
					m_spawnCarte[x][y] = this.inputStream.read();
					createEntity(x,y,m_spawnCarte[x][y]);
				}
			}
			m_carteCollider.initCarte(m_spawnCarte,m_consumables);
			m_renderings.remove((Rendering)player);
			m_renderings.add((Rendering)player);// last draw priority
			for(Vector2 vec : m_ghostPos)
			{
				Ghost g = new Ghost(vec,Constant.GHOST_COLOR,m_carteCollider,player);				
				m_behaviours.add((Behaviour)g);
				m_actor.add((Actor)g);
				m_entitys.add((Entity)g);
				m_renderings.add((Rendering)g);
			}
			player.setActors(m_actor);
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
				player = new Player(new Vector2(x,y),m_carteCollider,this.m_infoGraphics);		
				m_behaviours.add((Behaviour)player);
				m_renderings.add((Rendering)player);
				m_entitys.add((Entity)player);
				m_actor.add((Actor)player);
				break;
			case Constant.GHOST_ID:
					m_ghostPos.add(new Vector2(x,y));
				break;
			case Constant.GOME_ID:
				Gum gum = new Gum(new Vector2(x, y), m_carteCollider);
				m_renderings.add((Rendering)gum);
				m_entitys.add((Entity)gum);
				m_consumables[x][y] = (Consumable)gum;
				break;
			case Constant.PAC_GOME_ORANGE_ID:
				OrangeGum ogum = new OrangeGum(new Vector2(x, y), m_carteCollider);
				m_renderings.add((Rendering)ogum);
				m_entitys.add((Entity)ogum);
				m_consumables[x][y] = (Consumable)ogum;
				break;
			case Constant.PAC_GOME_VERT_ID:
				GreenGum ggum = new GreenGum(new Vector2(x, y), m_carteCollider);
				m_renderings.add((Rendering)ggum);
				m_entitys.add((Entity)ggum);
				m_consumables[x][y] = (Consumable)ggum;
				break;
			case Constant.PAC_GOME_VIOLET_ID:
				VioletGum vgum = new VioletGum(new Vector2(x, y), m_carteCollider);
				m_renderings.add((Rendering)vgum);
				m_entitys.add((Entity)vgum);
				m_consumables[x][y] = (Consumable)vgum;
				break;
			default:
				break;
		}
	}

	public CarteCollider getCarteCollider()
	{
		return m_carteCollider;
	}

	public java.util.List<Behaviour> getBehaviours()
	{
		return this.m_behaviours;
	}

	public java.util.List<Entity> getEntitys()
	{
		return this.m_entitys;
	}

	public java.util.List<Actor> getActors()
	{
		return this.m_actor;
	}

	public Player getPlayer()
	{
		return player;
	}

	@Override
	public void render(Graphics p,int width,int height)
	{		
		p.setColor(Constant.CARTE_COLOR);
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
		for(Rendering r : m_renderings)
		{
			r.render(p,sizeXCase,sizeYCase);
		}
	}
}
