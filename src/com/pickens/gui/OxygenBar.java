package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class OxygenBar extends GuiElement {

	int[] bubbles;
	int lastOxygen;
	
	Player p;
	
	public OxygenBar(int x, int y, GuiManager gc, Player p) {
		super(x, y, gc);
		this.p = p;
		
		lastOxygen = p.getOxygen();
		// Set hearts
		bubbles = new int[p.getOxygen()];
		for(int i = 0; i < bubbles.length; i++) {
			bubbles[i] = 1;
		}
		
		setWidth(bubbles.length * 20);
		setHeight(24);
	}

	@Override
	public void render(Graphics g) {
		for(int i = 0; i < bubbles.length; i++) {
			if(bubbles[i] == 1) {
				g.drawImage(Images.BUBBLE, x + i * 20, y);
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		for(int i = 0; i < Constants.currentCharacter.getOxygen(); i++) {
			bubbles[i] = 0;
		}
		for(int i = 0; i < p.getOxygen(); i++) {
			bubbles[i] = 1;
		}
	}

	@Override
	public void onClick(int button, GameContainer gc) {
		
	}

	@Override
	public void onRelease(int button, GameContainer gc) {
		
	}

	@Override
	public void onHover() {
		
	}

	@Override
	public void onLeave() {
		
	}

}
