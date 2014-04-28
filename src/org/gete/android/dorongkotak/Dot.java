package org.gete.android.dorongkotak;

public final class Dot {
	public int x;
	public int y;
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Dot add(Dot dot) {
		return new Dot(dot.x + this.x, dot.y + this.y);
	}
	
	public boolean onMap(TileMap map) {
		return (this.x >= 0 && this.x < map.getWidth() && this.y >= 0 && this.y < map.getHeight());
	}
	
	public int distanceTo(Dot dot) {
		if (dot != null) {
			return Math.abs(this.x - dot.x) + Math.abs(this.y - dot.y);
		}
		
		return 0;
	}

}
