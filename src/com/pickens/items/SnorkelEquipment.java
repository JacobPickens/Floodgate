package com.pickens.items;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.pickens.objects.Player;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class SnorkelEquipment extends Equipment {

	int lastDrownSpeed;
	int lastBreatheSpeed;
	int lastSpeed;
	int lastWaterSpeed;
	int speedEffect;
	float oxygen;
	
	public SnorkelEquipment(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setGearType(HEAD);
		switch(getCondition()) {
		case Equipment.DAMAGED:
			speedEffect = 3;
			setDurability(12);
			break;
		case Equipment.USED:
			speedEffect = 5;
			setDurability(25);
			break;
		case Equipment.NEW:
			speedEffect = 7;
			setDurability(50);
			break;
		}
		
		setName(Equipment.getConditionString(getCondition()) + " Snorkel");
		setDescription("* Slows oxygen loss by " + (int)Math.ceil(speedEffect*.5) +".\n* Slows breathing by " + (int)Math.ceil(speedEffect*.4) + ".\n(Equipment)(Head)");
		setImage(Images.ITEMS.getSubImage(2, 1));
		setType(Item.NORMAL);
	}

	@Override
	public void modify() {
		lastDrownSpeed = p.getDrownSpeed();
		lastBreatheSpeed = p.getBreatheSpeed();
		p.setDrownSpeed(lastDrownSpeed + (int)Math.ceil(speedEffect*.5));
		p.setBreatheSpeed(lastBreatheSpeed + (int)Math.ceil(speedEffect*.4));
	}

	@Override
	public void undo() {
		p.setDrownSpeed(p.getDrownSpeed() - (int)Math.ceil(speedEffect*.5));
		p.setBreatheSpeed(p.getBreatheSpeed() - (int)Math.ceil(speedEffect*.4));
	}

	@Override
	public void action() {
		
	}
	
	public float getOxygen() {
		return oxygen;
	}

	@Override
	public void damageCall(Player p) {
		addDurability(-1);
	}

	@Override
	public void onBreak() {
		
	}

}
