package src;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;


import com.jogamp.opengl.util.FPSAnimator;

import src.Models.BubbleSystem;
import src.Models.Button;
import src.Models.Darkness;
import src.Models.Plankton;

/**
 * This class handles the events of the programe.
 * It handles the window events and mouse events for the buttons.
 * I would have liked to get it to handle the key events too for the 
 * movement of the fish, but I didn't get it working and ran out of time.
 * This is to stay in line with the MVC style I was trying to use.
 * @author Davy
 *
 */
public class EventHandler implements WindowListener, MouseListener
{

	//ATTRIBUTES
	private FPSAnimator animator;
	private ArrayList<Displayable> gameObjects; //not sure yet if I need all the objects or just the fish

	//CONSTRUCTOR
	public EventHandler(FPSAnimator animator, ArrayList<Displayable> gameObjects)
	{
		this.animator = animator;
		this.gameObjects = gameObjects;
		animator.start();
	}

	

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Defined to capture the button clicks.
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{

		int mouseX = e.getX();
		int mouseY = e.getY();
		
		//If left click
		if (e.getButton() == 1)
		{
			//invert mouseY
			mouseY = Viewer.winHeight - mouseY;
			
			//scale for OpenGL
			double openglX = 2.0 * ((double)mouseX / Viewer.winWidth) - 1.0;
			double openglY = 2.0 * ((double)mouseY / Viewer.winHeight) - 1.0;
			Button clickedButton = getButtonClicked(openglX, openglY);

			//if button found
			if(clickedButton != null)
			{
				//if already pressed
				if(clickedButton.getPressed())
				{
					clickedButton.setPressed(false);
					stopAction(clickedButton.toString());
				}
				else //it is being pressed
				{
					clickedButton.setPressed(true);
					startAction(clickedButton.toString());
				}
			}
		}
		
	}
	
	/**
	 * A method that acts as an intermediary between a button being
	 * clicked and the action it is supposed to do.
	 * Which button was clicked is defined by a string passed in 
	 * as an argument.
	 * @param string defines which button was pressed.
	 */
	private void startAction(String string)
	{
		if(string == "Time")
		{
			Darkness.setActivated(true);
		}
		else if(string == "Plankton")
		{
			Plankton.setActivated(true);
		}
		else if(string == "Bubbles")
		{
			BubbleSystem.setActivated(true);
		}
		
	}

	/**
	 * A method that acts as an intermediary between a button being
	 * clicked and the action it is supposed to stop doing.
	 * Which button was clicked is defined by a string passed in 
	 * as an argument.
	 * @param string defines which button was pressed.
	 */
	private void stopAction(String string)
	{
		if(string == "Time")
		{
			Darkness.setActivated(false);
		}
		else if (string == "Plankton")
		{
			Plankton.setActivated(false);
		}
		else if (string == "Bubbles")
		{
			BubbleSystem.setActivated(false);
		}

	}

	/**
	 * This method will be called every time a mouse click event is fired.
	 * It will check if the mouse has been clicked over a button.
	 * @param x x position of the mouse.
	 * @param y y position of the mouse
	 * @return the button that has been clicked or null.
	 */
	public Button getButtonClicked(double x, double y)
	{
		Button found = null;
		
		for(Object o : gameObjects)
		{
			if (o instanceof Button)
			{
				Button button = (Button) o;
				
				if(x > button.getX() && x < button.getX() + button.getLength())
				{
					if(y > button.getY() && y < button.getY() + button.getWidth())
					{
						found = button;
					}
				}
			}
		}
		
		return found;
	}

	//UNUSED MOUSE LISTENERS:
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	//WINDOW STUFF:
	
	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		new Thread(new Runnable() {

            public void run() {
                animator.stop();
                System.out.println(Viewer.class + " has been closed.");
                System.exit(0);
            }
        }).start();
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
