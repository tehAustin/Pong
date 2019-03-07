package com.ui;

import java.awt.*;

import javax.swing.JFrame;

import com.main.Game;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game) {
		game.setMaximumSize(new Dimension(width, height));
		game.setMinimumSize(new Dimension(width, height));
		game.setPreferredSize(new Dimension(width, height));
		
		add(game);
		pack();
		setTitle(title);
		setSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		toFront();
		setState(Frame.NORMAL);

		game.start();
	}

}
