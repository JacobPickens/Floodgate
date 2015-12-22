package com.pickens.map;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.objects.Water;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class Tile {
	private int x;
	private int y;
	private int type;
	private Image image;
	private TileMap map;

	public Tile(int x, int y, int type, TileMap map) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.map = map;
		switch (type) {
		case Constants.EMPTY:
			this.image = Images.ACCENTS.getSubImage(3,2);
			break;
		case Constants.WALL:
			this.image = Images.WALL_SHEET.getSubImage(new Random().nextInt(3) + 1, new Random().nextInt(3));
			break;
		case Constants.FLOOR:
			this.image = Images.WALL_SHEET.getSubImage(new Random().nextInt(4), 3);
			break;
		case Constants.RIM_TOP:
			this.image = Images.ACCENTS.getSubImage(1, 0);
			break;
		case Constants.RIM_TOP_CORNER_LEFT:
			this.image = Images.ACCENTS.getSubImage(0, 0);
			break;
		case Constants.RIM_TOP_CORNER_RIGHT:
			this.image = Images.ACCENTS.getSubImage(2, 0);
			break;
		case Constants.RIM_LEFT:
			this.image = Images.ACCENTS.getSubImage(0, 1);
			break;
		case Constants.RIM_RIGHT:
			this.image = Images.ACCENTS.getSubImage(2, 1);
			break;
		case Constants.RIM_BOTTOM_CORNER_LEFT:
			this.image = Images.ACCENTS.getSubImage(0, 2);
			break;
		case Constants.RIM_BOTTOM_CORNER_RIGHT:
			this.image = Images.ACCENTS.getSubImage(2, 2);
			break;
		case Constants.RIM_BOTTOM:
			this.image = Images.ACCENTS.getSubImage(1, 2);
			break;
		case Constants.RIM_TOP_SHARP_CORNER_LEFT:
			this.image = Images.ACCENTS.getSubImage(2, 3);
			break;
		case Constants.RIM_TOP_SHARP_CORNER_RIGHT:
			this.image = Images.ACCENTS.getSubImage(3, 3);
			break;
		case Constants.RIM_BOTTOM_SHARP_CORNER_LEFT:
			this.image = Images.ACCENTS.getSubImage(1, 3);
			break;
		case Constants.RIM_BOTTOM_SHARP_CORNER_RIGHT:
			this.image = Images.ACCENTS.getSubImage(0, 3);
			break;
		case Constants.WATER:
			break;
		case Constants.STOPPER:
			break;
		case Constants.BARRIER:
			this.image = Images.WALL_SHEET.getSubImage(new Random().nextInt(3) + 1, new Random().nextInt(3));
			break;
		case Constants.CHEST:
			this.image = Images.RIM_SHEET.getSubImage(0,3);
			break;
		case Constants.WALL_CORNER_RIGHT:
			this.image = Images.WALL_SHEET.getSubImage(3, new Random().nextInt(3));
			break;
		case Constants.WALL_CORNER_LEFT:
			this.image = Images.WALL_SHEET.getSubImage(0, new Random().nextInt(3));
			break;
		case Constants.RIM_TUNNEL:
			this.image = Images.ACCENTS.getSubImage(3, 0);
			break;
		}
	}

	public void render(float mapOffsetX, float mapOffsetY, Graphics g) {
		if ((mapOffsetX + this.x >= -Constants.TILE_SIZE) && (mapOffsetX + this.x <= Constants.WIDTH + Constants.TILE_SIZE) && (mapOffsetY + this.y >= -Constants.TILE_SIZE)
				&& (mapOffsetY + this.y <= Constants.HEIGHT + Constants.TILE_SIZE)) {
			if (this.type == Constants.WATER) {
				if(map.getRawData()[this.x/Constants.TILE_SIZE][this.y/Constants.TILE_SIZE] == Constants.WATER && map.getObjectController().getObject(this.x/Constants.TILE_SIZE, this.y/Constants.TILE_SIZE) instanceof Water && ((Water) map.getObjectController().getObject(this.x/Constants.TILE_SIZE, this.y/Constants.TILE_SIZE)).isFrozen()) {
					Images.FROZEN_WATER.draw(mapOffsetX + this.x, mapOffsetY + this.y);
				} else {
					Images.WATER_ANIMATION.draw(mapOffsetX + this.x, mapOffsetY + this.y);
				}
			} else {
				this.image.draw(mapOffsetX + this.x, mapOffsetY + this.y);
			}
		}
	}

	public void update(GameContainer gc, int delta) {
	}

	public int getType() {
		return this.type;
	}
}
