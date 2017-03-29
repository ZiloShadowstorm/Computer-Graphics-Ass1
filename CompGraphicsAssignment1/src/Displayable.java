package src;
import com.jogamp.opengl.GLAutoDrawable;

/**
 * A very basic interface that all game ojbects will implement.
 * I preferred an interface of inheritance because all game objects
 * must define display and they are all very different.
 * 
 * Haveing this interface allows the game objects to be put in
 * a collection with the generic Displayable.
 * @author Davy
 *
 */
public interface Displayable
{
	public void display(GLAutoDrawable drawable);
}
