package fr.upec.PacMan.MODEL;
import java.awt.*;
/*
 * Nom de classe : Constant
 *
 * Description   : Les constantes du projet
 */

public class Constant 
{
	public static final byte NULL_ID = 0;
	public static final byte WALL_ID = 1;
	public static final byte WALL_SPEC_ID = 2;
	public static final byte PLAYER_ID = 3;
	public static final byte GHOST_ID = 4;
	public static final byte GOME_ID = 5;
	public static final byte PAC_GOME_VERT_ID = 6;
	public static final byte PAC_GOME_VIOLET_ID = 7;
	public static final byte PAC_GOME_ORANGE_ID = 8;


	public static final int HAUTEUR_BASE_FENETRE = 800;
	public static final int LARGEUR_BASE_FENETRE = 800;
	public static final int HAUTEUR_MINIMUM_FENETRE = 400;
	public static final int LARGEUR_MINIMUM_FENETRE = 400;
	public static final float FRAME_PAR_SECONDE = 1/60;

	public static final int PACMAN_BASE_LIFE = 3;
	public static final int ADDITIONAL_LIFE = 5000;
	public static final int[] PAC_GOME_SCORE = { 100,1000,300,500};
	public static final float BASE_SPEED = 0.2f;
	
	public static final Color PACMAN_COLOR = Color.yellow;
	public static final Color GHOST_COLOR = Color.red;
	public static final Color[] PAC_GOME_COLOR = { new Color(51,153,255),Color.green,new Color(102,0,153),Color.orange};
	public static final Color CARTE_COLOR = new Color(20,20,220);

	public static final Direction[] allDirection = {Direction.NORTH,Direction.SOUTH,Direction.WEST,Direction.EAST};

	public static final int STATE_ACTION_NORMAL = 0;
	public static final int STATE_ACTION_INVISIBLE = 1;
	public static final int STATE_ACTION_SUPER = 2;
	public static final int STATE_ACTION_LABYRINTHE = 3;
}