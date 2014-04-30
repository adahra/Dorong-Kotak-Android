package org.gete.android.dorongkotak;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TouchBox {
	private int x;
	private int y;
	private int w;
	private int h;
	private Dot id;
	
	public TouchBox(Dot id, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
	}
	
	public Dot getId() {
		return id;
	}
	
	public boolean isInside(int xx, int yy) {
		return (xx >= x && xx <= x + w && yy >= y && yy <= y + h);
	}
	
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawRect((float) x, (float) y, (float)(x + w - 1), (float)(y + h -1), paint);
	}

}
