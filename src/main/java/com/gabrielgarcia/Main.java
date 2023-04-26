package com.gabrielgarcia;

import javax.swing.*;

public class Main {
  private static void setLookAndFeel() { // Set OS look and feel
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.out.println("Could not set look and feel!");
      System.exit(1);
    }
  }

  public static void main(String[] args) {
    setLookAndFeel();
    new WelcomeMenu();
  }
}
