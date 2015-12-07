package com.pickens.items;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class BarrierItem extends Item {

	Image image;
	
	public BarrierItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setImage(Images.WALL_SHEET.getSubImage(new Random().nextInt(3) + 1, new Random().nextInt(3)));
		setName("Barrier");
		setDescription("* Used to block off water\n* Lasts 1 minute before the water destroys it.\n* (Placeable)");
		setType(Item.MINOR);
	}

	@Override
	public void render(Graphics g) {
		getImage().draw(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x), y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y));
	}

	@Override
	public void action() {
		Inventory.enterPlacemode(Constants.BARRIER_BLOCK);
	}

}
