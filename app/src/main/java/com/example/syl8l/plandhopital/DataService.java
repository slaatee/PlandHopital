package com.example.syl8l.plandhopital;
import java.util.ArrayList;

import java.util.List;

public class DataService {


    private DBManager db;
    private static List<Chemin> ListeChemin = new ArrayList<Chemin>();
    private static List<Noeud> ListeNoeud = new ArrayList<Noeud>();

    // ajout des chemins.
    private static void addChemin(int CheminId, String depart, String arrive,
                                  double distance, String txt) {
        Chemin lane = new Chemin(CheminId, depart, arrive, distance, txt);

        ListeChemin.add(lane);
    }

    // Liste des chemins.
    public static List<Chemin>  getChemin() {
// on ecrit le texte du chemin comme ca un entier qui vaut les pas puis un "." une valeur upz doorz rightz ou leftz suivi de"/" on écrit e parcours et on fini par une ","

        addChemin(1, "Entree", "Accueil", 26, "8.upz/Continuez tout droit,8.doorz/Passez le portique devant vous,10.doorz/Rentrez dans l'accueil,");
        addChemin(17, "Accueil", "Entree", 26, "8.upz/Tournez vous vers la porte,8.upz/Continuez tout droit,10.doorz/Rentrez dans l'entrée,");
        addChemin(2, "Accueil", "Consultation", 24, "10.leftz/A votre droite dirigez vous vers la porte coulissante,2.doorz/prenez la porte coulissante,2.upz/Rentrez dans le couloir,5.rightz/Puis allez sur la gauche,5.doorz/prenez la porte pour arriver sur la consultation,");
        addChemin(3, "Accueil", "Ophtalmologie", 39, "10.leftz/A votre droite dirigez vous vers la porte coulissante,2.doorz/prenez la porte coulissante,2.upz/Rentrez dans le couloir,5.leftz/Tournez à droitee,20.upz/ et continuer tout droit,4.doorz/ prenez la porte sur votre gauche et rentrez dans l'ophtamologie,");
        addChemin(4, "Accueil", "Urgences", 28, "8.upz/Avancez dans l'acceuil,12.upz/ suivez les flèches rouge au sol,8.doorz/ rentrez dans les urgences et continuez quelques pas,");
        addChemin(6, "Accueil", "Séjour", 18, "5.upz/Dirigez vous vers votre droite et avancer,5.rightz/tournez sur votre droite,8.upz/continuer vers le Séjour,");
        addChemin(7, "Accueil", "EscalierBas", 10, "10.rightz/Prenez l'escalier sur votre droite,");

        addChemin(18, "Urgences", "Accueil", 26, "10.upz/Traversez les urgences,8.doorz/ prenez la porte coulissante,8.upz/ continuer en suivant les flèches bleu au sol vous menant vers le centre de la pièce,");
        addChemin(13, "Urgences", "Pharmacie", 21, "8.upz/Marchez en suivant les lignes vertes au mur,5.doorz/ prendre la porte coulissante,8.upz/rentrez tout droit vers la pharmacie,");

        addChemin(9, "Séjour", "Pharmacie", 24, "8.upz/traverser le séjour,4.doorz/ Passer la porte,5.rightz/ Dans le couloir tournez à droite,5.leftz/ puis tournez à gauche,2.doorz/ et rentrez dans la pharmacie,");
        addChemin(19, "Séjour", "Accueil", 21, "8.upz/traverser le séjour,5.doorz/ rentrez par la porte devant vous,8.upz/ continuez vers le centre de l'accueil,");

        addChemin(20, "Consultation", "Accueil", 34, "12.upz/traverser la consultation: suivre ligne jaune au sol,3.doorz/prenez la porte,6.upz/traverser le couloir,5.doorz/prenez la porte coulissante,8.upz/continuer quelques pas vers l'accueil,");
        addChemin(5, "Consultation", "Imagerie", 10, "5.upz/Traversez la consultation,5.doorz/ et prenez le sas imagerie,");
        addChemin(21, "Consultation", "Ophtalmologie", 38, "8.upz/Traversez la consultation vers le couloir,5.doorz/rentrez dans le couloir,20.upz/ allez au fond du couloir,5.leftz/et prenez le sas ophtamologiesur la gauche,");

        addChemin(22, "Imagerie", "Consultation", 10, "5.upz/Traversez l'imagerie,5.doorz/ et prenez la porte de la consultation,");

        addChemin(23, "Ophtalmologie", "Consultation", 41, "5.doorz/Sortez de l'ophtamologie,5.leftz/prenez à gauche sur le couloir,18.upz/continuer tout droit,5.doorz/prendre la porte à droite,8.upz/et traverser la consultation,");
        addChemin(24, "Ophtalmologie", "Accueil", 40, "20.upz/Traversez le couloir,20.doorz/ prenez la porte,");

        addChemin(25, "Pharmacie", "Séjour", 15, "10.doorz/Prenez la porte du fond,5.upz/ et traverser le sejour,");
        addChemin(10, "Pharmacie", "Local", 15, "15.doorz/Prenez la porte du fond,");
        addChemin(31, "Pharmacie", "Urgences", 21, "8.upz/sortez de la pharmacie,5.doorz/ prendre la porte coulissante,8.upz/Marchez en suivant les lignes vertes au mur,");

        addChemin(26, "Local", "Pharmacie", 15, "15.doorz/Prenez la porte du fond,");

        addChemin(27, "EscalierBas", "Accueil", 13, "8.upz/En bas de l'escalier,5.upz/avancez tout droit,");
        addChemin(8, "EscalierBas", "EscalierHaut", 12, "12.stairz/Monter l'escalier(20 marches),");

        addChemin(28, "Oncologie", "EscalierHaut", 13, "8.doorz/Prenez la porte au fond,5.leftz/prenez à gauche,");

        addChemin(29, "LongSejour", "EscalierHaut", 5, "5.upz/continuer vers la porte Face à vous,");
        addChemin(30, "Ambulatoire", "EscalierHaut", 6, "6.leftz/Après la porte continuez à gauche,");

        addChemin(11, "EscalierHaut", "Oncologie", 13, "5.leftz/Prenez à gauche,8.doorz/Prenez la porte au fond,");
        addChemin(12, "EscalierHaut", "LongSejour", 5, "5.upz/continuer vers la porte Face à vous,");
        addChemin(15, "EscalierHaut", "Ambulatoire", 6, "6.rightz/Prenez la porte de gauche,");
        addChemin(16, "EscalierHaut", "EscalierBas", 12, "12.stairz/Descendre l'escalier(20 marches,");

        return ListeChemin;
    }




