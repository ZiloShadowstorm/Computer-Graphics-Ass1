package src;

import java.awt.Frame;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import src.Models.Bubble;
import src.Models.BubbleSystem;
import src.Models.Fish;

/**
 * The Viewer handles two things:
 * The displaying of the game objects, (including the frame needed)
 * and the collision detection between fish and bubbles.
 * The constructor does also create the EventHandler which
 * handles most user inputs.
 * @author Davy
 *
 */
public class Viewer implements GLEventListener
{
	public static int winWidth = 640;
	public static int winHeight = 640;
	
	private Frame frame;
	private final FPSAnimator animator;
	private ArrayList<Displayable> gameObjects;
	
	
	//CONSTRUCTOR
	public Viewer(ArrayList<Displayable> gameObjects)
	{
		this.gameObjects = gameObjects;
		
		//frame
		frame = new Frame("Fish Game");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas  canvas = new GLCanvas(capabilities);
        frame.add(canvas);
        canvas.addGLEventListener(this);
        frame.setSize(winWidth, winHeight);
        
        //Listeners and Animators
        canvas.addKeyListener(getFish());       
        animator = new FPSAnimator(canvas, 60);
        EventHandler eventHandler = new EventHandler(animator, gameObjects);
        frame.addWindowListener(eventHandler);
        canvas.addMouseListener(eventHandler);
       
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas.requestFocusInWindow();
       
	}
	
	/**
	 * Streams all the game objects and displays them one by one.
	 * Then it checks for bubble collision.
	 */
	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL2.GL_LINE_SMOOTH);

		gameObjects.stream().forEach((gameObject) -> gameObject.display(drawable));

		gl.glFlush();
		
		//DO SOME PHYSICS LOGIC TODO: move this to work on it's own, but then I'd need to make a game loop.
		checkBubbleCollision();
		
	}
	
	/**
	 * If bubbles has been activated then this method will
	 * go through each bubble and check if it's in range of 
	 * the fish. Range, or collision, is considered when the 
	 * center of the bubble and the center of the fish
	 * is equal to or less than the body radius of the fish.
	 * Caught bubbles will be given to the fish. The fish 
	 * will return the bubbles to the BubbleSystem when it explodes.
	 */
	public void checkBubbleCollision()
	{
		BubbleSystem bSys = getBubbleSystem(gameObjects);
		Fish fish = getFish();
		
		if (bSys.getActivated()) 
		{
			for (Bubble b : bSys.getBubbles()) 
			{
				//the differences in x and y
				float deltaX = fish.getX() - b.getX();
				float deltaY = fish.getY() - b.getY();

				//if the absolute of the differences are less then the fish's body radius
				if (Math.abs(deltaX) < fish.getBodyRadius() && Math.abs(deltaY) < fish.getBodyRadius()) 
				{
					Bubble caughtBubble = bSys.getBubble(b);
					fish.addBubble(caughtBubble);
				}
			}
			bSys.removeDeadBubbles();
		}
	}
	@Override
	public void dispose(GLAutoDrawable arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Initial setup of OpenGL
	 */
	@Override
	public void init(GLAutoDrawable drawable) 
	{
        GL2 gl = drawable.getGL().getGL2();
        gl.setSwapInterval(1);
        gl.glClearColor(1, 1, 1, 1);
        gl.glShadeModel(GL2.GL_SMOOTH); 
	}
	
	/**
	 * Handles the reshaping of the window
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
		height = (height == 0) ? 1 : height; // prevent divide by zero
		winWidth = width;
		winHeight = height;
	}
	
	/**
	 * Searches the list of game objects for the first
	 * instance of a fish and returns it.
	 * @return a fish
	 */
	public Fish getFish()
	{
		for(Object o : gameObjects)
		{
			if (o instanceof Fish)
			{
				return (Fish) o;
			}
		}

		return null;
	}
	
	/**
	 * Searches the list of game objects for the first
	 * instance of a BubbleSystem and returns it.
	 * @return a BubbleSystem
	 */
	public BubbleSystem getBubbleSystem(ArrayList<Displayable> gameObjects)
	{
		for(Object o : gameObjects)
		{
			if (o instanceof BubbleSystem)
			{
				return (BubbleSystem) o;
			}
		}

		return null;
	}
	
	/**
	 * I used this just to do a print off in Main,
	 * it was annoying me that Eclipse kept telling me
	 * I wasn't using Viewer for anything but instantiation.
	 * This is because the animator triggers all the events for me.
	 * So I just use this tell me the Viewer has started. =p
	 */
	public String toString()
	{
		return "Viewer";
	}
}
