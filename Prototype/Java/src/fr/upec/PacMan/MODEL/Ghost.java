package fr.upec.PacMan.MODEL;
import java.awt.*;

public class Ghost extends Actor
{
	public Ghost(Vector2 pos,Color c)
	{
		super(Constant.FANTOME_ID,pos,c);	
	}

    @Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
	}
}
