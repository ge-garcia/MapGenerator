package com.gabrielgarcia;

import java.awt.Color;

public class Colors {
  // Base colors for different terrain types
  Color water;
  Color sand;
  Color grass;
  Color mountain;
  Color snow;

  public Colors() {
    // default colors, based on https://github.com/catppuccin/catppuccin
    water    = new Color(130, 170, 255); // #7AA2F7
    sand     = new Color(255, 199, 119); // #FFC777
    grass    = new Color(195, 232, 141); // #C3E88D
    mountain = new Color(130, 139, 184); // #A9B1D6
    snow     = new Color(198, 208, 245); // #C6D0F5
  }

  public Colors(String[] custom) {
    // custom colors, based on user choice from a txt file
    water    = Color.decode(custom[0]);
    sand     = Color.decode(custom[1]);
    mountain = Color.decode(custom[2]);
    grass    = Color.decode(custom[3]);
    snow     = Color.decode(custom[4]);
  }
  public void printColors() {
    // Debugging console statements
    System.out.println(water.toString());
    System.out.println(sand.toString());
    System.out.println(grass.toString());
    System.out.println(mountain.toString());
    System.out.println(snow.toString());
  }

  public Color getBiomeBlock(double noiseValue) {
    // Sets a color based on height
    if (noiseValue < -0.7) {
      return water.darker();
    } else if (noiseValue < -0.3) {
      return water;
    } else if (noiseValue < 0) {
      return water.brighter();
    } else if (noiseValue < 0.2) {
      return sand;
    } else if (noiseValue < 0.5) {
      return grass;
    } else if (noiseValue < 0.7){
      return mountain;
    } else {
      return snow;
    }
  }
}
