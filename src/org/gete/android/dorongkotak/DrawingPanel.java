package org.gete.android.dorongkotak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Kelas yang digunakan untuk melakukan penggambaran
 * @author Adnanto Ahmad Ramadhon
 *
 */
public class DrawingPanel extends SurfaceView implements SurfaceHolder.Callback {
	private PanelThread panelThread;
	private Bitmap floor;
	private Bitmap wall;
	private Bitmap wallCornerLeft;
	private Bitmap wallCornerRight;
	private Bitmap wallLeftFloor;
	private Bitmap wallRightFloor;
	private Paint paint;
	private static int batas = 32;

	private static String TAG = "DrawingPanel";
	private int[][] gambarPeta = new int[][] {
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 3},
			{4, 5, 5, 5, 5, 5, 5, 5, 5, 6},
			{4, 5, 5, 5, 5, 5, 5, 5, 5, 6},
			{4, 5, 5, 5, 5, 5, 5, 5, 5, 6},
			{4, 5, 5, 5, 5, 5, 5, 5, 5, 6},
			{4, 5, 5, 5, 5, 5, 5, 5, 5, 6},
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
	};
	
	public DrawingPanel(Context context) {
		super(context);
		init();
	}
	
	public DrawingPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public DrawingPanel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		getHolder().addCallback(this);
		floor = BitmapFactory.decodeResource(getResources(), R.drawable.floor);
		wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
		wallCornerLeft = BitmapFactory.decodeResource(getResources(), R.drawable.wall_corner_left);
		wallCornerRight = BitmapFactory.decodeResource(getResources(), R.drawable.wall_corner_right);
		wallLeftFloor = BitmapFactory.decodeResource(getResources(), R.drawable.wall_left_floor);
		wallRightFloor = BitmapFactory.decodeResource(getResources(), R.drawable.wall_right_floor);
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setTextAlign(Paint.Align.LEFT);
	}
	
	@Override
	public void draw(Canvas canvas) {
		peta(canvas);
	}
	
	private void peta(Canvas canvas) {
		for (int i = 0; i < gambarPeta.length; i++) {
			for (int j = 0; j < gambarPeta[0].length; j++) {
				switch (gambarPeta[i][j]) {
				case 1:
					canvas.drawBitmap(wallCornerLeft, j * batas, i * batas, null);
					break;
				case 2:
					canvas.drawBitmap(wall, j * batas, i * batas, null);
					break;
				case 3:
					canvas.drawBitmap(wallCornerRight, j * batas, i * batas, null);
					break;
				case 4:
					canvas.drawBitmap(wallLeftFloor, j * batas, i * batas, null);
					break;
				case 5:
					canvas.drawBitmap(floor, j * batas, i * batas, null);
					break;
				case 6:
					canvas.drawBitmap(wallRightFloor, j * batas, i * batas, null);
				}
			}
		}
		
		// canvas.drawText("3 + 3 = ", 5, 200, paint);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		panelThread = new PanelThread(getHolder(), this);
		try {
			panelThread.setRunning(true);
			panelThread.start();
		} catch (Exception ie) {
			Log.e(TAG, "Terjadi Kesalahan: " + ie.getMessage());
		}
		
		Log.d(TAG, "surfaceCreated(holder)");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		try {
			panelThread.setRunning(false);
			panelThread.join();
		} catch (InterruptedException ie) {
			Log.e(TAG, "Terjadi kesalahan: " + ie.getMessage());
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}
	
}
