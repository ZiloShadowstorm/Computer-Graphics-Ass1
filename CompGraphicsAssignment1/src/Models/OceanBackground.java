package src.Models;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class OceanBackground implements Displayable
{
	private WaterLine waterline;
	private float waterColor[] = new float[]{0.6f, 0.9f, 0.95f, 1f};
	private float waterColor2[] = new float[]{0.7f, 0.95f, 1f, 1f};
	
	public OceanBackground(WaterLine waterline)
	{
		this.waterline = waterline;
	}
	
	
//	public void display(GLAutoDrawable drawable)
//	{
//		
//	}
	/**
	 * This method gets all the useful points from the waterline
	 * that is designed to be used in a quad strip to fill everything
	 * below the waterline. The useful points are all the even points
	 * (including zero) plus the last the point, which will most often
	 * be an odd number.
	 * @param points an array of points from the waterline
	 * @return an array of useful points to help fill below the waterline.
	 */
	private Point[] getUsefulPoints(Point[] points)
	{
		//I'll use an array list initially as it's easier to add to
		//because i will not always be relative.
		ArrayList<Point> list = new ArrayList<>();
		for (int i = 0; i < points.length; i++)
		{
			if (i % 2 == 0 || i == points.length - 1)
			{
				list.add(points[i]);
			}
		}
		
		//convert array list to point array
		Point[] returnPoints = new Point[list.size()];
		for (int j = 0; j < list.size(); j++)
		{
			returnPoints[j] = list.get(j);
		}
		
		return returnPoints;
	}


	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		
		
		Point[] waterLinePoints = waterline.getCurrentPositions();
		Point[] usefulPoints = getUsefulPoints(waterLinePoints);
		
		gl.glBegin(GL2.GL_QUAD_STRIP);
			for (int i =0; i < usefulPoints.length; i++)
			{
				gl.glColor4fv(waterColor2, 0);
				gl.glVertex2f(usefulPoints[i].getX(), usefulPoints[i].getY());
				gl.glColor4fv(waterColor, 0);
				gl.glVertex2f(usefulPoints[i].getX(), -1);
			}
		gl.glEnd();
		
	}
}
