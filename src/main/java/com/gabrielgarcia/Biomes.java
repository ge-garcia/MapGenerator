package com.gabrielgarcia;

import java.awt.Color;

public class Biomes {
    public static Color getBiomeBlock(int noiseValue, Colors colors) {
      if (noiseValue < 0) {
        return colors.water;
      } else if (noiseValue < 0.2) {
        return colors.sand;
      } else if (noiseValue < 0.5) {
        return colors.grass;
      } else {
        return colors.mountain;
      }
    }
}
