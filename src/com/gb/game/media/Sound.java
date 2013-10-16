package com.gb.game.media;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound Example = new Sound("/example.wav");
	public static final Sound explosion = new Sound("/explosion.wav");
	public static final Sound laser = new Sound("/laser.wav");
	public static final Midi test = new Midi("/song020.mid");
//	public static final Sound test = new Sound("/song020.mid");

	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		clip.stop();
	}

}
