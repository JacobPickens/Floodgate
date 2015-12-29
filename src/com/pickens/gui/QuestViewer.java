package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.pickens.objectives.Objective;
import com.pickens.util.Constants;
import com.pickens.util.Fonts;

public class QuestViewer extends GuiElement {

	private static Objective obj;
	private static boolean enabled;
	
	private Color color;
	
	private static String[] description;
	
	public QuestViewer(int x, int y, GuiManager gc) {
		super(x, y, gc);
		enabled = false;
		
		setWidth((int) (Constants.WIDTH*.5));
		setHeight((int) (Constants.HEIGHT *.8));
		
		color = new Color(0, 0, 0, 0);
	}

	@Override
	public void render(Graphics g) {
		if(enabled) {
			g.setColor(Color.lightGray);
			g.fillRect(x, y, width, height);
			
			g.setColor(Color.white);
			g.setFont(Fonts.MENU_TEXT);
			g.drawString(obj.getName(), x + 5, y + 5);
			g.setColor(Color.white);
			g.fillRect(x + 5, y + 80, width-10, 3);
			for(int i = 0; i < description.length; i++) {
				Fonts.CLASS_TEXT.drawString(x + 5, y + 100 + 20*i, description[i]);
			}
			
			g.setColor(Color.white);
			Fonts.CLASS_TEXT.drawString(x + 5, y + height - 25, "Click or press [SPACE] to minimize.");
			
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			enabled = false;
			Constants.PAUSED = false;
			Constants.PLAYER_PAUSED = false;
			obj = null;
		}
	}

	@Override
	public void onClick(int button, GameContainer gc) {
		
	}

	@Override
	public void onRelease(int button, GameContainer gc) {
		if(enabled) {
			enabled = false;
			Constants.PAUSED = false;
			Constants.PLAYER_PAUSED = false;
			obj = null;
		}
	}

	@Override
	public void onHover() {
		color.a = 0.6f;
	}

	@Override
	public void onLeave() {
		color.a = 0f;
	}
	
	public static void setQuest(Objective obj) {
		QuestViewer.obj = obj;
		enabled = true;
		Constants.PAUSED = true;
		Constants.PLAYER_PAUSED = true;
		description = obj.getLongDescription().split("\n");
	}
	
	public boolean isEnabled() {
		return enabled;
	}

}
