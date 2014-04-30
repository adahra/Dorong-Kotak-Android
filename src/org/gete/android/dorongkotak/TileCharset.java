package org.gete.android.dorongkotak;

import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Paint;

public class TileCharset {
	private Map<String, TileChar> charset;
	private int charHeight;
	private Resources resources;
	private Paint paint;
	
	public TileCharset(Resources res, int charHeight) {
		charset = new HashMap<String, TileChar>();
		this.charHeight = charHeight;
		resources = res;
		uglyLoadCharset();
	}
	
	public class CharColor {
		private int r;
		private int g;
		private int b;
		
		public CharColor(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		public int getR() {
			return r;
		}
		
		public int getG() {
			return g;
		}
		
		public int getB() {
			return b;
		}
	}
	
	public TileChar getChar(String name) {
		return charset.get(name);
	}
	
	public TileChar requestChar(String ch, String name, CharColor color) {
		TileChar ret = charset.get(name);
		if (ret == null) {
			ret = loadChar(new TileChar(ch, name, color, resources, charHeight));
		}
		
		return ret;
	}
	
	public TileChar loadChar(TileChar tileChar) {
		charset.put(tileChar.getName(), tileChar);
		return tileChar;
	}
	
	public void uglyLoadCharset() {
		loadChar(new TileChar("#", "wall", new CharColor(64, 64, 64), resources, charHeight));
		loadChar(new TileChar(".", "floor", new CharColor(128, 128, 128), resources, charHeight));
		loadChar(new TileChar("@", "player", new CharColor(255, 255, 255), resources, charHeight));
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

}
