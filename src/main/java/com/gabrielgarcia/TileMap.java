package com.gabrielgarcia;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class TileMap extends JPanel {
  private static final int TILE_SIZE = 10;
  private final double[][] tileMap;
  private final double[][] moistureMap;
  private final Colors colors;
  private final boolean biomes;
  private final long elevationSeed;
  private final long moistureSeed;
  private final Random random = new Random();
  public final int size;

  public TileMap(long seed, Colors colors, int size, boolean biomes) {
    tileMap = new double[size][size];
    moistureMap = new double[size][size];
    this.colors = colors;
    this.elevationSeed = seed;
    this.moistureSeed = random.nextLong();
    this.size = size;
    this.biomes = biomes;
    this.setSize(new Dimension(size * TILE_SIZE, size * TILE_SIZE));
    this.generateMap();
    this.printMap();
  }
   private void printMap() {
    // Used for debugging
    for (int y = 0; y < tileMap.length; y++) {
      for (int x = 0; x < tileMap[y].length; x++) {
        System.out.printf("%5.2f, ", tileMap[x][y]);
      }
      System.out.println();
    }
  }

  private void generateMap() {
    // Generate the values for the map
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        double heightValue = OpenSimplex2S.noise2(elevationSeed, x, y);
        double moistureValue = OpenSimplex2S.noise2(moistureSeed, x, y);
        tileMap[x][y] = heightValue;
        moistureMap[x][y] = moistureValue;
      }
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    // Paint to screen
    super.paintComponent(g);

    for (int y = 0; y < tileMap.length; y++) {
      for (int x = 0; x < tileMap[y].length; x++) {
        int i = x * TILE_SIZE;
        int j = y * TILE_SIZE;
        if (biomes) g.setColor(colors.getBiomeBlock(tileMap[y][x], moistureMap[x][y]));
        else g.setColor(colors.getBiomeBlock(tileMap[x][y]));
        g.fillRect(i, j, TILE_SIZE, TILE_SIZE);
      }
    }
  }
}
