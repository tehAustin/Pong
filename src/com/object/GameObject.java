package com.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected int width, height;
	protected ArrayList<Rectangle> collisionBoundaries;
	protected ObjectType type;
	protected float velX, velY;
	
	public GameObject(float x, float y, ObjectType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public abstract void onCollision(LinkedList<GameObject> objects);

	public abstract void setBoundaries();
	
	public abstract void render(Graphics g);
	
	public abstract void tick(LinkedList<GameObject> objects);
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

	public ArrayList<Rectangle> getCollisionBoundaries() {
		return collisionBoundaries;
	}

	public void setCollisionBoundaries(ArrayList<Rectangle> collisionBoundaries) {
		this.collisionBoundaries = collisionBoundaries;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	
}
