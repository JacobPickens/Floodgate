package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.main.Game;
import com.pickens.states.PlayState;
import com.pickens.util.Character;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class NewRatButton extends GuiElement {

	Color color;
	
	public NewRatButton(int x, int y, GuiManager gc) {
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
		g.drawString("New Rat", x + width/2 - Fonts.MENU_TEXT.getWidth("New Rat")/2, y + height/2 - Fonts.MENU_TEXT.getHeight("New Rat")/2);
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
		Constants.currentCharacter = new Character(15, 9, 2, 4, 4);
		
		PlayState.player.setStats(Constants.currentCharacter);
		
		Game.setState(Constants.GAME_STATE);
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
