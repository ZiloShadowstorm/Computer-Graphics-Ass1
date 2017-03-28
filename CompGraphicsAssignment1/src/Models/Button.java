package src.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;

import src.Displayable;

public class Button implements Displayable
{
	private float x;
	private float y;
	private String name;
	private float[] colour1 = {0.005f, 0.4f, 1f};
	private float[] colour2 = {0.486f, 0.878f, 1f};
	private float[] white = {1, 1, 1};
	private float[] black = {0, 0, 0};
	private boolean pressed = false;
	
	private float width = 0.12f;
	private float length = 0.3f;
	
	private float boarderWidth = 0.01f;
	
	public Button(float x, float y, String name)
	{
		this.x = x;
		this.y = y;
		this.name = name;
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		
		//Outer Border
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3fv(colour1, 0);
			gl.glVertex2f(x, y);
			gl.glVertex2f(x, y + width);
			gl.glColor3fv(colour2, 0);
			gl.glVertex2f(x + length, y + width);
			gl.glVertex2f(x + length, y);
		gl.glEnd();
		
		//White Border
		gl.glBegin(GL2.GL_QUADS);
			if (!pressed)
			{
				gl.glColor3fv(white, 0);
			}
			else
			{
				gl.glColor3fv(black, 0);
			}
			gl.glVertex2f(x + boarderWidth, y + boarderWidth);
			gl.glVertex2f(x + boarderWidth, y + width - boarderWidth);
			gl.glVertex2f(x + (length - boarderWidth), y + width - boarderWidth);
			gl.glVertex2f(x + (length - boarderWidth), y + boarderWidth);
		gl.glEnd();
		
		//Inner Box
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3fv(colour2, 0);
			gl.glVertex2f(x + boarderWidth*2, y + boarderWidth*2);
			gl.glVertex2f(x + boarderWidth*2, y + width - boarderWidth*2);
			gl.glColor3fv(colour1, 0);
			gl.glVertex2f(x + (length - boarderWidth*2), y + width - boarderWidth*2);
			gl.glVertex2f(x + (length - boarderWidth*2), y + boarderWidth*2);
		gl.glEnd();
		
		GLUT glut = new GLUT();
		gl.glColor3fv(white, 0);
		gl.glRasterPos2d(x + boarderWidth*4.5, y + boarderWidth*4.5);
		glut.glutBitmapString(GLUT.BITMAP_8_BY_13, name);
		
	}
	
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getLength()
	{
		return length;
	}
	
	public Boolean getPressed()
	{
		return pressed;
	}
	
	public void setPressed(Boolean b)
	{
		pressed = b;
	}
	
	/**
	 * for testing purposes
	 */
	public String toString()
	{
		return name;
	}
}