    // ajout des noeuds et des noeuds adjacents
    public static List<Noeud> getNoeud() {

        Noeud Noeud1 = new Noeud("Entree");
        Noeud Noeud2 = new Noeud("Accueil");
        Noeud Noeud3 = new Noeud("Urgences");
        Noeud Noeud4 = new Noeud("Séjour");
        Noeud Noeud5 = new Noeud("Consultation");
        Noeud Noeud6 = new Noeud("Imagerie");
        Noeud Noeud7 = new Noeud("Ophtalmologie");
        Noeud Noeud8 = new Noeud("Pharmacie");
        Noeud Noeud9 = new Noeud("Local");
        Noeud Noeud10 = new Noeud("Oncologie");
        Noeud Noeud11 = new Noeud("LongSejour");
        Noeud Noeud12 = new Noeud("Ambulatoire");
        Noeud Noeud13 = new Noeud("EscalierBas");
        Noeud Noeud14 = new Noeud("EscalierHaut");

        ListeNoeud.add(Noeud1);
        ListeNoeud.add(Noeud2);
        ListeNoeud.add(Noeud3);
        ListeNoeud.add(Noeud4);
        ListeNoeud.add(Noeud5);
        ListeNoeud.add(Noeud6);
        ListeNoeud.add(Noeud7);
        ListeNoeud.add(Noeud8);
        ListeNoeud.add(Noeud9);
        ListeNoeud.add(Noeud10);
        ListeNoeud.add(Noeud11);
        ListeNoeud.add(Noeud12);
        ListeNoeud.add(Noeud13);
        ListeNoeud.add(Noeud14);
        return ListeNoeud;
    }
    public static Noeud findNoeud(String Value){
        Noeud n = null;
        for (int i=0; i < ListeNoeud.size();i++){
            if (ListeNoeud.get(i).getValue().contains(Value)){
                n = ListeNoeud.get(i);
            }
        }
        return n;
    }

    public static  Graph.Pont[] getGraph() {

        Graph.Pont[] GRAPH2 = new Graph.Pont[ListeChemin.size()];
        for (int i = 0; i < ListeChemin.size(); i++) {
                GRAPH2[i]= new Graph.Pont(ListeChemin.get(i).getDepart(), ListeChemin.get(i).getArrive(), ListeChemin.get(i).getDistance());
        }
        return GRAPH2;

    }


    public void ajoutCheminDB(){
        for(int i=0;i>ListeChemin.size();i++){
            db.ajouterUnChemin(ListeChemin.get(i));
        }
    }
    public void ajoutNoeudDB(){
        for(int i=0;i>ListeNoeud.size();i++){
            db.ajouterUnNoeud(ListeNoeud.get(i));
        }
    }


}
