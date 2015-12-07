package com.pickens.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.pickens.gui.GuiManager;
import com.pickens.gui.InventoryButton;
import com.pickens.gui.MiniMap;
import com.pickens.gui.ToggleBlueButton;
import com.pickens.gui.ToggleGreenButton;
import com.pickens.gui.ToggleRedButton;
import com.pickens.items.Inventory;
import com.pickens.map.TileMap;
import com.pickens.objects.ObjectController;
import com.pickens.objects.Player;
import com.pickens.util.Constants;

public class PlayState extends BasicGameState {
	
	public static TileMap map;
	public static ObjectController objects;
	
	public static Player player;
	public static GuiManager gm;
	public static MiniMap mm;
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		reset();
	}
	
	public static void reset() {
		objects = new ObjectController();
		map = new TileMap(200, 200, 12, objects);
		map.spawnObjects(objects);
		Constants.mapOffsetX = (Constants.WIDTH/2)-((map.getWidth()*Constants.TILE_SIZE)/2) + Constants.TILE_SIZE/2;
		Constants.mapOffsetY = (Constants.HEIGHT/2)-((map.getHeight()*Constants.TILE_SIZE)/2) + Constants.TILE_SIZE/2;
		Constants.mapTargetX = Constants.mapOffsetX;
		Constants.mapTargetY = Constants.mapOffsetY;
		
		player = new Player(map.getWidth()/2-1, map.getHeight()/2-1, map, objects);
		
		gm = new GuiManager(0, 0, player);
		gm.setJustification(GuiManager.RIGHT_JUST);
		gm.add(new InventoryButton(Constants.WIDTH, Constants.HEIGHT, gm));
		gm.setJustification(GuiManager.LEFT_JUST);
		gm.add(new ToggleRedButton(0, Constants.HEIGHT-128, gm));
		gm.add(new ToggleGreenButton(0 + 128, Constants.HEIGHT-128, gm));
		gm.add(new ToggleBlueButton(0 + 128*2, Constants.HEIGHT-128, gm));
		mm = new MiniMap(Constants.WIDTH, 0, gm, map, objects, player);
		gm.add(mm);
		gm.setJustification(GuiManager.CENTER_JUST);
		
		Constants.RED_DOOR_STATE = false;
		Constants.GREEN_DOOR_STATE = false;
		Constants.BLUE_DOOR_STATE = false;
	}
	
	public static void win() {
		objects = new ObjectController();
		map = new TileMap(200, 200, 12, objects);
		map.spawnObjects(objects);
		Constants.mapOffsetX = (Constants.WIDTH/2)-((map.getWidth()*Constants.TILE_SIZE)/2) + Constants.TILE_SIZE/2;
		Constants.mapOffsetY = (Constants.HEIGHT/2)-((map.getHeight()*Constants.TILE_SIZE)/2) + Constants.TILE_SIZE/2;
		Constants.mapTargetX = Constants.mapOffsetX;
		Constants.mapTargetY = Constants.mapOffsetY;
		
		player.reset(map, objects, map.getWidth()/2-1, map.getHeight()/2-1);
		mm.reset(map, objects);
		
		Constants.INVENTORY_OPEN = false;
		Constants.RED_DOOR_STATE = false;
		Constants.GREEN_DOOR_STATE = false;
		Constants.BLUE_DOOR_STATE = false;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		map.render(Constants.mapOffsetX, Constants.mapOffsetY, g);
		objects.render(g);
		player.render(g);
		
		gm.render(g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE) && !Constants.INVENTORY_OPEN && !Constants.MINI_MAP_FOCUSED && !Constants.PLAYER_PAUSED) {
			System.exit(0);
		} else if(gc.getInput().isKeyDown(Input.KEY_ESCAPE)) {
			InventoryButton.toggle();
			Constants.INVENTORY_OPEN = false;
			Constants.MINI_MAP_FOCUSED = false;
			Constants.PLAYER_PAUSED = false;
			Constants.PAUSED = false;
		}
		
		map.update(gc, delta);
		objects.update(gc, delta);
		player.update(gc, sbg, delta);
		
		gm.update(gc, delta);
	}

	public int getID() {
		return Constants.GAME_STATE;
	}
	
}