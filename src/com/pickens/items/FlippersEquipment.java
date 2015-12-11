package com.pickens.items;

import java.util.Random;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class FlippersEquipment extends Equipment {

	int lastSpeed;
	int lastWaterSpeed;
	int speedEffect;
	
	Random r = new Random();
	
	public FlippersEquipment(int x, int y, Player p, Inventory i) {
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

	@Override
	public void damageCall(Player p) {
		if(p.isUnderwater()) {
			addDurability(-1);
		} else {
			addDurability(-(r.nextInt(3)+2));
		}
	}

	@Override
	public void onBreak() {
		// TODO Auto-generated method stub
		
	}

}
