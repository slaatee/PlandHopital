package com.example.syl8l.plandhopital;

import java.util.List;

public class Util {

    public int i;

/** Changement des images en fonction du choix utilisateur **/
    public static int changeImage(String position) {
        int i = 0;
        if (position.equals("Accueil")) {
            i = R.drawable.acceuil;
        }
        if (position.equals("Entree")) {
            i = R.drawable.entree;
        }
        if (position.equals("Séjour")) {
            i = R.drawable.sejour;
        }
        if (position.equals("Urgences")) {
            i = R.drawable.urgences;
        }
        if (position.equals("Consultation")) {
            i = R.drawable.consultation;
        }
        if (position.equals("Imagerie")) {
            i = R.drawable.imagerie;
        }
        if (position.equals("Ophtalmologie")) {
            i = R.drawable.ophtalmologie;
        }
        if (position.equals("Pharmacie")) {
            i = R.drawable.pharmacie;
        }
        if (position.equals("Local")) {
            i = R.drawable.local;
        }
        if (position.equals("Oncologie")) {
            i = R.drawable.oncologie;
        }
        if (position.equals("LongSejour")) {
            i = R.drawable.longsejoour;
        }
        if (position.equals("Ambulatoire")) {
            i = R.drawable.ambulatoire;
        } else {

        }

        return i;

    }
    /** création d'une liste chemin **/
    public static List<Chemin> ListeChemin1 = DataService.getChemin();

    /** méthode pour recupérer le texte du parcours et en faire des étapes */

    protected static String getParcours(String parcours) {
        StringBuilder res = new StringBuilder();

        String[] cheminement = parcours.split("\\~\\>");
        String newLine = "\n";
        int z = 1;
        for (int i = 0; i < cheminement.length-1; i++) {
            for (int c = 0; c < ListeChemin1.size(); c++) {
                if ((cheminement[i] + cheminement[i + 1]).contains(ListeChemin1.get(c).getDepart()+ListeChemin1.get(c).getArrive())) {
                    String[] cut = ListeChemin1.get(c).getTxt().split(",") ;
                    res.append("ETAPE "+z+":"+newLine+newLine);
                    for(int g = 0; g <cut.length;g++) {
                        int ip = cut[g].indexOf("/");
                        if (ip == -1) {
                        } else {
                            res.append(cut[g].substring(ip+1).trim()+ newLine);
                        }
                    }
                    res.append(newLine);
                    z++;
                }
                else{

                }

            }

        }
        return res.toString();
    }

    /** methode pour récupérer le nombre de pas sur le parcours **/
    public static int getPas(String parcours) {
        int x = 0;
        int pas;
        String[] cheminement = parcours.split("\\~\\>");
        for (int i = 0; i < cheminement.length - 1; i++) {
            for (int c = 1; c < ListeChemin1.size(); c++) {
                if ((cheminement[i] + cheminement[i + 1]).contains(ListeChemin1.get(c).getDepart()+ListeChemin1.get(c).getArrive())) {
                    pas =ListeChemin1.get(c).getDistance();
                    x = x + pas;
                }
            }
        }
        return x;
    }

    public static String getCheminement(String parcours){
        StringBuilder res = new StringBuilder();
        String[] cheminement = parcours.split("\\~\\>");
        for (int i = 0; i < cheminement.length - 1; i++) {
            for (int c = 1; c < ListeChemin1.size(); c++) {
                if ((cheminement[i] + cheminement[i + 1]).contains(ListeChemin1.get(c).getDepart()+ListeChemin1.get(c).getArrive())) {
                    res.append(ListeChemin1.get(c).getTxt());
                }
            }
        }
        return res.toString();
    }

      /** methde pour ajuster le nombre de pas suivant la taille*/
    public static int ajusterPas(int Nombre, int taille) {
        double x;
        if (taille<170) {
            x = (Nombre * 1.1);
        }
        else if(taille>170 && taille <190) {
            x = (Nombre * 1);
        }
        else {
            x = (Nombre * 0.90);
        }
        return (int)x;}

        /** Convertir pas en mètres*/
        public static int pasEnMètres(float pas) {
            double pas1 = pas*0.7;
            return (int)pas1;
        }

 }




