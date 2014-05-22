package org.gete.android.dorongkotak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Kelas yang digunakan untuk melakukan penggambaran dalam kanvas
 * @author Adnanto Ahmad Ramadhon
 *
 */
public class Kanvas extends SurfaceView {
	public SurfaceHolder surfaceHolder;
	public ThreadKanvas threadKanvas;
	public Bitmap bitmap;
	
	private int x = 0;
	private int y = 0;
	private int xSpeed = 5;
	private int ySpeed = 5;
	
	public Kanvas(Context context) {
		super(context);
		kanvasInit();
	}

	public Kanvas(Context context, AttributeSet attrs) {
		super(context, attrs);
		kanvasInit();
	}

	public Kanvas(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		kanvasInit();
	}
	
	private void kanvasInit() {
		threadKanvas = new ThreadKanvas(this);
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				threadKanvas.setRunning(false);
				while (retry) {
					try {
						threadKanvas.join();
						retry = false;
					} catch (InterruptedException ie) {
						
					}
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				threadKanvas.setRunning(true);
				threadKanvas.start();
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				
			}
		});
		
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (x >= getWidth() - bitmap.getWidth()) {
			xSpeed = -10;
		}
		
		if (x == 0) {
			xSpeed = 10;
		}
		
		x = x + xSpeed;
		if (y >= getHeight() - bitmap.getHeight()) {
			ySpeed = -10;
		}
		
		if (y == 0) {
			ySpeed = 10;
		}
		
		y = y + ySpeed;
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
}
