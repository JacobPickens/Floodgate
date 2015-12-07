package com.pickens.items;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class FlippersEquipment extends Equipment {

	int lastSpeed;
	int lastWaterSpeed;
	int speedEffect;
	
	public FlippersEquipment(int x, int y, Player p, Inventory i) {
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
		setName(Equipment.getConditionString(getCondition()) + " Flippers");
		setDescription("* Increases swim speed by " + speedEffect +".\n* Slows move speed by " + (int)Math.ceil(speedEffect*.8f) + "\n* Disables sprinting.\n* (Equipment)(Feet)");
		setImage(Images.ITEMS.getSubImage(1, 0));
		setType(Item.MAJOR);
	}

	@Override
	public void modify() {
		lastSpeed = p.getSpeed();
		lastWaterSpeed = p.getWaterSpeed();
		p.setWaterSpeed(lastWaterSpeed - speedEffect);
		p.setSpeed((int) (lastSpeed+Math.ceil(speedEffect*.8f)));
		Constants.CAN_SPRINT = false;
		System.out.println(p.getSpeed());
	}

	@Override
	public void undo() {
		p.setSpeed(lastSpeed);
		p.setWaterSpeed(lastWaterSpeed);
		Constants.CAN_SPRINT = true;
	}

	@Override
	public void action() {
		
	}

}
