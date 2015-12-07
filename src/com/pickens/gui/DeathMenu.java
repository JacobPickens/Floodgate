package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.main.Game;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class DeathMenu extends GuiElement {

	Color color;
	int ticker = 0;
	
	public DeathMenu(int x, int y, GuiManager gc) {
		super(x, y, gc);
		setWidth(Constants.WIDTH);
		setHeight(Constants.HEIGHT);
		color = new Color(0, 0, 0, 0f);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		g.setColor(color.brighter());
		Fonts.MENU_TEXT.drawString(Constants.WIDTH/2 - Fonts.MENU_TEXT.getWidth("You Died.")/2, Constants.HEIGHT/2 - Fonts.MENU_TEXT.getLineHeight()/2, "You Died.");
		Fonts.MENU_TEXT.drawString(Constants.WIDTH/2 - Fonts.MENU_TEXT.getWidth("Click to continue.")/2, Constants.HEIGHT/2 - Fonts.MENU_TEXT.getLineHeight()/2+64, "Click to continue.");
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(color.a < .6f) {
			color.a += .01f;
		}
	}

	@Override
	public void onClick(int button, GameContainer gc) {
		
	}

	@Override
	public void onRelease(int button, GameContainer gc) {
		PlayState.reset();
		Game.setState(Constants.MENU_STATE);
	}

	@Override
	public void onHover() {
		
	}

	@Override
	public void onLeave() {
		
	}

}
