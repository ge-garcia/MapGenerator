package com.gabrielgarcia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
public class WelcomeMenu extends JFrame implements ActionListener {
  // TitleBar icon
  ImageIcon icon = new ImageIcon("compass.png");
  // Layout panels
  JPanel north = new JPanel();
  JPanel south = new JPanel();
  JPanel center = new JPanel();
  JPanel seedComponent = new JPanel();
  JPanel sizeComponent = new JPanel();

  // This isn't necessary, but since my window manager lacks window titles I put this on for my presentation
  JLabel titleBar = new JLabel("Map Generator");

  // Jbuttons to draw and give a file path to a custom color palette
  JButton draw = new JButton("Draw");
  JButton chooseColor = new JButton("Set a custom color palette:");

  JTextField enterSeed = new JTextField("Enter a seed here");
  JButton submitSeed = new JButton("Submit");
  JTextField enterSize = new JTextField("Enter size here. (# of Tiles)");
  JButton submitSize = new JButton("Submit");
  JCheckBox biomeBox = new JCheckBox("Include Biomes");

  // Options to pass into TileMap
  Random random = new Random();
  long seed = random.nextLong();
  Colors colors = new Colors();
  int size = 16;
  boolean biomes = false;

  public WelcomeMenu() {
    /* Title bar and alignment options  -> NORTH */
    titleBar.setFont(new Font("JetBrains Mono", Font.PLAIN, 32));
    titleBar.setHorizontalTextPosition(JLabel.CENTER);
    titleBar.setVerticalTextPosition(JLabel.CENTER);
    titleBar.setHorizontalAlignment(JLabel.CENTER);
    titleBar.setVerticalAlignment(JLabel.TOP);

    /* JButtons to draw -> SOUTH */
    draw.addActionListener(this); // Will draw the map on click or use of mnemonic
    draw.setFocusable(false);
    draw.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
    draw.setMnemonic(KeyEvent.VK_D); // Alt + D to draw

    /* User options  -> CENTER */
    chooseColor.setAlignmentX(Component.CENTER_ALIGNMENT);
    chooseColor.addActionListener(this);
    submitSeed.addActionListener(this);
    submitSize.addActionListener(this);
    biomeBox.setAlignmentX(Component.CENTER_ALIGNMENT);

    /* JFrame Options */
    this.setResizable(true); // Allow resizing
    this.setTitle("Map Generator - Final Project"); // Set the title of the window
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Make sure it actually exits on close
    this.setIconImage(icon.getImage()); // Set the icon image chosen instead of Duke as top left
    this.setLayout(new BorderLayout());

    /* Add components to panels, then panels to frame */
    seedComponent.setLayout(new FlowLayout());
    seedComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
    seedComponent.add(enterSeed);
    seedComponent.add(submitSeed);

    sizeComponent.setLayout(new FlowLayout());
    sizeComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
    sizeComponent.add(enterSize);
    sizeComponent.add(submitSize);


    center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
    north.add(titleBar);
    south.add(draw);
    center.add(chooseColor);
    center.add(seedComponent);
    center.add(sizeComponent);
    center.add(biomeBox);

    this.add(north, BorderLayout.NORTH);
    this.add(south, BorderLayout.SOUTH);
    this.add(center, BorderLayout.CENTER);
    this.pack();
    this.setLocationRelativeTo(null); // Make frame appear in the center of the screen
    this.setVisible(true); // Makes sure the screen is actually visible, left at the end to avoid problems
  }

  @Override
   public void actionPerformed(ActionEvent e) {
    if (e.getSource() == chooseColor) {
      JOptionPane.showMessageDialog(null, """
              Please enter the path to your custom color palette.
              Should be a txt file with each color entered in hexadecimal format on its own line in the following order:
              Water, Sand, Grass, Forest, Rainforest, Mountain, Snow.
              If you do not have a custom color palette, you can use the default one included in the project.""", "Custom Color Palette", JOptionPane.INFORMATION_MESSAGE);
      JFileChooser fileChooser = new JFileChooser();
      int res = fileChooser.showOpenDialog(null); // Allows the user to select the file to open
      // If the user doesn't hit cancel or exit the window
      if (res == JFileChooser.APPROVE_OPTION) {
        // Get the absolute path of the file
        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
        if (!checkFileExtension(file)) {
          // If not a txt file, throw an error dialog message and prompt user to attempt again
          JOptionPane.showMessageDialog(null, "Error: not a .txt file, please try again", "Incorrect File Type", JOptionPane.ERROR_MESSAGE);
        } else {
          // If it is a txt file, give the user an info message stating success, and then try to load the color palette
          JOptionPane.showMessageDialog(null, ".txt file successfully found", "Color Palette Loaded", JOptionPane.INFORMATION_MESSAGE);
          try {
            // Change the colors object to = the users custom palette preference
            colors = new Colors(getCustomPalette(file));
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
      }
    }
    if (e.getSource() == submitSeed) {
      seed = Long.parseLong(enterSeed.getText());
    }
    if (e.getSource() == submitSize) {
      size = Integer.parseInt(enterSize.getText());
    }
    if (e.getSource() == draw) {
      biomes = biomeBox.isSelected();
      this.dispose();
      TileMap tileMap = new TileMap(seed, colors, size, biomes);
      MapFrame map = new MapFrame(tileMap);
    }
  }
  private String[] getCustomPalette(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    ArrayList<String> colors = new ArrayList<>();
    // Read each line in the file, trim whitespace, and add that to a String[] that will be used in the Colors class
    while ((line = reader.readLine()) != null) {
      line = line.trim();
      colors.add(line);
      System.out.println(line);
    }
    return colors.toArray(new String[colors.size()]);
  }
  private boolean checkFileExtension(File file) {
    String name = file.getName();
    // Get the file extension by finding the last occurrence of "."
    int lastIndex = name.lastIndexOf(".");
    // Check that the file has an extension, and that it equals ".txt"
    return lastIndex != -1 && name.substring(lastIndex).equals(".txt");
  }
}
