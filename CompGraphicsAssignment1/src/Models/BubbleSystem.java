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
			bubbles.add(new Bubble(x + xAdjustment, y - xAdjustment, speed, size));
		}
	}
	
	public void addBubbles(Bubble b)
	{
		bubbles.add(b);
	}
	
	public void addNewBubble(ArrayList<Bubble> bubbles)
	{
		float xAdjustment = rnd.nextFloat() * 0.3f;
		float speed = rnd.nextFloat() / 200 + 0.001f;
		float size = speed * 10;
		bubbles.add(new Bubble(x + xAdjustment, y - xAdjustment, speed, size));
	}
	
	@Override
	public void display(GLAutoDrawable drawable) 
	{
		if (activated) 
		{

			for (Bubble b : bubbles) 
			{
				b.display(drawable);
				
				if (b.getTransparency() < 0) 
				{
					b.setY(-1);
					b.setTransparency(1);
					deadBubbles.add(b);	
				}
			} 
			
			for (int i = 0; i < deadBubbles.size(); i++) 
			{
				addNewBubble(bubbles);
			}
			bubbles.removeAll(deadBubbles);
			deadBubbles.clear();
		}
		else //reset
		{
			bubbles.addAll(deadBubbles);
			deadBubbles.clear();
			resetBubbles();
		}
	}
	
	public static void setActivated(Boolean b)
	{
		activated = b;	
	}
	
	private void resetBubbles()
	{
		for(Bubble b : bubbles)
		{
			b.setY(-1);
		}
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
	
	public boolean getActivated()
	{
		return activated;
	}
}
