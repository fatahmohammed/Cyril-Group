package com.fatah.simulationfeuenforet.grille;

public class TestePosition {
    public static boolean positionValide(int[][] grille, int x, int y) {
        return x >= 0 && x < grille.length && y >= 0 && y < grille[0].length;
    }
}
