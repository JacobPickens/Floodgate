package com.pickens.buffs;

import org.newdawn.slick.Image;

import com.pickens.states.PlayState;
import com.pickens.util.Constants;

public class FreezeBuff extends Buff {
	
	public FreezeBuff(int effect, int time, Image image, BuffManager b) {
		super(effect, time, image, b);
	}

	@Override
	public void modify() {
		Constants.FROZEN = true;
	}

	@Override
	public void undo() {
		Constants.FROZEN = false;
	}
	
}
