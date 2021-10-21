package fr.upec.PacMan.VUE;
import fr.upec.PacMan.MODEL.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;    
import java.awt.event.WindowListener;   
/*
 * Nom de classe : Window
 *
 * Description   : Cree une fenetre avec une taille de base constante
 */

public class Window extends JFrame implements WindowListener
{
	private GridBagConstraints m_panelConstraint = new GridBagConstraints();
	private GridBagLayout m_layout = new GridBagLayout();
	private boolean windowClosing = false;

	public Window(JPanel gameGraphics,JPanel infoGraphics)
	{
		this.setTitle("PacMan");
	    this.setLayout(this.m_layout);
		this.setSize(Constant.LARGEUR_BASE_FENETRE, Constant.HAUTEUR_BASE_FENETRE);
	    this.setLocation(100, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setMinimumSize(new Dimension(Constant.LARGEUR_MINIMUM_FENETRE, Constant.HAUTEUR_MINIMUM_FENETRE));
		
		m_panelConstraint.gridx = 0;      
		m_panelConstraint.gridy = 0;      
		m_panelConstraint.gridwidth = 2;  
		m_panelConstraint.gridheight = 1; 
		m_panelConstraint.fill = GridBagConstraints.BOTH;
		m_panelConstraint.weightx = 0.5f;
		m_panelConstraint.weighty = 0.5f;
		m_panelConstraint.insets = new Insets(8, 8, 8, 8);
		this.add(gameGraphics, m_panelConstraint);			
		m_panelConstraint.gridx = 2;
		m_panelConstraint.gridy = 0;
		m_panelConstraint.weightx = 0.1f;
		m_panelConstraint.gridwidth = 1;
		m_panelConstraint.gridheight = 1;
		this.add(infoGraphics, m_panelConstraint);
		this.getContentPane().setBackground(new Color(100,100,100));
		this.addWindowListener(this);
	}

	@Override
	public void windowActivated (WindowEvent e) {}    
  
	@Override
	public void windowClosed (WindowEvent e) {}    
  
	@Override
	public void windowClosing (WindowEvent e) 
	{
		windowClosing = true;
	}    
  
	@Override
	public void windowDeactivated (WindowEvent e) {}    
  
	@Override
	public void windowDeiconified (WindowEvent e) {}    
  
	@Override
	public void windowIconified(WindowEvent e) {}  
  
	@Override
	public void windowOpened(WindowEvent e) {}    

	public boolean isClosing()
	{
		return windowClosing;
	}
}