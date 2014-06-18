package org.gete.android.dorongkotak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import org.gete.android.dorongkotak.R;

/**
 * Kelas yang digunakan untuk mengatur tampilan dari menu
 * @author Adnanto Ahmad Ramadhon
 *
 */
public class MenuView extends Activity {	
	private Intent mIntent;
	private static String TAG = "MenuView";
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_view);
		context = getApplicationContext();
		PemutarSuara.muatSuara(context);
		PemutarSuara.mainkanMusikSatu();
		Log.d(TAG, "onCreate(savedInstanceState");
	}
	
	public void tampilGame(View view) {
		mIntent = new Intent(MenuView.this, Play.class);
		MenuView.this.startActivity(mIntent);
	}
	
	public void tampilKredit(View view) {
		mIntent = new Intent(MenuView.this, CreditView.class);
		MenuView.this.startActivity(mIntent);
	}
	
	public void keluar(View view) {
		keluar();
	}
	
	@Override
	public void onDestroy() {
		PemutarSuara.hentikanMusikSatu();
		keluar();
		super.onDestroy();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		PemutarSuara.mainkanMusikSatu();
	}
	
	@Override
	public void onPause() {
		PemutarSuara.pauseMusikSatu();
		super.onPause();
	}
	
	@Override
	public void onBackPressed() {
    	super.onBackPressed();
    	keluar();
	}
	
	private void keluar() {
		PemutarSuara.hentikanMusikSatu();
    	Process.killProcess(Process.myPid());
	}
	
}
