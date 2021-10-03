package fr.upec.PacMan.VUE;
import javax.swing.*;

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
}
