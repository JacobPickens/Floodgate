package com.pickens.objectives;

import com.pickens.items.MapItem;
import com.pickens.items.Slot;
import com.pickens.states.PlayState;

public class SwimObjective extends Objective {

	public SwimObjective(Objectives objs) {
		super(objs);
		setName("Swim Test");
		setShortDescription("Swim in water.");
		setLongDescription("Touch some of that nasty sewer water.");
	}

	@Override
	public void check() {
		if(PlayState.player.isUnderwater()) {
			complete();
		}
	}

	@Override
	public void reward() {
		Slot slot = PlayState.player.getInventory().getEmptySlot();
		
		if(slot != null) {
			slot.setItem(new MapItem(slot.getX(), slot.getY(), PlayState.player, PlayState.player.getInventory()));
		}
	}

}
