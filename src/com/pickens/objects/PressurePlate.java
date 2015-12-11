package com.pickens.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.map.TileMap;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class PressurePlate extends Trap {

	Image image;
	TriggeredTrap trap;
	
	public PressurePlate(int x, int y, TileMap map, ObjectController oc, TriggeredTrap trap) {
		super(x, y, map, oc);
		this.trap = trap;
		image = Images.TRAPS.getSubImage(1, 0);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, Constants.mapOffsetX + x*Constants.TILE_SIZE, Constants.mapOffsetY + y*Constants.TILE_SIZE);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(PlayState.player.getX() == this.x && PlayState.player.getY() == this.y) {
			oc.remove(this);
			trap.trigger();
		}
		if(map.getRawData()[x][y] == Constants.WATER) {
			oc.remove(this);
		}
	}

}
