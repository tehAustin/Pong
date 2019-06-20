package com.object.objects;

import com.main.Game;
import com.main.Handler;
import com.object.GameObject;
import com.object.ObjectType;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Ball extends GameObject {

    private Color color = Color.WHITE;
    private BallDirection currentDirection = BallDirection.WEST;
    private static final int WIDTH = 12, HEIGHT = 12, NORMAL_SPEED = 7;
    private final float MAX_BOUNCE_ANGLE = (float) (5 * (Math.PI / Math.toRadians(12.0)));

    public Ball(float x, float y, ObjectType type) {
        super(x, y, type);
        this.velX = NORMAL_SPEED;
        this.velY = -2;
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setBoundaries();
        setDirection(currentDirection, null, false);

    }

    public void onCollision(LinkedList<GameObject> objects) {
        for (GameObject obj : objects) {
            if (obj == null) continue;
            if (obj.getType().equals(this.getType())) continue;
            if (obj.getCollisionBoundaries().size() <= 0) continue;

            for (int i = 0; i < obj.getCollisionBoundaries().size(); i++) {
                if (i >= getCollisionBoundaries().size()) break;
                if (getCollisionBoundaries().get(i) == null) break;

                if (getCollisionBoundaries().get(i).intersects(obj.getCollisionBoundaries().get(i))) {
                    if (isCollidableObject(obj)) {
                        Random r = new Random();
                        color = new Color(r.nextInt(128) + r.nextInt(128), r.nextInt(128) + r.nextInt(128), r.nextInt(128) + r.nextInt(128));
                        setDirection(currentDirection == BallDirection.WEST ? BallDirection.EAST : BallDirection.WEST, obj, true);
                        break;
                    }
                }

            }
        }
    }

    //TODO possibly write correct position function to be called as the ball collides with any object
    private void correctPosition(GameObject collider) {
    }

    private boolean isCollidableObject(GameObject obj) {
        if (obj.getType().equals(ObjectType.AI_PADDLE) || obj.getType().equals(ObjectType.PADDLE) || obj.getType().equals(ObjectType.BASIC_BLOCK)) {
            return true;
        }
        return false;
    }

    private void setDirection(BallDirection direction, GameObject collider, boolean collided) {
        Handler handler = getHandler();
        switch (direction) {
            case EAST:
            case WEST:
                if (collided) {
                    boolean isPaddle = (collider.getType().equals(ObjectType.PADDLE) || collider.getType().equals(ObjectType.AI_PADDLE));
                    if (isPaddle) {
                        double relativeIntersectY = handler.getRelativeIntersectY(collider, this);
                        double normalizedRelativeIntersectY = handler.getNormalizedRelativeIntersectY(relativeIntersectY, collider);
                        float bounceAngle = handler.getYBounceAngle(normalizedRelativeIntersectY, MAX_BOUNCE_ANGLE);
                        velX = (float) (NORMAL_SPEED * Math.cos(bounceAngle));
                        velY = (float) (NORMAL_SPEED * Math.sin(bounceAngle));
                    } else {
                        velX = -velX;
                        velY = -velY;
                        if (collider.getType().equals(ObjectType.BASIC_BLOCK)) {
                            velX = -velX;
                            ((Basic_Block) collider).destroy();
                        }
                    }

                } else {
                    velX = -velX;
                    velY = -velY;
                }
                break;

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

        boolean drawCollision = false;
        if (drawCollision) {
            g2d.setColor(Color.RED);
            for (Rectangle rect : getCollisionBoundaries()) {
                if (rect == null) continue;
                g2d.drawRect((int) rect.getX(), (int) rect.getY(), rect.width, rect.height);
            }
        }
    }

    public void tick(LinkedList<GameObject> objects) {
        x += velX;
        y += velY;

        setBoundaries();
        onCollision(objects);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BallDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(BallDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    private Handler getHandler() {
        return Game.getHandler();
    }

    enum BallDirection {
        WEST, EAST
    }
}
