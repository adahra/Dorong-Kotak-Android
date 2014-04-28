package org.gete.android.dorongkotak;

import android.graphics.Canvas;

public class TileLayer extends Layer<TileChar>{

	public TileLayer(int layerWidth, int layerHeight) {
		super(layerWidth, layerHeight);
	}
	
	public void draw(int startX, int startY, int w, int h, Canvas canvas) {
		for (int x = 0; x < super.width; x++) {
			for (int y = 0; y < super.height; y++) {
				TileChar tileChar = super.get(x, y);
				if (tileChar != null) {
					tileChar.draw(startX + x * w, startY + y * h, w, h, canvas);
				}
			}
		}
	}

}
