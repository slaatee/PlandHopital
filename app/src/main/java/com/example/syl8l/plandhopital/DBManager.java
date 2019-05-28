package com.example.syl8l.plandhopital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context){
        super(context, "planHopital.db", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase sqld) {
        Log.i("database", "DBManager.onCreate(): "
                + "CREATE TABLE Chemin (id INTEGER PRIMARY KEY autoincrement, nom  TEXT, distance TEXT, txt TEXT;"
                + "CREATE TABLE Noeud (String value;");
        sqld.execSQL("CREATE TABLE Chemin (id INTEGER PRIMARY KEY autoincrement, nom  TEXT,distance INTEGER,txt TEXT);");
        sqld.execSQL("CREATE TABLE Noeud (String value);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqld, int i, int i1) {
        // montée en version.
        sqld.execSQL("DROP TABLE if exists Chemin;");
        sqld.execSQL("DROP TABLE if exists Noeud;");
        this.onCreate(sqld);
    }
    public long ajouterUnChemin(final Chemin c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tuple = new ContentValues();
        tuple.put("depart", c.getDepart());
        tuple.put("arrive", c.getArrive());
        tuple.put("distance", c.getDistance());
        tuple.put("txt", c.getTxt());
        long id = db.insertOrThrow("Chemin", null, tuple);
        return id;
    }
    public void ajouterUnNoeud(final Noeud n){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tuple = new ContentValues();
        tuple.put("value", n.getValue());
        return;
    }

    public List<Noeud> voirTousLesNoeud(){
        ArrayList<Noeud> noe = new ArrayList<Noeud>();
        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM Noeud;", new String[0]);
        c.moveToFirst();
        while(! c.isAfterLast()){
           Noeud no = new Noeud(
                    c.getString(1));
                    noe.add(no);
            c.moveToNext();
        }
        return noe;
    }
    public List<Chemin> voirTousLesChemins(){
        ArrayList<Chemin> res = new ArrayList<Chemin>();
        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM Chemin;", new String[0]);
        c.moveToFirst();
        while(! c.isAfterLast()){
            Chemin ch = new Chemin(
                    c.getInt(1)
                    , c.getString(2)
                    , c.getString(3)
                    , c.getInt(4)
                    , c.getString(5));
            res.add(ch);
            c.moveToNext();
        }
        return res;
    }

}
