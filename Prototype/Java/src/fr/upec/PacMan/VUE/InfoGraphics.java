package fr.upec.PacMan.VUE;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
 * Nom de classe : InfoGraphics
 *
 * Description   : Cree un JComponent swing pour dessiner les textes/infos
 */

public class InfoGraphics extends JPanel
{
	@Override
	protected void paintComponent(Graphics pinceau) 
	{
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque())
		{
      		secondPinceau.setColor(Color.black);
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
	}
}
