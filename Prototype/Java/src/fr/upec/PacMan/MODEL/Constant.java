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
	public static final float BASE_SPEED = 0.2f;
	
	public static final Color PACMAN_COLOR = new Color(255,255,0);
	public static final Color[] GHOST_COLOR = { Color.red,Color.cyan,Color.orange,Color.pink};
	public static final Direction[] allDirection = {Direction.NORTH,Direction.SOUTH,Direction.WEST,Direction.EAST};
}