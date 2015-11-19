package scr.root.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import scr.root.world.Collidable;
import scr.root.world.World;

public abstract class Entity implements Collidable{
	
	private World     world;
	private Rectangle bounds; /* The rectangle describing this entities collision bounds */
	private Vector2f  position, v; /* The vector describing this entities movement */
	protected float     x; /* x, y, width and height of entity */
	protected float y;
	private float width;
	private float height;
	
	public Entity(){ this(null, 0, 0, 0, 0); }
	
	/** General constructor for defining an entity. The entity class
	 *  is abstract and therefore cannot be initiated. This serves as 
	 *  a common parent to all in game entities
	 *  
	 *  @see org.newdawn.slick.geom.Rectangle
	 *  @see org.newdawn.slick.geom.Vector2f
	 *  @author Nick Petryna
	 **/

	public Entity(World world, float x, float y, float width, float height){
		this.world    = world;
		this.x        = x; 
		this.y        = y;
		this.width    = width; 
		this.height   = height;
		this.position = new Vector2f();
		this.bounds   = new Rectangle(x, y, width, height);
	}
	
	
	
	/** Represents this entity's update implementation
	 *  @see org.newdawn.slick.GameContainer
	 *  @author Nick Petryna
	 */
	public abstract void update(GameContainer gc, int delta);
	
	/** Represents this entity's render implementation 
	 *  @see org.newdawn.slick.Graphics
	 *  @see org.newdawn.slick.GameContainer
	 *  @author Nick Petryna
	 */
	public abstract void render(GameContainer gc, Graphics g);
	
	public boolean isColliding(Entity entity){
		if(this.getBounds().intersects(entity.getBounds())){
			return true;
		}
		
		return false;
	}
	
	
	/** Returns the Rectangle describing this
	  * entity's bounds 
	  *  
	  * @see org.newdawn.slick.geom.Rectangle    
	  * @author Nick Petryna **/
	
	public Rectangle getBounds()   { return this.bounds;             }
	public Vector2f  getPosition() { return this.position;           }
	public float     getX()        { return this.x;                  }
	public float     getY()        { return this.y;                  }
	public float     getWidth()    { return this.bounds.getWidth();  }
	public float     getHeight()   { return this.bounds.getHeight(); }

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}