package com.pickens.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.map.TileMap;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class Floodgate extends Object {

	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	
	Image open;
	Image close;
	
	public boolean closed;
	public boolean disabled = false;
	
	public int type = 0;
	
	public Floodgate(int x, int y, TileMap map, ObjectController oc, int type) {
		super(x, y, map, oc);
		this.type = type;
		switch(type) {
		case RED:
			open = Images.RED_OPEN;
			close = Images.RED_CLOSED;
			break;
		case GREEN:
			open = Images.GREEN_OPEN;
			close = Images.GREEN_CLOSED;
			break;
		case BLUE:
			open = Images.BLUE_OPEN;
			close = Images.BLUE_CLOSED;
			break;
		}
		if(map.getRawData()[x][y] == Constants.WALL && map.getRawData()[x+1][y] == Constants.WALL) {
			oc.remove(this);
		}
	}

	@Override
	public void render(Graphics g) {
		if(!disabled) {
			if(closed) {
				close.draw(Constants.mapOffsetX + x*Constants.TILE_SIZE, Constants.mapOffsetY + y*Constants.TILE_SIZE);
			} else {
				open.draw(Constants.mapOffsetX + x*Constants.TILE_SIZE, Constants.mapOffsetY + y*Constants.TILE_SIZE);
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		switch(type) {
		case RED:
			closed = Constants.RED_DOOR_STATE;
			break;
		case GREEN:
			closed = Constants.GREEN_DOOR_STATE;
			break;
		case BLUE:
			closed = Constants.BLUE_DOOR_STATE;
			break;
		}
		if(map.getRawData()[x][y] == Constants.WATER) {
			disabled = true;
		}
		if(disabled == true) {
			closed = false;
			oc.remove(this);
		}
	}

}
