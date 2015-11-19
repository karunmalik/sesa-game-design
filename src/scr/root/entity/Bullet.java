package scr.root.entity;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import scr.root.world.World;
import src.root.objects.Block;

public class Bullet extends Entity{
	
	public Vector2f velocity; /* Direction vector of the bullet's trajectory */
	public final int SPEED = 7;
	public float toX, toY;
	public boolean fired = false, hasBounced = false;
	public float theta;
	
	public Bullet(World world, float x, float y, float width, float height, float toX, float toY){
		super(world, x, y, width, height);
		
		this.toX = toX;
		this.toY = toY;
		this.velocity = new Vector2f(0,0);
	}
	
	public void update(GameContainer gc, int delta) {
		getBounds().setLocation(getBounds().getX() + velocity.x, getBounds().getY() + velocity.y);
		
		if(!fired){
			fired = true;
			
			/* The calculations create an origin for the vector's magtitude */
			
			if(toX > x){
				velocity.x = SPEED;
			}else{
				velocity.x = -SPEED;
			}	
			
			if(toY > y){
				velocity.y = -SPEED;
			}else{
				velocity.y = SPEED;
			}
			
			float theta = (float) Math.atan2(toX - x, y - toY);

			/* Returns the value of the first argument raised to the power of the second argument. */
			velocity.setTheta(Math.toDegrees(theta) + 270);
		}
		
		/* Do collision checks */
		
		ArrayList<Block> clone = new ArrayList<Block>(getWorld().getCollidables());
		
		for(Block b : clone){
			if(b.getBounds().intersects(getBounds())){
				if(!hasBounced){
					reverse(b);
					hasBounced = true;
				}else{
					getWorld().getEntities().remove(this);
				}
			}
		}		
	}
	
	public void reverse(Block b){
		/*float[] normal = b.getBounds().getNormal(3);
		Vector2f n = new Vector2f(normal);
		velocity.setTheta(n.getTheta() + 270);*/
		
		velocity.setTheta(-velocity.getTheta() + 180);
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.fill(getBounds());	
	}

	@Override
	public boolean isPassable() {
		return false;
	}
}
