package com.pickens.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public abstract class GuiElement {

	protected int x;
	protected int y;
	protected int width;
	protected int height;	
	protected GuiManager gc;
	
	private boolean in;
	private boolean clicked;
	private int currentButton;
	
	public static final int LEFT_JUST = 0;
	public static final int RIGHT_JUST = 1;
	public static final int CENTER_JUST = 2;
	private int JUSTIFICATION = CENTER_JUST;
	
	public GuiElement(int x, int y, GuiManager gc) {
		this.x = x;
		this.y = y;
		this.gc = gc;
		width = 0;
		height = 0;
		in = false;
		clicked = false;
		currentButton = 0;
	}
	
	public abstract void render(Graphics g);
	public abstract void update(GameContainer gc, int delta);
	public abstract void onClick(int button, GameContainer gc);
	public abstract void onRelease(int button, GameContainer gc);
	public abstract void onHover();
	public abstract void onLeave();
	
	public void outside(int button, GameContainer gc) {
		
	}
	
	public void check(GameContainer gc, int delta) {
		Input input = gc.getInput();
		
		int mx = input.getMouseX();
		int my = input.getMouseY();
		
		if(width == 0 || height == 0) {
			throw new NullPointerException("You forgot to set the Width/Height of a GuiElement you dummy!");
		}
		
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
		if((mx > x && mx < x + width) && (my > y && my < y + height)) {
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
				currentButton = Input.MOUSE_LEFT_BUTTON;
				clicked = true;
			} else if(input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON) && !clicked) {
				currentButton = Input.MOUSE_MIDDLE_BUTTON;
				clicked = true;
			} else if(input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) && !clicked) {
				currentButton = Input.MOUSE_RIGHT_BUTTON;
				clicked = true;
			} else if(clicked == true && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON) && !input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
				outside(currentButton, gc);
				clicked = false;
			}
		}
		
		update(gc, delta);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public GuiManager getGc() {
		return gc;
	}

	public void setGc(GuiManager gc) {
		this.gc = gc;
	}

	public int getJustification() {
		return JUSTIFICATION;
	}

	public void setJustification(int jUSTIFICATION) {
		JUSTIFICATION = jUSTIFICATION;
	}
	
}
