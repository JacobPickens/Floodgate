package com.pickens.items;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.objects.Player;

public class InventoryManager {

	int mx, my;
	int sx, sy;
	
	Item item;
	
	public InventoryManager() {
		
	}
	
	public void render(Graphics g) {
		if(hasItem()) {
			item.getImage().draw(mx - item.getImage().getWidth()/2, my - item.getImage().getHeight()/2);
		}
	}
	
	public void update(GameContainer gc) {
		mx = gc.getInput().getMouseX();
		my = gc.getInput().getMouseY();
	}

	public boolean hasItem() {
		if(item == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public void putBack() {
		if(item != null) {
			if(item.y == -1) {
				switch(item.x) {
				case 0:
					Player.inv.getHead().setItem(item);
					break;
				case 1:
					Player.inv.getBody().setItem(item);
					break;
				case 2:
					Player.inv.getFeet().setItem(item);
					break;
				default:
					
				}
			} else {
				Player.inv.getSlots()[item.x][item.y].setItem(item);
			}
			setItem(null);
		}
	}
	
}
