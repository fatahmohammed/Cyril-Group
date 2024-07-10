package com.fatah.simulationfeuenforet.zonecombistible;

import com.fatah.simulationfeuenforet.image.EtatParImage;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

public class FireDetection {
    static {
        // Changez ce chemin vers le chemin où vous avez extrait OpenCV
        System.load("C:/opencv_java4100.dll");
    }
    static int[][] ImageZone;
    static int[][] Originale;
    static int[][] ImageContour;
    //public static void main1234(String imagePath) {
    public static void main1234(String imagePath) {
        // Utiliser un chemin absolu pour l'image
        //imagePath = "C:/Users/fatah/Pictures/image1.jpg";
        Mat image = Imgcodecs.imread(imagePath);
        Originale = new int[image.rows()][image.cols()];


        // Remplir le tableau 'tab' avec les valeurs RGB de l'image initiale
        for (int row = 0; row < image.rows(); row++) {
            for (int col = 0; col < image.cols(); col++) {
                double[] pixel = image.get(row, col);
                int blue = (int) pixel[0];
                int green = (int) pixel[1];
                int red = (int) pixel[2];
                Originale[row][col] = (red << 16) | (green << 8) | blue;
            }
        }

        if (image.empty()) {
            System.out.println("Erreur: Impossible de charger l'image");
            return;
        }

        // Convertir l'image en espace de couleur HSV
        Mat hsvImage = new Mat();
        Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV);

        // Définir les plages de couleur pour la détection de forêt (vert foncé) et des champs d'agriculture (vert clair)
        Scalar lowerForestGreen = new Scalar(35, 50, 50);  // Borne inférieure pour la couleur vert foncé
        Scalar upperForestGreen = new Scalar(85, 255, 150); // Borne supérieure pour la couleur vert foncé
        Scalar lowerLightGreen = new Scalar(35, 100, 150); // Borne inférieure pour la couleur vert clair
        Scalar upperLightGreen = new Scalar(85, 255, 255); // Borne supérieure pour la couleur vert clair

        // Masque pour les couleurs vert foncé et vert clair
        Mat forestGreenMask = new Mat();
        Mat lightGreenMask = new Mat();
        Core.inRange(hsvImage, lowerForestGreen, upperForestGreen, forestGreenMask);
        Core.inRange(hsvImage, lowerLightGreen, upperLightGreen, lightGreenMask);

        // Combiner les deux masques
        Mat combinedMask = new Mat();
        Core.add(forestGreenMask, lightGreenMask, combinedMask);

        // Optionnel: Appliquer des transformations morphologiques pour améliorer le masque
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(5, 5));
        Imgproc.morphologyEx(combinedMask, combinedMask, Imgproc.MORPH_CLOSE, kernel);
        Imgproc.morphologyEx(combinedMask, combinedMask, Imgproc.MORPH_OPEN, kernel);

        // Trouver les contours des zones détectées
        java.util.List<MatOfPoint> contours = new java.util.ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(combinedMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);


        Scalar fillColor = new Scalar(0, 0, 0); // Rouge
        Imgproc.fillPoly(image, contours, fillColor);

        // Créer une copie de l'image originale pour afficher les zones détectées avec des couleurs uniques
        Mat detectedImage = image.clone();

        // Appliquer des couleurs uniques pour les zones détectées
        for (int row = 0; row < combinedMask.rows(); row++) {
            for (int col = 0; col < combinedMask.cols(); col++) {
                double[] pixelValue = combinedMask.get(row, col);
                if (pixelValue[0] > 0) { // Si le pixel fait partie des zones détectées
                    if (forestGreenMask.get(row, col)[0] > 0) {
                        // Zone de forêt détectée - utiliser une couleur unique (par exemple, rouge)
                        detectedImage.put(row, col, new double[]{0, 0, 0}); // Rouge
                    } else if (lightGreenMask.get(row, col)[0] > 0) {
                        // Zone de champs d'agriculture détectée - utiliser une autre couleur unique (par exemple, bleu)
                        detectedImage.put(row, col, new double[]{0, 0, 0}); // Bleu
                    }
                }
            }
        }

        // Créer des tableaux pour stocker les valeurs des pixels des images initiale et détectée


        ImageZone = new int[detectedImage.rows()][detectedImage.cols()];
        // Remplir le tableau 'tab2' avec les valeurs RGB de l'image détectée
        for (int row = 0; row < detectedImage.rows(); row++) {
            for (int col = 0; col < detectedImage.cols(); col++) {
                double[] pixel = detectedImage.get(row, col);
                int blue = (int) pixel[0];
                int green = (int) pixel[1];
                int red = (int) pixel[2];
                ImageZone[row][col] = (red << 16) | (green << 8) | blue;
            }
        }

// Dessiner les contours sur l'image originale
        Imgproc.drawContours(image, contours, -1, new Scalar(0, 0, 255), 2);
        ImageContour = new int[image.rows()][image.cols()];

        // Remplir le tableau 'tab' avec les valeurs RGB de l'image initiale
        for (int row = 0; row < image.rows(); row++) {
            for (int col = 0; col < image.cols(); col++) {
                double[] pixel = image.get(row, col);
                int blue = (int) pixel[0];
                int green = (int) pixel[1];
                int red = (int) pixel[2];
                ImageContour[row][col] = (red << 16) | (green << 8) | blue;
            }
        }
        new EtatParImage(Originale, ImageZone, ImageContour);

        // Afficher les tableaux
        //printArray(tab, "Initial Image Array");
        // printArray(tab2, "Detected Zones Image Array");

        // Afficher l'image originale avec les contours détectés
        HighGui.imshow("Detected Fire Zones", image);
        // Afficher l'image avec les zones détectées en couleurs uniques
        HighGui.imshow("Detected Zones with Unique Colors", detectedImage);
        //HighGui.waitKey();
    }



    public int[][] getOriginale()
    {

        return Originale;

    }
    public int[][]getImageZone()
    {
        return ImageZone;
    }
    public int[][] getImageZoneCountour()
    {
        return ImageContour;
    }
}