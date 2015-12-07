package com.pickens.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.map.TileMap;

public abstract class Object {

	protected int x, y, w, h;
	
	protected TileMap map;
	protected ObjectController oc;
	
	public Object(int x, int y, TileMap map, ObjectController oc) {
		this.x = x;
		this.y = y;
		this.map = map;
		this.oc = oc;
	}
	
	public abstract void render(Graphics g);
	public abstract void update(GameContainer gc, int delta);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}
	
	public TileMap getMap() {
		return map;
	}
	
}
