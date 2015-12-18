package com.pickens.util;

import java.util.Random;

import org.newdawn.slick.Color;

import com.pickens.items.BarrierItem;
import com.pickens.items.BubbleItem;
import com.pickens.items.CarbonatedWaterItem;
import com.pickens.items.DiceItem;
import com.pickens.items.DuplicationPotionItem;
import com.pickens.items.FlipFlopEquipment;
import com.pickens.items.FlippersEquipment;
import com.pickens.items.FreezePotionItem;
import com.pickens.items.Inventory;
import com.pickens.items.Item;
import com.pickens.items.LuckyUnderwearEquipment;
import com.pickens.items.MajorItems;
import com.pickens.items.MapItem;
import com.pickens.items.MinorItems;
import com.pickens.items.NormalItems;
import com.pickens.items.RunningShoesEquipment;
import com.pickens.items.SnorkelEquipment;
import com.pickens.items.SpeedPotionItem;
import com.pickens.items.TankEquipment;
import com.pickens.items.UltraMajorItems;
import com.pickens.items.WaterShoesEquipment;
import com.pickens.objects.Player;

public class Constants {

	public static final int MENU_STATE = 0;
	public static final int GAME_STATE = 1;
	
	public static int WIDTH = 1500;
	public static int HEIGHT = 800;
	
	public static final int TILE_SIZE = 64;
	
	//////////////////// Tiles ////////////////////
	public static final int FLOOR = 0;
	public static final int WALL = 1;
	public static final int EMPTY = 2;
	public static final int RIM_TOP = 3;
	public static final int RIM_TOP_CORNER_LEFT = 4;
	public static final int RIM_TOP_CORNER_RIGHT = 5;
	public static final int RIM_LEFT = 6;
	public static final int RIM_RIGHT = 7;
	public static final int RIM_BOTTOM_CORNER_LEFT = 8;
	public static final int RIM_BOTTOM_CORNER_RIGHT = 9;
	public static final int RIM_BOTTOM = 10;
	public static final int RIM_TOP_SHARP_CORNER_LEFT = 11;
	public static final int RIM_TOP_SHARP_CORNER_RIGHT = 12;
	public static final int RIM_BOTTOM_SHARP_CORNER_LEFT = 13;
	public static final int RIM_BOTTOM_SHARP_CORNER_RIGHT = 14;
	public static final int WATER = 15;
	public static final int STOPPER = 16;
	public static final int BARRIER = 17;
	public static final int CHEST = 18;
	
	// Door states
	public static boolean RED_DOOR_STATE = false;
	public static boolean GREEN_DOOR_STATE = false;
	public static boolean BLUE_DOOR_STATE = false;
	
	// Map Offset
	public static float mapOffsetX = 0;
	public static float mapOffsetY = 0;
	public static float mapTargetX = 0;
	public static float mapTargetY = 0;
	
	public static boolean PLAYER_PAUSED = false;
	public static boolean PAUSED = false;
	
	//////////////////// Chances ////////////////////
	public static int NORMAL_CHANCE = 40;
	public static int MAJOR_CHANCE = 20;
	public static int ULTRA_CHANCE = 5;
	
	//////////////////// Classes ////////////////////
	public static Character NORMAL;
	public static Character RUNNER;
	public static Character BIG_LUNGED;
	public static Character currentCharacter;
	public static void loadClasses() {
		NORMAL = new Character(15, 9, 2, 4, 4);
		RUNNER = new Character(10, 6, 1, 3, 3);
		BIG_LUNGED = new Character(20, 10, 3, 6, 5);
	}
	
	//////////////////// Place Types ////////////////////
	public static final int NONE = 0;
	public static final int BARRIER_BLOCK = 1;
	
	//////////////////// Game States ////////////////////
	public static boolean INVENTORY_OPEN = false;
	public static boolean PLACE_MODE = false;
	public static boolean REMOVE_MODE = false;
	public static int PLACE_TYPE = NONE;
	public static boolean REROLL = false;
	public static boolean CLONE = false;
	
	public static boolean MINI_MAP = false;
	public static boolean MINI_MAP_FOCUSED = false;
	public static boolean CAN_SPRINT = true;
	
