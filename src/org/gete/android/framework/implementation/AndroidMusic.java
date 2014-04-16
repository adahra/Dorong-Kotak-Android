package org.gete.android.framework.implementation;

import java.io.IOException;

import org.gete.android.framework.Music;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;

public class AndroidMusic implements Music, OnCompletionListener, OnSeekCompleteListener, OnPreparedListener, OnVideoSizeChangedListener {
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;
	
	public AndroidMusic(AssetFileDescriptor assetDescriptor) {
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnSeekCompleteListener(this);
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setOnVideoSizeChangedListener(this);
		} catch (Exception e) {
			
		}
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		synchronized (this) {
			isPrepared = true;
		}
	}

	@Override
	public void onSeekComplete(MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		synchronized (this) {
			isPrepared = false;
		}
	}

	@Override
	public void play() {
		if (this.mediaPlayer.isPlaying()) {
			return;
		}
		
		try {
			synchronized (this) {
				if (!isPrepared) {
					mediaPlayer.prepare();
				}
				
				mediaPlayer.start();
			}
		} catch (IllegalStateException ise) {
			ise.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void stop() {
		if (this.mediaPlayer.isPlaying() == true) {
			this.mediaPlayer.stop();
			
			synchronized (this) {
				isPrepared = false;
			}
		}
	}

	@Override
	public void pause() {
		if (this.mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

	@Override
	public void setLooping(boolean isLooping) {
		mediaPlayer.setLooping(isLooping);
	}

	@Override
	public void setVolume(float volume) {
		mediaPlayer.setVolume(volume, volume);
	}

	@Override
	public boolean isPlaying() {
		return this.mediaPlayer.isPlaying();
	}

	@Override
	public boolean isStopped() {
		return !isPrepared;
	}

	@Override
	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}

	@Override
	public void dispose() {
		if (this.mediaPlayer.isPlaying()) {
			this.mediaPlayer.stop();
		}
		
		this.mediaPlayer.release();
	}

	@Override
	public void seekBegin() {
		mediaPlayer.seekTo(0);
	}

}
