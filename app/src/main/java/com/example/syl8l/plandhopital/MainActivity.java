package com.example.syl8l.plandhopital;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public Noeud position;
    private List<Noeud>noeuds;
    private ImageView imV;
    private Button close;
    public int i =0;
    public int o =0;
    public int c =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noeuds = DataService.getNoeud();
        position = noeuds.get(0);
        this.imV=findViewById(R.id.carteActivity);
        this.close=findViewById(R.id.closeCarteActivity);
    }

/** onClick vers la classe scan */
    public void scanUp(View v) {
        this.startActivity(new Intent(this, Scan.class));
    }


        public void helpMe(View v) {
        setContentView(R.layout.aide);
        o ++;
        c--;

            }
    public void onBackPressed() {
        if(o!=0){
            setContentView(R.layout.activity_main);
            c++;
            o--;
        }
        if(o==0){
            c++;
        }
        if(c==2){
            finish();
        }

    }

/** onClick visuel carte */
    public void carteUp(View v){
        if (i==0){
            imV.setVisibility(View.VISIBLE);
            close.setVisibility(View.VISIBLE);
            i++;
        }
        else{
            imV.setVisibility(View.INVISIBLE);
            close.setVisibility(View.INVISIBLE);
            i--;
        }
    }


    @Override
    public void onResume(){
        super.onResume();
    }

    }
