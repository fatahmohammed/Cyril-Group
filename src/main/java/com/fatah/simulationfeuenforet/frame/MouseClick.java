package com.fatah.simulationfeuenforet.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MouseClick {
    static String imagePaths;
    public static String chargerImage() {
        JFileChooser fileChooser = new JFileChooser();

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            imagePaths = selectedFile.getPath();

        } else {
            System.out.println("No file selected");
        }
        return imagePaths;
    }

    public static void updateImageLabel(String paths, JLabel jLabel, ScrollPane  scrollPane, JRadioButton countour, JRadioButton imageOriginale, JRadioButton zoneCombustible, JLabel infoPoint, JButton chargerImage, JButton star,JScrollPane jScrollPane,JPanel jPanel) {
        // Effacer les anciennes images
        jLabel.setIcon(null);
        jLabel.setText(null);
        int imageWidth = 0;
        int imageHeight=0;
        try {
            // Charger l'image en utilisant BufferedImage
            BufferedImage bufferedImage = ImageIO.read(new File(paths));
            if (bufferedImage != null) {
                // Convertir BufferedImage en ImageIcon
                ImageIcon icon = new ImageIcon(bufferedImage);

                // Mettre à jour avec la nouvelle image
               imageWidth = bufferedImage.getWidth();
               imageHeight = bufferedImage.getHeight();
                jLabel.setBounds(new Rectangle(0, 0, imageWidth, imageHeight));
                if(imageWidth<800 &&imageHeight<600) {
                    scrollPane.setBounds(new Rectangle(10, 25, imageWidth, imageHeight));
                    countour.setBounds(new Rectangle(imageWidth+10, 80, 150, 20));
                    imageOriginale.setBounds(new Rectangle(imageWidth+10, 30, 150, 20));
                    zoneCombustible.setBounds(new Rectangle(imageWidth+10, 55, 150, 20));
                    chargerImage.setBounds(new Rectangle(imageWidth+10, 10, 200, 20));

                    infoPoint.setBounds(new Rectangle(imageWidth+10, 130, 300, 20));
                    jScrollPane.setBounds(new Rectangle(imageWidth+320, 10, 400, 300));
                    jPanel.setBounds(new Rectangle(imageWidth+320, 320, 400, 450));
                }else
                {
                    scrollPane.setBounds(new Rectangle(10, 25, 800, 600));
                    countour.setBounds(new Rectangle(810, 80, 150, 20));
                    imageOriginale.setBounds(new Rectangle(810, 30, 150, 20));
                    zoneCombustible.setBounds(new Rectangle(810, 55, 150, 20));
                    chargerImage.setBounds(new Rectangle(810, 10, 200, 20));

                    infoPoint.setBounds(new Rectangle(810, 130, 300, 20));
                    jScrollPane.setBounds(new Rectangle(800+320, 10, 400, 300));
                    jPanel.setBounds(new Rectangle(1120, 320, 400, 450));
                    if(imageWidth<800 ) {
                        scrollPane.setBounds(new Rectangle(10, 25, imageWidth, 600));
                    }else if(imageHeight<600)
                    {
                        scrollPane.setBounds(new Rectangle(10, 25, 800, imageHeight));
                    }
                }



                jLabel.setIcon(icon);
                jLabel.setVisible(true);

            } else System.out.println("Erreur de chargement de l'image : " + paths);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void updateFeuInitial(List<int[]> feuxInitials, List<String[]> feuxInitialsInfoPoint, int coulur, DefaultTableModel tableModel, MouseEvent e) {
        if(coulur==0)
        {
            feuxInitialsInfoPoint.add(new String[]{""+e.getY(),""+e.getX(),""+coulur,"OUI"});
        }
        else
        {
            feuxInitialsInfoPoint.add(new String[]{""+e.getY(),""+e.getX(),""+coulur,"NON"});
        }
        tableModel.setRowCount(0);
        for (String[] feu : feuxInitialsInfoPoint)
        {
            tableModel.addRow(new Object[]{feu[0], feu[1],feu[2], feu[3]});
        }
    }

    public static void updateSliderBorder(JSlider jSlider) {
        double value = jSlider.getValue() / 100.0; // Convertir la valeur en décimales
        String formattedValue = String.format("%.2f", value); // Formater en 2 décimales
        jSlider.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), formattedValue, TitledBorder.LEFT, TitledBorder.TOP));
    }

    public static void directionVent(JLabel label2,int i,List<String> directions) {


            BufferedImage bufferedImage= ImageBuffeur.loadImage("D:\\fatah ciril\\SemulationFeuEnForet\\"+directions.get(i)+".png");

            if (bufferedImage != null) {
                ImageIcon icon = new ImageIcon(bufferedImage);
                int imageWidth = bufferedImage.getWidth();
                int imageHeight = bufferedImage.getHeight();
                label2.setIcon(icon);
                label2.setVisible(true);

            }


    }
}
