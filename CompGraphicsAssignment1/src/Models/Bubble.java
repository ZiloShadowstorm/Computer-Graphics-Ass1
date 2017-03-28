package src.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class Bubble implements Displayable
{
	private float x;
	private float y;
	private float dy;	
	private float radius;
	
	private float[] outerColour = {0.522f, 0.8667f, 1};
	
	public Bubble(float x, float y)
	{
		this(x, y, 0.025f, 0.05f);
	}
	
	public Bubble(float x, float y, float dy, float radius)
	{
		this.x = x;
		this.y = y;
		this.dy = dy;
		this.radius = radius;
	}

	@Override
	public void display(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glColor3f(1, 1, 1);//white
			gl.glVertex2f(x, y);
			gl.glColor3fv(outerColour, 0);
			for(int i = 0; i <= 300; i++)
		 	{
		 		double theta = (2.0f * Math.PI * i / 300);
		 		gl.glVertex2d(x + Math.sin(theta) * (radius), y + Math.cos(theta) * (radius));
		 	}
		gl.glEnd();
		
		y += dy;
	}
	
	public float getY()
	{
		return y;
	}

	public void setY(float f) 
	{
		y = f;
	}
	
	
}
