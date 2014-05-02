package org.gete.android.dorongkotak;

import org.gete.android.framework.Screen;
import org.gete.android.framework.implementation.AndroidGame;

public class DorongKotak extends AndroidGame {
	private boolean pertamaKali = true;
	
	@Override
	public Screen getInitScreen() {
		if (pertamaKali) {
			Asset.loadTheme(this);
			pertamaKali = false;
		}
		return new SplashLoadingScreen(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Asset.theme.play();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Asset.theme.pause();
	}
}
