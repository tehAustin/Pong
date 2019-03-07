package com.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.object.GameObject;
import com.object.ObjectType;
import com.object.objects.Basic_Block;
import com.object.objects.Paddle;

public class Handler {

    private Game game;
    private LinkedList<GameObject> objects = new LinkedList<GameObject>();
    private Paddle player;
    private Paddle aiPaddle;

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
        for (GameObject o : objects) {
            if (o == null) continue;
            o.tick(objects);
        }
    }

    void render(Graphics g) {
        for (GameObject o : objects) {
            if (o == null) continue;
            o.render(g);
        }
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
