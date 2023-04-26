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
    water    = new Color(130, 170, 255);    // #7AA2F7
    sand     = new Color(255, 199, 119);     // #FFC777
    grass    = new Color(195, 232, 141);    // #C3E88D
    mountain = new Color(130, 139, 184); // #A9B1D6
    snow     = new Color (198, 208, 245);    // #C6D0F5
  }

  public Colors(String[] custom) {
    // custom colors, based on user choice from a txt file
    water    = Color.decode(custom[0]);
    sand     = Color.decode(custom[1]);
    mountain = Color.decode(custom[2]);
    grass    = Color.decode(custom[3]);
    snow     = Color.decode(custom[4]);
  }

  public Color getBiomeBlock(double value) {
    if (value < 0) {
      return water;
    } else if (value < 0.3) {
      return sand;
    } else if (value < 0.5) {
      return grass;
    } else if (value < 0.7) {
      return mountain;
    } else {
      return snow;
    }
  } 
}
