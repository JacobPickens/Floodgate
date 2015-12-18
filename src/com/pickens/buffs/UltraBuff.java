package com.pickens.buffs;

import org.newdawn.slick.Image;

import com.pickens.util.Constants;

public class UltraBuff extends Buff {
	
	public UltraBuff(int effect, int time, Image image, BuffManager b) {
		super(effect, time, image, b);
	}

	@Override
	public void modify() {
		Constants.CHEST_STATE = Constants.ULTRA_STATE;
	}

	@Override
	public void undo() {
		Constants.CHEST_STATE = Constants.RANDOM_STATE;
	}
	
}

