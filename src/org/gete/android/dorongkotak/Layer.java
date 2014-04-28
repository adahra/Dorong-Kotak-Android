package org.gete.android.dorongkotak;

public class Layer<E> {
	public final int width;
	public final int height;
	private Object[][] data;
	
	public Layer(int layerWidth, int layerHeight) {
		width = layerWidth;
		height = layerHeight;
		data = new Object[width][height];
	}
	
	public boolean onLayer(Dot dot) {
		return (dot != null && dot.x >= 0 && dot.x < width && dot.y >= 0 && dot.y < height);
	}
	
	public boolean setData(Layer<E> data) {
		if (data.isSameSize(data)) {
			this.data = data.data;
			return true;
		}
		
		return false;
	}
	
	public boolean isSameSize(Layer<E> other) {
		return (other.width == this.width && other.height == this.height);
	}
	
	public void mergeTo(Layer<E> layer) {
		if (this.isSameSize(layer)) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if (this.data[x][y] != null) {
						layer.data[x][y] = this.data[x][y];
					}
				}
			}
		}
	}
	
	public void differenceWith(Layer<E> layer) {
		if (this.isSameSize(layer)) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if (this.data[x][y] == layer.data[x][y]) {
						this.data[x][y] = null;
					}
				}
			}
		}
	}
	
	public void box(int xm, int ym, int w, int h, Object object) {
		for (int x = xm; x < xm + w; x++) {
			if (x >= 0 && x < width) {
				for (int y = ym; y < ym + h; y++) {
					if (y >= 0 && y < height) {
						data[x][y] = object;
					}
				}
			}
		}
	}
	
	public void xLine(int x, int y, int x2, Object object) {
		if (y >= 0 && y < height) {
			if (x < x2) {
				for (int dx = x; dx <= x2; dx++) {
					if (dx >= 0 && dx < width) {
						data[dx][y] = object;
					}
				}
			} else {
				for (int dx = x2; dx <= x; dx++) {
					if (dx >= 0 && dx < width) {
						data[dx][y] = object;
					}
				}
			}
		}
	}
	
	public void yLine(int x, int y, int y2, Object object) {
		if (x >= 0 && x < width) {
			if (y < y2) {
				for (int dy = y; dy <= y2; dy++) {
					if (dy >= 0 && dy < height) {
						data[x][dy] = object;
					}
				}
			} else {
				for (int dy = y2; dy <= y; dy++) {
					if (dy >= 0 && dy < height) {
						data[x][dy] = object;
					}
				}
			}
		}
	}
	
	public void fill(Object object) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				data[x][y] = object;
			}
		}
	}
	
	public boolean put(Dot dot, Object object) {
		if (onLayer(dot)) {
			data[dot.x][dot.y] = object;
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public E get(Dot dot) {
		if (onLayer(dot)) {
			return (E) data[dot.x][dot.y];
		}
		
		return null;
	}
	
	public boolean put(int x, int y, Object object) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			data[x][y] = object;
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public E get(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return (E) data[x][y];
		}
		
		return null;
	}
}
