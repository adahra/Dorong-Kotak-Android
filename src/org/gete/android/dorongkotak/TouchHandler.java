package org.gete.android.dorongkotak;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TouchHandler {
	private ArrayList<TouchBox> touchables;
	private Paint paint;
	
	public TouchHandler() {
		this.touchables = new ArrayList<TouchBox>();
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setARGB(64, 255, 255, 255);
	}
	
	public void add(TouchBox touchable) {
		this.touchables.add(touchable);
	}
	
	public void drawTouchables(Canvas canvas) {
		for (TouchBox t : this.touchables) {
			t.draw(canvas, paint);
		}
	}
	
	public Dot getTouchable(int x, int y) {
		for (TouchBox t : this.touchables) {
			if (t.isInside(x, y)) {
				return t.getId();
			}
		}
		
		return null;
	}

}
