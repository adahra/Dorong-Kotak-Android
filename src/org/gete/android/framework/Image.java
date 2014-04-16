package org.gete.android.framework;

import org.gete.android.framework.Graphics.ImageFormat;

public interface Image {
	
	public int getWidth();
	
	public int getHeight();
	
	public ImageFormat getFormat();
	
	public void dispose();
}
