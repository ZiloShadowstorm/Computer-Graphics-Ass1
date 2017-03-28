package src.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class FishFin extends Fish implements Displayable
{
	private float xModifier = 3.5f;
	private float yModifier = 4;
	
	boolean fanningIN = true;

	@Override
	public void display(GLAutoDrawable drawable)
	{
		if (!full) 
		{
			GL2 gl = drawable.getGL().getGL2();
			gl.glColor3fv(darkOrange, 0);
			gl.glLineWidth(1);
			gl.glBegin(GL2.GL_LINE_STRIP);
			for (int i = 0; i <= 300; i++) {
				if (i <= 25 || i >= 125) {

					double theta = (2.0f * Math.PI * i / 300);
					gl.glVertex2d(getX() + Math.sin(theta) * (bodyRadius / xModifier),
							y + Math.cos(theta) * (bodyRadius / yModifier));
				}
				if (i == 26) {
					gl.glEnd();
					gl.glBegin(GL2.GL_LINE_STRIP);
				}
			}
			gl.glEnd();
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2f(getX(), y);
			double theta = (2.0f * Math.PI * 225 / 300);
			gl.glVertex2d(x + Math.sin(theta) * (bodyRadius / xModifier - 0.01),
					y + Math.cos(theta) * (bodyRadius / yModifier));
			gl.glVertex2f(getX(), y + 0.01f);
			theta = (2.0f * Math.PI * 240 / 300);
			gl.glVertex2d(getX() + Math.sin(theta) * (bodyRadius / xModifier - 0.01),
					y + Math.cos(theta) * (bodyRadius / yModifier));
			gl.glVertex2f(getX(), y - 0.01f);
			theta = (2.0f * Math.PI * 210 / 300);
			gl.glVertex2d(getX() + Math.sin(theta) * (bodyRadius / xModifier - 0.01),
					y + Math.cos(theta) * (bodyRadius / yModifier));
			gl.glEnd();
			if (fanningIN) {
				if (xModifier < 6) {
					xModifier += 0.05f;
				} else {
					fanningIN = false;
				}
			} else {
				if (xModifier > 3.5) {
					xModifier -= 0.05f;
				} else {
					fanningIN = true;
				}
			} 
		}
		
	}
}
