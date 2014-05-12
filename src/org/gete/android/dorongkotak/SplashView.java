package org.gete.android.dorongkotak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashView extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new Handler().postDelayed(new Thread() {
			
			@Override
			public void run() {
				Intent mainMenu = new Intent(SplashView.this, MenuView.class);
				SplashView.this.startActivity(mainMenu);
				SplashView.this.finish();
				overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			}
		}, 3000);
	}
}
