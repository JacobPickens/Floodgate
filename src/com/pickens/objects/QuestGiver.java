package com.pickens.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pickens.map.TileMap;
import com.pickens.objectives.Objective;
import com.pickens.objectives.SwimObjective;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class QuestGiver extends NPC {

	boolean taken;
	Image image;
	static Objective obj;
	
	public QuestGiver(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		obj = new SwimObjective(PlayState.player.getObjectives());
		image = Images.PLAYER_SHEET.getSubImage(0, 0);
		taken = false;
	}

	@Override
	public void action() {
		if(!PlayState.qv.isEnabled()) {
			if(!taken && !PlayState.player.getObjectives().hasObjective(obj)) {
				PlayState.player.getObjectives().add(obj);
				PlayState.qv.setQuest(obj);
			} else {
				PlayState.qv.setQuest(obj);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, Constants.mapOffsetX + x*Constants.TILE_SIZE, Constants.mapOffsetY + y*Constants.TILE_SIZE);
	}

	@Override
	public void updateCall(GameContainer gc, int delta) {
		
	}

}
