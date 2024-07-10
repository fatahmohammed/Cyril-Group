package com.fatah.simulationfeuenforet.pointinitiale;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class PointParCercle {
    static {

        System.load("C:\\opencv_java4100.dll");
    }

    public static void faire(int x, int y, String pathsOriginale) {

        Mat image = Imgcodecs.imread(pathsOriginale);

        if (image.empty()) {
            System.out.println("Erreur: Impossible de charger l'image");
            return;
        }
        // grand cercle
        Point center = new Point(x, y);
        int rayon = 5;
        Scalar color = new Scalar(0, 0, 255);
        int epaisseur =1;
        Imgproc.circle(image, center, rayon, color, epaisseur);

        //point centrale
        int RayonPoint = 2;
        Imgproc.circle(image, center, RayonPoint, color, 2);
        Imgcodecs.imwrite(pathsOriginale, image);



    }
}
