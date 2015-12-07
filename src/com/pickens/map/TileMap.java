package com.pickens.map;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.objects.Chest;
import com.pickens.objects.FaucetStopper;
import com.pickens.objects.Floodgate;
import com.pickens.objects.ObjectController;
import com.pickens.objects.Slime;
import com.pickens.objects.Water;
import com.pickens.util.Constants;

public class TileMap {
	Random r = new Random();
	ArrayList<Room> rooms = new ArrayList<Room>();
	ArrayList<Corridor> corridors = new ArrayList<Corridor>();
	private int w;
	private int h;
	private Tile[][] tile;
	private int[][] map;
	private int numberOfRooms;
	private ObjectController oc;

	public TileMap(int w, int h, int numberOfRooms, ObjectController oc) {
		this.w = w;
		this.h = h;
		this.numberOfRooms = numberOfRooms;
		this.oc = oc;
		generateMap(w, h);
		this.tile = new Tile[w][h];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				this.tile[x][y] = new Tile(x * 64, y * 64, this.map[x][y]);
			}
		}
	}

	public void swap(int x, int y) {
		this.tile[x][y] = new Tile(x * 64, y * 64, this.map[x][y]);
	}

	public void render(float mapOffsetX, float mapOffsetY, Graphics g) {
		for (int y = 0; y < this.h; y++) {
			for (int x = 0; x < this.w; x++) {
				this.tile[x][y].render(mapOffsetX, mapOffsetY, g);
			}
		}
	}

	int i = 0;
	Room p = null;

	public void update(GameContainer gc, int delta) {
	}

	public void generateMap(int w, int h) {
		this.map = new int[w][h];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				this.map[x][y] = 2;
			}
		}
		createRoom(w / 2, h / 2);
		for (this.i = 0; this.i < this.numberOfRooms; this.i += 1) {
			createRoom();
		}
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (this.map[x][y] == 0) {
					if ((x > 0) && (this.map[(x - 1)][y] == 2)) {
						this.map[(x - 1)][y] = 1;
					}
					if ((x < w - 1) && (this.map[(x + 1)][y] == 2)) {
						this.map[(x + 1)][y] = 1;
					}
					if ((y > 0) && (this.map[x][(y - 1)] == 2)) {
						this.map[x][(y - 1)] = 1;
					}
					if ((y < h - 1) && (this.map[x][(y + 1)] == 2)) {
						this.map[x][(y + 1)] = 1;
					}
				}
			}
		}
	}

	public void createRoom() {
		int ww = 5 + this.r.nextInt(5);
		int hh = 5 + this.r.nextInt(5);
		int x = this.r.nextInt(this.w - 4 - ww) + 2;
		int y = this.r.nextInt(this.h - 4 - hh) + 2;
		if (x > this.w) {
			x--;
		}
		if (y > this.h) {
			y--;
		}
		Room newRoom = new Room(x, y, ww, hh);
		boolean failed = false;
		for (int ii = 0; ii < this.rooms.size(); ii++) {
			Room otherRoom = (Room) this.rooms.get(ii);
			if (newRoom.intersects(otherRoom)) {
				failed = true;
				this.i -= 1;
				break;
			}
		}
		if (!failed) {
			this.rooms.add(newRoom);
			if (this.p != null) {
				if (this.r.nextInt(2) == 0) {
					hCorridor(this.p.center.x, newRoom.center.x, this.p.center.y);
					vCorridor(this.p.center.y, newRoom.center.y, newRoom.center.x);
				} else {
					vCorridor(this.p.center.y, newRoom.center.y, this.p.center.x);
					hCorridor(this.p.center.x, newRoom.center.x, newRoom.center.y);
				}
			}
			this.p = newRoom;

			carveRoom(newRoom);
		}
	}

	public void createRoom(int xx, int yy) {
		int ww = 5 + this.r.nextInt(5);
		int hh = 5 + this.r.nextInt(5);
		int x = xx - ww / 2;
		int y = yy - hh / 2;
		Room newRoom = new Room(x, y, ww, hh);
		boolean failed = false;
		for (int ii = 0; ii < this.rooms.size(); ii++) {
			Room otherRoom = (Room) this.rooms.get(ii);
			if (newRoom.intersects(otherRoom)) {
				failed = true;
				this.i -= 1;
				break;
			}
		}
		if (!failed) {
			this.rooms.add(newRoom);
			if (this.p != null) {
				if (this.r.nextInt(2) == 0) {
					hCorridor(this.p.center.x, newRoom.center.x, this.p.center.y);
					vCorridor(this.p.center.y, newRoom.center.y, newRoom.center.x);
				} else {
					vCorridor(this.p.center.y, newRoom.center.y, this.p.center.x);
					hCorridor(this.p.center.x, newRoom.center.x, newRoom.center.y);
				}
			}
			this.p = newRoom;

			carveRoom(newRoom);
		}
	}

	public void carveRoom(Room r) {
		for (int y = r.y1; y < r.y1 + r.h; y++) {
			for (int x = r.x1; x < r.x1 + r.w; x++) {
				this.map[x][y] = 0;
			}
		}
	}

	public void hCorridor(int x1, int x2, int y) {
		this.corridors.add(new Corridor(x1, x2, y, y + 2, 2, 0));
		for (int x = Math.min(x1, x2); x < Math.max(x1, x2) + 1; x++) {
			this.map[x][y] = 0;
			if (y > 0) {
				this.map[x][(y + 1)] = 0;
			}
		}
	}

	public void vCorridor(int y1, int y2, int x) {
		this.corridors.add(new Corridor(x, x + 2, y1, y2, 2, 1));
		for (int y = Math.min(y1, y2); y < Math.max(y1, y2) + 1; y++) {
			this.map[x][y] = 0;
			if (x > 0) {
				this.map[(x + 1)][y] = 0;
			}
		}
	}

	public void spawnObjects(ObjectController oc) {
		for (int i = 0; i < this.rooms.size(); i++) {
			Room room = (Room) this.rooms.get(i);
			if ((i > 0) && ((this.r.nextInt(100) < 10) || (i == this.rooms.size() - 1))) {
				oc.add(new FaucetStopper(room.center.x, room.center.y, this, oc));
				this.rooms.remove(room);
				break;
			}
		}
		for (int i = 0; i < this.corridors.size(); i++) {
			Corridor c = (Corridor) this.corridors.get(i);
			if (c.type == 0) {
				boolean failed = false;
				int starty = Math.min(c.y1, c.y2);
				int endy = Math.max(c.y1, c.y2);
				int x = c.center.x;
				while ((this.map[x][(starty - 1)] != 1) || (this.map[x][(endy + 1)] != 1)) {
					x++;
					if ((x == 0) || (x == this.h)) {
						failed = true;
						break;
					}
					if ((this.map[x][starty] == 1) && (this.map[x][starty] == 1)) {
						failed = true;
						break;
					}
				}
				if (!failed) {
					int color = this.r.nextInt(3);
					for (int y = starty; y < endy; y++) {
						oc.add(new Floodgate(x, y, this, oc, color));
					}
				}
			} else {
				boolean failed = false;
				int startx = Math.min(c.x1, c.x2);
				int endx = Math.max(c.x1, c.x2);
				int y = c.center.y;
				while ((this.map[(startx - 1)][y] != 1) || (this.map[(endx + 1)][y] != 1)) {
					y++;
					if ((y == 0) || (y == this.h)) {
						failed = true;
						break;
					}
					if ((this.map[startx][y] == 1) && (this.map[endx][y] == 1)) {
						failed = true;
						break;
					}
				}
				if (!failed) {
					int color = this.r.nextInt(3);
					for (int x = startx; x < endx; x++) {
						oc.add(new Floodgate(x, y, this, oc, color));
					}
				}
			}
		}
		int waterCount = 0;
		for (int i = 0; i < this.rooms.size(); i++) {
			Room room = (Room) this.rooms.get(i);
			if (i > 0) {
				if (this.r.nextInt(100) < 45) {
					oc.add(new Water(room.center.x, room.center.y, this, oc));
					waterCount++;
				} else if ((waterCount == 0) && (i == this.rooms.size() - 1)) {
					oc.add(new Water(room.center.x, room.center.y, this, oc));
					waterCount++;
				}
			}
		}
		
		for (int i = 0; i < this.rooms.size(); i++) {
			Room room = (Room) this.rooms.get(i);
			if (i > 0) {
				if (this.r.nextInt(100) < 80) {
					int amount = r.nextInt(4)+1;
					for(int a = 0; a < amount; a++) {
						oc.add(new Chest(room.x1 + r.nextInt(room.w), room.y1 + r.nextInt(room.h), this, oc));
					}
				}
			}
		}
	}

	public int getWidth() {
		return this.w;
	}

	public int getHeight() {
		return this.h;
	}

	public int[][] getRawData() {
		return this.map;
	}

	public int[] convertToTile(float x, float y) {
		float xx = x % Constants.TILE_SIZE;
		x -= xx;

		float yy = y % Constants.TILE_SIZE;
		y -= yy;

		int[] array = { (int) ((-Constants.mapOffsetX + x) / Constants.TILE_SIZE) + 1, (int) ((-Constants.mapOffsetY + y) / Constants.TILE_SIZE) - 1};

		return array;
	}
}
