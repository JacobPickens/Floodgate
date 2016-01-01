package com.pickens.objectives;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.pickens.util.Fonts;

public class Objectives {

	ArrayList<Objective> objectives;
	
	public Objectives() {
		objectives = new ArrayList<Objective>();
	}
	
	public void renderList(Graphics g, int x, int y) {
		for(int i = 0; i < objectives.size(); i++){
			if(objectives.get(i).isCompleted()) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.white);
			}
			g.setFont(Fonts.CLASS_TEXT);
			g.drawString(objectives.get(i).getShortDescription(), x, y+(i*30));
		}
	}
	
	public void check() {
		//System.out.println("asdf");
		for(int i = 0; i < objectives.size(); i++){
			objectives.get(i).update();
		}
	}
	
	public void add(Objective obj) {
		objectives.add(obj);
	}
	
	public void remove(Objective obj) {
		objectives.remove(obj);
	}
	
	public ArrayList<Objective> getObjectives() {
		return objectives;
	}

	public boolean hasObjective(Objective obj) {
		for(int i = 0; i < objectives.size(); i++){
			if(objectives.get(i).getClass().equals(obj.getClass())) {
				return true;
			}
		}
		return false;
	}
	
}
