package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);
        
        window.setTitle("Caatinga Adventure");

        GamePainel gamePainel = new GamePainel();
        window.add(gamePainel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePainel.startGameThread();
    }
}
