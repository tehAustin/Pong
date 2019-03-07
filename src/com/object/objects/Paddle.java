package com.object.objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import com.object.GameObject;
import com.object.ObjectType;

@SuppressWarnings("Duplicates")
public class Paddle extends GameObject {

    public static final int WIDTH = 10, HEIGHT = 96;
    private int aiTick = 0;
    private boolean drawCollision = true;

    public Paddle(float x, float y, ObjectType type) {
        super(x, y, type);
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setBoundaries();
    }

    public void setBoundaries() {
        Rectangle rightBoundary = new Rectangle((int) (x + (width / 2)), (int) y, width / 2, height);
        Rectangle leftBoundary = new Rectangle((int) x, (int) y, width / 2, height);

        collisionBoundaries = new ArrayList<>();
        getCollisionBoundaries().add(rightBoundary);
        getCollisionBoundaries().add(leftBoundary);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) x, (int) y, width, height);
        if (drawCollision) {
            g2d.setColor(Color.RED);
            for (Rectangle rect : getCollisionBoundaries()) {
                if (rect == null) continue;
                g2d.drawRect((int) rect.getX(), (int) rect.getY(), rect.width, rect.height);
            }
        }
    }

    public void tick(LinkedList<GameObject> objects) {
        y += velY;

        setBoundaries();
        onCollision(objects);
    }

    public void onCollision(LinkedList<GameObject> objects) {
        for (GameObject obj : objects) {
            if (obj == null) continue;
            if (obj.getCollisionBoundaries().size() <= 0) continue;

            for (int i = 0; i < obj.getCollisionBoundaries().size(); i++) {
                if (getCollisionBoundaries().get(i) == null) continue;
                if (getCollisionBoundaries().get(i).intersects(obj.getCollisionBoundaries().get(i))) {

                }
            }

            if (obj.getType() == ObjectType.BALL) {

            }
        }
    }


}
