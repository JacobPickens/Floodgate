package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.main.Game;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class RunnerClassButton extends GuiElement {

	public RunnerClassButton(int x, int y, GuiManager gc) {
		super(x, y, gc);
		setWidth((int) (Constants.WIDTH * 0.2f));
		setHeight((int) (getWidth()/.5));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		Fonts.CLASS_TEXT.drawString(x+5, y+12, "Runner");
		Fonts.CLASS_TEXT.drawString(x+5, y+width/2, "* Runs at fast speeds");
		Fonts.CLASS_TEXT.drawString(x+5, y+width/2 + (width/4), "* 10 seconds of oxygen");
		Fonts.CLASS_TEXT.drawString(x+5, y+width/2 + (width/4)*2, "* 1 Heart");
		Fonts.CLASS_TEXT.drawString(x+5, y+width/2 + (width/4)*3, "* 9 Inventory Slots");
	}

	@Override
	public void update(GameContainer gc, int delta) {
		
	}

	@Override
	public void onClick(int button, GameContainer gc) {
		
	}

	@Override
	public void onRelease(int button, GameContainer gc) {
		if(button == Input.MOUSE_LEFT_BUTTON) {
			Constants.currentCharacter = Constants.RUNNER;
			
			PlayState.player.setStats(Constants.currentCharacter);
			
			Game.setState(Constants.GAME_STATE);
		}
	}

	@Override
	public void onHover() {
		
	}

	@Override
	public void onLeave() {
		
	}

	
	
}
