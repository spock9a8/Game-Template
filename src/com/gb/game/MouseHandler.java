package com.gb.game;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

	private static final int BUTTON_COUNT = 3;
	private int mousePosX;
	private int mousePosY;
	private int currentPosX;
	private int currentPosY;
	private boolean[] state = null;
	private MouseState[] poll = null;

	private enum MouseState {
		RELEASED, PRESSED, ONCE
	}

	public MouseHandler(Game game) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
		state = new boolean[BUTTON_COUNT];
		poll = new MouseState[BUTTON_COUNT];
		for (int i = 0; i < BUTTON_COUNT; ++i) {
			poll[i] = MouseState.RELEASED;
		}

	}

	public synchronized void poll() {
		mousePosX = currentPosX;
		mousePosY = currentPosY;
		for (int i = 0; i < BUTTON_COUNT; ++i) {
			if (state[i]) {
				if (poll[i] == MouseState.RELEASED)
					poll[i] = MouseState.ONCE;
				else
					poll[i] = MouseState.PRESSED;
			} else {
				poll[i] = MouseState.RELEASED;
			}

		}

	}

	public int x() {
		return mousePosX;
	}

	public int y() {
		return mousePosY;
	}

	public boolean buttonDownOnce(int button) {
		return poll[button - 1] == MouseState.ONCE;
	}

	public boolean buttonDown(int button) {
		return poll[button - 1] == MouseState.ONCE || poll[button - 1] == MouseState.PRESSED;
	}

	public synchronized void mousePressed(MouseEvent e) {
		state[e.getButton() - 1] = true;
	}

	public synchronized void mouseReleased(MouseEvent e) {
		state[e.getButton() - 1] = false;
	}

	public synchronized void mouseEntered(MouseEvent e) {
		mouseMoved(e);
	}

	public synchronized void mouseExited(MouseEvent e) {
		mouseMoved(e);
	}

	public synchronized void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	public synchronized void mouseMoved(MouseEvent e) {
		currentPosX = e.getX() * (Game.WIDTH / Game.instance.getWidth());
		currentPosY = e.getY() * (Game.HEIGHT / Game.instance.getHeight());
	}

	public void mouseClicked(MouseEvent e) {

	}

}