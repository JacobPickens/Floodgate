package com.pickens.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.objects.Player;
import com.pickens.util.Images;

public class HealthBar extends GuiElement {

	int[] hearts;
	int lastHealth;
	
	Player p;
	
	public HealthBar(int x, int y, GuiManager gc, Player p) {
		super(x, y, gc);
		this.p = p;
		lastHealth = p.getHealth();
		// Set hearts
		hearts = new int[p.getHealth()];
		for(int i = 0; i < hearts.length; i++) {
			hearts[i] = 1;
		}
		
		setWidth(hearts.length * 48);
		setHeight(32);
	}

	@Override
	public void render(Graphics g) {
		for(int i = 0; i < hearts.length; i++) {
			if(hearts[i] == 1) {
				g.drawImage(Images.HEART_FULL, x + i * 48, y);
			} else {
				g.drawImage(Images.HEART_EMPTY, x + i * 48, y);
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(lastHealth > p.getHealth()) {
			hearts[p.getHealth()] = 0;
		}
		lastHealth = p.getHealth();
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
