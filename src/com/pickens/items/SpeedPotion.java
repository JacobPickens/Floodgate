package com.pickens.items;

import org.newdawn.slick.Image;

import com.pickens.objects.Player;
import com.pickens.util.Images;

public class SpeedPotion extends Item {
	
	int amount;
	
	public SpeedPotion(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		amount = 3;
		setName("Potion of Speed");
		setDescription("* Increases all speeds by " + amount + " for 5 seconds.");
		setImage(Images.POTIONS.getSubImage(0, 0));
		setType(Item.NORMAL);
	}

	@Override
	public void action() {
		p.getBuffs().add(new SpeedBuff(5*60, getImage(), p.getBuffs()));
		i.getSlots()[x][y].setItem(null);
	}
	
	private class SpeedBuff extends Buff {

		int lastSpeed;
		int lastWaterSpeed;
		
		public SpeedBuff(int time, Image image, BuffManager b) {
			super(time, image, b);
		}

		@Override
		public void modify() {
			lastSpeed = p.getSpeed();
			lastWaterSpeed = p.getWaterSpeed();
			p.setSpeed(lastSpeed - amount);
			p.setWaterSpeed(lastWaterSpeed - amount);
		}

		@Override
		public void undo() {
			p.setSpeed(p.getSpeed()+amount);
			p.setWaterSpeed(p.getWaterSpeed()+amount);
		}
		
	}

}
