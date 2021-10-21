package fr.upec.PacMan.VUE;
import fr.upec.PacMan.MODEL.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
 * Nom de classe : GameGraphics
 *
 * Description   : Cree un panel swing pour la carte
 */

public class GameGraphics extends JPanel
{		
	private java.util.List<Rendering> m_renderings = new ArrayList<Rendering>();

	public void add(Rendering r)
	{
		m_renderings.add(r);
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
		for(Rendering r : m_renderings)
		{
			r.render(secondPinceau,this.getWidth(),this.getHeight()); 
		}
	}
}