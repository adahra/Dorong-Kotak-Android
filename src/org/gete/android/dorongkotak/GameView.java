package org.gete.android.dorongkotak;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;

public class GameView extends Activity implements OnTouchListener, OnTouchModeChangeListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_view);
	}
	
	@Override
	public void onBackPressed() {
		GameView.this.finish();
	}

	@Override
	public void onTouchModeChanged(boolean isInTouchMode) {
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
}
