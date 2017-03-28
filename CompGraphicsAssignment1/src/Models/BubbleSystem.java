package src.Models;

import java.util.ArrayList;
import java.util.Random;

import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class BubbleSystem implements Displayable
{
	private static boolean activated = false;
	
	private ArrayList<Bubble> bubbles = new ArrayList<>();
	private ArrayList<Bubble> deadBubbles = new ArrayList<>();
	
	private Random rnd = new Random();
	private float x = -0.6f;
	private float y = -1;
	
	
	private final int numBubbles = 10;
	
	public BubbleSystem()
	{
		for (int i = 0; i < numBubbles; i++)
		{
			float xAdjustment = rnd.nextFloat() / 10;
			float speedAdjustment = rnd.nextFloat() / 200;
			float sizeAdjustment = rnd.nextFloat() / 100;
			bubbles.add(new Bubble(x + xAdjustment, y, 0.01f + speedAdjustment, 0.025f + sizeAdjustment));
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
					deadBubbles.add(b);
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
	
	
}
