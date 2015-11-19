package scr.root.entity;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import scr.root.world.World;
import src.root.objects.Block;

public class Player extends Entity{
	
	public final int SPEED = 3;
	public final float FRICTION = 0.50f;
	public final int shootDelay = 1000;
	public float rotation;
	public float shootTimer = 0;
	public float toX = Mouse.getX(), toY = Mouse.getY();
	public float centerX = 0, centerY = 0;
	public float lastX = 0, lastY = 0;
	public Rectangle top = new Rectangle(getBounds().getX() + 15, getBounds().getY(), getBounds().getWidth() - 30, getBounds().getHeight());

	public Player(World world, int x, int y, int width, int height){
		super(world, x, y, width, height);
		
		this.centerX = top.getCenterX();
		this.centerY = top.getCenterY();
	}
	
	@Override
	public void update(GameContainer gc, int delta){
		Input input = gc.getInput();
		
		shootTimer += delta;
		toX   = Mouse.getX();
		toY   =  gc.getHeight() - Mouse.getY(); /* Mouse Y is inverted in slick */
		x     = getBounds().getX();
		y     = getBounds().getY();
		lastX = getBounds().getX();
		lastY = getBounds().getY();
		centerX = top.getCenterX();
		centerY = top.getCenterY();
		top.setLocation(getBounds().getX() + 15, getBounds().getY());
		
		/* Usually, player movement is gone by moving/rotating the world around the player,
		 * but because each level is only one screen, we can get away with moving the
		 * player around
		 */
		
		if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)){
			 y -= SPEED * FRICTION;
		}
		if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)){
			x -= SPEED * FRICTION;
		}
		
		if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)){
			x += SPEED * FRICTION;
		}
		
		if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)){			
			y += SPEED * FRICTION;
		}
		
		this.getBounds().setLocation(x, y);
		
		doRigidCollisionDetection();	
		
		/* Bullet shooting */
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			shoot();
		}
	}
	
	public void shoot(){
		if(shootTimer >= shootDelay){
			Bullet bullet = new Bullet(this.getWorld(), top.getCenterX(), top.getCenterY(), 5, 5, toX, toY);
			getWorld().entities.add(bullet);
			shootTimer = 0;
		}
	}
	
	public void doRigidCollisionDetection(){
		ArrayList<Block> blocks = new ArrayList<Block>(getWorld().getCollidables());
		
		for(Block b : blocks){
			if(b.getBounds().intersects(getBounds())){
				if(!b.isPassable()){
					
					/* Colliding from the left */

					if(getBounds().getMinX() < b.getBounds().getMinX() && !(getBounds().getMaxX() > b.getBounds().getMinX() +  SPEED)){
						if(getBounds().getMinY() < b.getBounds().getMinY()){
							if(getBounds().getMaxY() > b.getBounds().getMinY()){
								x = b.getBounds().getMinX() - getBounds().getWidth();
							}
						}else if(getBounds().getMinY() >= b.getBounds().getMinY() && (getBounds().getMaxY() <= b.getBounds().getMaxY())){
							x = b.getBounds().getMinX() - getBounds().getWidth();
						}else if(getBounds().getMaxY() > b.getBounds().getMaxY()){
							if(getBounds().getMaxY() > b.getBounds().getMaxY()){
								x = b.getBounds().getMinX() - getBounds().getWidth();
							}
						}
					}
					
					/* Colliding from the top */
					
					if(getBounds().getMaxY() - SPEED < b.getBounds().getMinY()){
						y = b.getBounds().getMinY() - getBounds().getHeight();
					}
					
					/* Colliding right */
					
					if(getBounds().getMaxX() > b.getBounds().getMaxX() && !(getBounds().getMinX() < b.getBounds().getMaxX() - SPEED)){
						if(getBounds().getMinY() < b.getBounds().getMinY() && getBounds().getMaxY() > b.getBounds().getMinY()){							
							x = b.getBounds().getMaxX(); /* Right top half */
						}else if(getBounds().getMinY() >= b.getBounds().getMinY() && (getBounds().getMaxY() <= b.getBounds().getMaxY())){
							x = b.getBounds().getMaxX(); /* Right middle */
						}else if(getBounds().getMinY() < b.getBounds().getMaxY() && (getBounds().getMaxY() > b.getBounds().getMaxY())){
							x = b.getBounds().getMaxX(); /* Right bottom half */
						}
					}
					
					/* Colliding bottom */
					
					if(getBounds().getMinY() + SPEED > b.getBounds().getMaxY()){
						y = b.getBounds().getMaxY();
					}
						
					getBounds().setLocation(x, y);
				}
			}
		}
	}
	
	public float getTheta(){
		return (float) StrictMath.toDegrees((float) Math.atan2(toX - x, y - toY) - 190);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g){
		/* Bottom half */
		g.fill(this.getBounds());
		g.setColor(Color.blue);

		
		/* Rotate top portion */
		float theta = getTheta();
		g.rotate(x + getWidth() / 2, y + getHeight() / 2, theta);
		g.fill(top);
		g.rotate(x + getWidth() / 2, y + getHeight() / 2, -theta);

		g.setColor(Color.white);
	}

	@Override
	public boolean isPassable() {
		return false;
	}
}
