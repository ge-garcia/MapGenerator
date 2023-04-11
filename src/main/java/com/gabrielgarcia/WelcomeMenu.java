package com.gabrielgarcia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeMenu extends JFrame implements ActionListener {
  public ImageIcon icon;

  public WelcomeMenu() {
    /* Panels and panel options */
    icon = new ImageIcon("compass.png");
    JPanel panel = new JPanel(); // Panel to serve as layout
    JPanel north = new JPanel(); // Panel to hold title label
    JPanel south = new JPanel(); // Panel to hold the two buttons
    JPanel center = new JPanel(); // Panel to hold the user options prompt
    // JPanel east = new JPanel();
    // JPanel west = new JPanel();

    panel.setLayout(new BorderLayout());
    panel.setSize(400, 400);
    south.setSize(400, 100);
    north.setSize(400, 100);
    center.setSize(200, 600);


    /* Title bar and alignment options  -> NORTH */
    JLabel titleBar = new JLabel();
    titleBar.setText("Map Generator");
    titleBar.setFont(new Font("JetBrains Mono", Font.PLAIN, 32));
    titleBar.setHorizontalTextPosition(JLabel.CENTER);
    titleBar.setVerticalTextPosition(JLabel.CENTER);
    titleBar.setHorizontalAlignment(JLabel.CENTER);
    titleBar.setVerticalAlignment(JLabel.TOP);

    /* JButtons to save and draw -> SOUTH */
    JButton saveToFile = new JButton(); // Save to file option button
    saveToFile.addActionListener(this); // Will pop up a file chooser on click or use of mnemonic
    // saveToFile.setSize(300,100);
    saveToFile.setFocusable(false);
    saveToFile.setText("Save to file");
    saveToFile.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
    saveToFile.setMnemonic(KeyEvent.VK_S); // Alt + S to save

    JButton drawInGUI = new JButton(); // Draw in GUI option button
    drawInGUI.addActionListener(this); // Will draw the map with corresponding on click or use of mnemonic
    // drawInGUI.setSize(300, 100);
    drawInGUI.setFocusable(false);
    drawInGUI.setText("Draw in new window");
    drawInGUI.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
    drawInGUI.setMnemonic(KeyEvent.VK_D); // Alt + D to draw

    /* User option labels and buttons */
    JCheckBox biomePrompt= new JCheckBox();
    biomePrompt.setText("Check the box to include biomes");
    biomePrompt.addActionListener(this);


    String[] fileTypes = { "JPEG", "JPG", "PNG" };
    JComboBox fileTypeBox = new JComboBox(fileTypes);

    fileTypeBox.addActionListener(this);

    /* JFrame Options */
    this.setSize(400,400); // Initial size (and permanent since resizable is set to false)
    this.setLocationRelativeTo(null); // Make frame appear in the center of the screen, not necessary, but was giving me issues with my window manager
    this.setResizable(false); // Don't allow the user to resize, since this is just a welcome menu
    this.setTitle("Map Generator - Final Project"); // Set the title of the window
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Make sure it actually exits on close instead of running the background
    this.setIconImage(icon.getImage()); // Set the icon image chosen instead of Duke as top left

    /* Add the panels to the frame, as well as the corresponding components */
    north.add(titleBar);
    south.add(saveToFile);
    south.add(drawInGUI);
    center.add(fileTypeBox);
    center.add(biomePrompt);
    panel.add(north, BorderLayout.NORTH);
    panel.add(south, BorderLayout.SOUTH);
    panel.add(center, BorderLayout.CENTER);
    this.add(panel);

    this.setVisible(true); // Makes sure the screen is actually visible, left at the end to avoid problems
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    /* if (e.getSource() == saveToFile) {

    }
    if (e.getSource() == drawInGUI) {

    }
    if (e.getSource() == biomePrompt) {

    }
    if (e.getSource() == fileTypeBox) {
      String fileType = fileTypeBox.getSelectedItem();
    } */
  }
}
