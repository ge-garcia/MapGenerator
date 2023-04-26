package com.gabrielgarcia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

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
  JButton submitSeed = new JButton("Submit");

  JTextField enterSeed = new JTextField("Enter a seed here");
  JTextField enterSize = new JTextField("Enter size here. (will be a square)");
  JButton submitSize = new JButton("Enter the size here. will be a square");

  JCheckBox biomes = new JCheckBox("Check the box to include biomes");
  JComboBox fileTypeBox = new JComboBox(new String[] {"JPG", "PNG"});

  // Options to pass into TileMap
  Random random = new Random();
  long seed = random.nextLong();
  Colors colors = new Colors();
  int size = 16;

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
    biomes.setAlignmentX(Component.CENTER_ALIGNMENT);
    fileTypeBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    chooseColor.setAlignmentX(Component.CENTER_ALIGNMENT);
    biomes.addActionListener(this);
    fileTypeBox.addActionListener(this);
    chooseColor.addActionListener(this);
    submitSeed.addActionListener(this);
    submitSize.addActionListener(this);

    /* JFrame Options */
    this.setResizable(true); // Allow resizing
    this.setTitle("Map Generator - Final Project"); // Set the title of the window
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Make sure it actually exits on close
    this.setIconImage(icon.getImage()); // Set the icon image chosen instead of Duke as top left
    this.setLayout(new BorderLayout());

    /* Add components to panels, then panels to frame */
    seedComponent.setLayout(new FlowLayout());
    seedComponent.add(enterSeed);
    seedComponent.add(submitSeed);

    sizeComponent.setLayout(new FlowLayout());
    sizeComponent.add(enterSize);
    sizeComponent.add(submitSize);

    center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
    north.add(titleBar);
    south.add(draw);
    center.add(fileTypeBox);
    center.add(biomes);
    center.add(chooseColor);
    center.add(seedComponent);

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
      JOptionPane.showMessageDialog(null, "Please enter the path to your custom color palette. Should be a txt file with each color entered in hexadecimal format on its own line in the following order: water, sand, grass, forest, mountain, and snow. If you do not have a custom color palette, you can use the default one included in the project.", "Custom Color Palette", JOptionPane.INFORMATION_MESSAGE);
      JFileChooser fileChooser = new JFileChooser();
    }
    if (e.getSource() == submitSeed) {
      seed = Long.parseLong(enterSeed.getText());
    }
    if (e.getSource() == submitSize) {
      size = Integer.parseInt(enterSize.getText());
    }
    if (e.getSource() == draw) {
      this.dispose();
      MapFrame mapFrame = new MapFrame(seed, colors, size);
    }
  }
}
