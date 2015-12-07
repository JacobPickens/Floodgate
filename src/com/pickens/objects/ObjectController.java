package com.pickens.objects;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.util.Constants;
import com.pickens.util.Images;

public class ObjectController {

	private ArrayList<Object> objects = new ArrayList<Object>();

	Random r = new Random();
	
	public ObjectController() {
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			Object object = objects.get(i);
			if(Constants.mapOffsetX + (object.x*Constants.TILE_SIZE) >= -Constants.TILE_SIZE && Constants.mapOffsetX + (object.x*Constants.TILE_SIZE) <= Constants.WIDTH + Constants.TILE_SIZE
				&& Constants.mapOffsetY + (object.y*Constants.TILE_SIZE) >= -Constants.TILE_SIZE && Constants.mapOffsetY + (object.y*Constants.TILE_SIZE) <= Constants.HEIGHT + Constants.TILE_SIZE) {
				object.render(g);
			}
		}
	}
	
	public void update(GameContainer gc, int delta) {
		Images.WATER_ANIMATION.update(delta);
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, delta);
		}
	}
	
	public void add(Object o) {
		objects.add(o);
	}
	
	public void remove(Object o) {
		objects.remove(o);
	}
	
	public Object getObject(int x, int y) {
		for(int i = 0; i < objects.size(); i++) {
			Object o = objects.get(i);
			if(o.x == x && o.y == y) {
				return o;
			}
		}
		
		return null;
	}
	
	public ArrayList<Object> getObjects() {
		return objects;
	}
}
