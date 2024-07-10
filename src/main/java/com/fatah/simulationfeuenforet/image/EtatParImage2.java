package com.fatah.simulationfeuenforet.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EtatParImage2 {

          public EtatParImage2(int[][] Originale, int etat ,int[][] grilleOrg) {
            image2(Originale,etat,grilleOrg);

        }
        private void image2(int[][] tab,int etat1,int[][] original) {
            int hauteur = tab.length;
            int largeur = tab[0].length;
            // Créer une image BufferedImage
            BufferedImage image = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);

            for(int i = 0;i<hauteur;i++)
            {
                for (int j = 0; j < largeur; j++)
                {

                    if(tab[i][j]==0)
                    {
                        image.setRGB(j, i, original[i][j]);
                    }else
                    {
                        image.setRGB(j, i, tab[i][j]);
                    }

                }
            }

            File outputFile = new File("target/fatah"+etat1+".jpg");
            try
            {
                ImageIO.write(image, "png", outputFile);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

}
