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

import src.Models.Fish;

public class View implements GLEventListener
{
	public static int winWidth = 640;
	public static int winHeight = 640;
	
	private Frame frame;
	private final FPSAnimator animator;
	private ArrayList<Displayable> gameObjects;
	
	
	//CONSTRUCTOR
	public View(ArrayList<Displayable> gameObjects)
	{
		this.gameObjects = gameObjects;
		
		frame = new Frame("Fish Game");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas  canvas = new GLCanvas(capabilities);
        frame.add(canvas);
        canvas.addGLEventListener(this);
        frame.setSize(winWidth, winHeight);
        
        canvas.addKeyListener(getFish(this.gameObjects));
        
        animator = new FPSAnimator(canvas, 60);
        EventHandler eventHandler = new EventHandler(animator, gameObjects);
        frame.addWindowListener(eventHandler);
        canvas.addMouseListener(eventHandler);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.requestFocusInWindow();
       
	}
	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL2.GL_LINE_SMOOTH);
		 
		
		 gameObjects.stream().forEach((gameObject) -> gameObject.display(drawable));
		 
		
		 gl.glFlush();
		
	}
	@Override
	public void dispose(GLAutoDrawable arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init(GLAutoDrawable drawable) 
	{
        GL2 gl = drawable.getGL().getGL2();
        gl.setSwapInterval(1);
        gl.glClearColor(1, 1, 1, 1);
        gl.glShadeModel(GL2.GL_SMOOTH); 
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
		height = (height == 0) ? 1 : height; // prevent divide by zero
		winWidth = width;
		winHeight = height;
	}
	
	public Fish getFish(ArrayList<Displayable> gameObjects)
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
	
	public String toString()
	{
		return "View";
	}
}
