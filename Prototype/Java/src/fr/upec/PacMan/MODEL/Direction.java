package fr.upec.PacMan.MODEL;

/*
 * Nom de classe : Direction
 *
 * Description   : enum de Direction
 */

 public enum Direction 
 {
    NORTH ("NORTH"),
    EAST ("EAST"),
    SOUTH ("SOUTH"),
    WEST ("WEST");

	private final String name;

	private Direction(String n)
	{
		this.name = n;
	}

	private static final int DTOP[][] = { {0,-1}, {1,0}, {0,1}, {-1,0} };

    public Direction getOppositeDirection() 
	{
        return Direction.values()[(this.ordinal()+2)%4];
    }

	public Vector2 getPosDirection()
	{		
		return new Vector2(DTOP[this.ordinal()]);
	}

	@Override
	public String toString()
	{
		return this.name;
	}
}