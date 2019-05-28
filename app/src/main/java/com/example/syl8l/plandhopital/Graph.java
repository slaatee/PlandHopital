package com.example.syl8l.plandhopital;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Graph {

    private final Map<String, Sommet> graph; // mappage sommet des point du noeud

    /**pont pour le graph */
    public static class Pont {
        public final String v1, v2;
        public final int dist;


        public Pont(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    public static class Sommet implements Comparable<Sommet>{

        public final String nom;
        public int dist = Integer.MAX_VALUE;
        public Sommet previous = null;
        public final Map<Sommet, Integer> neighbours = new HashMap<>();

        public Sommet(String nom)
        {
            this.nom = nom;
        }
        public StringBuilder res;


/** on retourne le chemin le plus court**/
        private String printPath()
        {

            StringBuilder res = new StringBuilder ();
            if (this == this.previous)
            {
                res.append(this.nom);
            }
            else if (this.previous == null)
            {
                res.append(this.nom);
            }
            else
            {
                this.previous.printPath();
                res.append(this.previous.printPath() +"~>"+ this.nom  );// renvoie un string de chaque etape du chemin suivi de "~>"
            }

            return res.toString();
        }
    /** on a besoin de minimum api kitkat ppour la methode compare to donc la 4.4**/
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public int compareTo(Sommet other)
        {
            if (dist == other.dist)
                return nom.compareTo(other.nom);
            return Integer.compare(dist, other.dist);
        }

        @Override public String toString()
        {
            return "(" + nom + ", " + dist + ")";
        }
    }

    /** on crée le graph */
    public Graph(Pont[] edges) {
        graph = new HashMap<>(edges.length);


        for (Pont e : edges) {
            if (!graph.containsKey(e.v1)) graph.put(e.v1, new Sommet(e.v1));
            if (!graph.containsKey(e.v2)) graph.put(e.v2, new Sommet(e.v2));
        }


        for (Pont e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);

        }
    }

    /**  */
    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            return;
        }
        final Sommet source = graph.get(startName);
        NavigableSet<Sommet> q = new TreeSet<>();

        // set-up vertices
        for (Sommet v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    /** mise en place de l'algo de dijsktra  on compare les ponts et les distances **/
    private void dijkstra(final NavigableSet<Sommet> q) {
        Sommet u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst(); // vertex à la distance la plus courte (premiere itération)
            if (u.dist == Integer.MAX_VALUE) break;

            //on regarde les distance avec les noeuds voisins
            for (Map.Entry<Sommet, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); //le voisin dans l'iteration

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }


    public String printPath(String endName) {
        if (!graph.containsKey(endName)) {
             return endName;
        }
        String res;
        res = graph.get(endName).printPath();
        return res;
    }

}

