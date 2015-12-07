package com.pickens.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.items.BarrierItem;
import com.pickens.items.Slot;
import com.pickens.map.TileMap;
import com.pickens.objects.Barrier;
import com.pickens.objects.ObjectController;
import com.pickens.objects.Player;

public class BlockPlacer {

	TileMap map;
	ObjectController oc;
	Player p;
	
	int mx;
	int my;
	
	Color eraser;
	
	public BlockPlacer(TileMap map, ObjectController oc, Player p) {
		this.map = map;
		this.oc = oc;
		this.p = p;
		Constants.PLACE_MODE = false;
		Constants.PLACE_TYPE = Constants.NONE;
		eraser = new Color(256, 0, 0, .5f);
	}
	
	public void render(Graphics g) {
		if(Constants.PLACE_MODE) {
			if(Constants.PLACE_TYPE == Constants.BARRIER_BLOCK) {
				int[] mapCoords = map.convertToTile(mx, my + Constants.TILE_SIZE);
				if((mapCoords[0] > 0 && mapCoords[0] < map.getWidth()) && (mapCoords[1] > 0 && mapCoords[1] < map.getHeight()) && map.getRawData()[mapCoords[0]][mapCoords[1]] == Constants.FLOOR && oc.getObject(mapCoords[0], mapCoords[1]) == null) {
					Images.BARRIER.setAlpha(.5f);
					g.drawImage(Images.BARRIER, Constants.mapOffsetX + mapCoords[0] * Constants.TILE_SIZE, Constants.mapOffsetY + mapCoords[1] * Constants.TILE_SIZE);
					Images.BARRIER.setAlpha(1f);
				}
			}
			
			g.setColor(Color.white);
			Fonts.MENU_TEXT.drawString(Constants.WIDTH/2 - Fonts.MENU_TEXT.getWidth("[SPACE] to Exit Place Mode.")/2, Constants.HEIGHT-64, "[SPACE] to Exit Place Mode.");
		}
		
		if(Constants.REMOVE_MODE) {
			int[] mapCoords = map.convertToTile(mx, my + Constants.TILE_SIZE);
			if((mapCoords[0] > 0 && mapCoords[0] < map.getWidth()) && (mapCoords[1] > 0 && mapCoords[1] < map.getHeight()) && (map.getRawData()[mapCoords[0]][mapCoords[1]] == Constants.FLOOR || map.getRawData()[mapCoords[0]][mapCoords[1]] == Constants.BARRIER) && (oc.getObject(mapCoords[0], mapCoords[1]) instanceof Barrier || oc.getObject(mapCoords[0], mapCoords[1]) == null)) {
				g.setColor(eraser);
				g.fillRect(Constants.mapOffsetX + mapCoords[0] * Constants.TILE_SIZE, Constants.mapOffsetY + mapCoords[1] * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
			}
			
			g.setColor(Color.white);
			Fonts.MENU_TEXT.drawString(Constants.WIDTH/2 - Fonts.MENU_TEXT.getWidth("[SPACE] to Exit Remove Mode.")/2, Constants.HEIGHT-64, "[SPACE] to Exit Remove Mode.");
		}
	}
	
	public void update(GameContainer gc) {
		mx = gc.getInput().getMouseX();
		my = gc.getInput().getMouseY();
		if(Constants.PLACE_MODE) {
			if(gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
				Constants.PLACE_MODE = false;
				Constants.PLACE_TYPE = Constants.NONE;
			}
			
			int[] mapCoords = map.convertToTile(mx, my + Constants.TILE_SIZE);
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !Constants.INVENTORY_OPEN && oc.getObject(mapCoords[0], mapCoords[1]) == null && hasBlockLeft()) {
				if ((map.getRawData()[mapCoords[0]][mapCoords[1]] == 0)
						&& ((mapCoords[0] != p.getX()) || (mapCoords[1] != p.getY()))) {
					
					if(Constants.PLACE_TYPE == Constants.BARRIER_BLOCK) {
						oc.add(new Barrier(mapCoords[0], mapCoords[1], this.map, this.oc));
						
						removeBlockFromInventory();
					}
				}
			}
			
			if(!hasBlockLeft()) {
				Constants.PLACE_MODE = false;
				Constants.PLACE_TYPE = Constants.NONE;
			}
		}
		
		if(Constants.REMOVE_MODE) {
			if(gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
				Constants.REMOVE_MODE = false;
			}
			
			int[] mapCoords = map.convertToTile(mx, my + Constants.TILE_SIZE);
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !Constants.INVENTORY_OPEN && oc.getObject(mapCoords[0], mapCoords[1]) instanceof Barrier) {
				if ((map.getRawData()[mapCoords[0]][mapCoords[1]] == Constants.BARRIER)
						&& ((mapCoords[0] != p.getX()) || (mapCoords[1] != p.getY()))) {
					oc.remove(oc.getObject(mapCoords[0], mapCoords[1]));
					map.getRawData()[mapCoords[0]][mapCoords[1]] = Constants.FLOOR;
					map.swap(mapCoords[0], mapCoords[1]);
				}
			}
		}
	}
	
	public boolean hasBlockLeft() {
		Slot[][] slots = p.getInventory().getSlots();
		for(int y = p.getInventory().getHeight()-1; y >= 0; y--) {
			for(int x = p.getInventory().getWidth()-1; x >= 0; x--) {
				Slot slot = slots[x][y];
				if(slot.getItem() instanceof BarrierItem) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void removeBlockFromInventory() {
		Slot[][] slots = p.getInventory().getSlots();
		loop:
		for(int y = p.getInventory().getHeight()-1; y >= 0; y--) {
			for(int x = p.getInventory().getWidth()-1; x >= 0; x--) {
				Slot slot = slots[x][y];
				if(slot.getItem() instanceof BarrierItem) {
					slot.setItem(null);
					break loop;
				}
			}
		}
	}
	
}
