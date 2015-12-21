package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.main.Game;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class AdventureModeButton extends GuiElement {

	Color color;
	
	public AdventureModeButton(int x, int y, GuiManager gc) {
		super(x, y, gc);
		setWidth(256);
		setHeight(96);
		color = new Color(0, 0, 0, 0);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.setFont(Fonts.MENU_TEXT);
		g.setColor(Color.black);
		g.drawString("Adventure", x + width/2 - Fonts.MENU_TEXT.getWidth("Adventure")/2, y + height/2 - Fonts.MENU_TEXT.getHeight("Adventure")/2);
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
		Game.setState(Constants.MENU_STATE);
	}

	@Override
	public void onHover() {
		color.a = .6f;
	}

	@Override
	public void onLeave() {
		color.a = 0;
	}

}
