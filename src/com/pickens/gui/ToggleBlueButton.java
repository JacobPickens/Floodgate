package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.util.Constants;
import com.pickens.util.Images;

public class ToggleBlueButton extends GuiElement {

	Color color;
	
	public ToggleBlueButton(int x, int y, GuiManager gc) {
		super(x, y, gc);
		
		setWidth(128);
		setHeight(128);
		
		color = new Color(0, 0, 0, 0);
	}

	@Override
	public void render(Graphics g) {
		if(Constants.BLUE_DOOR_STATE) {
			g.drawImage(Images.BLUE_BUTTON.getSubImage(1, 0), x, y);
		} else {
			g.drawImage(Images.BLUE_BUTTON.getSubImage(0, 0), x, y);
		}
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		
	}

	@Override
	public void onClick(int button, GameContainer gc) {
		
	}

	@Override
	public void onRelease(int button, GameContainer gc) {
		if(Constants.BLUE_DOOR_STATE) {
			Constants.BLUE_DOOR_STATE = false;
		} else {
			Constants.BLUE_DOOR_STATE = true;
		}
	}

	@Override
	public void onHover() {
		color.a = .6f;
	}

	@Override
	public void onLeave() {
		color.a = 0f;
	}

}
