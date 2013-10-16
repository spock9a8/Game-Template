package com.gb.game;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	private static final int KEY_COUNT = 256;

	private enum KeyState {
		RELEASED,
		PRESSED,
		ONCE
	}

	private boolean[] currentKeys = null;

	private KeyState[] keys = null;

	public KeyHandler(Game game) {
		game.addKeyListener(this);
		currentKeys = new boolean[KEY_COUNT];
		keys = new KeyState[KEY_COUNT];
		for (int i = 0; i < KEY_COUNT; ++i) {
			keys[i] = KeyState.RELEASED;
		}
	}

	public synchronized void poll() {
		for (int i = 0; i < KEY_COUNT; ++i) {
			if (currentKeys[i]) {
				if (keys[i] == KeyState.RELEASED)
					keys[i] = KeyState.ONCE;
				else
					keys[i] = KeyState.PRESSED;
			} else {
				keys[i] = KeyState.RELEASED;
			}
		}
	}

	public boolean isKeyDown(int keyCode) {
		return keys[keyCode] == KeyState.ONCE
				|| keys[keyCode] == KeyState.PRESSED;
	}

	public boolean isKeyDownOnce(int keyCode) {
		return keys[keyCode] == KeyState.ONCE;
	}

	public boolean isKeyUp(int keyCode) {
		return keys[keyCode] == KeyState.ONCE
				|| keys[keyCode] == KeyState.RELEASED;
	}
	public boolean isKeyDown(Key keyCode) {
		return keys[keyCode.code] == KeyState.ONCE
				|| keys[keyCode.code] == KeyState.PRESSED;
	}

	public boolean isKeyDownOnce(Key keyCode) {
		return keys[keyCode.code] == KeyState.ONCE;
	}

	public boolean isKeyUp(Key keyCode) {
		return keys[keyCode.code] == KeyState.ONCE
				|| keys[keyCode.code] == KeyState.RELEASED;
	}

	public synchronized void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			currentKeys[keyCode] = true;
		}
	}

	public synchronized void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			currentKeys[keyCode] = false;
		}

	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public static class Key {
		int code;
		
		public Key(int code) {
			this.code = code;
		}

		public static Key a = new Key(KeyEvent.VK_A);
		public static Key b = new Key(KeyEvent.VK_B);
		public static Key c = new Key(KeyEvent.VK_C);
		public static Key d = new Key(KeyEvent.VK_D);
		public static Key e = new Key(KeyEvent.VK_E);
		public static Key f = new Key(KeyEvent.VK_F);
		public static Key g = new Key(KeyEvent.VK_G);
		public static Key h = new Key(KeyEvent.VK_H);
		public static Key i = new Key(KeyEvent.VK_I);
		public static Key j = new Key(KeyEvent.VK_J);
		public static Key k = new Key(KeyEvent.VK_K);
		public static Key l = new Key(KeyEvent.VK_L);
		public static Key m = new Key(KeyEvent.VK_M);
		public static Key n = new Key(KeyEvent.VK_N);
		public static Key o = new Key(KeyEvent.VK_O);
		public static Key p = new Key(KeyEvent.VK_P);
		public static Key q = new Key(KeyEvent.VK_Q);
		public static Key r = new Key(KeyEvent.VK_R);
		public static Key s = new Key(KeyEvent.VK_S);
		public static Key t = new Key(KeyEvent.VK_T);
		public static Key u = new Key(KeyEvent.VK_U);
		public static Key v = new Key(KeyEvent.VK_V);
		public static Key w = new Key(KeyEvent.VK_W);
		public static Key x = new Key(KeyEvent.VK_X);
		public static Key y = new Key(KeyEvent.VK_Y);
		public static Key z = new Key(KeyEvent.VK_Z);
		public static Key Zero = new Key(KeyEvent.VK_0);
		public static Key One = new Key(KeyEvent.VK_1);
		public static Key Two = new Key(KeyEvent.VK_2);
		public static Key Three = new Key(KeyEvent.VK_3);
		public static Key Four = new Key(KeyEvent.VK_4);
		public static Key Five = new Key(KeyEvent.VK_5);
		public static Key Six = new Key(KeyEvent.VK_6);
		public static Key Seven = new Key(KeyEvent.VK_7);
		public static Key Eight = new Key(KeyEvent.VK_8);
		public static Key Nine = new Key(KeyEvent.VK_9);
		public static Key Enter = new Key(KeyEvent.VK_A);
		public static Key Space = new Key(KeyEvent.VK_A);
		public static Key BackSpace = new Key(KeyEvent.VK_A);
		public static Key Down = new Key(KeyEvent.VK_A);
		public static Key Up = new Key(KeyEvent.VK_A);
		public static Key Left = new Key(KeyEvent.VK_A);
		public static Key Right = new Key(KeyEvent.VK_A);
	}
}