package com.fatah.simulationfeuenforet.frame;

import com.fatah.simulationfeuenforet.simulation.SimulationForetEnFeu1;
import com.fatah.simulationfeuenforet.pointinitiale.PointParCercle;
import com.fatah.simulationfeuenforet.zonecombistible.FireDetection;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class InterfaceParImageSat extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private ScrollPane scrollPane = null;
    private JButton ChargerImage = null;
    private JRadioButton ImageOriginale = null;
    private JRadioButton ZoneCombustible = null;
    private JRadioButton countour = null;
    private JRadioButton rondom = null;
    private JRadioButton climatique = null;
    private JLabel infoPoint = null;
    private JLabel jLabel4=null;
    private JLabel jLabel2=null;
    private JLabel jLabel3=null;
    private  JLabel jLabel5=null;
    private  JLabel directinVent=null;
    private  JLabel jLabel6=null;
    private  Label jLabel7=null;
    private JButton Star = null;
   // private JTextField Probabilite=null;
    private  JPanel jPanel=null;
    private JTable jTable = null;
    private JScrollPane jScrollPane = null;
    private JSlider jSlider = null;
    private JSlider vent = null;
    private JSlider humid = null;
    private JSlider tomperature = null;
    private int etat;
    private boolean instanceAfficherPointsMouveMouse=true;
    boolean conditionPositionActiv=false;
    private AfficherPointsMouveMouse afficherPointsMouveMouse;
    private final String pathsImageAZoneCombistible="target/fatah Zone Combistible Contoure.jpg";
    private final String pathsContourZoneCombistible="target/fatah contoure des Zones Combistible.jpg";
    private final String pathsOriginale="target/fatah Originale.jpg";
    private String path;
    private List<int[]> feuxInitials;
    private List<String[]> feuxInitialsInfoPoint;
    private int indiceDirection=0;
    List<String> directions = Arrays.asList("N", "NE", "E", "SE", "S", "SW", "W", "NW");  //  @jve:decl-index=0:
    FireDetection fireDetection;
    private int[][] originaleImage;
    private int[][] imageZone;
    public InterfaceParImageSat() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(1822, 800);
        this.setContentPane(getJContentPane());
        this.setTitle("JFrame");
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            infoPoint = new JLabel();
            infoPoint.setBounds(new Rectangle(655, 110, 300, 20));
            infoPoint.setText("JLabel");
            TransparentLabel label = new TransparentLabel();
            label.setBounds(new Rectangle(486, 256, 45, 23));
            label.setText("x : y");
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(215, 126, 70, 53));
            jLabel.add(label, null);
            jLabel.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent e) {

                    if(conditionPositionActiv) {
                        if (instanceAfficherPointsMouveMouse) {
                            afficherPointsMouveMouse = new AfficherPointsMouveMouse();
                            instanceAfficherPointsMouveMouse = false;
                        }
                        afficherPointsMouveMouse.affiche(pathsImageAZoneCombistible, infoPoint, label, e);
                    }

                    }
            });

            jLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    PointParCercle.faire(e.getX(),e.getY(),pathsOriginale);
                    MouseClick.updateImageLabel(pathsOriginale,jLabel,scrollPane, countour, ImageOriginale,
                            ZoneCombustible, infoPoint, ChargerImage, Star,jScrollPane,jPanel);
                    feuxInitials.add(new int[]{e.getY(),e.getX()});

                    DefaultTableModel tableModel = (DefaultTableModel) getJTable().getModel();
                    MouseClick.updateFeuInitial(feuxInitials,feuxInitialsInfoPoint,afficherPointsMouveMouse.
                            affiche(pathsImageAZoneCombistible,infoPoint,label,e),tableModel,e);
                }
            });
            jContentPane = new JPanel();
            jContentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Simulation Feu En Foret Pour CIRIL GROUPE : ", TitledBorder.LEFT, TitledBorder.TOP));
            jContentPane.setLayout(null);
            jContentPane.add(getScrollPane());
            jContentPane.add(getChargerImage(), null);
            jContentPane.add(getImageOriginale(), null);
            jContentPane.add(getZoneCombustible(), null);
            jContentPane.add(getCountour(), null);
            jContentPane.add(infoPoint, null);
            jContentPane.add(getJScrollPane(), null);
            jContentPane.add(getJPanel(),null);

        }
        return jContentPane;
    }

    private ScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new ScrollPane();

            scrollPane.setBounds(new Rectangle(10, 25, 645, 500));

            scrollPane.add(jLabel, jLabel.getName());
              }
        return scrollPane;
    }

    private JButton getChargerImage() {
        if (ChargerImage == null) {
            ChargerImage = new JButton();
            ChargerImage.setText("Charger une Image");
            ChargerImage.setBounds(new Rectangle(655, 10, 150, 20));
            ChargerImage.addActionListener(e -> {

                instanceAfficherPointsMouveMouse=true;
                conditionPositionActiv=true;
                path=MouseClick.chargerImage();
                fireDetection = new FireDetection();
                FireDetection.main1234(path);
                MouseClick.updateImageLabel(path, jLabel,scrollPane,countour,ImageOriginale,
                        ZoneCombustible,infoPoint,ChargerImage,Star,jScrollPane,jPanel);
                countour.setSelected(false);
                ImageOriginale.setSelected(true);
                ZoneCombustible.setSelected(false);
                countour.setEnabled(true);
                ImageOriginale.setEnabled(true);
                ZoneCombustible.setEnabled(true);
                feuxInitials =new ArrayList<>();
                feuxInitialsInfoPoint=new ArrayList<>();
                System.out.println(path);

            });
        }
        return ChargerImage;
    }

    private JRadioButton getImageOriginale() {
        if (ImageOriginale == null) {
            ImageOriginale = new JRadioButton();
            ImageOriginale.setText("Image Originale");
            ImageOriginale.setEnabled(false);
            ImageOriginale.setBounds(new Rectangle(655, 30, 150, 20));
            ImageOriginale.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    countour.setSelected(false);
                    ImageOriginale.setSelected(true);
                    ZoneCombustible.setSelected(false);
                    MouseClick.updateImageLabel(pathsOriginale,jLabel,scrollPane, countour, ImageOriginale,
                            ZoneCombustible, infoPoint, ChargerImage, Star,jScrollPane,jPanel);
                }
            });
        }
        return ImageOriginale;
    }

    private JRadioButton getZoneCombustible() {
        if (ZoneCombustible == null) {
            ZoneCombustible = new JRadioButton();
            ZoneCombustible.setText("Zone Combustible");
            ZoneCombustible.setEnabled(false);
            ZoneCombustible.setBounds(new Rectangle(655, 55, 150, 20));
            ZoneCombustible.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    countour.setSelected(false);
                    ImageOriginale.setSelected(false);
                    ZoneCombustible.setSelected(true);
                    MouseClick.updateImageLabel(pathsImageAZoneCombistible,jLabel,scrollPane, countour, ImageOriginale,
                            ZoneCombustible, infoPoint, ChargerImage, Star,jScrollPane,jPanel);
                }
            });
        }
        return ZoneCombustible;
    }

    private JRadioButton getCountour() {
        if (countour == null) {
            countour = new JRadioButton();
            countour.setText("Zone Avec Counoure");
            countour.setEnabled(false);
            countour.setBounds(new Rectangle(655, 80, 150, 20));
            countour.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {


                    countour.setSelected(true);
                    ImageOriginale.setSelected(false);
                    ZoneCombustible.setSelected(false);
                    MouseClick.updateImageLabel(pathsContourZoneCombistible,jLabel,scrollPane, countour, ImageOriginale,
                            ZoneCombustible, infoPoint, ChargerImage, Star,jScrollPane,jPanel);
                }
            });
        }
        return countour;
    }

    private JButton getStar() {
        if (Star == null) {
            Star = new JButton();
            Star.setEnabled(false);
            Star.setText("Star");
            Star.setBounds(new Rectangle(130, 320, 150, 20));
            Star.addActionListener(e -> {

                FireDetection.main1234(path);
                originaleImage=fireDetection.getOriginale();
                imageZone=fireDetection.getImageZone();
                double probabilite= (double)jSlider.getValue()/100;

                double humiditeSelectioner=0;
                double tomperatureSelectioner=0;
                double vitesseVentSelectioner = 0;
                int directionVont=0;
                boolean AleatoireOuClimatique=false;

                if(climatique.isSelected())
                {
                    AleatoireOuClimatique=true;
                if(indiceDirection==0)
                {
                    directionVont=0;
                } else if (indiceDirection==1) {
                    directionVont=5;

                } else if (indiceDirection==2) {
                    directionVont=3;
                } else if (indiceDirection==3) {
                    directionVont=6;
                } else if (indiceDirection==4) {
                    directionVont=1;
                }else if (indiceDirection==5) {
                    directionVont=7;
                } else if (indiceDirection==6) {
                    directionVont=2;
                } else if (indiceDirection==7) {
                    directionVont=4;
                }
                humiditeSelectioner= humid.getValue();
                tomperatureSelectioner= tomperature.getValue();
                vitesseVentSelectioner= vent.getValue();
                }
                //System.out.println(humiditeSelectioner+" : "+tomperatureSelectioner+" : "+vitesseVentSelectioner);


                new SimulationForetEnFeu1(originaleImage, imageZone, probabilite, feuxInitials).lancerLeFeu(directionVont,humiditeSelectioner,tomperatureSelectioner,vitesseVentSelectioner,AleatoireOuClimatique);
                etat=SimulationForetEnFeu1.getEtape();

                    new LireImages().execute();

                feuxInitials=new ArrayList<>();
                feuxInitialsInfoPoint=new ArrayList<>();

            });
        }
        return Star;
    }

    private JTable getJTable() {
        if (jTable == null) {
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(new String[]{"X", "Y", "Coulur", "Oui/Non"}); // Définir les noms de colonnes

            jTable = new JTable(tableModel);



        }
        return jTable;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Points Initiale de Feu : ", TitledBorder.LEFT, TitledBorder.TOP));

            jScrollPane.setBounds(new Rectangle(950, 10, 400, 300));
            jScrollPane.setViewportView(getJTable());


        }
        return jScrollPane;
    }


    private JSlider getJSlider() {
        if (jSlider == null) {
            jSlider = new JSlider();
            jSlider.setValue(60);
            MouseClick.updateSliderBorder(jSlider);
            jSlider.setBounds(new Rectangle(100, 20, 200, 35));
            jSlider.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    MouseClick.updateSliderBorder(jSlider);
                }


            });
            jSlider.addMouseWheelListener(e -> {
                int notches = e.getWheelRotation();
                int currentValue = jSlider.getValue();
                int newValue = currentValue - notches;
                if (newValue >= jSlider.getMinimum() && newValue <= jSlider.getMaximum()) {
                    jSlider.setValue(newValue);
                    MouseClick.updateSliderBorder(jSlider);
                }
            });

        }


        return jSlider;
    }
    private JSlider getVent() {
        if (vent == null) {
            vent = new JSlider();
            vent.setValue(20);
            MouseClick.updateSliderBorder(vent);
            jLabel5.setText(vent.getValue()+" Km/h");
            vent.setBounds(new Rectangle(100, 100, 200, 35));
            vent.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    MouseClick.updateSliderBorder(vent);
                    jLabel5.setText(vent.getValue()+" Km/h");
                }


            });
            vent.addMouseWheelListener(e -> {
                int notches = e.getWheelRotation();
                int currentValue = vent.getValue();
                int newValue = currentValue - notches;
                if (newValue >= vent.getMinimum() && newValue <= vent.getMaximum()) {
                    vent.setValue(newValue);
                    MouseClick.updateSliderBorder(vent);
                    jLabel5.setText(vent.getValue()+" Km/h");
                }
            });

        }


        return vent;
    }

    private JSlider getHumid() {
        if (humid == null) {
            humid = new JSlider();
            MouseClick.updateSliderBorder(humid);
            humid.setValue(40);
            jLabel6.setText(humid.getValue()+" %");
            humid.setBounds(new Rectangle(100, 135, 200, 35));
            humid.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    MouseClick.updateSliderBorder(humid);
                    jLabel6.setText(humid.getValue()+" %");
                }


            });
            humid.addMouseWheelListener(e -> {
                int notches = e.getWheelRotation();
                int currentValue = humid.getValue();
                int newValue = currentValue - notches;
                if (newValue >= humid.getMinimum() && newValue <= humid.getMaximum()) {
                    humid.setValue(newValue);
                    MouseClick.updateSliderBorder(humid);
                    jLabel6.setText(humid.getValue()+" %");
                }
            });

        }


        return humid;
    }

    private JSlider getTomperature() {
        if (tomperature == null) {
            tomperature = new JSlider();
            tomperature.setMinimum(0);
            tomperature.setMaximum(55);
            tomperature.setValue(30);
            MouseClick.updateSliderBorder(tomperature);
            jLabel7.setText(tomperature.getValue()+"°");
            tomperature.setBounds(new Rectangle(100, 170, 200, 35));
            tomperature.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    MouseClick.updateSliderBorder(tomperature);
                    jLabel7.setText(tomperature.getValue()+"°");
                }



            });
            tomperature.addMouseWheelListener(e -> {
                int notches = e.getWheelRotation();
                int currentValue = tomperature.getValue();
                int newValue = currentValue - notches;
                if (newValue >= tomperature.getMinimum() && newValue <= tomperature.getMaximum()) {
                    tomperature.setValue(newValue);
                    MouseClick.updateSliderBorder(tomperature);

                    jLabel7.setText(tomperature.getValue()+" °");
                }
            });

        }


        return tomperature;
    }

    private JRadioButton getRondom() {
        if (rondom == null) {
            rondom = new JRadioButton();
            rondom.setText("Aléatoire");
            rondom.setBounds(new Rectangle(10, 60, 150, 20));
            rondom.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {


                    Star.setEnabled(true);
                    rondom.setSelected(true);
                    climatique.setSelected(false);
                    jLabel2.setVisible(false);
                    jLabel3.setVisible(false);
                    jLabel4.setVisible(false);
                    jLabel5.setVisible(false);
                    jLabel6.setVisible(false);
                    jLabel7.setVisible(false);
                    directinVent.setVisible(false);
                    tomperature.setVisible(false);
                    humid.setVisible(false);
                    vent.setVisible(false);

                }
            });
        }
        return rondom;
    }
    private JRadioButton getClimatique() {
        if (climatique == null) {
            climatique = new JRadioButton();
            climatique.setText("Condition Climatique ");
            climatique.setBounds(new Rectangle(10, 80, 150, 20));
            climatique.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    Star.setEnabled(true);

                    climatique.setSelected(true);
                    rondom.setSelected(false);
                    jLabel2.setVisible(true);
                    jLabel3.setVisible(true);
                    jLabel4.setVisible(true);
                    jLabel5.setVisible(true);
                    jLabel6.setVisible(true);
                    jLabel7.setVisible(true);
                    directinVent.setVisible(true);
                    tomperature.setVisible(true);
                    humid.setVisible(true);
                    vent.setVisible(true);

                }
            });
        }
        return climatique;
    }


    private JPanel getJPanel() {

        if (jPanel == null) {
            directinVent = new JLabel();
            MouseClick.directionVent(directinVent,indiceDirection,directions);
            directinVent.setBounds(new Rectangle(100, 210, 90, 90));
            directinVent.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    indiceDirection=(indiceDirection+1)%8;
                    MouseClick.directionVent(directinVent,indiceDirection,directions);
                }
            });

            JLabel jLabel1 = new JLabel();
            jLabel1.setBounds(new Rectangle(10, 30, 80, 20));
            jLabel1.setText("probabilité : ");

            jLabel2 = new JLabel();
            jLabel2.setBounds(new Rectangle(10, 110, 80, 20));
            jLabel2.setText("Vent : ");

           jLabel3 = new JLabel();
            jLabel3.setBounds(new Rectangle(10, 145, 80, 20));
            jLabel3.setText("Humidité : ");

            jLabel4 = new JLabel();
            jLabel4.setBounds(new Rectangle(10, 180, 80, 20));
            jLabel4.setText("Tompérature : ");

            jLabel5 = new JLabel();
             jLabel5.setBounds(new Rectangle(305, 110, 80, 20));


            jLabel6 = new JLabel();
            jLabel6.setBounds(new Rectangle(305, 145, 80, 20));


            jLabel7 = new Label();
            jLabel7.setBounds(new Rectangle(305, 180, 80, 20));


            jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Information de la simulation : ", TitledBorder.LEFT, TitledBorder.TOP));
            jPanel.setBounds(new Rectangle(950,320,400,450));
            jPanel.add(jLabel1, null);
            jPanel.add(jLabel2, null);
            jPanel.add(jLabel3, null);
            jPanel.add(jLabel4, null);

            jPanel.add(jLabel5, null);
            jPanel.add(jLabel6, null);
            jPanel.add(jLabel7, null);

            jPanel.add(getJSlider(), null);
            jPanel.add(getVent(), null);
            jPanel.add(getHumid(), null);
            jPanel.add(getTomperature(), null);
            jPanel.add(getClimatique(), null);
            jPanel.add(getRondom(), null);
            jPanel.add(directinVent,null);
            jPanel.add(getStar(), null);
        }
        return jPanel;
    }
    public void setjLabel(JLabel jLabel)
    {
        this.jLabel=jLabel;
    }
    public class LireImages extends SwingWorker<Void, Void> {


        protected Void doInBackground() throws Exception {
            BufferedImage image=null;
            for (int i=0;i<=etat;i++)
            {
                image=ImageBuffeur.loadImage("target/fatah"+i+".jpg");
                ImageIcon image2=new ImageIcon(image);
                jLabel.setIcon(image2);

                Thread.sleep(100);
            }
            return null;
        }
    }
}
