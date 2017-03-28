package src.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class FishEye extends Fish implements Displayable
{
	private float outerEyeRadius = 0.0475f;
	private float pupilRadius = 0.03f;
	private float pupilShineRadius = 0.014f;
	
	private float deltaX = bodyRadius / 2;
	private float deltaY = bodyRadius / 2.2f;
	
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		
		//update:
		deltaX = bodyRadius / 2;
		deltaY = bodyRadius / 2.2f;
		
		//EYE
				gl.glBegin(GL2.GL_TRIANGLE_FAN);	
					gl.glColor3f(1, 1, 1);
					gl.glVertex2f(x + deltaX, y + deltaY);
					for(int i = 0; i <= 300; i++)
				 	{
				 		double theta = (2.0f * Math.PI * i / 300);
				 		gl.glVertex2d(x + deltaX + Math.sin(theta) * (outerEyeRadius), y + deltaY + Math.cos(theta) * (outerEyeRadius));
				 	}
			 	gl.glEnd();
				gl.glBegin(GL2.GL_TRIANGLE_FAN);	
					gl.glColor3f(0, 0, 0);
					gl.glVertex2f(x + deltaX + 0.01f, y + deltaY + 0.01f);
					for(int i = 0; i <= 300; i++)
				 	{
				 		double theta = (2.0f * Math.PI * i / 300);
				 		gl.glVertex2d(x + deltaX + 0.01f + Math.sin(theta) * (pupilRadius), y + deltaY + 0.01f + Math.cos(theta) * (pupilRadius));
				 	}
			 	gl.glEnd();
			 	gl.glBegin(GL2.GL_TRIANGLE_FAN);	
					gl.glColor3f(1, 1, 1);
					gl.glVertex2f(x + deltaX + 0.02f, y + deltaY + 0.02f);
					for(int i = 0; i <= 300; i++)
				 	{
				 		double theta = (2.0f * Math.PI * i / 300);
				 		gl.glVertex2d(x + deltaX + 0.02f + Math.sin(theta) * (pupilShineRadius), y + deltaY + 0.02 + Math.cos(theta) * (pupilShineRadius));
				 	}
			 	gl.glEnd();
		
	}
}
