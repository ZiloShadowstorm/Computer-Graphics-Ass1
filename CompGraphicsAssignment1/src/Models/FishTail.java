package src.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class FishTail extends Fish implements Displayable
{

	@Override
	public void display(GLAutoDrawable drawable)
	{
		
		if (!full) 
		{
			GL2 gl = drawable.getGL().getGL2();
			gl.glLineWidth(1);
			gl.glBegin(GL2.GL_QUAD_STRIP);
			gl.glColor3fv(fishColor, 0);
			for (int i = 215; i <= 235; i += 5) {
				double theta = (2.0f * Math.PI * i / 300);
				gl.glVertex2d(x + Math.sin(theta) * (bodyRadius), y + Math.cos(theta) * (bodyRadius));
				gl.glVertex2d(x + Math.sin(theta) * (bodyRadius + 0.05f), y + Math.cos(theta) * (bodyRadius + 0.05f));
			}
			gl.glEnd();
			gl.glBegin(GL2.GL_LINES);
			gl.glLineWidth(5);
			gl.glColor3fv(darkOrange, 0);
			for (int i = 215; i <= 235; i += 5) {
				double theta = (2.0f * Math.PI * i / 300);
				gl.glVertex2d(x + Math.sin(theta) * (bodyRadius), y + Math.cos(theta) * (bodyRadius));
				gl.glVertex2d(x + Math.sin(theta) * (bodyRadius + 0.05f), y + Math.cos(theta) * (bodyRadius + 0.05f));

				if (i > 215) {
					gl.glVertex2d(x + Math.sin(theta) * (bodyRadius + 0.05f),
							y + Math.cos(theta) * (bodyRadius + 0.05f));
					double oldTheta = (2.0f * Math.PI * (i - 5) / 300);
					gl.glVertex2d(x + Math.sin(oldTheta) * (bodyRadius + 0.05f),
							y + Math.cos(oldTheta) * (bodyRadius + 0.05f));
				}
			}
			gl.glEnd();
		}
		
		
		
	}
}
