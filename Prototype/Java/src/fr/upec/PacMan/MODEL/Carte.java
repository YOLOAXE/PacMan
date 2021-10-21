package fr.upec.PacMan.MODEL;
import java.util.*;

import fr.upec.PacMan.VUE.Rendering;
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
				}
			}
			this.inputStream.close();
				
		} 
		catch (IOException ex) 
		{
            ex.printStackTrace();
        }
    }

	public Consumable[][] getConsumableCarte()
	{
		return this.m_consumables;
	}

    public int[][] getSpawnCarte()
    {
        return this.m_spawnCarte;
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
	public void render(Graphics p,JPanel panel)
	{		
		p.setColor(new Color(20,20,220));
		int sizeXCase = panel.getWidth()/this.m_largeur;
		int sizeYCase = panel.getHeight()/this.m_hauteur;
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
	}
}
