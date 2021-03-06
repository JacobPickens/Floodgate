package com.pickens.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.pickens.map.TileMap;
import com.pickens.objects.Barrier;
import com.pickens.objects.Chest;
import com.pickens.objects.FaucetStopper;
import com.pickens.objects.Floodgate;
import com.pickens.objects.ObjectController;
import com.pickens.objects.Player;
import com.pickens.objects.Water;
import com.pickens.util.Constants;

public class MiniMap extends GuiElement {

	private int tilesize;

	private TileMap map;
	private ObjectController oc;
	private Player p;

	private Image img;
	private Image subimg;

	private int ticker = 0;
	private int update = 80;

	private static int miniX;
	private static int miniY;
	private int oy;
	private int[][] last;
	private boolean firstDraw;
	
	Color color;
	Graphics g;

	public MiniMap(int x, int y, GuiManager gc, TileMap map, ObjectController oc, Player p) {
		super(x, y, gc);

		color = new Color(0, 0, 0, 0);

		this.map = map;
		this.oc = oc;
		this.p = p;

		tilesize = (int) (map.getWidth() * (Constants.WIDTH * 0.000013f));
		setWidth(tilesize * map.getWidth());
		setHeight(tilesize * map.getHeight());

		this.x -= 256;
		this.y -= height - 256;
		oy = height - 256;
		miniX = this.x;
		miniY = this.y;

		try {
			img = new Image(width, height);
			g = img.getGraphics();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		firstDraw = true;
		drawToMap();
	}

	Color red = new Color(256, 0, 0);
	Color redDim = new Color(128, 0, 0);
	Color blue = new Color(256, 0, 0);
	Color blueDim = new Color(128, 0, 0);
	Color green = new Color(256, 0, 0);
	Color greenDim = new Color(128, 0, 0);
	public void drawToMap() {
		g.setBackground(Color.white);
		
		if(firstDraw) {
			firstDraw = false;
			for (int y = 0; y < map.getHeight(); y++) {
				for (int x = 0; x < map.getWidth(); x++) {
					int tile = map.getRawData()[x][y];

					switch (tile) {
					case Constants.FLOOR:
						g.setColor(Color.darkGray);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
						break;
					case Constants.WATER:
						g.setColor(Color.yellow);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
						break;
					case Constants.CHEST:
						g.setColor(Color.magenta);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
					}

					Object o = oc.getObject(x, y);
					if (o instanceof Barrier) {
						g.setColor(Color.red);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
					} else if (o instanceof FaucetStopper) {
						g.setColor(Color.green);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
					} else if (o instanceof Floodgate) {
						Color color = red;
						switch (((Floodgate) o).type) {
						case Floodgate.RED:
							if (Constants.RED_DOOR_STATE) {
								color = red;
							} else {
								color = redDim;
							}
							break;
						case Floodgate.GREEN:
							if (Constants.GREEN_DOOR_STATE) {
								color = green;
							} else {
								color = greenDim;
							}
							break;
						case Floodgate.BLUE:
							if (Constants.BLUE_DOOR_STATE) {
								color = blue;
							} else {
								color = blueDim;
							}
							break;
						}
						g.setColor(color);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
					} else if(o instanceof Water) {
						if(((Water)o).isFrozen()) {
							g.setColor(Color.cyan);
							g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
						}
					}
				}
			}
		} else {
			for (int y = 0; y < map.getHeight(); y++) {
				for (int x = 0; x < map.getWidth(); x++) {
					int tile = map.getRawData()[x][y];

					switch (tile) {
					case Constants.WATER:
						g.setColor(Color.yellow);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
						break;
					case Constants.CHEST:
						g.setColor(Color.magenta);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
					}

					Object o = oc.getObject(x, y);
					if (o instanceof Barrier) {
						g.setColor(Color.red);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
					} else if (o instanceof FaucetStopper) {
						g.setColor(Color.green);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
					} else if (o instanceof Floodgate) {
						Color color = red;
						switch (((Floodgate) o).type) {
						case Floodgate.RED:
							if (Constants.RED_DOOR_STATE) {
								color = red;
							} else {
								color = redDim;
							}
							break;
						case Floodgate.GREEN:
							if (Constants.GREEN_DOOR_STATE) {
								color = green;
							} else {
								color = greenDim;
							}
							break;
						case Floodgate.BLUE:
							if (Constants.BLUE_DOOR_STATE) {
								color = blue;
							} else {
								color = blueDim;
							}
							break;
						}
						g.setColor(color);
						g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
					} else if(o instanceof Water) {
						if(((Water)o).isFrozen()) {
							g.setColor(Color.cyan);
							g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);
						}
					}
				}
			}
		}

		g.setColor(color);
		g.fillRect(x * tilesize, y * tilesize, tilesize, tilesize);

		g.flush();
		last = map.getRawData();
	}

	@Override
	public void render(Graphics g) {
		if (Constants.MINI_MAP) {
			if (Constants.MINI_MAP_FOCUSED) {
				g.setColor(Color.gray);
				g.fillRect(x, y, width, height);
				g.drawImage(img, x, y);
				g.setColor(Color.blue);
				g.fillRect(x + p.getX() * tilesize, y + p.getY() * tilesize, tilesize, tilesize);
				g.setColor(color);
				g.fillRect(x, y, width, height);
			} else {
				subimg = img.getSubImage(p.getX() * tilesize - 128, p.getY() * tilesize - 128, 256, 256);
				g.setColor(Color.gray);
				g.fillRect(x, y + oy, 256, 256);
				g.drawImage(subimg, x, y + oy);
				g.setColor(Color.blue);
				g.fillRect(x + 256 / 2, (y + 256 / 2) + oy, tilesize, tilesize);
				g.setColor(color);
				g.fillRect(x, y + oy, 256, 256);
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if (Constants.MINI_MAP) {
			ticker++;
			if (ticker > update) {
				ticker = 0;
				drawToMap();
			}

			if (this.x != miniX && !Constants.MINI_MAP_FOCUSED) {
				this.x = miniX;
				this.y = miniY;
			}

			if (gc.getInput().isKeyPressed(Input.KEY_M) && !Constants.INVENTORY_OPEN) {
				toggle();
			}
		}
	}

	@Override
	public void onClick(int button, GameContainer gc) {

	}

	@Override
	public void onRelease(int button, GameContainer gc) {
		toggle();
	}

	@Override
	public void outside(int button, GameContainer gc) {
		if (Constants.MINI_MAP_FOCUSED) {
			toggle();
		}
	}

	@Override
	public void onHover() {
		if (!Constants.INVENTORY_OPEN) {
			color.a = .6f;
		}
	}

	@Override
	public void onLeave() {
		color.a = 0f;
	}

	public void setMap(TileMap map) {
		this.map = map;
	}

	public void setObjectController(ObjectController oc) {
		this.oc = oc;
	}

	public void reset(TileMap map, ObjectController oc) {
		this.map = map;
		this.oc = oc;
		firstDraw = true;
		try {
			img = new Image(width, height);
			g = img.getGraphics();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		drawToMap();
	}

	public void toggle() {
		if (Constants.MINI_MAP && !Constants.INVENTORY_OPEN) {
			if (Constants.MINI_MAP_FOCUSED) {
				Constants.MINI_MAP_FOCUSED = false;
				Constants.PLAYER_PAUSED = false;
			} else {
				this.x = Constants.WIDTH / 2 - width / 2;
				this.y = Constants.HEIGHT / 2 - height / 2;
				Constants.MINI_MAP_FOCUSED = true;
				Constants.PLAYER_PAUSED = true;
			}
		}
	}

}
