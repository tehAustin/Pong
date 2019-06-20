package com.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.object.GameObject;
import com.object.ObjectType;
import com.object.objects.Ball;
import com.object.objects.Basic_Block;
import com.object.objects.Paddle;

public class Handler {

    private Game game;
    private LinkedList<GameObject> objects = new LinkedList<GameObject>();
    private Paddle player;
    private Paddle aiPaddle;
    private Ball ball;

    Handler(Game game) {
        this.game = game;
    }

    private void addObject(GameObject object) {
        objects.add(object);
    }

    void addPaddles() {
        player = new Paddle((float) Game.WIDTH / 6, (float) ((Game.HEIGHT / 2) - (Paddle.HEIGHT / 1.25)), ObjectType.PADDLE);
        aiPaddle = new Paddle((float) ((Game.WIDTH / 2) + (Game.WIDTH / 3.25)), (float) ((Game.HEIGHT / 2) - (Paddle.HEIGHT / 1.25)), ObjectType.AI_PADDLE);
        addObject(player);
        addObject(aiPaddle);
    }

    public void addBall() {
        ball = new Ball((float) Game.WIDTH / 2, (float) Game.HEIGHT / 2, ObjectType.BALL);
        addObject(ball);
    }

    public void resetBlocks() {
        for (GameObject obj : getObjects()) {
            if (obj == null) continue;
            if (obj.getType().equals(ObjectType.BASIC_BLOCK)) {
                ((Basic_Block) obj).destroy();
            }
        }
        createWalls();
    }

    void createWalls() {
        final int amount = 18, multiplier = 46;
        final int bottomY = 532, topY = 0, rightX = 752;
        //bottom
        for (int x = 0; x < amount * multiplier; x += 32) {
            addObject(new Basic_Block(x, bottomY, ObjectType.BASIC_BLOCK));
        }
        //top
        for (int x = 0; x < amount * multiplier; x += 32) {
            addObject(new Basic_Block(x, topY, ObjectType.BASIC_BLOCK));
        }
        //left wall
        for (int y = 0; y < amount * (multiplier - 6); y += 32) {
            addObject(new Basic_Block(0, y, ObjectType.BASIC_BLOCK));
        }
        //right wall
        for (int y = 0; y < amount * (multiplier - 6); y += 32) {
            addObject(new Basic_Block(rightX, y, ObjectType.BASIC_BLOCK));
        }
    }

    void tick() {
        try {
            for (GameObject o : objects) {
                if (o == null) continue;
                o.tick(objects);
            }
        } catch (Exception e) {
        }
    }

    void render(Graphics g) {
        try {
            for (GameObject o : objects) {
                if (o == null) continue;
                o.render(g);
            }
        } catch (Exception e) {
        }
    }

    public Ball getBall() {
        return ball;
    }

    public double getRelativeIntersectY(GameObject collider, GameObject collided) {
        float colliderIntersectY = collider.getY();
        return ((int) collided.getY() + (collided.getHeight() / 2.0)) - colliderIntersectY;
    }

    public double getNormalizedRelativeIntersectY(double relativeIntersectY, GameObject collider) {
        float colliderMiddle = (float) (collider.getHeight() / 2.0);
        return relativeIntersectY / (colliderMiddle);
    }

    public int getYBounceAngle(double normalizedRelativeIntersectY, final float MAX_BOUNCE_ANGLE) {
        return (int) (normalizedRelativeIntersectY * (int) MAX_BOUNCE_ANGLE);
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }

    public void setObjects(LinkedList<GameObject> objects) {
        this.objects = objects;
    }

    public Game getGame() {
        return game;
    }

    public Paddle getPlayer() {
        return player;
    }

    public Paddle getAiPaddle() {
        return aiPaddle;
    }


}
