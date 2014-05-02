package org.gete.android.dorongkotak;

import org.gete.android.framework.Screen;
import org.gete.android.framework.implementation.AndroidGame;

public class DorongKotak extends AndroidGame {
	@Override
	public Screen getInitScreen() {
		return new LoadingScreen(this);
	}
}
