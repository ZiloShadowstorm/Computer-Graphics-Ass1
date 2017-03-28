package src.Models;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import src.Displayable;

public class WaterLine implements Displayable
{
	private Point[] startPositions = new Point[] 
			{
					new Point(-1, 0.75f),
					new Point(-0.75f, 0.725f),
					new Point(-0.750f, 0.725f),
					new Point(-0.500f, 0.7f),
					new Point(-0.500f, 0.7f),
					new Point(-0.250f, 0.730f),
					new Point(-0.250f, 0.730f),
					new Point(0.000f, 0.780f),
					new Point(0.000f, 0.780f),
					new Point(0.250f, 0.800f),
					new Point(0.250f, 0.800f),
					new Point(0.500f, 0.780f),
					new Point(0.500f, 0.780f),
					new Point(0.750f, 0.760f),
					new Point(0.750f, 0.760f),
					new Point(1.000f, 0.750f),
					
			};
	private Point[] currentPositions = new Point[]
			{
					new Point(-1, 0.75f),
					new Point(-0.75f, 0.7f),
					new Point(-0.750f, 0.7f),
					new Point(-0.500f, 0.718f),
					new Point(-0.500f, 0.718f),
					new Point(-0.250f, 0.730f),
					new Point(-0.250f, 0.730f),
					new Point(0.000f, 0.780f),
					new Point(0.000f, 0.780f),
					new Point(0.250f, 0.800f),
					new Point(0.250f, 0.800f),
					new Point(0.500f, 0.780f),
					new Point(0.500f, 0.780f),
					new Point(0.750f, 0.760f),
					new Point(0.750f, 0.760f),
					new Point(1.000f, 0.750f),
			};
	
	private Point[] targetPositions = new Point[]
			{
				new Point(-1.000f, 0.750f),
				new Point(-0.750f, 0.775f),
				new Point(-0.750f, 0.775f),
				new Point(-0.500f, 0.78f),
				new Point(-0.500f, 0.78f),
				new Point(-0.250f, 0.770f),
				new Point(-0.250f, 0.770f),
				new Point(0.000f, 0.720f),
				new Point(0.000f, 0.720f),
				new Point(0.250f, 0.700f),
				new Point(0.250f, 0.700f),
				new Point(0.500f, 0.720f),
				new Point(0.500f, 0.720f),
				new Point(0.750f, 0.740f),
				new Point(0.750f, 0.740f),
				new Point(1.000f, 0.750f),
				
			};
	
	public WaterLine()
	{
		
		
	}
	
	void update()
	{		
		
		for(int i = 0; i < currentPositions.length; i++)
		{
			if (currentPositions[i].getY() > targetPositions[i].getY())
			{
				currentPositions[i].setY(currentPositions[i].getY() - 0.001f);
			}
			else if (currentPositions[i].getY() < targetPositions[i].getY())
			{
				currentPositions[i].setY(currentPositions[i].getY() + 0.001f);
			}
			//testing:
//			else if (currentPositions.equals(targetPositions))
//			{
//				System.out.println("Position " + i + "is at target!");
//			}
			
			if (closeEnough(currentPositions, targetPositions))
			{
				Point[] temp = startPositions;
				startPositions = targetPositions;
				targetPositions = temp;
			}
		}
		
	}
	
	public void display(GLAutoDrawable drawable)
	{
		update(); //TODO move this outside
		
		GL2 gl = drawable.getGL().getGL2();
		gl.glPointSize(3); //TODO move outside
		
		//Colour
		gl.glColor3f(0.3f, 0.75f, 1);
		gl.glLineWidth(2);
		
		gl.glBegin(GL2.GL_LINES); 	//TODO move outside
			for (Point point : currentPositions)
			{
				gl.glVertex2d(point.getX(), point.getY());
			}
		gl.glEnd();
		//gl.glFlush(); //TODO move outside
	}
	
	public boolean closeEnough(Point[] pointsA, Point[]  pointsB)
	{
		if(pointsA.length != pointsB.length)
		{
			return false;
		}
		
		float aX, aY, bX, bY, deltaX, deltaY;
		for (int i = 0; i < pointsA.length; i++)
		{
			aX = pointsA[i].getX();
			aY = pointsA[i].getY();
			bX = pointsB[i].getX();
			bY = pointsB[i].getY();
			deltaX = aX - bX;
			deltaY = aY - bY;
			
			if (deltaX <= -0.001 || deltaX >= 0.001)
			{
				return false;
			}
			
			if (deltaY <= -0.001 || deltaY >= 0.001)
			{
				return false;
			}
		}
		return true;
	}
	
	public Point[] getCurrentPositions()
	{
		return currentPositions;
	}
	
}
