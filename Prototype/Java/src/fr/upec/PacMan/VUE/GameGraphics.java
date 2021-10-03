package fr.upec.PacMan.VUE;
import fr.upec.PacMan.MODEL.*;
import javax.swing.*;

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
}