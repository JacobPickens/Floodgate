package com.pickens.objects;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import com.pickens.buffs.BuffManager;
import com.pickens.gui.DeathMenu;
import com.pickens.gui.GuiManager;
import com.pickens.gui.HealthBar;
import com.pickens.gui.OxygenBar;
import com.pickens.items.Equipment;
import com.pickens.items.Inventory;
import com.pickens.items.SnorkelEquipment;
import com.pickens.items.TankEquipment;
import com.pickens.map.TileMap;
import com.pickens.states.PlayState;
import com.pickens.util.BlockPlacer;
import com.pickens.util.Character;
import com.pickens.util.Constants;

public class Player extends Humanoid {
	private int oxygen;
	private int speed;
	private int waterSpeed;
	private int health;
	private int drownSpeed;
	private int breatheSpeed;
	private int maxOxygen;
	
	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;
	boolean dead = false;
	boolean sprint = false;
	int deplete = 0;
	int moveTicker = 0;
	
	private final int UP = 0;
	private final int DOWN = 1;
	private final int LEFT = 2;
	private final int RIGHT = 3;
	int direction = DOWN;
	
	static Color color;
	
	private static GuiManager gm;
	public static Inventory inv;
	private static BlockPlacer bp;
	private static BuffManager buffs;
	
	public static DeathMenu deathMenu;
	
	Random r = new Random();
	
	public Player(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		
		gm = new GuiManager(0, 0, this);
		
		deathMenu = new DeathMenu(0, 0, gm);
		bp = new BlockPlacer(map, oc, this);
		buffs = new BuffManager();
		Constants.INVENTORY_OPEN = false;
		Constants.MINI_MAP = false;
		Constants.MINI_MAP_FOCUSED = false;
		Constants.PLAYER_PAUSED = false;
	}
	
