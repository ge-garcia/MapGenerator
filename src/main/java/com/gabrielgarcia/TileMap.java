package com.gabrielgarcia;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class TileMap extends JPanel {
  private static final int TILE_SIZE = 20;
  private double[][] tileMap;
  private Colors colors;
  private long seed;
  private int size;
  private JFrame mapWindow;

  public TileMap() { // Default constructor, should never be used
    tileMap = new double[16][16];
    colors = new Colors();
  }
  public TileMap(long seed, Colors colors, int size) {
    tileMap = new double[size][size];
    this.colors = colors;
    this.seed = seed;
    this.size = size;
    this.setPreferredSize(new Dimension(size * TILE_SIZE, size * TILE_SIZE));
    this.generateMap();
    this.printMap();
  }

  // I just check the length of the array since its designed to have uniform width and height
  public int getWidth() { return tileMap[0].length; }
  public int getHeight() {
    return tileMap.length;
  }
  private void generateMap() {
    // Generate the values for the map
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        double value = OpenSimplex2S.noise2(seed, x, y);
        tileMap[x][y] = value;
      }
    }
  }

  private void printMap() {
    // Only here for debugging
    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        System.out.printf("%.2f", tileMap[x][y]);
      }
      System.out.println();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    // Paint to screen
    super.paintComponent(g);
    g.clearRect(0, 0, getWidth() * TILE_SIZE, getHeight() * TILE_SIZE);
    int rectWidth = TILE_SIZE;
    int rectHeight = TILE_SIZE;

    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        int i = x * rectWidth;
        int j = y * rectHeight;
        Color tileColor = colors.getBiomeBlock(tileMap[x][y]);
        g.setColor(tileColor);
        g.fillRect(i, j, rectWidth, rectHeight);
      }
    }
    SwingUtilities.invokeLater(this::repaint);
  }

}
