package fr.upec.PacMan.MODEL;
import java.lang.Math;

/*
 * Nom de classe : Vector2
 *
 * Description   : Pour manipuler les objects dans un environement 2D
 */

public class Vector2
{
	public float x;
    public float y;
       
    public Vector2() 
	{
        this.x = 0.0f;
        this.y = 0.0f;
    }

	public Vector2(int tab[2]) 
	{
        this.x = tab[0];
        this.y = tab[1];
    }
       
    public Vector2(float x, float y) 
	{
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector2 other)
	{
        return (this.x == other.x && this.y == other.y);
    }

	public float Distance(Vector2 other)
	{
		float dx = x - other.x;
        float dy = y - other.y;
        return (float)Math.sqrt(dx * dx + dy * dy);
	}
}