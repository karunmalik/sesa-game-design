package scr.root.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends BasicGameState{

	/* Our current world */
	public World currentWorld;
	
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		this.currentWorld = new GameWorld();
		currentWorld.init(gc, s);
	}

	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		currentWorld.render(gc, s, g);
	}

	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		currentWorld.update(gc, s, delta);
	}

	public int getID() {
		return 0;
	}
}
