package com.pickens.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.pickens.gui.AdventureModeButton;
import com.pickens.gui.GuiManager;
import com.pickens.gui.Label;
import com.pickens.gui.MapEditorModeButton;
import com.pickens.gui.MapSelectModeButton;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class ModeState extends BasicGameState {

	public GuiManager menu;
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Fonts.loadFonts();
		menu = new GuiManager(0, 0, null);
		menu.setJustification(GuiManager.CENTER_JUST);
		menu.add(new Label("Select a mode: ", Constants.WIDTH/4, 32, menu));
		menu.setJustification(GuiManager.CENTER_JUST);
		menu.add(new AdventureModeButton(Constants.WIDTH/2, Constants.HEIGHT/4, menu));
		menu.add(new MapSelectModeButton(Constants.WIDTH/2, (Constants.HEIGHT/4)*2, menu));
		menu.add(new MapEditorModeButton(Constants.WIDTH/2, (Constants.HEIGHT/4)*3, menu));
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
		return Constants.MODE_STATE;
	}

}
