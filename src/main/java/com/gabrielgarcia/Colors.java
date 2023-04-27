package com.gabrielgarcia;

import java.awt.Color;

public class Colors {
  // Base colors for different terrain types
  Color water;
  Color sand;
  Color grass;
  Color forest;
  Color rainforest;
  Color mountain;
  Color snow;

  public Colors() {
    // default colors, based on https://github.com/catppuccin/catppuccin
    water      = new Color(130, 170, 255); // #7AA2F7
    sand       = new Color(255, 199, 119); // #FFC777
    grass      = new Color(195, 232, 141); // #C3E88D
    forest     = new Color(64, 160, 43);   // #40A02B
    rainforest = new Color(129, 200, 190); // #81C8BE
    mountain   = new Color(130, 139, 184); // #A9B1D6
    snow       = new Color(198, 208, 245); // #C6D0F5
  }

  public Colors(String[] custom) {
    // custom colors, based on user choice from a txt file
    water      = Color.decode(custom[0]);
    sand       = Color.decode(custom[1]);
    grass      = Color.decode(custom[2]);
    forest     = Color.decode(custom[3]);
    rainforest = Color.decode(custom[4]);
    mountain   = Color.decode(custom[5]);
    snow       = Color.decode(custom[6]);
  }

  public Color getBiomeBlock(double height) {
    // For when the user does not want biomes
    if (height < -0.4) return water.darker();
    if (height < 0) return water;
    if (height < 0.5) return grass;
    else return grass.darker();
  }
  public Color getBiomeBlock(double height, double moisture) {
    // Below "sea-level"
    if (height < -0.5) return water.darker();
    if (height < -0.1) return water;

    // Above "sea-level"
    if (height > 0.8) {
        if (moisture < 0.5) return mountain;
        else return snow;
    } else if (height > 0.5) {
        if (moisture < 0.5) return forest;
        else return snow;
    } else {
        if (moisture < 0.3) return sand;
        if (moisture < 0.6) return grass;
        else return rainforest;
    }
  }
}
