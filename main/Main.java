package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dance Party");

        window.add(gamePanel);
        window.pack();

        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
