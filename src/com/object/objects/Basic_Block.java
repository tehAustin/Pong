package com.object.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import com.object.GameObject;
import com.object.ObjectType;

public class Basic_Block extends GameObject{

	private boolean destroyed = false;
	public static final int WIDTH = 32, HEIGHT = 32;
	
	public Basic_Block(float x, float y, ObjectType type) {
		super(x, y, type);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setBoundaries();
	}

	public void onCollision(LinkedList<GameObject> objects) {
		for (GameObject obj : objects) {
			if (obj == null) continue;
			if (obj.getCollisionBoundaries().size() <= 0) continue;
			for (int i = 0; i < obj.getCollisionBoundaries().size(); i++) {
				if (getCollisionBoundaries().get(i) == null) continue;
				if (getCollisionBoundaries().get(i).intersects(obj.getCollisionBoundaries().get(i))) {
					//if anything collides with the block


				}
			}
		}
	}

	private void destroy() {
		setDestroyed(true);
		setVisible(false);
	}

	public void render(Graphics g) {
		if (isDestroyed() || !isVisible()) return;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.DARK_GRAY);
		g2d.drawRect((int) x, (int) y, width, height);

		for (Rectangle rect : getCollisionBoundaries()) {
			if (rect == null) continue;
		}
	}
	
	public void tick(LinkedList<GameObject> objects) {
		if (isDestroyed()) return;
		setBoundaries();
	}


	public void setBoundaries() {
		collisionBoundaries = new ArrayList<>();
		getCollisionBoundaries().add(new Rectangle((int) x, (int) y, width, height));
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
}
