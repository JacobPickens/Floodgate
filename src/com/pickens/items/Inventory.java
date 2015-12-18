package com.pickens.items;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class Inventory {

	Slot[][] slots;
	private int width;
	private int height;
	private int x, y;
	
	public static final int SLOT_SPACING = 4;
	
	Color color;
	
	Random r = new Random();
	
	Player p;
	private ItemInfo ii;
	private InventoryManager im;
	private RemoveSlot remove;
	private HeadSlot head;
	private BodySlot body;
	private FeetSlot feet;
	
	public Inventory(Player player) {
		this.p = player;
		width = Constants.currentCharacter.getInventoryWidth();
		height = Constants.currentCharacter.getInventoryHeight();
		this.x = Constants.WIDTH/2 - (width * Constants.TILE_SIZE + (SLOT_SPACING * width))/2;
		this.y = Constants.HEIGHT/2 - (height * Constants.TILE_SIZE + (SLOT_SPACING * height))/2;
		slots = new Slot[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Slot s = new Slot(x, y, this.x, this.y, this);
				if(r.nextInt(100) < 10) {
					s.setItem(new BarrierItem(x, y, p, this));
				} else if(r.nextInt(100) < 5) {
					s.setItem(new BubbleItem(x, y, p, this));
				}
				s.setItem(new MinorPotionItem(x, y, p, this));
				slots[x][y] = s;
			}
		}
		slots[0][0].setItem(new MapItem(0, 0, p, this));
		slots[1][0].setItem(new SpeedPotionItem(1, 0, p, this));
		remove = new RemoveSlot(-1, height-1, this.x, this.y, this);
		head = new HeadSlot(0, -1, 5, Constants.HEIGHT-133, this);
		body = new BodySlot(1, -1, 5, Constants.HEIGHT-133, this);
		feet = new FeetSlot(2, -1, 5, Constants.HEIGHT-133, this);
		
		ii = new ItemInfo();
		im = new InventoryManager();
		
		color = new Color(0, 0, 0, 0f);
	}
	
	public void render(Graphics g) {
		if(Constants.PLAYER_PAUSED) {
			if(color.a < .6f) {
				color.a += .05f;
			}
		} else {
			if(color.a > -.1f) {
				color.a -= .05f;
			}
		}
		g.setColor(color);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		head.render(g);
		body.render(g);
		feet.render(g);
		if(Constants.INVENTORY_OPEN) {
			remove.render(g);
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					slots[x][y].render(g);
				}
			}
			
			g.setColor(Color.white);
			Fonts.CLASS_TEXT.drawString(x, y+(height * Constants.TILE_SIZE + (SLOT_SPACING * height))+25, "Left Click to move. Double Click to use. Right Click for more info.");
			
			if(!Constants.PLACE_MODE && !Constants.REMOVE_MODE) {
				g.setColor(Color.white);
				Fonts.MENU_TEXT.drawString(Constants.WIDTH/2 - Fonts.MENU_TEXT.getWidth("[SPACE] to Enter Remove Mode.")/2, Constants.HEIGHT-64, "[SPACE] to Enter Remove Mode.");
			}
			
			if(Constants.REROLL) {
				g.setColor(Color.white);
				Fonts.MENU_TEXT.drawString(Constants.WIDTH/2 - Fonts.MENU_TEXT.getWidth("Click an item to reroll.")/2, 64, "Click an item to reroll.");
			}
			if(Constants.CLONE) {
				g.setColor(Color.white);
				Fonts.MENU_TEXT.drawString(Constants.WIDTH/2 - Fonts.MENU_TEXT.getWidth("Click an item to duplicate.")/2, 64, "Click an item to duplicate.");
			}
			im.render(g);
			ii.render(g);
		}
	}
	
	public void update(GameContainer gc, int delta) {
		head.check(gc, delta);
		body.check(gc, delta);
		feet.check(gc, delta);
		if(Constants.INVENTORY_OPEN) {
			remove.check(gc, delta);
			head.ox = this.x;
			head.oy = this.y;
			body.ox = this.x;
			body.oy = this.y;
			feet.ox = this.x;
			feet.oy = this.y;
			if(gc.getInput().isKeyPressed(Input.KEY_SPACE) && !Constants.REMOVE_MODE && !Constants.PLACE_MODE) {
				Constants.REMOVE_MODE = true;
				Constants.INVENTORY_OPEN = false;
				Constants.PLAYER_PAUSED = false;
				Constants.PAUSED = false;
			}
					
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					slots[x][y].check(gc, delta);
				}
			}
			ii.update(gc, delta);
			im.update(gc);
		} else {
			head.ox = 5;
			head.oy = Constants.HEIGHT-133;
			body.ox = 5;
			body.oy = Constants.HEIGHT-133;
			feet.ox = 5;
			feet.oy = Constants.HEIGHT-133;
		}
		
//		if(gc.getInput().isKeyPressed(Input.KEY_F)) {
//			slots[0][0].setItem(new MapItem(0, 0, p, this));
//		}
	}

	public Slot[][] getSlots() {
		return slots;
	}

	public void setSlots(Slot[][] slots) {
		this.slots = slots;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public ItemInfo getItemInfo() {
		return ii;
	}
	
	public InventoryManager getInventoryManager() {
		return im;
	}
	
	public static void enterPlacemode(int i) {
		Constants.PLAYER_PAUSED = false;
		Constants.REMOVE_MODE = false;
		Constants.PLACE_MODE = true;
		Constants.PLACE_TYPE = i;
		Constants.INVENTORY_OPEN = false;
		Constants.PAUSED = false;
	}

	public HeadSlot getHead() {
		return head;
	}

	public BodySlot getBody() {
		return body;
	}

	public FeetSlot getFeet() {
		return feet;
	}
	
}
