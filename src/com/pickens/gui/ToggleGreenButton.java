package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.util.Constants;
import com.pickens.util.Images;

public class ToggleGreenButton extends GuiElement {

	Color color;
	
	public ToggleGreenButton(int x, int y, GuiManager gc) {
		super(x, y, gc);
		
		setWidth(128);
		setHeight(128);
		
		color = new Color(0, 0, 0, 0);
	}

	@Override
	public void render(Graphics g) {
		if(Constants.GREEN_DOOR_STATE) {
			g.drawImage(Images.GREEN_BUTTON.getSubImage(1, 0), x, y);
		} else {
			g.drawImage(Images.GREEN_BUTTON.getSubImage(0, 0), x, y);
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
		if(Constants.GREEN_DOOR_STATE) {
			Constants.GREEN_DOOR_STATE = false;
		} else {
			Constants.GREEN_DOOR_STATE = true;
		}
	}

	@Override
	public void onHover() {
		color.a = 0.6f;
	}

	@Override
	public void onLeave() {
		color.a = 0f;
	}

}
