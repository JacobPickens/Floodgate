package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class InventoryButton extends GuiElement {

	Color color;
	
	public InventoryButton(int x, int y, GuiManager gc) {
		super(x, y, gc);
		
		setWidth(128);
		setHeight(128);
		
		color = new Color(0, 0, 0, 0);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.INVENTORY_BUTTON, x, y);
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(gc.getInput().isKeyPressed(Input.KEY_I)) {
			toggle();
		}
	}

	@Override
	public void onClick(int button, GameContainer gc) {
		
	}

	@Override
	public void onRelease(int button, GameContainer gc) {
		toggle();
	}
	
	public static void toggle() {
		if(Constants.INVENTORY_OPEN) {
			Constants.INVENTORY_OPEN = false;
			Constants.PLAYER_PAUSED = false;
			Constants.PAUSED = false;
			Player.inv.getInventoryManager().putBack();
			Player.inv.getItemInfo().setItem(null);
		} else {
			if(!Constants.PLAYER_PAUSED) {
				Constants.INVENTORY_OPEN = true;
				Constants.PLAYER_PAUSED = true;
				Constants.PAUSED = true;
			}
		}
	}

	@Override
	public void onHover() {
		color.a = 0.6f;
	}

	@Override
	public void onLeave() {
		color.a = 0;
	}

}
