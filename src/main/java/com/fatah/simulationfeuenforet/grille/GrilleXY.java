package com.fatah.simulationfeuenforet.grille;


import com.fatah.simulationfeuenforet.image.EtatParImage2;

import java.util.ArrayList;
import java.util.List;

public class GrilleXY {
    private boolean caseEnFeu=false;
    private int[][] grille;
    private int[][] grilleOrg;
    private int hauteur;
    private int largeur;
    private double probabilite;

    private List<int[]> listeIndicesFeu;
    private List<Integer> listeIndicesFeuAll;


    public GrilleXY(int[][] initial, int[][] zone, double probabilite) {

        this.hauteur = initial.length;
        this.largeur = initial[0].length;
        this.probabilite = probabilite;
        this.grille = zone;
        this.grilleOrg=initial;
        this.listeIndicesFeuAll=new ArrayList<Integer>();

    }


    // Méthode pour initialiser les cases en feu (état initial) dans la grille
    public void etatInitial(int xInitial,int yInitial) {
        if (TestePosition.positionValide(grille,xInitial, yInitial)) {
            grille[xInitial][yInitial] = 0;

        }
    }


    // Méthode pour enregistrer l'état de la grille sous forme d'une image et un fichier au format JSON
    public void affich(int e) {

        new EtatParImage2(grille,e,grilleOrg);

    }

    /*   C'est le cœur de l'algorithme demandé. */

    // La méthode pour chaque état qui suit l'état initial de l'état t à l'état t+1, puis de t+1 à t+2, et ainsi de suite
    // Qui prend en paramètre les indices x et y de les cases en question et qui ont l'état en feu

    public void etape(List<int[]> pointsEnFeu, StrategieCalcul strategie,GrilleXY grille1,int indice,
                      double humiditeSelectioner, double tomperatureSelectioner, double vitesseVentSelectioner) {
        listeIndicesFeu =new ArrayList<>();
        strategie.calculer(pointsEnFeu,this.grille,listeIndicesFeu,probabilite,caseEnFeu,listeIndicesFeuAll,
                grille1,indice,humiditeSelectioner,tomperatureSelectioner,vitesseVentSelectioner );

    }

    // Méthode pour calculer le nombre de cases cendre dans la grille
    public int compterCendres() {
        int compteur = 0;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (grille[i][j] == 9671822) {
                    compteur++;
                }
            }
        }
        return compteur;
    }


    public List<int[]> getListeIndicesFeu()
    {return listeIndicesFeu;
    }
    public List<Integer> getListeNombreFeuAChaqueEtat()
    {return listeIndicesFeuAll;
    }
    public Boolean getCaseEnFeu()
    {

        return caseEnFeu;
    }

    public void setListeIndicesFeu(List<int[]> listeIndicesFeu)
    {
        this.listeIndicesFeu=listeIndicesFeu;
    }
    public void setCaseEnFeu(boolean caseEnFeu)
    {
        this.caseEnFeu=caseEnFeu;
    }
}
