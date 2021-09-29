package fr.upec.PacMan.VUE;
import fr.upec.PacMan.MODEL.*;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame 
{
	public Window()
	{
		this.setSize(Constant.LARGEUR_BASE_FENETRE, Constant.HAUTEUR_BASE_FENETRE);
	    this.setLocation(100, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setMinimumSize(new Dimension(Constant.LARGEUR_MINIMUM_FENETRE, Constant.HAUTEUR_MINIMUM_FENETRE));
	    //GameVue vuePrincipale = new GameVue(this);
	    //this.add(vuePrincipale,BorderLayout.CENTER);
	}
}