package org.gete.android.framework.implementation;

import java.util.List;

import org.gete.android.framework.Input;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

public class AndroidInput implements Input {
	TouchHandler touchHandler;

	@SuppressWarnings("deprecation")
	public AndroidInput(Context context, View view, float scaleX, float scaleY) {
		if (Integer.parseInt(VERSION.SDK) < 5) {
			touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
		} else {
			touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
		}
	}

	@Override
	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}

	@Override
	public int getTouchX(int pointer) {
		return touchHandler.getTouchX(pointer);
	}

	@Override
	public int getTouchY(int pointer) {
		return touchHandler.getTouchY(pointer);
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		return touchHandler.getTouchEvents();
	}

}
