package org.gete.android.dorongkotak;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Process;
import android.view.View;

public class MenuView extends Activity {
	private MediaPlayer mBackgroundMusic;
	private Intent mIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_view);
		mBackgroundMusic = MediaPlayer.create(this, R.raw.menuscreenmusic);
		mBackgroundMusic.setLooping(true);
		mBackgroundMusic.setVolume(1, 1);
		mBackgroundMusic.start();
	}
	
	public void tampilGame(View view) {
		mIntent = new Intent(MenuView.this, MainActivity.class);
		startActivity(mIntent);
	}
	
	public void tampilKredit(View view) {
		mIntent = new Intent(MenuView.this, CreditView.class);
		startActivity(mIntent);
	}
	
	public void keluar(View view) {
		mBackgroundMusic.stop();
		mBackgroundMusic.release();
		hentikanProses();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mBackgroundMusic.release();
		hentikanProses();
	}
	
	@Override
	public void onResume() {
		mBackgroundMusic.start();
		super.onResume();
	}
	
	@Override
	public void onPause() {
		mBackgroundMusic.pause();
		super.onPause();
	}
	
	@Override
	public void onBackPressed() {
		mBackgroundMusic.stop();
		hentikanProses();
	}
	
	public void hentikanProses() {
		Process.killProcess(Process.myPid());
	}
	
}
