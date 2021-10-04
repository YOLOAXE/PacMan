package fr.upec.PacMan.VUE;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
 * Nom de classe : InfoGraphics
 *
 * Description   : Cree un JComponent swing pour dessiner les textes/infos
 */

public class InfoGraphics extends JComponent implements PanelWindow
{
    private JPanel m_infoPanel = new JPanel();

    public InfoGraphics()
	{
        m_infoPanel.add(this);
	}

    @Override
    public JPanel getPanel()
    {
        return m_infoPanel;
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
		
	}
}
