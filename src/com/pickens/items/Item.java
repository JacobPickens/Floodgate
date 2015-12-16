package com.pickens.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.objects.Player;
import com.pickens.util.Constants;

public abstract class Item implements Cloneable {

	int x, y, ox, oy;
	
	public static final int MINOR = 0;
	public static final int NORMAL = 1;
	public static final int MAJOR = 2;
	public static final int ULTRA = 3;
	
	int type = MINOR;
	
	Player p;
	Inventory i;
	
	private String name;
	private String description;
	private Image image;
	
	public Item(int x, int y, Player p, Inventory i) {
		this.x = x;
		this.y = y;
		this.p = p;
		this.i = i;
	}
	
	public void render(Graphics g) {
		getImage().draw(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x), y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y));
	}
	
	public void update(GameContainer gc, int delta) {
		
	}
	
	public abstract void action();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	 @Override
	  protected Object clone() throws CloneNotSupportedException {
	      return super.clone();
	  }
	
}
