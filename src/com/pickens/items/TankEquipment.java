package com.pickens.items;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.pickens.objects.Player;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class TankEquipment extends Equipment {

	int lastDrownSpeed;
	int lastBreatheSpeed;
	int lastSpeed;
	int lastWaterSpeed;
	int speedEffect;
	float maxOxygen;
	float oxygen;
	
	public TankEquipment(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setGearType(BODY);
		maxOxygen = 5;
		switch(getCondition()) {
		case Equipment.DAMAGED:
			speedEffect = 3;
			maxOxygen = 5;
			setDurability(10);
			break;
		case Equipment.USED:
			speedEffect = 5;
			maxOxygen = 15;
			setDurability(35);
			break;
		case Equipment.NEW:
			speedEffect = 7;
			maxOxygen = 20;
			setDurability(75);
			break;
		}
		if(getCondition() != Equipment.NEW) {
			oxygen = maxOxygen-new Random().nextInt(5);
		} else {
			oxygen = maxOxygen;
		}
		
		// TODO: Make a health bar for oxygen left
		setName(Equipment.getConditionString(getCondition()) + " Scuba Tank");
		setDescription("* Has " + (int)oxygen + " stored oxygen.\n* Slows oxygen loss by " + (int)Math.ceil(speedEffect*.5) +".\n* Slows breathing by " + (int)Math.ceil(speedEffect*.3) + ".\n* Slows both swim and land speed by " + (int)Math.ceil(speedEffect*.3f) + ".\n* Disables sprinting.\n* Fillable with bubbles.\n(Equipment)(Body)");
		setImage(Images.ITEMS.getSubImage(2, 0));
		setType(Item.MAJOR);
	}

	@Override
	public void modify() {
		lastDrownSpeed = p.getDrownSpeed();
		lastBreatheSpeed = p.getBreatheSpeed();
		lastSpeed = p.getSpeed();
		lastWaterSpeed = p.getWaterSpeed();
		p.setDrownSpeed(lastDrownSpeed + (int)Math.ceil(speedEffect*.5));
		p.setBreatheSpeed(lastBreatheSpeed + (int)Math.ceil(speedEffect*.3));
		p.setSpeed(lastSpeed + (int)Math.ceil(speedEffect*.3));
		p.setWaterSpeed(lastWaterSpeed + (int)Math.ceil(speedEffect*.3));
		Constants.CAN_SPRINT = false;
	}

	@Override
	public void undo() {
		p.setDrownSpeed(lastDrownSpeed);
		p.setBreatheSpeed(lastBreatheSpeed);
		p.setSpeed(lastSpeed);
		p.setWaterSpeed(lastWaterSpeed);
		Constants.CAN_SPRINT = true;
	}

	@Override
	public void action() {
		
	}
	
	public void refill(int o) {
		if(oxygen + o > maxOxygen) {
			oxygen = maxOxygen;
		} else {
			oxygen += o;
		}
		damage(PlayState.player);
		setName(Equipment.getConditionString(getCondition()) + " Scuba Tank");
		setDescription("* Has " + (int)oxygen + " stored oxygen left.\n* Slows oxygen loss by " + (int)Math.ceil(speedEffect*.5) +".\n* Slows breathing by " + (int)Math.ceil(speedEffect*.3) + ".\n* Slows both swim and land speed by " + (int)Math.ceil(speedEffect*.3f) + ".\n* Disables sprinting.\n* Fillable with bubbles.\n(Equipment)(Body)");
	}
	
	@Override
	public void render(Graphics g) {
		getImage().draw(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x), y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y));
		g.setColor(Color.white);
		g.fillRect(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x) + 2, (y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y))+Constants.TILE_SIZE - 8, (Constants.TILE_SIZE-4), 6);
		g.setColor(Color.red);
		float scale = (float)getDurability()/(float)getMaxDurability();
		g.fillRect(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x) + 2, (y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y))+Constants.TILE_SIZE - 8, (Constants.TILE_SIZE-4)*scale, 6);
		
		g.setColor(Color.white);
		g.fillRect(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x) + 2, (y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y))+Constants.TILE_SIZE - 16, (Constants.TILE_SIZE-4), 6);
		g.setColor(Color.cyan);
		scale = oxygen/maxOxygen;
		g.fillRect(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x) + 2, (y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y))+Constants.TILE_SIZE - 16, (Constants.TILE_SIZE-4)*scale, 6);
	}
	
	public void deplete() {
		oxygen--;
		setDescription("* Has " + (int)oxygen + " stored oxygen left.\n* Slows oxygen loss by " + (int)Math.ceil(speedEffect*.5) +".\n* Slows breathing by " + (int)Math.ceil(speedEffect*.3) + ".\n* Slows both swim and land speed by " + (int)Math.ceil(speedEffect*.3f) + ".\n* Disables sprinting.\n* Fillable with bubbles.\n(Equipment)(Body)");
		if(oxygen == 0) {
			undo();
			i.getBody().setItem(null);
			setName(Equipment.getConditionString(getCondition()) + " Empty Scuba Tank");
			loop:
			for (int y = 0; y < Player.inv.getHeight(); y++) {
				for (int x = 0; x < Player.inv.getWidth(); x++) {
					Slot slot = Player.inv.getSlots()[x][y];
					if (slot.getItem() == null) {
						this.x = slot.x;
						this.y = slot.y;
						slot.setItem(this);
						break loop;
					}
				}
			}
		}
	}
	
	public float getOxygen() {
		return oxygen;
	}

	@Override
	public void damageCall(Player p) {
		addDurability(-(r.nextInt(4)+1));
	}

	@Override
	public void onBreak() {
		
	}

}
