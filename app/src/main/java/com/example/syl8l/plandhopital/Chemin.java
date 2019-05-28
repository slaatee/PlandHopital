package com.example.syl8l.plandhopital;

/** class chemin avec une id , un valeur depart arrivé, et la distance de ce dhemin, ainsi que le texte correspondant */

public class Chemin {


    private final int id;
    private final String depart;
    private final String arrive;
    private final double distance;
    private String  txt;

    public Chemin (int id,String depart,String arrive,double distance, String txt) {

        this.id =  id;
        this.depart = depart;
        this.arrive = arrive;
        this.distance = distance;
        this.txt = txt;



    }
    public String getDepart() {
        return depart;
    }
    public String getArrive() { return arrive;}
    public String getTxt() {
        return txt;
    }
    public int getId() { return id;  }
    public int getDistance() {
        return (int)distance;
    }

    @Override
    public String toString() {
        return depart +  " " + arrive +" "+ distance;
    }
}
