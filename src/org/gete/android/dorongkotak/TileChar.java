package org.gete.android.dorongkotak;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class TileChar {
	private Bitmap bitmap;
	private Paint paint;
	private Canvas canvas;
	private String cString;
	private String name;
	private CharColor color;
	
	public TileChar(String chString, String name, CharColor color, Resources res, int charHeight) {
		bitmap = BitmapFactory.decodeResource(res, R.drawable.floor);
		canvas = new Canvas(bitmap);
		paint = new Paint();
		paint.setTextSize(charHeight - 4);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setSubpixelText(true);
		paint.setARGB(255, color.getR(), color.getG(), color.getB());
		canvas.drawText(chString, 0, 1 + charHeight / 2, paint);
		cString = chString;
		this.name = name;
	}
	
	public void draw(int x, int y, int w, int h, Canvas canvas) {
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	public CharColor getColor() {
		return color;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public String getChar() {
		return cString;
	}
	
	public String getName() {
		return name;
	}

}
