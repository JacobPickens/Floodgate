package com.pickens.items;

import java.util.Random;

import org.newdawn.slick.Graphics;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class BubbleItem extends Item {
	
	int amount;
	
	public BubbleItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		amount = new Random().nextInt(5)+1;
		setName("Bubble");
		setDescription("* Gives " + amount + " oxygen bubbles.\n* Used for quick bursts of oxygen.\n* 1 time use.\n* Can be used to fill scuba tanks.");
		setImage(Images.BUBBLE);
		setType(Item.NORMAL);
	}

	@Override
	public void render(Graphics g) {
		getImage().draw(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x) + (int)(Images.BUBBLE.getWidth()*1.5), y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y) + (int)(Images.BUBBLE.getHeight()*1.5));
	}

	@Override
	public void action() {
		if(p.getOxygen()+amount <= Constants.currentCharacter.getOxygen()) {
			p.setOxygen(p.getOxygen()+amount);
		} else if(p.getOxygen() <= Constants.currentCharacter.getOxygen()) {
			p.setOxygen(Constants.currentCharacter.getOxygen());
		}
		i.getSlots()[x][y].setItem(null);
	}

}
