package org.gete.android.dorongkotak;

import org.gete.android.framework.Image;
import org.gete.android.framework.Music;

public class Asset {
	public static Image actorBack;
	public static Image actorFront;
	public static Image actorLeft;
	public static Image actorRight;
	public static Image crate;
	public static Image floor;
	public static Image wall;
	public static Image wallCornerLeft;
	public static Image wallCornerRight;
	public static Image wallLeftFloor;
	public static Image wallRightFloor;
	public static Image woodenHole;
	public static Image splash;
	public static Image menu;
	public static Music theme;
	
	public static void loadTheme(DorongKotak dorongKotak) {
		theme = dorongKotak.getAudio().createMusic("sfx/musictheme.mp3");
		theme.setLooping(true);
		theme.setVolume(1.00f);
		theme.play();
	}
	
}
