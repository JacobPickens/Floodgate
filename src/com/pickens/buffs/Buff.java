package com.pickens.buffs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public abstract class Buff {

	private BuffManager b;
	private int ticker = 0;
	private int time;
	private int effect;
	private Image image;
	
	public Buff(int effect, int time, Image image, BuffManager b) {
		this.effect = effect;
		this.time = time;
		this.image = image;
		this.b = b;
		this.modify();
	}
	
	public abstract void modify();
	public abstract void undo();
	
	public void update(GameContainer gc, int delta) {
		ticker++;
		if(ticker >= time) {
			this.undo();
			b.remove(this);
		}
	}
	
	public int getTime() {
		return ticker;
	}
	
	public int getEffect() {
		return effect;
	}
	
	public Image getImage() {
		return image;
	}
	
}
