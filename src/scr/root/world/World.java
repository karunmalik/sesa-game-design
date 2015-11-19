package scr.root.world;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import scr.root.entity.Entity;
import src.root.objects.Block;

public abstract class World {

	public ArrayList<Entity>     entities    = new ArrayList<Entity>();                        /* Active entities in the world */
	public ArrayList<Block>      collidables = new ArrayList<Block>();					   /* Rigid collidables in the world */

	public abstract void init     (GameContainer gc, StateBasedGame s);                 /* This world's init implementation     */
	public abstract void render   (GameContainer gc, StateBasedGame state, Graphics g); /* This world's render implementation   */
	public abstract void update   (GameContainer gc, StateBasedGame state, int delta ); /* This world's update implementation   */
	public abstract void populate (ArrayList<Entity> entitites);                        /* This world's populate implementation */
	public abstract ArrayList<Entity> getEntities();
	public abstract ArrayList<Block> getCollidables();

}
