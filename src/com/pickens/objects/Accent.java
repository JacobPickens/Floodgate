package com.pickens.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.map.TileMap;
import com.pickens.util.Constants;

public class Accent extends Object {

	Image image;
	
	public Accent(int x, int y, TileMap map, ObjectController oc, Image image) {
		super(x, y, map, oc);
		this.image = image;
	}

	@Override
	public void render(Graphics g) {
		if(map.getRawData()[x][y] == Constants.FLOOR || map.getRawData()[x][y] == Constants.WALL || map.getRawData()[x][y] == Constants.WALL_CORNER_RIGHT || map.getRawData()[x][y] == Constants.WALL_CORNER_LEFT ||  map.getRawData()[x][y] == Constants.CHEST || map.getRawData()[x][y] == Constants.WATER) {
			oc.remove(this);
			return;
		}
		image.draw(Constants.mapOffsetX + x*Constants.TILE_SIZE, Constants.mapOffsetY + y*Constants.TILE_SIZE);
	}

	@Override
	public void update(GameContainer gc, int delta) {

	}

}
