package com.pickens.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.pickens.gui.BigLungedClassButton;
import com.pickens.gui.GuiManager;
import com.pickens.gui.Label;
import com.pickens.gui.NewRatButton;
import com.pickens.gui.NormalClassButton;
import com.pickens.gui.RunnerClassButton;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class MenuState extends BasicGameState {

	public GuiManager menu;
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Fonts.loadFonts();
		menu = new GuiManager(0, 0, null);
		menu.setJustification(GuiManager.CENTER_JUST);
		menu.add(new Label("Select a class: ", Constants.WIDTH/4, 32, menu));
		menu.setJustification(GuiManager.CENTER_JUST);
		menu.add(new NormalClassButton(Constants.WIDTH/4, Constants.HEIGHT/2, menu));
		menu.add(new RunnerClassButton((Constants.WIDTH/4)*2, Constants.HEIGHT/2, menu));
		menu.add(new BigLungedClassButton((Constants.WIDTH/4)*3, Constants.HEIGHT/2, menu));
		//menu.add(new NewRatButton(Constants.WIDTH/2, Constants.HEIGHT/2, menu));
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
