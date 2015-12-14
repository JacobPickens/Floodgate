package com.pickens.items;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class LuckyUnderwearEquipment extends Equipment {

	int effect;
	
	public LuckyUnderwearEquipment(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setGearType(HEAD);
		switch(getCondition()) {
		case Equipment.DAMAGED:
			effect = 5;
			setDurability(2);
			break;
		case Equipment.USED:
			effect = 12;
			setDurability(3);
			break;
		case Equipment.NEW:
			effect = 20;
			setDurability(6);
			break;
		}
		setName(Equipment.getConditionString(getCondition()) + " Lucky Underwear");
		setDescription("* Wearing this lovely head piece increases the chance of major chests by " + effect + "%.\n * The effect only takes place when a new map is created, so you must complete a level for the effect to commense.\n* (Equipment)(Head)");
		setImage(Images.ITEMS.getSubImage(0, 1));
		setType(Item.MAJOR);
	}

	@Override
	public void modify() {
		Constants.MAJOR_CHANCE += effect;
	}

	@Override
	public void undo() {
		Constants.MAJOR_CHANCE -= effect;
	}

	@Override
	public void damageCall(Player p) {
		addDurability(-1);
	}

	@Override
	public void onBreak() {
		
	}

	@Override
	public void action() {
		
	}

}
