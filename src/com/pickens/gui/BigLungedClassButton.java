package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.main.Game;
import com.pickens.objects.Player;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class BigLungedClassButton extends GuiElement {

	public BigLungedClassButton(int x, int y, GuiManager gc) {
		super(x, y, gc);
		setWidth((int) (Constants.WIDTH * 0.2f));
		setHeight((int) (getWidth()/.5));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		Fonts.CLASS_TEXT.drawString(x+5, y+12, "Big Lunged");
		Fonts.CLASS_TEXT.drawString(x+5, y+width/2, "* Runs at slow speeds");
		Fonts.CLASS_TEXT.drawString(x+5, y+width/2 + (width/4), "* 20 seconds of oxygen");
		Fonts.CLASS_TEXT.drawString(x+5, y+width/2 + (width/4)*2, "* 3 Hearts");
		Fonts.CLASS_TEXT.drawString(x+5, y+width/2 + (width/4)*3, "* 30 Inventory Slots");
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
			Constants.currentCharacter = Constants.BIG_LUNGED;
			
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
