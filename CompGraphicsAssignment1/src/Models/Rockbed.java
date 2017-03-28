package src.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class Rockbed implements Displayable
{
	Point[] jaggedEdge = new Point[]
			{
					new Point(-1, -0.8f),
					new Point(-0.8f, -0.78f),
					new Point(-0.75f, -0.82f),
					new Point(-0.72f, -0.79f),
					new Point(-0.5f, -0.835f),
					new Point(-0.3f, -0.78f),
					new Point(0, -0.8f),
					new Point(0.2f, -0.7f),
					new Point(0.6f, -0.75f),
					new Point(0.75f, -0.77f),
					new Point(0.9f, -0.75f),
					new Point(1, -0.8f)
			};

	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glColor3f(0.5f, 0.5f, 0.5f);
		
		gl.glBegin(GL2.GL_QUAD_STRIP);
			for (int i = 0; i < jaggedEdge.length; i++)
			{
				gl.glVertex2f(jaggedEdge[i].getX(), jaggedEdge[i].getY());
				gl.glVertex2f(jaggedEdge[i].getX(), -1);
			}
		gl.glEnd();
		
	}

}
