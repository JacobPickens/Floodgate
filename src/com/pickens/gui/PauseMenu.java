package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class PauseMenu extends GuiElement  {

	CancelButton cb;
	QuitButton qb;
	
	boolean showing;
	
	public PauseMenu(int x, int y, GuiManager gc) {
		super(x, y, gc);
		x -= (196*2+15)/2;
		y -= 106/2;
		cb = new CancelButton(x+5, y+5, gc);
		qb = new QuitButton(x+cb.getWidth()+10, y+5, gc);
		setWidth(cb.getWidth() + qb.getWidth() + 15);
		setHeight(106);
		showing = false;
	}

	@Override
	public void render(Graphics g) {
		if(showing) {
			g.setColor(Color.black);
			g.fillRect(x, y, width, height);
			g.setColor(Color.white);
			Fonts.MENU_TEXT.drawString(x + width/2 - Fonts.MENU_TEXT.getWidth("Paused")/2, y - Fonts.MENU_TEXT.getLineHeight() * 1.5f, "Paused");
			cb.render(g);
			qb.render(g);
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(showing) {
			cb.check(gc, delta);
			qb.check(gc, delta);
			
			if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
				System.exit(0);
			}
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
	
	public void toggle() {
		if(showing) {
			showing = false;
		} else {
			showing = true;
		}
	}
	
	private class CancelButton extends GuiElement {

		Color color;
		
		public CancelButton(int x, int y, GuiManager gc) {
			super(x, y, gc);
			setWidth(196);
			setHeight(96);
			color = new Color(0, 0, 0, 0);
		}

		@Override
		public void render(Graphics g) {
			g.setColor(Color.green);
			g.fillRect(x, y, width, height);
			g.setColor(Color.white);
			Fonts.MENU_TEXT.drawString(this.x + width/2 - Fonts.MENU_TEXT.getWidth("Resume")/2, y + Fonts.MENU_TEXT.getLineHeight()/2, "Resume");
			g.setColor(color);
			g.fillRect(this.x, this.y, this.width, this.height);
		}

		@Override
		public void update(GameContainer gc, int delta) {
			
		}

		@Override
		public void onClick(int button, GameContainer gc) {
			toggle();
		}

		@Override
		public void onRelease(int button, GameContainer gc) {
			
		}

		@Override
		public void onHover() {
			color.a = .6f;
		}

		@Override
		public void onLeave() {
			color.a = 0;
		}
		
	}
	
	private class QuitButton extends GuiElement {

		Color color;
		
		public QuitButton(int x, int y, GuiManager gc) {
			super(x, y, gc);
			setWidth(196);
			setHeight(96);
			color = new Color(0, 0, 0, 0);
		}

		@Override
		public void render(Graphics g) {
			g.setColor(Color.gray);
			g.fillRect(x, y, width, height);
			g.setColor(Color.white);
			Fonts.MENU_TEXT.drawString(this.x + width/2 - Fonts.MENU_TEXT.getWidth("Quit (ESC)")/2, y + Fonts.MENU_TEXT.getLineHeight()/2, "Quit (ESC)");
			g.setColor(color);
			g.fillRect(this.x, this.y, this.width, this.height);
		}

		@Override
		public void update(GameContainer gc, int delta) {
			
		}

		@Override
		public void onClick(int button, GameContainer gc) {
			System.exit(0);
		}

		@Override
		public void onRelease(int button, GameContainer gc) {
			
		}

		@Override
		public void onHover() {
			color.a = .6f;
		}

		@Override
		public void onLeave() {
			color.a = 0;
		}
		
	}

}
