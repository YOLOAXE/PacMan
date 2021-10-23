package fr.upec.PacMan.VUE;
import fr.upec.PacMan.MODEL.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/*
 * Nom de classe : InfoGraphics
 *
 * Description   : Cree un JComponent swing pour dessiner les textes/infos
 */

public class InfoGraphics extends JPanel
{
	private int vie = Constant.PACMAN_BASE_LIFE;
	private int point = 0;

	public void setVie(int v)
	{
		this.vie = v;
		this.repaint();
	}

	public void setPoint(int p)
	{
		point = p;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics pinceau) 
	{
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque())
		{
      		secondPinceau.setColor(Color.black);
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }		
		secondPinceau.setColor(Color.white);	
		secondPinceau.setFont(new Font("serif", Font.BOLD, 20));
		secondPinceau.drawString("LIFE:", 0, 20);	
		secondPinceau.drawString("SCORE: " + point, 0, 2*(this.getWidth()/4));	
		pinceau.setColor(Constant.PACMAN_COLOR);		
		for(int i = 0 ; i < vie;i++)
		{
			pinceau.fillOval(i*(this.getWidth()/3) , this.getWidth()/6 , this.getWidth()/5, this.getWidth()/5);
		}
	}
}
