package src.Models;

import java.util.Random;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class Seaweed implements Displayable
{
	//ATTRIBUTES
	private float x = 0;
	private float y = 0;
	private float radius;
	private float[] outerColor = {0, 0, 0};
	private float[] innerColor = {0, 0, 0};
	private final Random rnd = new Random();
	private float[] displacements = new float[301];
	
	//CONSTRUCTORS
	public Seaweed(float x, float y, float radius, float[] outerColor, float[] innerColor)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.outerColor = outerColor;
		this.innerColor = innerColor;
		
		for (int i = 0; i <= 300; i++)
		{
			displacements[i] = rnd.nextFloat()/30;
		}
	}
	
	//METHODS
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glPointSize(3);
		 
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		 	gl.glColor3f(innerColor[0], innerColor[1], innerColor[2]);
		 	gl.glVertex2f(x, y);
		 	gl.glColor3f(outerColor[0], outerColor[1], outerColor[2]);
		 	for(int i = 0; i <= 300; i++)
		 	{
		 		double theta = (2.0f * Math.PI * i / 300);
		 		gl.glVertex2d(x + Math.sin(theta) * (radius + displacements[i]), y + Math.cos(theta) * (radius + displacements[i]));
		 	}
	 	gl.glEnd();
		
	}
}
