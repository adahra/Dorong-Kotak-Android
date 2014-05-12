package org.gete.android.dorongkotak;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class ThreadKanvas extends Thread {
	public Boolean isRunning = true;
	public Kanvas kanvas;
	private static final long FPS = 60;
	private boolean running = false;
	
	public ThreadKanvas(Kanvas kanvas) {
		this.kanvas = kanvas;
	}
	
	@SuppressLint("WrongCall")
	@Override
	public void run() {
		long tickFPS = 100 / FPS;
		long waktuMulai;
		long waktuStop;
		
		while (running) {
			Canvas canvas = null;
			waktuMulai = System.currentTimeMillis();
			try {
				canvas = kanvas.getHolder().lockCanvas();
				synchronized (kanvas.getHolder()) {
					kanvas.onDraw(canvas);
				}
			} finally {
				if (canvas != null) {
					kanvas.getHolder().unlockCanvasAndPost(canvas);
				}
			}
			
			waktuStop = tickFPS - (System.currentTimeMillis() - waktuMulai);
			
			try {
				if (waktuStop > 0) {
					sleep(waktuStop);
				} else {
					sleep(10);
				}
			} catch (Exception e) {
				
			}
		}
	}
	
	public void setRunning(boolean run) {
		running = run;
	}

}
