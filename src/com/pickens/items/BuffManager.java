package com.pickens.items;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class BuffManager {

	public ArrayList<Buff> buffs;
	
	public BuffManager() {
		buffs = new ArrayList<Buff>();
	}
	
	public void render(Graphics g) {
		
	}
	
	public void update(GameContainer gc, int delta) {
		for(int i = 0; i < buffs.size(); i++) {
			buffs.get(i).update(gc, delta);
		}
	}	
	
	public void add(Buff b) {
		buffs.add(b);
	}
	
	public void remove(Buff b) {
		buffs.remove(b);
	}
	
	public void resetBuffs() {
		buffs = new ArrayList<Buff>();
	}
	
	public ArrayList<Buff> getBuffs() {
		return buffs;
	}
	
}
