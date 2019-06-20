package com.input;

import com.main.Game;
import com.main.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyMap = new boolean[256];

    public KeyInput(Game game) {
        this.handler = game.getHandler();
    }

    public void keyPressed(KeyEvent e) {
        keyMap[e.getKeyCode()] = true;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                handler.resetBlocks();
                break;
            case KeyEvent.VK_B:
                handler.addBall();
                break;
            case KeyEvent.VK_UP:
                handler.getPlayer().setVelY(-10);
                break;
            case KeyEvent.VK_DOWN:
                handler.getPlayer().setVelY(10);
                break;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && keyMap[KeyEvent.VK_DOWN]) {
            handler.getPlayer().setVelY(-10);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && keyMap[KeyEvent.VK_UP]) {
            handler.getPlayer().setVelY(10);
        }
    }

    public void keyReleased(KeyEvent e) {
        keyMap[e.getKeyCode()] = false;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                handler.getPlayer().setVelY(0);
                break;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && keyMap[KeyEvent.VK_DOWN]) {
            handler.getPlayer().setVelY(10);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && keyMap[KeyEvent.VK_UP]) {
            handler.getPlayer().setVelY(-10);
        }
    }

}
