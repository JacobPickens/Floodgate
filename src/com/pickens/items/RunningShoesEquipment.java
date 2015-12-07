package com.pickens.items;

import com.pickens.objects.Player;
import com.pickens.util.Images;

public class RunningShoesEquipment extends Equipment {

	int lastSpeed;
	int lastWaterSpeed;
	int speedEffect;
	
	public RunningShoesEquipment(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setGearType(FEET);
		switch(getCondition()) {
		case Equipment.DAMAGED:
			speedEffect = 1;
			break;
		case Equipment.USED:
			speedEffect = 3;
			break;
		case Equipment.NEW:
			speedEffect = 5;
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

}
