package com.example.syl8l.plandhopital;

import java.io.Serializable;

/** class noeud serializable pour pouvoir envoyez les informations entre intent **/

public class Noeud implements Comparable<Noeud>,Serializable {

    public String value;
    public double shortestDistance = Double.POSITIVE_INFINITY;


    public Noeud(String val){
        value = val;
    }

    public String getValue() {
        return value;
    }
    public String toString(){
        return value;
    }

    public int compareTo(Noeud other){
        return Double.compare(shortestDistance, other.shortestDistance);
    }

}