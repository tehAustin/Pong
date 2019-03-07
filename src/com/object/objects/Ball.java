package com.object.objects;

import com.object.GameObject;
import com.object.ObjectType;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Ball extends GameObject {

    private boolean drawCollision = true;
    private Color color = Color.WHITE;
    private static final int WIDTH = 12, HEIGHT = 12;

    public Ball(float x, float y, ObjectType type) {
        super(x, y, type);
    }

    public void onCollision(LinkedList<GameObject> objects) {
        for (GameObject obj : objects) {
            if (obj == null) continue;
            if (obj.getCollisionBoundaries().size() <= 0) continue;

            for (int i = 0; i < obj.getCollisionBoundaries().size(); i++) {
                if (getCollisionBoundaries().get(i) == null) continue;
                if (getCollisionBoundaries().get(i).intersects(obj.getCollisionBoundaries().get(i))) {
                    Random r = new Random();
                    color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                }
            }


        }
    }

    public void setBoundaries() {
        collisionBoundaries = new ArrayList<>();

        getCollisionBoundaries().add(new Rectangle((int) x, (int) y, width, height));
    }

    public void render(Graphics g) {
        if (!isVisible()) return;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillOval((int) x, (int) y, width, height);

        if (drawCollision) {
            g2d.setColor(Color.RED);
            for (Rectangle rect : getCollisionBoundaries()) {
                if (rect == null) continue;
                g2d.drawRect((int) rect.getX(), (int) rect.getY(), rect.width, rect.height);
            }
        }
    }

    public void tick(LinkedList<GameObject> objects) {
        onCollision(objects);
    }
}
