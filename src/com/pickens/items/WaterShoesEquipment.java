package com.pickens.items;

import java.util.Random;

import com.pickens.objects.Player;
import com.pickens.util.Images;

public class WaterShoesEquipment extends Equipment {

	int lastSpeed;
	int lastWaterSpeed;
	int speedEffect;
	
	Random r = new Random();
	
	public WaterShoesEquipment(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setGearType(FEET);
		speedEffect = 1;
		setDurability(100);
		setName("Water Shoes");
		setDescription("* Increases movement speed by " + speedEffect +".\n* Increases swim speed by " + (int)Math.ceil(speedEffect*.5f) + "\n(Equipment)(Feet)");
		setImage(Images.ITEMS.getSubImage(3, 2));
		setType(Item.NORMAL);
	}

	@Override
	public void modify() {
		lastSpeed = p.getSpeed();
		lastWaterSpeed = p.getWaterSpeed();
		p.setSpeed(lastSpeed - speedEffect);
		p.setWaterSpeed(lastWaterSpeed - speedEffect);
	}

	@Override
	public void undo() {
		p.setSpeed(p.getSpeed() + speedEffect);
		p.setWaterSpeed(p.getWaterSpeed() + speedEffect);
	}

	@Override
	public void action() {
		
	}

	@Override
	public void damageCall(Player p) {
		addDurability(-(r.nextInt(3)+1));
	}

	@Override
	public void onBreak() {
		
	}

}
