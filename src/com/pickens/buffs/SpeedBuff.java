package com.pickens.buffs;

import org.newdawn.slick.Image;

import com.pickens.states.PlayState;

public class SpeedBuff extends Buff {

	int lastSpeed;
	int lastWaterSpeed;
	
	public SpeedBuff(int effect, int time, Image image, BuffManager b) {
		super(effect, time, image, b);
	}

	@Override
	public void modify() {
		lastSpeed = PlayState.player.getSpeed();
		lastWaterSpeed = PlayState.player.getWaterSpeed();
		PlayState.player.setSpeed(lastSpeed - getEffect());
		PlayState.player.setWaterSpeed(lastWaterSpeed - getEffect());
	}

	@Override
	public void undo() {
		PlayState.player.setSpeed(PlayState.player.getSpeed()+getEffect());
		PlayState.player.setWaterSpeed(PlayState.player.getWaterSpeed()+getEffect());
	}
	
}
