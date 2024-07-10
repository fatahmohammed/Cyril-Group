package com.fatah.simulationfeuenforet.grille;

public class ConditionClimatique {
    public static double conditionsClilatiquePropagentFeu(int direction, double humidite, double temperature, double vitesseVent, int directionVent) {
        double impactHumidite = (100 - humidite) / 100;


        double impactTemperature = Math.min(temperature / 50.0, 1.0);


        double impactVent = Math.min(1.0 + vitesseVent / 100.0, 1.5);


        double impactDirection = (direction == directionVent) ? 1.5 : 1.0;
        if(directionVent==4&&direction==0)
        {
            impactDirection=1.5;
        }
        if(directionVent==4&&direction==2)
        {
            impactDirection=1.5;
        }

        if(directionVent==5&&direction==0)
        {
            impactDirection=1.5;
        }
        if(directionVent==5&&direction==3)
        {
            impactDirection=1.5;
        }

        if(directionVent==6&&direction==3)
        {
            impactDirection=1.5;
        }
        if(directionVent==6&&direction==1)
        {
            impactDirection=1.5;
        }

        if(directionVent==7&&direction==1)
        {
            impactDirection=1.5;
        }
        if(directionVent==7&&direction==2)
        {
            impactDirection=1.5;
        }

        double scorePropagation = impactHumidite * impactTemperature * impactVent * impactDirection;

        return Math.min(scorePropagation, 1.0);
    }
}