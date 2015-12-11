package com.pickens.items;

import java.util.Random;

import com.pickens.objects.Player;
import com.pickens.util.Images;

public class RunningShoesEquipment extends Equipment {

	int lastSpeed;
	int lastWaterSpeed;
	int speedEffect;
	
	Random r = new Random();
	
	public RunningShoesEquipment(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setGearType(FEET);
		switch(getCondition()) {
		case Equipment.DAMAGED:
			speedEffect = 1;
			setDurability(50);
			break;
		case Equipment.USED:
			speedEffect = 3;
			setDurability(100);
			break;
		case Equipment.NEW:
			speedEffect = 5;
			setDurability(200);
			break;
		}
		setName(Equipment.getConditionString(getCondition()) + " Shoes of Running");
		setDescription("* Increases movement speed by " + speedEffect +".\n* Slows swim speed by " + (int)Math.ceil(speedEffect*.5f) + "\n(Equipment)(Feet)");
		setImage(Images.ITEMS.getSubImage(1, 0));
		setType(Item.MAJOR);
	}

	@Override
	public void modify() {
		lastSpeed = p.getSpeed();
		lastWaterSpeed = p.getWaterSpeed();
		p.setSpeed(lastSpeed - speedEffect);
		p.setWaterSpeed((int)(lastWaterSpeed + Math.ceil(speedEffect*.5f)));
	}

	@Override
	public void undo() {
		p.setSpeed(lastSpeed);
		p.setWaterSpeed(lastWaterSpeed);
	}

	@Override
	public void action() {
		
	}

	@Override
	public void damageCall(Player p) {
		if(p.isUnderwater()) {
			addDurability(-(r.nextInt(3)+2));
		} else {
			addDurability(-1);
		}
	}

	@Override
	public void onBreak() {
		// TODO Auto-generated method stub
		
	}

}
