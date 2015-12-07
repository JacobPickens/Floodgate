package com.pickens.objects;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.map.TileMap;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class Slime extends Object {

	Image img;
	
	public Slime(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		if(map.getRawData()[x][y] != Constants.FLOOR) {
			oc.remove(this);
		}
		img = Images.RIM_SHEET.getSubImage(new Random().nextInt(2) + 2, 1);
	}

	@Override
	public void render(Graphics g) {
		img.draw(Constants.mapOffsetX + x*Constants.TILE_SIZE, Constants.mapOffsetY + y*Constants.TILE_SIZE);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		
	}

}
