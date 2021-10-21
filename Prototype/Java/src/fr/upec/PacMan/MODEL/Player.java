package fr.upec.PacMan.MODEL;
import javax.swing.*;
import java.awt.*;

public class Player extends Actor
{
	public Player(Vector2 pos)
	{
		super(Constant.PLAYER_ID,pos,new Color(255,255,0));		
	}

	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
	}
}