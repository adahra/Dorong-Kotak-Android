package org.gete.android.dorongkotak;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * Kelas yang digunakan untuk menangani suara
 * dalam game
 * @author Adnanto Ahmad Ramadhon
 *
 */
public class PemutarSuara {
	private static MediaPlayer mDeviceMoving;
	private static MediaPlayer mMusik;
	private static boolean mSuaraAktif = true;
	private static SoundPool mSuara;
	
	public static void muatSuara(Context context) {
		mSuara = new SoundPool(4, 3, 0);
		// mTombol = mSuara.load(context, R.id.tombol, 1);
		// mMusik = MediaPlayer.create(context, resid);
		mMusik.setLooping(true);
		// mDeviceMoving = MediaPlayer.create(context, resid);
		mDeviceMoving.setLooping(true);
	}
	
	public static final void pauseDeviceMoving() {
		if ((mSuaraAktif) && (mDeviceMoving.isPlaying())) mDeviceMoving.pause();
	}
	
	public static final void pauseMusik() {
		if ((mSuaraAktif) && (mMusik.isPlaying())) mMusik.pause();
	}
	
	public static void playButton() {
		// if ((mSuaraAktif) mSuara.play(mTombol, 1.0F, 1.0F, 1, 0, 1.0F);
	}
	
	public static final void playDeviceMoving() {
		if ((mSuaraAktif) && (!mDeviceMoving.isPlaying())) {
			mDeviceMoving.seekTo(0);
			mDeviceMoving.start();
		}
	}
	
	public static final void release() {
		if (mSuaraAktif) mSuara.release();
		if ((mMusik != null) && (mMusik.isPlaying())) {
			mMusik.stop();
			mMusik.release();
		}
		
		if ((mDeviceMoving != null) && (mDeviceMoving.isPlaying())) {
			mDeviceMoving.stop();
			mDeviceMoving.release();
		}
	}
	
	public static final void setSuaraAktif(boolean suaraAktif) {
		mSuaraAktif = suaraAktif;
	}
}
