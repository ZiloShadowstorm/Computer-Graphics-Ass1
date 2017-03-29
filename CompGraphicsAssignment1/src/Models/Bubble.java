package src.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class Bubble implements Displayable
{
	private float transparency = 1;
	
	private float x;
	private float y;
	private float dy;	
	private float radius;

	
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
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		float[] outerColour = {0.522f, 0.8667f, 1, transparency};
		
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glColor4f(1, 1, 1, transparency);//white
			gl.glVertex2f(x, y);
			gl.glColor4fv(outerColour, 0);
			for(int i = 0; i <= 300; i++)
		 	{
		 		double theta = (2.0f * Math.PI * i / 300);
		 		gl.glVertex2d(x + Math.sin(theta) * (radius), y + Math.cos(theta) * (radius));
		 	}
		gl.glEnd();
		gl.glDisable(GL2.GL_BLEND);
		
		y += dy;
		transparency -= 0.0035f;
		
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getX()
	{
		return x;
	}

	public void setY(float f) 
	{
		y = f;
	}
	
	public float getTransparency()
	{
		return transparency;
	}
	
	public void setTransparency(float f)
	{
		transparency = f;
	}
	
}
