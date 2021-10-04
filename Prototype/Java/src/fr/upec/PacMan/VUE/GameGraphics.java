package fr.upec.PacMan.VUE;
import fr.upec.PacMan.MODEL.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
 * Nom de classe : GameGraphics
 *
 * Description   : Cree un panel swing pour JComponent la carte
 */

public class GameGraphics extends JComponent implements PanelWindow
{	
	private JPanel m_gamePanel = new JPanel();

	public GameGraphics()
	{
		m_gamePanel.add(this);
	}

	@Override
    public JPanel getPanel()
    {
        return m_gamePanel;
    }

	@Override
	protected void paintComponent(Graphics pinceau) 
	{
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque())
		{
      		secondPinceau.setColor(new Color(0,0,0));
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
		secondPinceau.setColor(Color.RED);
	}
}