package org.gete.android.dorongkotak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

@SuppressLint("NewApi")
public class CreditView extends Activity {
	private TextView mTextView;
	private Animation mAnimation;
	private Context mContext;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credit_view);
		mTextView = (TextView) findViewById(R.id.textView2);
	}
	
	public void kembali(View view) {
		CreditView.this.finish();
	}
		
	@SuppressWarnings("unused")
	private void animateTextView() {
		int textWidth = getTextViewWidth(mTextView);
		int displayWidth = getDisplayWidth(mContext);
		
		if (displayWidth < textWidth) {
			mAnimation = new TranslateAnimation(0, displayWidth - textWidth, 0, 0);
			mAnimation.setDuration(3000);
			mAnimation.setStartOffset(500);
			mAnimation.setRepeatMode(Animation.REVERSE);
			mAnimation.setRepeatCount(Animation.INFINITE);
			
			mTextView.startAnimation(mAnimation);
		}
	}
	
	@SuppressWarnings("deprecation")
	private int getDisplayWidth(Context context) {
		int displayWidth;
		
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		Point screenSize = new Point();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			display.getSize(screenSize);
			displayWidth = screenSize.x;
		} else {
			displayWidth = display.getWidth();
		}
		
		return displayWidth;
	}
	
	private int getTextViewWidth(TextView textView) {
		textView.measure(0, 0);
		return textView.getMeasuredWidth();
	}

}
