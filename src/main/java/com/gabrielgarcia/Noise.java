package com.gabrielgarcia;
public class Noise {
    private int[] noise;
    public Noise() {
        noise = new int[256];
        for (int i = 0; i < noise.length; i++) { // Initialize the array with each value = index
            noise[i] = i;
        }
        shuffle(noise); // Shuffle the array of indices
    }
    public Noise(int userMapSizeChoice) {
        noise = new int[userMapSizeChoice * userMapSizeChoice];
        for (int i = 0; i < noise.length; i++) { // Initialize the array with each value = index
            noise[i] = i;
        }
        shuffle(noise); // Shuffle the array of indices
    }
    private static void shuffle(int[] arr) {
        // Shuffles the given array of indices
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int j = i + (int) (Math.random() * (len - i));
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private static double fade(double t) {
        // This is what makes Perlin special, the 'smoothing' of the values, such that the transition between heights feels more natural
        // Equivalent to t^3 * (6t^5 - 15t^4 + 10t^3), with t varying from 0 to 1
        return t * t * t * (t * (t * 6 - 15) + 10);
    }
    private static double lerp(double t, double a, double b) {
        // lerp -> Linear interpolation between a, b, and t
        return a + t * (b - a);
    }
    private static double grad(int hash, double x, double y) {
        int h = hash & 15;
        double u = h < 8 ? x : y;
        double v = h < 4 ? y : h == 12 || h == 14 ? x : 0;
        return ((h & 1) == 0 ? u : -u ) + ((h & 2) == 0 ? v : -v);
    }

    public double noise(double x, double y) {
        int xi = (int) Math.floor(x) & 255;
        int yi = (int) Math.floor(y) & 255;

        double xf = x - Math.floor(x);
        double yf = y - Math.floor(y);

        double u = fade(xf);
        double v = fade(yf);

        int a = noise[xi] + yi;
        int aa = noise[a];
        int ab = noise[a + 1];
        int b = noise[xi + 1] + yi;
        int ba = noise[b];
        int bb = noise[b + 1];

        double res = lerp(v, lerp(u, grad(aa, xf, yf), grad(ba, xf - 1, yf)), lerp(u, grad(ab, xf, yf -1), grad(bb, xf -1, yf -1)));
        return res;
    }
}
