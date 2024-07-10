package com.fatah.simulationfeuenforet.grille;

import java.util.List;

public interface StrategieCalcul {
    void calculer(List<int[]> pointsEnFeu, int[][] grille, List<int[]> listeIndicesFeu, double probabilite,
                  boolean caseEnFeu,List<Integer> listeIndicesFeuAll, GrilleXY grille1,int indice,
                  double humiditeSelectioner, double tomperatureSelectioner, double vitesseVentSelectioner);

}
