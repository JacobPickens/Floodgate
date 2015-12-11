package com.pickens.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.map.TileMap;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;

public class SpikeTrap extends Trap {

	public SpikeTrap(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(Constants.mapOffsetX + x*Constants.TILE_SIZE, Constants.mapOffsetY + y*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(PlayState.player.getX() == this.x && PlayState.player.getY() == this.y) {
			oc.remove(this);
			PlayState.player.addHealth(-1);
		}
		if(map.getRawData()[x][y] == Constants.WATER) {
			oc.remove(this);
		}
	}

}
