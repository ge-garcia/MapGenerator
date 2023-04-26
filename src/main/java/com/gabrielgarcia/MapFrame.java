package com.gabrielgarcia;

import javax.swing.*;

public class MapFrame extends JFrame {
    TileMap tileMap;
    ImageIcon icon = new ImageIcon("compass.png");
    public MapFrame() {
       tileMap = new TileMap();
       this.setResizable(false);
       this.setTitle("Map Generator");
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setIconImage(icon.getImage());
       this.add(tileMap);
       this.setVisible(true);
    }
    public MapFrame(long seed, Colors colors, int size) {
        tileMap = new TileMap(seed, colors, size);
        this.setResizable(false);
        this.setTitle("Map Generator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());
        this.add(tileMap);
        this.setVisible(true);
    }
}
