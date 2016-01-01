package com.pickens.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.pickens.gui.GuiManager;
import com.pickens.gui.NewRatButton;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class MenuState extends BasicGameState {

	public GuiManager menu;
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Fonts.loadFonts();
		menu = new GuiManager(0, 0, null);
		menu.setJustification(GuiManager.CENTER_JUST);
		menu.add(new NewRatButton(Constants.WIDTH/2, Constants.HEIGHT/2, menu));
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		menu.render(g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		menu.update(gc, delta);
	}

	public int getID() {
		return Constants.MENU_STATE;
	}

}
