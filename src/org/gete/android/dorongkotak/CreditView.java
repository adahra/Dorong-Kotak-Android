package org.gete.android.dorongkotak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.gete.android.dorongkotak.R;

/**
 * Kelas yang digunakan untuk menampilkan layar tentang aplikasi
 * @author Adnanto Ahmad Ramadhon
 *
 */
public class CreditView extends Activity {
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credit_view);
		mContext = getApplicationContext();
		PemutarSuara.muatSuara(mContext);
		PemutarSuara.mainkanMusikSatu();
	}
	
	public void kembali(View view) {
		kembali();
	}
	
	@Override
	public void onBackPressed() {
    	super.onBackPressed();
    	kembali();
	}
	
	private void kembali() {
		PemutarSuara.hentikanMusikSatu();
    	Intent intent = new Intent(CreditView.this, MenuView.class);
    	CreditView.this.startActivity(intent);
    	CreditView.this.finish();
	}
}
