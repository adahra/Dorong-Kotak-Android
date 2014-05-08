package org.gete.android.dorongkotak;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

@SuppressLint("WrongCall")
public class PanelThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private DrawingPanel drawingPanel;
	private boolean run = false;
	private Canvas canvas;
	private static String TAG = "PanelThread";
	
	public PanelThread(SurfaceHolder surfaceHolder, DrawingPanel drawingPanel) {
		this.surfaceHolder = surfaceHolder;
		this.drawingPanel = drawingPanel;
	}
	
	public void setRunning(boolean run) {
		this.run = run;
	}
	
	@Override
	public void run() {
		super.run();
		while (this.run) {
			canvas = null;
			try {
				canvas = this.surfaceHolder.lockCanvas(null);
				synchronized (this.surfaceHolder) {
					drawingPanel.draw(canvas);
				}
			} catch (Exception e) {
				Log.d(TAG, "run() is " + e.getMessage());
			} finally {
				if (canvas != null) {
					this.surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
}
