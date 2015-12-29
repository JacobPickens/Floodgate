package com.pickens.objectives;

import org.newdawn.slick.Image;

public abstract class Objective {

	Image icon;
	String name;
	String longDescription;
	String shortDescription;
	
	boolean completed;
	
	Objectives objs;
	
	public Objective(Objectives objs) {
		this.objs = objs;
		completed = false;
		name = "";
		longDescription = "";
		shortDescription = "";
	}
	
	public abstract void check();
	public abstract void reward();
	
	int ticker = 0;
	public void update() {
		if(isCompleted()) {
			ticker++;
			if(ticker > 30) {
				ticker = 0;
				objs.remove(this);
			}
		} else {
			check();
		}
	}
	
	public void complete() {
		reward();
		completed = true;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
