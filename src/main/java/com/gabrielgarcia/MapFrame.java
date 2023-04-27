package com.gabrielgarcia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapFrame extends JFrame implements ActionListener {
    private final TileMap tileMap;
    private final JButton jpgButton = new JButton("Save to jpg");
    private final JButton pngButton = new JButton("Save to png");
    private final JPanel saveButtons = new JPanel(new FlowLayout());
    private final int height;
    private final int width;

    public MapFrame(TileMap tileMap) {
        jpgButton.addActionListener(this);
        pngButton.addActionListener(this);
        saveButtons.add(jpgButton);
        saveButtons.add(pngButton);
        this.tileMap = tileMap;
        this.height = tileMap.getHeight();
        this.width = tileMap.getWidth();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(tileMap.getSize());
        this.setLayout(new BorderLayout()); // Set the layout manager
        this.add(tileMap, BorderLayout.CENTER); // Add the TileMap to the center
        this.add(saveButtons, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void capturePanel(TileMap tileMap, String type) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        tileMap.paint(image.getGraphics());
        ImageIO.write(image, type, new File(System.getProperty("user.home") + "/map." + type));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jpgButton) {
            try {
                capturePanel(tileMap, "jpg");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == pngButton) {
            try {
                capturePanel(tileMap, "png");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
