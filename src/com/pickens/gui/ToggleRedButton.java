package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.util.Constants;
import com.pickens.util.Images;

public class ToggleRedButton extends GuiElement {

	Color color;
	
	public ToggleRedButton(int x, int y, GuiManager gc) {
		super(x, y, gc);
		
		setWidth(128);
		setHeight(128);
		
		color = new Color(0, 0, 0, 0);
	}

	@Override
	public void render(Graphics g) {
		if(Constants.RED_DOOR_STATE) {
			g.drawImage(Images.RED_BUTTON.getSubImage(1, 0), x, y);
		} else {
			g.drawImage(Images.RED_BUTTON.getSubImage(0, 0), x, y);
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
		if(Constants.RED_DOOR_STATE) {
			Constants.RED_DOOR_STATE = false;
		} else {
			Constants.RED_DOOR_STATE = true;
		}
	}

	@Override
	public void onHover() {
		color.a = 0.6f;
	}

	@Override
	public void onLeave() {
		color.a = 0;
	}

}
