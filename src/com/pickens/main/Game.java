package com.pickens.main;

import static com.pickens.util.Constants.GAME_STATE;
import static com.pickens.util.Constants.MENU_STATE;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.pickens.states.MenuState;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;
import com.pickens.util.Images;

public class Game extends StateBasedGame {

	private static Game g;
	
	public Game(String name) {
		super(name);
		
		this.addState(new MenuState());
		this.addState(new PlayState());
	}
	
	public static void setState(int state) {
		g.enterState (state);
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		Images.load();
		Constants.loadClasses();
		
		this.getState(MENU_STATE).init(gc, this);
		this.getState(GAME_STATE).init(gc, this);
		
		this.enterState(MENU_STATE);
	}
	
	public static void main(String[] args) {		
		boolean fullscreen = false;
	    int reply = JOptionPane.showConfirmDialog(null, "Would you like to run Floodgate in fullscreen?", "Fullscreen?", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	Constants.WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    		Constants.HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    		fullscreen = true;
        } else {
        	String[] values = {"2000x1125", "1500x844", "1000x563"};

        	Object selected = JOptionPane.showInputDialog(null, "Please pick a windowed size:", "Windowed Mode", JOptionPane.DEFAULT_OPTION, null, values, "1500x844");
        	if ( selected != null ){//null if the user cancels. 
        	    String selectedString = selected.toString();
        	    switch(selectedString) {
        	    case "2000x1125":
        	    	Constants.WIDTH = 2000;
            		Constants.HEIGHT = 1125;
        	    	break;
        	    case "1500x844":
        	    	Constants.WIDTH = 1500;
            		Constants.HEIGHT = 844;
        	    	break;
        	    case "1000x563":
        	    	Constants.WIDTH = 1000;
            		Constants.HEIGHT = 563;
        	    	break;
        	    }
        	}else{
        	    System.exit(0);
        	}
        }
		
        g = new Game("Floodgate v0.0.7");
        
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(g);
			appgc.setDisplayMode(Constants.WIDTH, Constants.HEIGHT, fullscreen);
			appgc.setVSync(true);
			appgc.setTargetFrameRate(60);
			appgc.setAlwaysRender(true);
			appgc.setVerbose(false);
			appgc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
}
