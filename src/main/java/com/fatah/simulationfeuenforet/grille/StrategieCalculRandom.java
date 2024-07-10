package com.fatah.simulationfeuenforet.grille;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategieCalculRandom implements StrategieCalcul {

    private Random random = new Random();



    @Override
    public void calculer(List<int[]> pointsEnFeu, int[][] grille, List<int[]> listeIndicesFeu, double probabilite,
                         boolean caseEnFeu,List<Integer> listeIndicesFeuAll,GrilleXY grilleXY,int indice,
                         double humiditeSelectioner, double tomperatureSelectioner, double vitesseVentSelectioner)
    {
       listeIndicesFeu=new ArrayList<>();
        listeIndicesFeuAll.add(pointsEnFeu.size());

        for (int[] indices : pointsEnFeu) {
            // pour chaque indice x y Rendre la case en question à l'état cendre
            grille[indices[0]][indices[1]]=9671822;
            int x=indices[0];
            int y=indices[1];

            for (int i = 0; i < 4; i++) {
                int positionCasesAdjacenteX=x;
                int positionCasesAdjacenteY=y;

                if(i==0){
                    positionCasesAdjacenteX=x-1;  // La case adjacente au-dessus de la case en question

                }else if(i==1)
                {
                    positionCasesAdjacenteX=x+1; // La case adjacente en dessous de la case en question

                }else if(i==2)
                {
                    positionCasesAdjacenteY=y-1; // La case adjacente à gauche de la case en question

                } else if (i==3)
                {
                    positionCasesAdjacenteY=y+1; // La case adjacente à droite de la case en question

                }
                // Vérifier si cette case adjacente a une position valide dans la grille
                // Vérifier si cette case adjacente est un arbre et n'est ni cendre ni feu
                // Vérifier aussi si la probabilité générée aléatoirement pour cette case appartient à l'intervalle
                // de probabilité donné
                if (    TestePosition.positionValide(grille,positionCasesAdjacenteX, positionCasesAdjacenteY) &&
                        grille[positionCasesAdjacenteX][positionCasesAdjacenteY] == 0 &&
                        random.nextDouble() < probabilite)

                {

                    // Si les trois conditions sont correctes, alors la case adjacente prend l'état en feu
                    grille[positionCasesAdjacenteX][positionCasesAdjacenteY] =16711680 ;
                    listeIndicesFeu.add(new int[]{positionCasesAdjacenteX, positionCasesAdjacenteY});
                    caseEnFeu=true;

                }
            }

        }

        grilleXY.setListeIndicesFeu(listeIndicesFeu);

        caseEnFeu=!listeIndicesFeu.isEmpty();
        grilleXY.setCaseEnFeu(caseEnFeu);


    }
}
