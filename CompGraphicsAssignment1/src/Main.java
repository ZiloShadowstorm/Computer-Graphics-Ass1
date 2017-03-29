/**
 * 
 */
package src;

import java.util.ArrayList;

import src.Models.*;
import src.Models.Seaweed;
import src.Models.WaterLine;

/**
 * @author Davy
 *
 */
public class Main
{

	/**
	 * Entry point for the program.
	 * Creates all the game objects.
	 * Adds them to a list.
	 * Creates the viewer and pass the list of game objects to it.
	 * @param args
	 */
	public static void main(String[] args)
	{
		//COLOURS
		float[] seaweedOuterColor = { 0, 0.5f, 1f };
		float[] seaweedInnerColor = { 0, 0, 0 };
		float[] seaweedOuterColor2 = { 1f, 0.5f, 1f };

		//GAME OBJECTS
		WaterLine waterline = new WaterLine();
		OceanBackground oceanBackground = new OceanBackground(waterline);
		Seaweed seaweed1 = new Seaweed(-0.5f, -1, 0.25f, seaweedOuterColor, seaweedInnerColor);
	    Seaweed seaweed2 = new Seaweed(-0.3f, -1, 0.25f, seaweedOuterColor2, seaweedInnerColor);
	    Rockbed rockbed = new Rockbed();
	    Fish fish = new Fish();
	    FishFin fishFin = new FishFin();
	    FishTail fishTail = new FishTail();
	    FishEye fishEye = new FishEye();
	    Button bubbleButton = new Button(-0.9f, 0.85f, "Bubbles");
	    Button timeButton = new Button(-0.5f, 0.85f, "Time");
	    Button planktonButton = new Button(-0.1f, 0.85f, "Plankton");
	    Darkness darkness = new Darkness();
	    Plankton plankton = new Plankton();
	    BubbleSystem bubbleSystem = new BubbleSystem();
	    
	    //LIST TO HOLD GAME OBJECTS
		ArrayList<Displayable> gameObjects = new ArrayList<>();
		gameObjects.add(waterline);
		gameObjects.add(oceanBackground);
		gameObjects.add(rockbed);		
		gameObjects.add(fish);
		gameObjects.add(fishFin);
		gameObjects.add(fishTail);
		gameObjects.add(bubbleSystem);
		gameObjects.add(seaweed1);
		gameObjects.add(seaweed2);
		
		gameObjects.add(darkness);
		gameObjects.add(plankton);
		gameObjects.add(fishEye);
		
		gameObjects.add(bubbleButton);
		gameObjects.add(timeButton);
		gameObjects.add(planktonButton);
		
		Viewer viewer = new Viewer(gameObjects);
		System.out.println(viewer + " is running.");


	}

}
