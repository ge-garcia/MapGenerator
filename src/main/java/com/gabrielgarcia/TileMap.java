package com.gabrielgarcia;

import java.awt.*;
import java.util.Random;
import javax.swing.JPanel;

public class TileMap extends JPanel {
  private double[][] tileMap;
  private Colors colors;
  private long seed;
  Random random = new Random();


  public TileMap() {
    tileMap = new double[16][16];
    colors = new Colors();
    seed = random.nextLong();
  }
  public TileMap(long seed, Colors colors, int size) {
    tileMap = new double[size][size];
    this.colors = colors;
    this.seed = seed;
  }

  public int getWidth() {
    return tileMap.length;
  }
  public int getHeight() {
    return tileMap[0].length;
  }
  private void generateMap() {
    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        double value = OpenSimplex2S.noise2(seed, x, y);
        tileMap[x][y] = value;
      }
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        Color biomeBlock = colors.getBiomeBlock(tileMap[x][y]);
        g.setColor(biomeBlock);
        g.fillRect(x, y, getWidth(), getHeight());
      }
    }
  }
}
