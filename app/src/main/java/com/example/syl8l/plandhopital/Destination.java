package com.example.syl8l.plandhopital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import java.util.List;

/** classe destination pour choisir la destination on enverra vers parcours les information de position de départ et de destination dans l'intent**/
public class Destination extends AppCompatActivity {

    private Spinner spinner;
    public Noeud position;
    public Noeud Destination;
    ArrayAdapter<CharSequence> positionChoix;
    public Intent intent3;
    public ImageView dest;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.destination);

        this.dest= findViewById(R.id.imgDest);
        this.spinner = findViewById(R.id.spinner);
        positionChoix = ArrayAdapter.createFromResource(this,
                R.array.position_array, android.R.layout.simple_spinner_item);
        positionChoix.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(positionChoix);

        Intent intent = getIntent();
        position = (Noeud) intent.getSerializableExtra("noeud");
        if (null != savedInstanceState) {
        }
        intent3 = new Intent(this, Parcours.class);
        intent3.putExtra("position",position);
        dest.setImageResource(Util.changeImage(position.toString()));
    }

    /** spinner du choix de destination et valide le noeud destination */
    public void maDestination(View v) {
        Destination = DataService.findNoeud(String.valueOf(spinner.getSelectedItem()));
        intent3.putExtra("destination",Destination);
        this.startActivity(intent3);


        }

    }
