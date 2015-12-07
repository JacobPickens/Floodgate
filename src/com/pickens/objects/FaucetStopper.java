package com.pickens.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.map.TileMap;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class FaucetStopper extends Object {

	Image sprite;
	
	public FaucetStopper(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		
		sprite = Images.RIM_SHEET.getSubImage(0, 2);
	}

	@Override
	public void render(Graphics g) {
		sprite.draw(Constants.mapOffsetX + x*Constants.TILE_SIZE, Constants.mapOffsetY + y*Constants.TILE_SIZE);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		
	}

}
