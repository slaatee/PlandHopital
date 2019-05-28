package com.example.syl8l.plandhopital;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;


public class Parcours extends AppCompatActivity implements SensorEventListener {


    boolean running = false;
    String comptage;
    private SensorManager sensorManager;
    private TextView compteur;
    private int compte;

    // mes noeuds
    private Noeud position;
    private Noeud destination;

    private String textValue;
    private String audioCompare = "";
    private String compareParcourons = "";
    private StringBuilder res;
    private ImageView imV;
    private ImageView direction;

    //boutons
    private Button close;
    private Button close2;
    private ImageView sound;
    // textview des parcours
    private TextView parcoursText;
    private TextView text;
    private TextView vosDeplacement;
    private ScrollView scVparcours;

    //initialisation du textspeech
    private TextToSpeech mTts;

    //mon compteur de pas
    private int stepCounter = 0;
    private int counterSteps;
    private float value2;

    //ma barre de progression
    private SeekBar simpleProgressBar;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
            this.setContentView(R.layout.parcours);

            this.imV = findViewById(R.id.carteParcours);
            this.direction = findViewById(R.id.imageViewParcours);
            this.close = findViewById(R.id.closeCarte);
            this.close2 = findViewById(R.id.closeParcours);
            this.text = findViewById(R.id.tV2);
            this.vosDeplacement = findViewById(R.id.tV);
            this.parcoursText = findViewById(R.id.toutLeParcours);
            this.sound = findViewById(R.id.audioBtnSon);
            res = new StringBuilder();
            this.scVparcours = findViewById(R.id.scrollparcours);
            // compteur de pas
            this.compteur = findViewById(R.id.compteur);
            //this.compteur2 = findViewById(R.id.comptage);
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

            // récupération des données des autres vues.
            Intent intent = getIntent();
            position = (Noeud) intent.getSerializableExtra("position");
            destination = (Noeud) intent.getSerializableExtra("destination");

