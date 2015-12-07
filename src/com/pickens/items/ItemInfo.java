package com.pickens.items;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class ItemInfo {

	int x, y, ox, oy, width, height;
	
	private boolean in;
	private boolean clicked;
	private int currentButton;
	
	Item item;
	String name;
	String[] description;
	
	public int lag = 0;
	
	public ItemInfo() {
		ox = 0;
		oy = 0;
		width = (int) (Constants.WIDTH*.5);
		height = (int) (Constants.HEIGHT *.8);
		x = Constants.WIDTH/2 - width/2;
		y = Constants.HEIGHT/2 - height/2;
	}
	
	public void render(Graphics g) {
		if(hasItem()) {
			g.setColor(Color.lightGray);
			g.fillRect(x, y, width, height);
			
			g.setColor(Constants.getTypeColor(item.getType()));
			g.setFont(Fonts.MENU_TEXT);
			item.getImage().draw(x + 5, y + 20);
			g.drawString(name, x + 72, y + 30);
			g.setColor(Color.white);
			g.fillRect(x + 5, y + 100, width-10, 3);
			for(int i = 0; i < description.length; i++) {
				Fonts.CLASS_TEXT.drawString(x + 5, y + 120 + 20*i, description[i]);
			}
			g.setColor(Constants.getTypeColor(item.getType()));
			g.setFont(Fonts.CLASS_TEXT);
			g.drawString("(" + Constants.getTypeString(item.getType()) + " Item)", x + 5, y + 120 + 20*description.length);
			g.setColor(Color.white);
			Fonts.CLASS_TEXT.drawString(x + 5, y + height - 25, "Click outside to minimize.");
		}
	}
	
	public void update(GameContainer gc, int delta) {
		lag++;
		check(gc, delta);
	}
	
	public void check(GameContainer gc, int delta) {		
		Input input = gc.getInput();
		
		int mx = input.getMouseX();
		int my = input.getMouseY();
		
		// On Hover
		if((mx > x && mx < x + width) && (my > y && my < y + height)) {
			if(!in) {
				onHover();
				in = true;
			}
		} else if(in == true) {
			in = false;
			onLeave();
		}
		
		// On Click
		if((mx > ox + x && mx < ox + x + width) && (my > oy + y && my < oy + y + height)) {
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !clicked) {
				onClick(Input.MOUSE_LEFT_BUTTON, gc);
				currentButton = Input.MOUSE_LEFT_BUTTON;
				clicked = true;
			} else if(input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON) && !clicked) {
				onClick(Input.MOUSE_MIDDLE_BUTTON, gc);
				currentButton = Input.MOUSE_MIDDLE_BUTTON;
				clicked = true;
			} else if(input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) && !clicked) {
				onClick(Input.MOUSE_RIGHT_BUTTON, gc);
				currentButton = Input.MOUSE_RIGHT_BUTTON;
				clicked = true;
			} else if(clicked == true && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON) && !input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
				onRelease(currentButton, gc);
				clicked = false;
			}
		} else {
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !clicked) {
				onClick(Input.MOUSE_LEFT_BUTTON, gc);
				currentButton = Input.MOUSE_LEFT_BUTTON;
				clicked = true;
			} else if(input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON) && !clicked) {
				onClick(Input.MOUSE_MIDDLE_BUTTON, gc);
				currentButton = Input.MOUSE_MIDDLE_BUTTON;
				clicked = true;
			} else if(input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) && !clicked) {
				onClick(Input.MOUSE_RIGHT_BUTTON, gc);
				currentButton = Input.MOUSE_RIGHT_BUTTON;
				clicked = true;
			} else if(clicked == true && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON) && !input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
				outside();
				clicked = false;
			}
		}
	}

	public void onClick(int button, GameContainer gc) {
		
	}
	
	public void outside() {
		if(hasItem()) {
			item = null;
		}
	}

	public void onRelease(int button, GameContainer gc) {
		
	}
	
	public void onHover() {
		
	}
	
	public void onLeave() {
		
	}
	
	public boolean hasItem() {
		if(item == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setItem(Item i) {
		item = i;
		if(item != null) {
			name = i.getName();
			description = i.getDescription().split("\n");
		}
	}
	
}
