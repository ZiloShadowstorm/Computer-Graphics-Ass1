package src.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class Darkness implements Displayable
{
	private static float alpha = 0f;
	private static boolean activated = false;
	
	private float[] black = {0, 0, 0, alpha};	
	private boolean darkening = true;
	private float speed = 0.001f;

	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		if (activated)
		{						
			gl.glColor4fv(black, 0);
			gl.glBegin(GL2.GL_QUADS);
				gl.glVertex2f(-1, -1);
				gl.glVertex2f(-1, 1);
				gl.glVertex2f(1, 1);
				gl.glVertex2f(1, -1);
			gl.glEnd();
			
			if (darkening)
			{
				alpha += speed;
				black[3] = alpha;
				if(alpha > 1f)
				{
					darkening = false;
				}
			}
			else
			{
				alpha -= speed;
				black[3] = alpha;
				if(alpha < 0f)
				{
					darkening = true;
				}
			}
			
		}
		gl.glDisable(GL2.GL_BLEND);
	}
	
	public static void setActivated(boolean b)
	{
		activated = b;
	}
	
	public static boolean getActivated()
	{
		return activated;
	}

	public static float getAlpha()
	{
		return alpha;
	}
}
