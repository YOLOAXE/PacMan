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

	public Vector2(int tab[]) 
	{
        this.x = tab[0];
        this.y = tab[1];
    }
       
    public Vector2(float x, float y) 
	{
        this.x = x;
        this.y = y;
    }

	public int getIntX()
	{
		return (int)this.x;
	}

	public int getIntY()
	{
		return (int)this.y;
	}

    public boolean equals(Vector2 other)
	{
        return (this.x == other.x && this.y == other.y);
    }

	public boolean equalsInt(Vector2 other)
	{
        return (this.getIntX() == other.getIntX() && this.getIntY() == other.getIntY());
    }

	public float Distance(Vector2 other)
	{
		float dx = x - other.x;
        float dy = y - other.y;
        return (float)Math.sqrt(dx * dx + dy * dy);
	}

	public Vector2 addition(Vector2 other)
	{
		Vector2 result = new Vector2(other.x+x,other.y+y);
		return result;
	}

	public Vector2 additionModuloDeuxDirection(Vector2 other,Vector2 modulo)
	{
		Vector2 result = new Vector2((other.x+x)%modulo.getIntX(),(other.y+y)%modulo.getIntY());
		if(result.getIntX() < 0)
		{
			result.x = modulo.getIntX() + result.getIntX();
		}
		if(result.getIntY() < 0)
		{
			result.y = modulo.getIntY() + result.getIntY();
		}
		return result;
	}

	public Vector2 soustraction(Vector2 other)
	{
		Vector2 result = new Vector2(x-other.x,y-other.y);
		return result;
	}

	public Vector2 multiplication(float f)
	{
		Vector2 result = new Vector2(x*f,y*f);
		return result;
	}

	public static Vector2 Lerp(Vector2 start, Vector2 end, float percent)
	{
		 return end.soustraction(start).multiplication(percent).addition(start);
	}

	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}