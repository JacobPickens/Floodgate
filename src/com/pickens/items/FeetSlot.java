package com.pickens.items;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.util.Constants;
import com.pickens.util.Fonts;
import com.pickens.util.Images;

public class FeetSlot {

	Item item;
	
	int x, y, ox, oy, width, height;
	
	private boolean in;
	private boolean clicked;
	private int currentButton;
	private boolean tooltip = false;
	private boolean left = false;
	
	int clickCount = 0;
	int ticker = 0;
	
	Color color;
	Inventory inv;
	
	int lag = 0;
	
	public FeetSlot(int x, int y, int ox, int oy, Inventory inv) {
		this.x = x;
		this.y = y;
		this.width = Constants.TILE_SIZE;
		this.height = Constants.TILE_SIZE;
		this.ox = ox;
		this.oy = oy;
		this.inv = inv;
		
		color = new Color(0, 0, 0, 0);
	}	
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		Images.ITEMS.getSubImage(3, 3).draw(ox + (x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x)), oy + (y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y)));
		g.translate(ox, oy);
		if(hasItem()) {
			item.render(g);
		}
		g.translate(-ox, -oy);
		g.setColor(color);
		g.fillRect(ox + (x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x)), oy + (y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y)), Constants.TILE_SIZE, Constants.TILE_SIZE);
		if(tooltip) {
			int x = ox + (this.x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * this.x));
			int y = oy + (this.y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * this.y));
			if(hasItem()) {
				g.setColor(Color.black);
				g.fillRect(x, y-25, Fonts.CLASS_TEXT.getWidth(item.getName()), 20);
				g.setColor(Color.white);
				Color c = Color.white;
				if(hasItem()) {
					c = Constants.getTypeColor(item.getType());
				}
				g.setColor(c);
				g.setFont(Fonts.CLASS_TEXT);
				g.drawString(item.getName(), x, y-23);
			} else {
				g.setColor(Color.black);
				g.fillRect(x, y-25, Fonts.CLASS_TEXT.getWidth("Feet"), 20);
				g.setColor(Color.white);
				g.setFont(Fonts.CLASS_TEXT);
				g.drawString("Feet", x, y-23);
			}
		}
	}
	
	public void check(GameContainer gc, int delta) {
		int x = this.x*width;
		int y = this.y*height;
		
		Input input = gc.getInput();
		
		int mx = input.getMouseX();
		int my = input.getMouseY();
		
		// On Hover
		if((mx > ox + x && mx < ox + x + width) && (my > oy + y && my < oy + y + height)) {
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
		}
		
		update(gc, delta);
	}

	public void update(GameContainer gc, int delta) {
		ticker++;
		
		if(ticker > 10) {
			if(left && clickCount == 1) {
				if(hasItem() && !inv.getInventoryManager().hasItem()) {
					inv.getInventoryManager().setItem(item);
					((Equipment) item).undo();
					setItem(null);
				}
			}
			
			if(left && clickCount > 1) {
				if(hasItem()) {
					item.action();
				}
			}
			clickCount = 0;
			ticker = 0;
		}
	}

	public void onClick(int button, GameContainer gc) {
		ticker = 0;
		if(!hasItem() && inv.getInventoryManager().hasItem() && button == Input.MOUSE_LEFT_BUTTON && inv.getInventoryManager().getItem() instanceof Equipment) {
			if(((Equipment) inv.getInventoryManager().getItem()).getGearType() == Equipment.FEET) {
				Item item = inv.getInventoryManager().getItem();
				item.x = this.x;
				item.y = this.y;
				setItem(item);
				inv.getInventoryManager().setItem(null);
				((Equipment) item).modify();
				return;
			}
		}
		clickCount++;
	}

	public void onRelease(int button, GameContainer gc) {		
		if(button == Input.MOUSE_LEFT_BUTTON) {
			left = true;
		} else {
			left = false;
		}
		
		if(hasItem() && button == Input.MOUSE_RIGHT_BUTTON && !inv.getInventoryManager().hasItem()) {
			inv.getItemInfo().setItem(item);
		}
	}
	
	public void onHover() {
		color.a = .3f;
		tooltip = true;
	}
	
	public void onLeave() {
		color.a = 0;
		tooltip = false;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item i) {
		item = i;
	}
	
	public boolean hasItem() {
		if(item == null) {
			return false;
		} else {
			return true;
		}
	}
	
}