	public void setStats(Character c) {
		oxygen = c.getOxygen();
		speed = c.getSpeed();
		waterSpeed = c.getWaterSpeed();
		health = c.getHealth();
		color = c.getColor();
		drownSpeed = 60;
		breatheSpeed = 30;
		maxOxygen = Constants.currentCharacter.getOxygen();
		
		gm.setJustification(GuiManager.LEFT_JUST);
		gm.add(new HealthBar(10, 10, gm, this));
		gm.add(new OxygenBar(10, 60, gm, this));
		inv = new Inventory(this);
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getOxygen() {
		return oxygen;
	}

	public void render(Graphics g) {
		switch(direction) {
		case UP:
			if(!moved) {
				g.drawImage(Constants.currentCharacter.PLAYER_UP, Constants.WIDTH/2-Constants.TILE_SIZE/2, Constants.HEIGHT/2-Constants.TILE_SIZE/2);
			} else {
				g.drawAnimation(Constants.currentCharacter.PLAYER_WUP, Constants.WIDTH/2-Constants.TILE_SIZE/2, Constants.HEIGHT/2-Constants.TILE_SIZE/2);
			}
			break;
		case DOWN:
			if(!moved) {
				g.drawImage(Constants.currentCharacter.PLAYER_DOWN, Constants.WIDTH/2-Constants.TILE_SIZE/2, Constants.HEIGHT/2-Constants.TILE_SIZE/2);
			} else {
				g.drawAnimation(Constants.currentCharacter.PLAYER_WDOWN, Constants.WIDTH/2-Constants.TILE_SIZE/2, Constants.HEIGHT/2-Constants.TILE_SIZE/2);
			}
			break;
		case LEFT:
			if(!moved) {
				g.drawImage(Constants.currentCharacter.PLAYER_LEFT, Constants.WIDTH/2-Constants.TILE_SIZE/2, Constants.HEIGHT/2-Constants.TILE_SIZE/2);
			} else {
				g.drawAnimation(Constants.currentCharacter.PLAYER_WLEFT, Constants.WIDTH/2-Constants.TILE_SIZE/2, Constants.HEIGHT/2-Constants.TILE_SIZE/2);
			}
			break;
		case RIGHT:
			if(!moved) {
				g.drawImage(Constants.currentCharacter.PLAYER_RIGHT, Constants.WIDTH/2-Constants.TILE_SIZE/2, Constants.HEIGHT/2-Constants.TILE_SIZE/2);
			} else {
				g.drawAnimation(Constants.currentCharacter.PLAYER_WRIGHT, Constants.WIDTH/2-Constants.TILE_SIZE/2, Constants.HEIGHT/2-Constants.TILE_SIZE/2);
			}
			break;
		}
		bp.render(g);
		inv.render(g);
		gm.render(g);
		buffs.render(g);
		if(dead) {
			deathMenu.render(g);
		}
	}

	boolean moved = false;
	boolean dontBreathe = false;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		
		Constants.currentCharacter.PLAYER_WUP.update(delta);
		Constants.currentCharacter.PLAYER_WDOWN.update(delta);
		Constants.currentCharacter.PLAYER_WLEFT.update(delta);
		Constants.currentCharacter.PLAYER_WRIGHT.update(delta);
		
		if(!Constants.INVENTORY_OPEN) buffs.update(gc, delta);
		
		if(gc.getInput().isKeyPressed(Input.KEY_R)) {
			PlayState.win();
		}
		
		if(dead) {
			deathMenu.check(gc, delta);
		} else {	
			if(!Constants.PLAYER_PAUSED) {
				if(gc.getInput().isKeyPressed(Input.KEY_0)) {
					health--;
				}
				
				bp.update(gc);
				
				moved = false;
				if (gc.getInput().isKeyPressed(Input.KEY_W)) {
					moved = true;
					this.moveTicker = (this.speed*50);
					this.up = true;
				}
				if (gc.getInput().isKeyPressed(Input.KEY_S)) {
					moved = true;
					this.moveTicker = (this.speed*50);
					this.down = true;
				}
				if (gc.getInput().isKeyPressed(Input.KEY_A)) {
					moved = true;
					this.moveTicker = (this.speed*50);
					this.left = true;
				}
				if (gc.getInput().isKeyPressed(Input.KEY_D)) {
					moved = true;
					this.moveTicker = (this.speed*50);
					this.right = true;
				}
				if (gc.getInput().isKeyDown(Input.KEY_W)) {
					moved = true;
					this.up = true;
				} else {
					this.up = false;
				}
				if (gc.getInput().isKeyDown(Input.KEY_S)) {
					moved = true;
					this.down = true;
				} else {
					this.down = false;
				}
				if (gc.getInput().isKeyDown(Input.KEY_A)) {
					moved = true;
					this.left = true;
				} else {
					this.left = false;
				}
				if (gc.getInput().isKeyDown(Input.KEY_D)) {
					moved = true;
					this.right = true;
				} else {
					this.right = false;
				}
				
				dontBreathe = false;
				sprint = false;
				if(gc.getInput().isKeyDown(Input.KEY_LSHIFT) && !isUnderwater() && moved && Constants.CAN_SPRINT) {
					dontBreathe = true;
					if(oxygen > 2) {
						drowning();
						drowning();
						sprint = true;
					}
				}
				
				if(moved) {
					move();
				}
				if ((!gc.getInput().isKeyDown(Input.KEY_W)) && (!gc.getInput().isKeyDown(Input.KEY_S)) && (!gc.getInput().isKeyDown(Input.KEY_A))
						&& (!gc.getInput().isKeyDown(Input.KEY_D))) {
					this.moveTicker = 0;
				}
				//////////////////// Object Interaction ////////////////////
				if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
					//////////////////// Faucet Stopper ////////////////////
					if ((this.x > 0) && ((this.oc.getObject(this.x - 1, this.y) instanceof FaucetStopper))) {
						PlayState.win();
					}
					if ((this.x < this.map.getWidth())
							&& ((this.oc.getObject(this.x + 1, this.y) instanceof FaucetStopper))) {
						PlayState.win();
					}
					if ((this.y > 0) && ((this.oc.getObject(this.x, this.y - 1) instanceof FaucetStopper))) {
						PlayState.win();
					}
					if ((this.y < this.map.getHeight())
							&& ((this.oc.getObject(this.x, this.y + 1) instanceof FaucetStopper))) {
						PlayState.win();
					}
					
					//////////////////// Chests ////////////////////
					if ((this.x > 0) && ((this.oc.getObject(this.x - 1, this.y) instanceof Chest))) {
						((Chest) this.oc.getObject(this.x - 1, this.y)).roll();
						PlayState.mm.reset(map, oc);
					}
					if ((this.x < this.map.getWidth())
							&& ((this.oc.getObject(this.x + 1, this.y) instanceof Chest))) {
						((Chest) this.oc.getObject(this.x + 1, this.y)).roll();
						PlayState.mm.reset(map, oc);
					}
					if ((this.y > 0) && ((this.oc.getObject(this.x, this.y - 1) instanceof Chest))) {
						((Chest) this.oc.getObject(this.x, this.y - 1)).roll();
						PlayState.mm.reset(map, oc);
					}
					if ((this.y < this.map.getHeight())
							&& ((this.oc.getObject(this.x, this.y + 1) instanceof Chest))) {
						((Chest) this.oc.getObject(this.x, this.y + 1)).roll();
						PlayState.mm.reset(map, oc);
					}
				}
			}
			
			inv.update(gc, delta);
			
			if(!Constants.PAUSED) {
				if (gc.getInput().isKeyPressed(Input.KEY_1) || gc.getInput().isKeyPressed(Input.KEY_NUMPAD1)) {
					if (Constants.RED_DOOR_STATE) {
						Constants.RED_DOOR_STATE = false;
					} else {
						Constants.RED_DOOR_STATE = true;
					}
				}
				if (gc.getInput().isKeyPressed(Input.KEY_2) || gc.getInput().isKeyPressed(Input.KEY_NUMPAD2)) {
					if (Constants.GREEN_DOOR_STATE) {
						Constants.GREEN_DOOR_STATE = false;
					} else {
						Constants.GREEN_DOOR_STATE = true;
					}
				}
				if (gc.getInput().isKeyPressed(Input.KEY_3) || gc.getInput().isKeyPressed(Input.KEY_NUMPAD3)) {
					if (Constants.BLUE_DOOR_STATE) {
						Constants.BLUE_DOOR_STATE = false;
					} else {
						Constants.BLUE_DOOR_STATE = true;
					}
				}

				if (isUnderwater()) {
					drowning();
				} else if(!sprint && !dontBreathe) {
					breathing();
				}
				
				if(this.health == 0) {
					dead = true;
				}
			}
		}
		
