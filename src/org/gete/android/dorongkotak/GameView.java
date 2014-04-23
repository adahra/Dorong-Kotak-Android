package org.gete.android.dorongkotak;

import android.app.Activity;
import android.os.Bundle;

public class GameView extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_view);
	}
	
	@Override
	public void onBackPressed() {
		GameView.this.finish();
	}
}
