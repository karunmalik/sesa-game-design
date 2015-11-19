package src.root.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import scr.root.world.Collidable;
import scr.root.world.World;

public class Block implements Collidable{
	
	public Rectangle bounds;
	
	public Block(World world, int x, int y, int width, int height){
		this.bounds = new Rectangle(x, y, width, height);
	}

	public boolean isPassable() {
		return false;
	}
	
	public Rectangle getBounds(){
		return this.bounds;
	}
	
	public void render(GameContainer gc, Graphics g){
		g.fill(getBounds());
	}

}
