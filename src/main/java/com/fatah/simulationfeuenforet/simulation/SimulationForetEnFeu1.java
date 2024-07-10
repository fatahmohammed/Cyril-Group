package com.fatah.simulationfeuenforet.simulation;



import com.fatah.simulationfeuenforet.grille.GrilleXY;
import com.fatah.simulationfeuenforet.grille.StrategieCalcul;
import com.fatah.simulationfeuenforet.grille.StrategieCalculConditionClimatique;
import com.fatah.simulationfeuenforet.grille.StrategieCalculRandom;

import java.util.ArrayList;
import java.util.List;

public class SimulationForetEnFeu1 {
    static GrilleXY grille;
    private static int etat;
    private StrategieCalcul strategie;
    int hauteur;
    int largeur;
    List<int[]> feuxInitials;
    static List<Integer> nombreCenderParEtat;
    public SimulationForetEnFeu1(int[][] initial, int[][] zone, double probabilite, List<int[]> feuxInitials) {

        this.largeur=initial.length;
        this.hauteur=initial[0].length;
        this.feuxInitials=feuxInitials;

        this.nombreCenderParEtat=new ArrayList<>();

        grille=new GrilleXY(initial,zone,probabilite);

        for (int[] feu : feuxInitials) {
            grille.etatInitial(feu[0],feu[1]);
        }
        etat = 0;
        grille.affich(etat);
        nombreCenderParEtat.add(grille.compterCendres());
    }
    public void lancerLeFeu(int indiceDirection, double humiditeSelectioner, double tomperatureSelectioner, double vitesseVentSelectioner, boolean aleatoireOuClimatique) {
        if(aleatoireOuClimatique) {

            strategie=new StrategieCalculConditionClimatique();
        }else {
            strategie=new StrategieCalculRandom();
        }
        grille.etape(feuxInitials,strategie,grille,indiceDirection,humiditeSelectioner,tomperatureSelectioner,vitesseVentSelectioner);
        etat++;
        nombreCenderParEtat.add(grille.compterCendres());
        grille.affich(etat);

        while (grille.getCaseEnFeu())
        {

            grille.etape(grille.getListeIndicesFeu(),strategie,grille,indiceDirection,humiditeSelectioner,tomperatureSelectioner,vitesseVentSelectioner);
            etat++;
            grille.affich(etat);
            nombreCenderParEtat.add(grille.compterCendres());
        }
    }



    public static int getNombreCendres() {
        return grille.compterCendres();
    }
    public static int getNombreFeux()
    {
        return grille.getListeIndicesFeu().size();
    }
    public static List<Integer> getNombreCendresParEtat() {
        return nombreCenderParEtat;
    }
    public static List<Integer> getListeNombreFeuAChaqueEtat()
    {return grille.getListeNombreFeuAChaqueEtat();
    }
    public static int getEtape() {
        return etat;
    }



    public static List<String> getImages(int etat) {
        List<String> listImage=new ArrayList<>();
        for(int i=0;i<=etat;i++)
            listImage.add("/assets/image/Etat " + i + ".png");
        return listImage;
    }
}