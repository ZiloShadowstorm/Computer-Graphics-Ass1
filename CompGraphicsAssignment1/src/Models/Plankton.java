package src.Models;

import java.util.Random;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class Plankton implements Displayable
{
	private static boolean activated = false;
	
	private float[] colour = {0.1255f, 1f, 0.0078f};
	private Random rnd = new Random();
	
	private float[] positions = new float[100];
	
	//CONSTRUCTOR
	public Plankton()
	{
		for(int i = 0; i < 100; i += 2)
		{
			positions[i] = rnd.nextFloat() * 2 - 1;
			positions[i + 1] = rnd.nextFloat() * 0.5f + 0.2f; // this will give range of 0.2 - 0.7
		}
	}
	
	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glColor3fv(colour, 0);
		gl.glPointSize(1);
		
		if (Darkness.getAlpha() > 0.7f && Darkness.getActivated() && activated)
		{
			gl.glBegin(GL2.GL_POINTS);
			for (int i = 0; i < 100; i += 2)
			{
				float x = positions[i];
				float y = positions[i + 1];
				gl.glVertex2f(x, y);
			}
			gl.glEnd();
		}
	}
	
	public static void setActivated(boolean b)
	{
		activated = b;
	}
	
	public static boolean getActivated()
	{
		return activated;
	}

}
