package com.pickens.gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import com.pickens.util.Fonts;

public class Label extends GuiElement {

	protected String text;
	protected int c = 0xFFFFFF;
	protected Color color;
	protected TrueTypeFont font;
	
	public Label(String text, int x, int y, GuiManager gc) {
		super(x, y, gc);
		this.text = text;
		this.color = new Color(c);
		font = Fonts.MENU_TEXT;
		setWidth(font.getWidth(text));
		setHeight(font.getHeight());
	}
	
	public Label(String text, int x, int y, int color, GuiManager gc) {
		super(x, y, gc);
		this.text = text;
		this.c = color;
		this.color = new Color(c);
		font = Fonts.MENU_TEXT;
		setWidth(font.getWidth(text));
		setHeight(font.getHeight());
	}
	
	public Label(String text, int x, int y, int color, TrueTypeFont fnt, GuiManager gc) {
		super(x, y, gc);
		this.text = text;
		this.c = color;
		this.color = new Color(c);
		font = fnt;
		setWidth(font.getWidth(text));
		setHeight(font.getHeight());
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		
	}

	@Override
	public void onClick(int button, GameContainer gc) {
		
	}

	@Override
	public void onRelease(int button, GameContainer gc) {
		
	}

	@Override
	public void onHover() {
		
	}

	@Override
	public void onLeave() {
		
	}

}
