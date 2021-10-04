package fr.upec.PacMan.VUE;
import fr.upec.PacMan.MODEL.*;
import javax.swing.*;
import java.awt.*;

/*
 * Nom de classe : Window
 *
 * Description   : Cree une fenetre avec une taille de base constante
 */

public class Window extends JFrame 
{
	private GridBagConstraints m_panelConstraint = new GridBagConstraints();
	private GridBagLayout m_layout = new GridBagLayout();

	public Window(PanelWindow gameGraphics,PanelWindow infoGraphics)
	{
	    this.setLayout(this.m_layout);
		this.setSize(Constant.LARGEUR_BASE_FENETRE, Constant.HAUTEUR_BASE_FENETRE);
	    this.setLocation(100, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setMinimumSize(new Dimension(Constant.LARGEUR_MINIMUM_FENETRE, Constant.HAUTEUR_MINIMUM_FENETRE));

		m_panelConstraint.gridx = 0;      
		m_panelConstraint.gridy = 0;      
		m_panelConstraint.gridwidth = 1;  
		m_panelConstraint.gridheight = 2; 
		m_panelConstraint.fill = GridBagConstraints.BOTH;
		m_panelConstraint.weightx = 1.0;
		m_panelConstraint.weighty = 1.0;
		m_panelConstraint.insets = new Insets(16, 16, 0, 0);
		this.add(gameGraphics.getPanel(), m_panelConstraint);			
		m_panelConstraint.gridx = 0;
		m_panelConstraint.gridy = 1;
		m_panelConstraint.gridwidth = 1;
		m_panelConstraint.gridheight = 1;
		this.add(infoGraphics.getPanel(), m_panelConstraint);
	}
}