package fr.upec.PacMan.MODEL;

/*
 * Nom de classe : Direction
 *
 * Description   : enum de Direction
 */

 public enum Direction 
 {
    NORTH,
    EAST,
    SOUTH,
    WEST;

	private static final int DTOP[4][2] = { {0,-1}, {1,0}, {0,1}, {-1,0} };

    public Direction getOppositeDirection() 
	{
        return Direction.values()[(this.ordinal()+2)%4];
    }

	public Vector2 getPosDirection()
	{
		
		return new Vector2(DTOP[this.ordinal()])
	}
}