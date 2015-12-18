package com.pickens.buffs;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.util.Constants;

public class BuffManager {

	private int x = Constants.WIDTH - 196 - 5;
	private int y = Constants.HEIGHT-Constants.TILE_SIZE-5;
	public ArrayList<Buff> buffs;
	
	public BuffManager() {
		buffs = new ArrayList<Buff>();
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < buffs.size(); i++) {
			g.drawImage(buffs.get(i).getImage(), x+(i*-Constants.TILE_SIZE), y);
		}
	}
	
	public void update(GameContainer gc, int delta) {
		for(int i = 0; i < buffs.size(); i++) {
			buffs.get(i).update(gc, delta);
		}
	}	
	
	public void add(Buff b) {
		for(int i = 0; i < buffs.size(); i++) { // Anti-Effect Duplication
			if(buffs.get(i).getClass().equals(b.getClass())) {
				buffs.get(i).undo();
				buffs.remove(i);
				buffs.add(b);
				return;
			}
		}
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
