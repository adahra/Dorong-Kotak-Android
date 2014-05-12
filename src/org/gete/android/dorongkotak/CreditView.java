package org.gete.android.dorongkotak;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CreditView extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credit_view);
	}
	
	public void kembali(View view) {
		CreditView.this.finish();
	}
		
}
