package src.Models;

import java.util.ArrayList;
import java.util.Random;

import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class BubbleSystem implements Displayable
{
	private static boolean activated = false;
	
	private final int numBubbles = 20;
	
	private ArrayList<Bubble> bubbles = new ArrayList<>();
	private ArrayList<Bubble> deadBubbles = new ArrayList<>();
	
	private Random rnd = new Random();
	private float x = -0.6f;
	private float y = -1;
	

	
	
	public BubbleSystem()
	{
		for (int i = 0; i < numBubbles; i++)
		{
			float xAdjustment = rnd.nextFloat() * 0.3f;
			float speed = rnd.nextFloat() / 200 + 0.001f;
			float size = speed * 10;
			bubbles.add(new Bubble(x + xAdjustment, y, speed, size));
		}
	}
	
	public void addBubbles(Bubble b)
	{
		bubbles.add(b);
	}
	
	@Override
	public void display(GLAutoDrawable drawable) 
	{
		if (activated) 
		{

			for (Bubble b : bubbles) 
			{
				b.display(drawable);
				
				if (b.getY() > 0.7f) 
				{
					b.setY(-1);
				}
			} 
			
			bubbles.removeAll(deadBubbles);
		}
		else //reset
		{
			bubbles.addAll(deadBubbles);
			deadBubbles.clear();
		}
	}
	
	public static void setActivated(Boolean b)
	{
		activated = b;	
	}
	
	public Bubble getBubble(Bubble b)
	{
		int found = bubbles.indexOf(b);
		
		if(found > -1)
		{
			Bubble foundBubble = bubbles.get(found);
			deadBubbles.add(foundBubble);
			return foundBubble;
		}
		
		return null;
	}
	
	public void removeDeadBubbles()
	{
		bubbles.removeAll(deadBubbles);
	}
	
	public ArrayList<Bubble> getBubbles()
	{
		return bubbles;
	}
}