		gm.update(gc, delta);
	}

	public void move() {
		this.moveTicker += 1;
		int s;
		if(isUnderwater()) {
			s = getWaterSpeed();
		} else {
			s = getSpeed();
		}
		if(s < 3) {
			s = 3;
		}
		if(sprint) {
			s *= .6;
		}
		// Damage Feet Items
		if(inv.getFeet().hasItem()) {
			Equipment i = (Equipment) inv.getFeet().getItem();
			if(r.nextInt(100) < 3) {
				i.damage(this);
			}
		}
		
		if (this.moveTicker >= s) {
			this.moveTicker = 0;
			if ((this.y > 0) && (this.up)
					&& ((this.map.getRawData()[this.x][(this.y - 1)] == 0)
							|| (this.map.getRawData()[this.x][(this.y - 1)] == 15))
					&& (!(this.oc.getObject(this.x, this.y - 1) instanceof FaucetStopper))) {
				direction = UP;
				
				Object test = this.oc.getObject(this.x, this.y - 1);
				if ((test instanceof Floodgate)) {
					if (!((Floodgate) test).closed) {
						Constants.mapOffsetY += Constants.TILE_SIZE;
						this.y -= 1;
					}
				} else {
					Constants.mapOffsetY += Constants.TILE_SIZE;
					this.y -= 1;
				}
			}
			if ((this.y < this.map.getHeight()) && (this.down)
					&& ((this.map.getRawData()[this.x][(this.y + 1)] == 0)
							|| (this.map.getRawData()[this.x][(this.y + 1)] == 15))
					&& (!(this.oc.getObject(this.x, this.y + 1) instanceof FaucetStopper))) {
				direction = DOWN;
				Object test = this.oc.getObject(this.x, this.y + 1);
				if ((test instanceof Floodgate)) {
					if (!((Floodgate) test).closed) {
						Constants.mapOffsetY -= Constants.TILE_SIZE;
						this.y += 1;
					}
				} else {
					Constants.mapOffsetY -= Constants.TILE_SIZE;
					this.y += 1;
				}
			}
			if ((this.x > 0) && (this.left)
					&& ((this.map.getRawData()[(this.x - 1)][this.y] == 0)
							|| (this.map.getRawData()[(this.x - 1)][this.y] == 15))
					&& (!(this.oc.getObject(this.x - 1, this.y) instanceof FaucetStopper))) {
				direction = LEFT;
				Object test = this.oc.getObject(this.x - 1, this.y);
				if ((test instanceof Floodgate)) {
					if (!((Floodgate) test).closed) {
						Constants.mapOffsetX += Constants.TILE_SIZE;
						this.x -= 1;
					}
				} else {
					Constants.mapOffsetX += Constants.TILE_SIZE;
					this.x -= 1;
				}
			}
			if ((this.x < this.map.getWidth()) && (this.right)
					&& ((this.map.getRawData()[(this.x + 1)][this.y] == 0)
							|| (this.map.getRawData()[(this.x + 1)][this.y] == 15))
					&& (!(this.oc.getObject(this.x + 1, this.y) instanceof FaucetStopper))) {
				direction = RIGHT;
				Object test = this.oc.getObject(this.x + 1, this.y);
				if ((test instanceof Floodgate)) {
					if (!((Floodgate) test).closed) {
						Constants.mapOffsetX -= Constants.TILE_SIZE;
						this.x += 1;
					}
				} else {
					Constants.mapOffsetX -= Constants.TILE_SIZE;
					this.x += 1;
				}
			}
		}
	}

	public void drowning() {
		this.deplete += 1;
		if (this.deplete >= drownSpeed) {
			if(inv.getHead().getItem() instanceof SnorkelEquipment && !sprint) {
				Equipment i = (Equipment) inv.getHead().getItem();
				i.damage(this);
			}
			
			this.deplete = 0;
			if(inv.getBody().getItem() instanceof TankEquipment) { // Scuba tank depelteion
				((TankEquipment)inv.getBody().getItem()).deplete();
			} else {
				if(this.oxygen > 0) {
					this.oxygen -= 1;
				} else {
					this.health--;
					if(this.health < 0) {
						this.health = 0;
					}
				}
			}
		}
	}
	
	public void breathing() {
		this.deplete += 1;
		if (this.deplete >= breatheSpeed) {
			this.deplete = 0;
			if(this.oxygen < this.maxOxygen) {
				this.oxygen += 1;
			}
		}
	}

	public void update(GameContainer gc, int delta) {
	}
	
	public Inventory getInventory() {
		return inv;
	}
	
	public void setMap(TileMap map) {
		this.map = map;
	}
	
	public void setObjects(ObjectController oc) {
		this.oc = oc;
	}
	
	public void setHealth(int h) {
		this.health = h;
	}
	
	public void setOxygen(int o) {
		this.oxygen = o;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int moveCheck) {
		this.speed = moveCheck;
	}

	public void reset(TileMap map, ObjectController oc, int x, int y) {
		this.map = map;
		this.oc = oc;
		this.x = x;
		this.y = y;
		this.health = Constants.currentCharacter.getHealth();
		this.oxygen = Constants.currentCharacter.getOxygen();
		
		bp = new BlockPlacer(map, oc, this);
		
		Constants.MINI_MAP = false;
	}
	
	public boolean isUnderwater() {
		if(this.map.getRawData()[this.x][this.y] == Constants.WATER && this.map.getObjectController().getObject(this.x, this.y) instanceof Water && !((Water)this.map.getObjectController().getObject(this.x, this.y)).isFrozen()) {
			return true;
		}
		return false;
	}

	public int getWaterSpeed() {
		return waterSpeed;
	}

	public void setWaterSpeed(int waterSpeed) {
		this.waterSpeed = waterSpeed;
	}

	public int getDrownSpeed() {
		return drownSpeed;
	}

	public void setDrownSpeed(int drownSpeed) {
		this.drownSpeed = drownSpeed;
	}

	public int getBreatheSpeed() {
		return breatheSpeed;
	}

	public void setBreatheSpeed(int breatheSpeed) {
		this.breatheSpeed = breatheSpeed;
	}
	
	public void addHealth(int h) {
		this.health += h;
	}
	
	public BuffManager getBuffs() {
		return buffs;
	}

	public int getMaxOxygen() {
		return maxOxygen;
	}

	public void setMaxOxygen(int maxOxygen) {
		this.maxOxygen = maxOxygen;
	}
}
