package org.gete.android.dorongkotak;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Kelas yang digunakan untuk menangani suara
 * dalam game
 * @author Adnanto Ahmad Ramadhon
 *
 */
public class PemutarSuara {
	private static MediaPlayer mMusikSatu;
	private static MediaPlayer mMusikDua;
	private static boolean mSuaraAktif = true;
	// private static SoundPool mSuara;
	
	public static void muatSuara(Context context) {
		// mSuara = new SoundPool(4, 3, 0);
		// mTombol = mSuara.load(context, R.id.tombol, 1);
		mMusikSatu = MediaPlayer.create(context, R.raw.menuscreenmusic);
		mMusikSatu.setLooping(true);
		mMusikDua = MediaPlayer.create(context, R.raw.greippiardudar);
		mMusikDua.setLooping(true);
	}
	
	public static final void pauseMusikSatu() {
		if ((mSuaraAktif) && (mMusikSatu.isPlaying())) mMusikSatu.pause();
	}
	
	public static final void mainkanMusikSatu() {
		if ((mSuaraAktif) && (!mMusikSatu.isPlaying())) {
			mMusikSatu.seekTo(0);
			mMusikSatu.start();
		}
	}
	
	public static final void mainkanMusikDua() {
		if ((mSuaraAktif) && (!mMusikDua.isPlaying())) {
			mMusikDua.seekTo(0);
			mMusikDua.start();
		}
	}
	
	public static final void pauseMusikDua() {
		if ((mSuaraAktif) && (mMusikDua.isPlaying())) mMusikDua.pause();
	}
	
	public static void musikTombol() {
		// if ((mSuaraAktif) mSuara.play(mTombol, 1.0F, 1.0F, 1, 0, 1.0F);
	}
	
	public static final void hentikanMusikSatu() {
		// if (mSuaraAktif) mSuara.release();
		if ((mMusikSatu != null) && (mMusikSatu.isPlaying())) {
			mMusikSatu.stop();
			mMusikSatu.release();
		}
	}
	
	public static final void hentikanMusikDua() {
		// if (mSuaraAktif) mSuara.release();
		if ((mMusikDua != null) && (mMusikDua.isPlaying())) {
			mMusikDua.stop();
			mMusikDua.release();
		}
	}
	
	public static final void setSuaraAktif(boolean suaraAktif) {
		mSuaraAktif = suaraAktif;
	}
}