            // on initialise notre GRAPH
            Graph g = new Graph(DataService.getGraph());
            g.dijkstra(position.toString());
            // on récupère la liste des noeuds parcourus
            comptage = g.printPath(destination.toString());
            // on récupère les chemins de ses parcours
            textValue = Util.getParcours(comptage);
            parcoursText.setText(textValue);
            vosDeplacement.setText("Votre déplacement " + position.toString() + " pour " + destination.toString());
            // on creer notre TextToSpeech (on met la valeur langue francaise
            mTts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        mTts.setLanguage(Locale.FRANCE);

                    }
                }
            });
            mTts.setSpeechRate(.8f);
            simpleProgressBar = findViewById(R.id.progress);
            simpleProgressBar.setEnabled(false);
        }


    // OnClick pour rescanner sa position
    public void scanPar(View v) {
        Intent intentParcours = new Intent(this, Scan.class);
        intentParcours.putExtra("destination", destination);
        this.startActivity(intentParcours);
    }
    // gestion du son sur le audioTextTospeech
    public int btnSound = 0;
    public HashMap<String, String> params = new HashMap<String, String>();
    public void couperLeSon(View V) {
        if (btnSound == 0) {
            sound.setImageResource(R.drawable.ic_volume_off_black_24dp);
            btnSound++;
            // popup de son
            Toast toast = Toast.makeText(this, "Son couper", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
            toast.show();
        } else {
            sound.setImageResource(R.drawable.ic_volume_up_black_24dp);
            btnSound--;
            // popup de son
            Toast toast = Toast.makeText(this, "Son ouvert", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
            toast.show();
        }
    }

    //OnClick pour entendre le parcours en audio
    public void audioUp(View v) {
        String toSpeak = vosDeplacement.getText().toString();
        Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
        mTts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, params);
    }


    //onPause pour le textToSpeech
    public void onPause() {
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onPause();
        running = false;
    }

    public void parcoursUp(View v) {
        if (parcoursText.getVisibility() == View.INVISIBLE) {
            scVparcours.setVisibility(View.VISIBLE);
            parcoursText.setVisibility(View.VISIBLE);
            close2.setVisibility(View.VISIBLE);
        } else {
            parcoursText.setVisibility(View.INVISIBLE);
            scVparcours.setVisibility(View.INVISIBLE);
            close2.setVisibility(View.INVISIBLE);

        }
    }

    public void carteUp(View v) {
        if (imV.getVisibility() == View.INVISIBLE) {
            imV.setVisibility(View.VISIBLE);
            close.setVisibility(View.VISIBLE);
        } else {
            imV.setVisibility(View.INVISIBLE);
            close.setVisibility(View.INVISIBLE);
        }
    }

    public void goToAccueil(View V) {
        Intent accueil = new Intent(this, MainActivity.class);
        this.startActivity(accueil);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, sensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.sensorManager.unregisterListener(this);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        compte = Util.getPas(comptage);
        simpleProgressBar.setMax(compte);
        if (running) {
            if (counterSteps < 1) {
                // initial value
                counterSteps = (int) event.values[0];
            }
            // Calcul des pas
            stepCounter = (int) event.values[0] - counterSteps;
            value2 = compte - stepCounter;
            compteur.setText("il vous reste :"+(int)value2+" mètres");
            setParcours(value2);
            simpleProgressBar.setProgress(stepCounter);
        }
        if (value2 <= 0) {
            compteur.setText("ok");
            Toast.makeText(this, "Vous êtes arrivés à votre destination!", Toast.LENGTH_SHORT).show();
        }

    }

    // envoie le son
    public void audioMts(String txtValue) {
        if(btnSound==0){
        if (!audioCompare.equals(txtValue)) {
            mTts.speak(txtValue, TextToSpeech.QUEUE_FLUSH, null);
            audioCompare = txtValue;
        }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public int fP;
    public int cP;
    public void setParcours(float value) {
    if(value<=0){}
    else{
        String newLine = "\n";
        String parcourons = "";
// on divise le parcours àla virgule
        String[] parcours = Util.getCheminement(comptage).split(",");

        if (cP == 0) {
            // on recupère l'entier du début pour savoir combien de pas il faut faire.
            int iend = parcours[fP].indexOf(".");
            if (iend != -1) {
                cP = Integer.parseInt(parcours[fP].substring(0, iend));
            }
        }

        int i = parcours[fP].indexOf("/");
// si le compteur est superieur ou egale au total -
        if (value >= compte - cP) {
            int ip = parcours[fP].indexOf("/");
            if (ip == -1) {

            } else {
                parcourons = parcours[fP].substring(i + 1).trim();

            }

            vosDeplacement.setText((parcourons));
            if (parcours[fP].contains("doorz")) {
                direction.setImageResource(R.drawable.ic_door);
                audioMts(parcourons);
            }
            if (parcours[fP].contains("upz")) {
                direction.setImageResource(R.drawable.walk);
                audioMts(parcourons);
            }
            if (parcours[fP].contains("leftz")) {
                direction.setImageResource(R.drawable.left);
                audioMts(parcourons);
            }
            if (parcours[fP].contains("rightz")) {
                direction.setImageResource(R.drawable.right);
                audioMts(parcourons);
            }
            if (parcours[fP].contains("stairz")) {
                direction.setImageResource(R.drawable.escalier);
                audioMts(parcourons);
            }
            if (parcours[fP].contains("finishz")) {
                direction.setImageResource(R.drawable.ic_pin_drop_black_24dp);
                audioMts(parcourons);
            }
            if (!parcourons.equals(compareParcourons)) {
                res.append(parcourons + newLine);
                text.setText(res.toString());
                compareParcourons = parcourons;
            }
        } else {

            fP++;
            int cP2 = 0;
            int iend = parcours[fP].indexOf(".");
            if (iend != -1) {
                cP2 = Integer.parseInt(parcours[fP].substring(0, iend));
            }
            cP = cP + cP2;
        }

    }
    }
}