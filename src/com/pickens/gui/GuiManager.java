package com.pickens.gui;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.objects.Player;

public class GuiManager {

	private ArrayList<GuiElement> elements;
	
	private int ox;
	private int oy;
	
	public static final int LEFT_JUST = 0;
	public static final int RIGHT_JUST = 1;
	public static final int CENTER_JUST = 2;
	private int JUSTIFICATION = CENTER_JUST;
	private Player p;
	
	public GuiManager(int ox, int oy, Player p) {
		elements = new ArrayList<GuiElement>();
		this.ox = ox;
		this.oy = oy;
		this.p = p;
	}
	
	public void render(Graphics g) {
		g.translate(ox, oy);
		for(int i = 0; i < elements.size(); i++) {
			GuiElement e = elements.get(i);			
			e.render(g);
		}
		g.translate(-ox, -oy);
	}
	
	public void update(GameContainer gc, int delta) {
		for(int i = 0; i < elements.size(); i++) {
			elements.get(i).check(gc, delta);
		}
	}
	
	public void add(GuiElement e) {
		e.setJustification(JUSTIFICATION);
		switch(e.getJustification()) {
		case RIGHT_JUST:
			e.setX(e.getX() - e.getWidth());
			e.setY(e.getY() - e.getHeight());
			break;
		case CENTER_JUST:
			e.setX(e.getX() - e.getWidth()/2);
			e.setY(e.getY() - e.getHeight()/2);
			break;
		}
		elements.add(e);
	}
	
	public void remove(GuiElement e) {
		elements.remove(e);
	}
	
	public GuiElement getElement(int index) {
		return elements.get(index);
	}
	
	public ArrayList<GuiElement> getElements() {
		return elements;
	}

	public int getOffsetX() {
		return ox;
	}

	public void setOffsetX(int ox) {
		this.ox = ox;
	}

	public int getOffsetY() {
		return oy;
	}

	public void setOffsetY(int oy) {
		this.oy = oy;
	}
	
	public int getJustification() {
		return JUSTIFICATION;
	}
	
	public void setJustification(int j) {
		JUSTIFICATION = j;
	}

	public Player getPlayer() {
		return p;
	}

	public void setPlayer(Player p) {
		this.p = p;
	}
	
}
