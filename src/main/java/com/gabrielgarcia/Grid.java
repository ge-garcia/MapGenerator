package com.gabrielgarcia;

public class Grid {
    private int[][] grid;

    public Grid() {
       grid = new int[16][16];
    }
    public Grid(int userMapSizeChoice) {
        grid = new int[userMapSizeChoice][userMapSizeChoice];
    }

}
