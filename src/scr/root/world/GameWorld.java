package scr.root.world;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import scr.root.entity.Entity;
import scr.root.entity.Player;
import src.root.objects.Block;

public class GameWorld extends World{

	Player player = new Player(this, 400, 300, 60, 30);
	
	public void init(GameContainer gc, StateBasedGame s){
		ArrayList<Entity> addToWorld = new ArrayList<Entity>();
				
		collidables.add(new Block(this, 0, 0, 30, gc.getHeight())); /* Left wall */
		collidables.add(new Block(this, 0, 0, gc.getWidth(), 30)); /* Top wall */
		collidables.add(new Block(this, gc.getWidth() - 30, 0, 30, gc.getHeight())); /* Right wall */
		collidables.add(new Block(this, 0, gc.getHeight() - 30, gc.getWidth(), 30)); /* Bottom wall */
		collidables.add(new Block(this, gc.getWidth() / 4, gc.getHeight() / 3, 30, gc.getHeight() / 3)); /* Left-middle wall */
		collidables.add(new Block(this, gc.getWidth() / 2 - 15, gc.getHeight() / 3, 30, gc.getHeight() / 8)); /* Upper middle */
		collidables.add(new Block(this, gc.getWidth() / 2 - 15, gc.getHeight() / 3 * 2 - (gc.getHeight() / 8), 30, gc.getHeight() / 8)); /* Upper middle */
		collidables.add(new Block(this, gc.getWidth()/ 3 *2 + 30, gc.getHeight() / 3, 30, gc.getHeight() / 3)); /* Right-middle wall */
		
		//populate(addToWorld);
	}
	
	public void render(GameContainer gc, StateBasedGame state, Graphics g) {
		ArrayList<Entity> clone = new ArrayList<Entity>(this.entities);
		ArrayList<Block> collidableClone = new ArrayList<Block>(this.collidables);
		
		player.render(gc, g);
		
		for(Entity e : clone){
			e.render(gc, g);
		}	
		
		for(Block c : collidableClone){
			c.render(gc, g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int delta) {
		ArrayList<Entity> clone = new ArrayList<Entity>(this.entities);
		
		player.update(gc, delta);
		
		for(Entity e : clone){
			e.update(gc, delta);
		}
	}

	@Override
	public void populate(ArrayList<Entity> entities) {
		this.entities.clear();
		this.entities.addAll(entities);
	}

	@Override
	public ArrayList<Entity> getEntities() {
		return this.entities;
	}

	@Override
	public ArrayList<Block> getCollidables() {
		return this.collidables;
	}
}
