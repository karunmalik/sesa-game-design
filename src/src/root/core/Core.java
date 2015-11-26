package src.root.core;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import scr.root.world.PlayState;

public class Core extends StateBasedGame{

	public static final String TITLE = "GAME TITLE";
	
	public Core(String name) {
		super(name);
	}

	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new PlayState());
	}
	
	public static void main(String...args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Core(TITLE));
		
		app.setTargetFrameRate(70);
		app.setShowFPS(true);
		app.setDisplayMode(1080, 680, false);
		app.setVSync(true);
		app.start();
		
		
	}

}
