package com.pickens.map;

import com.pickens.util.Point;

public class Room {

	private static final int ROOM_PADDING = 8;
	
	public int x1, x2, y1, y2, w, h;
	public Point center = new Point(0, 0);
	
	public Room(int x, int y, int w, int h) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x + w;
		this.y2 = y + h;
		this.w = w;
		this.h = h;
		center.x = (x1+x2)/2;
		center.y = (y1+y2)/2;
	}
	
	public boolean intersects(Room room) {
		return (x1-ROOM_PADDING <= room.x2+ROOM_PADDING && x2+ROOM_PADDING >= room.x1-ROOM_PADDING &&
			y1-ROOM_PADDING <= room.y2+ROOM_PADDING && room.y2-ROOM_PADDING >= room.y1+ROOM_PADDING);
	}
}
