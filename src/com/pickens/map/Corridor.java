package com.pickens.map;

import com.pickens.util.Point;

public class Corridor {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	
	public int x1, x2, y1, y2, w, h, type;
	public Point center = new Point(0, 0);
	
	public Corridor(int x1, int x2, int y1, int y2, int w, int type) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.w = w;
		this.type = type;
		center.x = (x1+x2)/2;
		center.y = (y1+y2)/2;
	}
	
	public boolean intersects(Room room) {
		return (x1 <= room.x2 && x2 >= room.x1 &&
			y1 <= room.y2 && room.y2 >= room.y1);
	}
	
}