	public static boolean FROZEN = false;
	
	//////////////////// Player Stats ////////////////////
	public static int MAP_NUMBER = 1;
	
	//////////////////// Loot Pools ////////////////////
	private static MinorItems[] minorPool = {MinorItems.BARRIER};
	private static NormalItems[] normalPool = {NormalItems.BUBBLE, NormalItems.BUBBLE, NormalItems.BUBBLE, NormalItems.WATER_SHOES, NormalItems.SNORKEL, NormalItems.SPEED_POTION};
	private static MajorItems[] majorPool = {MajorItems.MAP, MajorItems.RUNNING_SHOES, MajorItems.FLIPPERS, MajorItems.DICE, MajorItems.TANK, MajorItems.UNDERWEAR, MajorItems.CARBONATED_WATER, MajorItems.ICE_POTION};
	private static UltraMajorItems[] ultraPool = {UltraMajorItems.FLIP_FLOPS, UltraMajorItems.DUPLICATION};
	
	private static Random r = new Random();
	public static Item rollMinor(int x, int y, Player p, Inventory inv) {
		MinorItems i = minorPool[r.nextInt(minorPool.length)];
		
		switch(i) {
		case BARRIER:
			return new BarrierItem(x, y, p, inv);
		default:
			return new BarrierItem(x, y, p, inv);
		}
	}
	
	public static Item rollNormal(int x, int y, Player p, Inventory inv) {
		NormalItems i = normalPool[r.nextInt(normalPool.length)];
		
		switch(i) {
		case BUBBLE:
			return new BubbleItem(x, y, p, inv);
		case WATER_SHOES:
			return new WaterShoesEquipment(x, y, p, inv);
		case SNORKEL:
			return new SnorkelEquipment(x, y, p, inv);
		case SPEED_POTION:
			return new SpeedPotionItem(x, y, p, inv);
		default:
			return new BarrierItem(x, y, p, inv);
		}
	}
	
	
	// Todo: Make gear actually work
	public static Item rollMajor(int x, int y, Player p, Inventory inv) {
		MajorItems i = majorPool[r.nextInt(majorPool.length)];
		
		switch(i) {
		case MAP:
			return new MapItem(x, y, p, inv);
		case RUNNING_SHOES:
			return new RunningShoesEquipment(x, y, p, inv);
		case FLIPPERS:
			return new FlippersEquipment(x, y, p, inv);
		case DICE:
			return new DiceItem(x, y, p, inv);
		case TANK:
			return new TankEquipment(x, y, p, inv);
		case UNDERWEAR:
			return new LuckyUnderwearEquipment(x, y, p, inv);
		case CARBONATED_WATER:
			return new CarbonatedWaterItem(x, y, p, inv);
		case ICE_POTION:
			return new FreezePotionItem(x, y, p, inv);
		default:
			return new BarrierItem(x, y, p, inv);
		}
	}
	
	public static Item rollUltra(int x, int y, Player p, Inventory inv) {
		UltraMajorItems i = ultraPool[r.nextInt(ultraPool.length)];
		
		switch(i) {
		case FLIP_FLOPS:
			return new FlipFlopEquipment(x, y, p, inv);
		case DUPLICATION:
			return new DuplicationPotionItem(x, y, p, inv);
		default:
			return new BarrierItem(x, y, p, inv);
		}
	}
	
	public static String getTypeString(int type) {
		String t;
		switch(type) {
		case Item.NORMAL:
			t = "Normal";
			break;
		case Item.MAJOR:
			t = "Major";
			break;
		case Item.ULTRA:
			t = "Ultra-Major";
			break;
		default:
			t = "Minor";
			break;
		}
		return t;
	}
	
	public static Color getTypeColor(int type) {
		Color c;
		switch(type) {
		case Item.NORMAL:
			c = Color.blue;
			break;
		case Item.MAJOR:
			c = Color.red;
			break;
		case Item.ULTRA:
			c = Color.magenta;
			break;
		default:
			c = Color.gray;
			break;
		}
		return c;
	}
}
