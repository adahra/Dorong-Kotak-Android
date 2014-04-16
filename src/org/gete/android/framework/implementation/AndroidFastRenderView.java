package org.gete.android.framework.implementation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable {
	AndroidGame game;
	Bitmap frameBuffer;
	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;
	
	public AndroidFastRenderView(AndroidGame game, Bitmap frameBuffer) {
		super(game);
		
		this.game = game;
		this.frameBuffer = frameBuffer;
		this.holder = getHolder();
	}

	public AndroidFastRenderView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AndroidFastRenderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	@Override
	public void run() {
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();
		while (running) {
			if (!holder.getSurface().isValid()) {
				continue;
			}
			
			float deltaTime = (System.nanoTime() - startTime) / 10000000.000f;
			startTime = System.nanoTime();
			
			if (deltaTime > 3.15) {
				deltaTime = (float) 3.15;
			}
			
			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().paint(deltaTime);
			
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(frameBuffer, null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);
		}
	}
	
	public void pause() {
		running = false;
		while (true) {
			try {
				renderThread.join();
				break;
			} catch (InterruptedException ie) {
				
			}
		}
	}

}
