package org.gete.android.framework.implementation;

import java.io.IOException;

import org.gete.android.framework.Audio;
import org.gete.android.framework.Music;
import org.gete.android.framework.Sound;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

public class AndroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}

	@Override
	public Music createMusic(String fileName) {
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			return new AndroidMusic(assetDescriptor);
		} catch (IOException ioe) {
			throw new RuntimeException("Couldn't load music '" + fileName + "'");
		}
	}

	@Override
	public Sound createSound(String fileName) {
		try {
			AssetFileDescriptor assetDecriptor = assets.openFd(fileName);
			int soundId = soundPool.load(assetDecriptor, 0);
			return new AndroidSound(soundPool, soundId);
		} catch (IOException ioe) {
			throw new RuntimeException("Couldn't load sound '" + fileName + "'");
		}
	}

}
