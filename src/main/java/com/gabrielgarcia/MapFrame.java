package com.gabrielgarcia;

import javax.swing.JFrame;
import java.awt.*;

public class MapFrame extends JFrame {
    public MapFrame(TileMap tileMap, int size) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout()); // Set the layout manager
        this.getContentPane().add(tileMap, BorderLayout.CENTER); // Add the TileMap to the center
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
