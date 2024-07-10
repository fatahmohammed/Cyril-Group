package com.fatah.simulationfeuenforet.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class EtatParImage {
 public EtatParImage(int[][] Originale, int[][] grilleOrg,int[][] tab3) {

        image(Originale,"Originale");
        image(grilleOrg,"Zone Combistible Contoure");
        image(tab3,"contoure des Zones Combistible");

    }
 private void image(int[][] tab,String nom) {
        int hauteur = tab.length;
        int largeur = tab[0].length;
        // Créer une image BufferedImage
        BufferedImage image = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);

        for(int i = 0;i<hauteur;i++)
        {
            for (int j = 0; j < largeur; j++)
            {


                    image.setRGB(j, i, tab[i][j]);


            }
        }

        File outputFile = new File("target/fatah "+nom+".jpg");
        try
        {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }





}