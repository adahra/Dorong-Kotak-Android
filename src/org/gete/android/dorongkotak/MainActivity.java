package org.gete.android.dorongkotak;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new TileView(this));
	}
	
	class TileView extends SurfaceView implements SurfaceHolder.Callback {
		private DrawThread dtThread;
		private GameGenerator gameGenerator;
		private GameHandler gameHandler;
		private int screenWidth;
		private int screenHeight;
		
		@SuppressWarnings("deprecation")
		public TileView(Context context) {
			super(context);
			getHolder().addCallback(this);
			dtThread = new DrawThread(getHolder(), this);
			setFocusable(true);
			Resources res = getResources();
			Display display = getWindowManager().getDefaultDisplay();
			screenWidth = display.getWidth();
			screenHeight = display.getHeight();
			gameGenerator = new GameGenerator(res, screenWidth, screenHeight);
			gameHandler = new GameHandler(gameGenerator);
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				int x = (int) event.getX();
				int y = (int) event.getY();
				gameHandler.movePlayer(gameGenerator.getTouchHandler().getTouchable(x, y));
			} else {
				
			}
			
			return true;
		}
		
		@Override
		public void onDraw(Canvas canvas) {
			synchronized (dtThread.getSurfaceHolder()) {
				gameGenerator.draw(canvas);
			}
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			
		}
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			dtThread.setRunning(true);
			dtThread.start();
		}
		
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			boolean retry = true;
			dtThread.setRunning(true);
			while (retry) {
				try {
					dtThread.join();
					retry = false;
				} catch (InterruptedException ie) {
					
				}
			}
		}
		
		class DrawThread extends Thread {
			private SurfaceHolder shSurfaceHolder;
			private TileView tvTileView;
			private boolean bRun = false;
			
			public DrawThread(SurfaceHolder surfaceHolder, TileView tileView) {
				shSurfaceHolder = surfaceHolder;
				tvTileView = tileView;
			}
			
			public void setRunning(boolean run) {
				bRun = run;
			}
			
			public TileView getView() {
				return tvTileView;
			}
			
			public SurfaceHolder getSurfaceHolder() {
				return shSurfaceHolder;
			}
			
			@Override
			public void run() {
				Canvas canvas;
				while (bRun) {
					canvas = null;
					try {
						canvas = shSurfaceHolder.lockCanvas(null);
						synchronized (shSurfaceHolder) {
							tvTileView.onDraw(canvas);
						}
					} finally {
						if (canvas != null) {
							shSurfaceHolder.unlockCanvasAndPost(canvas);
						}
					}
				}
			}
		}
	}
}
