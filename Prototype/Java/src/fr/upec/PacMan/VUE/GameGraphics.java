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
	private java.util.List<Rendering> entitysRendering = new ArrayList<Rendering>();

	public GameGraphics()
	{
		m_gamePanel.add(this);
	}

	@Override
    public JPanel getPanel()
    {
        return m_gamePanel;
    }

	public void addArrayList(Rendering render)
	{
		entitysRendering.add(render);
	}

	@Override
	protected void paintComponent(Graphics pinceau) 
	{
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque())
		{
      		secondPinceau.setColor(Color.white);
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
		secondPinceau.setColor(Color.red);
      	secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(Rendering r : entitysRendering)
		{
			r.render(secondPinceau);
		}
	}
}