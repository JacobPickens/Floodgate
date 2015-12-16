package com.pickens.items;

import java.util.Random;

import com.pickens.objects.Player;
import com.pickens.util.Images;

public class FlipFlopEquipment extends Equipment {

	int lastSpeed;
	int lastWaterSpeed;
	int speedEffect;
	
	Random r = new Random();
	
	public FlipFlopEquipment(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setGearType(FEET);
		switch(getCondition()) {
		case Equipment.DAMAGED:
			speedEffect = 3;
			setDurability(50);
			break;
		case Equipment.USED:
			speedEffect = 5;
			setDurability(100);
			break;
		case Equipment.NEW:
			speedEffect = 7;
			setDurability(200);
			break;
		}
		setName(Equipment.getConditionString(getCondition()) + " Flip Flops");
		setDescription("* Increases movement speed by " + speedEffect +".\n* Increases swim speed by " + (int)Math.ceil(speedEffect*.5f) + "\n(Equipment)(Feet)");
		setImage(Images.ITEMS.getSubImage(3, 1));
		setType(Item.ULTRA);
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
		p.setSpeed(lastSpeed);
		p.setWaterSpeed(lastWaterSpeed);
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
		// TODO Auto-generated method stub
		
	}

}
