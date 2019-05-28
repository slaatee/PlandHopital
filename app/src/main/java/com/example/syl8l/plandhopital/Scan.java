package com.example.syl8l.plandhopital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/** class scan avec le choix de scanner a&vec un qrCode si il se trouvent sur la machine ou par un spinner **/

public class Scan extends AppCompatActivity {

    public Noeud position;
    public Button scanBtn;
    public Button positionBtn;
    public Noeud destination;
    // liste de string du spinner
    ArrayAdapter<CharSequence> positionChoix;
    private Spinner spinner;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.scan);

        scanBtn = (Button) findViewById(R.id.scan);
        positionBtn = (Button) findViewById(R.id.positionBtn);
        this.spinner = findViewById(R.id.spinnerScan);
        positionChoix = ArrayAdapter.createFromResource(this,
                R.array.position_array, android.R.layout.simple_spinner_item);
        positionChoix.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(positionChoix);
        Intent intent = getIntent();
        destination = (Noeud) intent.getSerializableExtra("destination");

    }

    /** onClick oour declancher scan QrCode **/
    public void scannerUp(View v) {
        if (v.getId() == R.id.scan) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        } else finish();

    }

    /** recupération du résultat deu scanner **/
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult =
                IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null && resultCode==RESULT_OK) {
            String scanContent = scanningResult.getContents();
            /** on recherche la valeur dans la liste de noeud**/
            position = DataService.findNoeud(scanContent);
            if (position != null) {
                Toast toast = Toast.makeText(getApplicationContext(), "Vous êtes ici :" + position, Toast.LENGTH_SHORT);
                toast.show();
                /** choix si on revient de l'intent parcours**/
                if (destination != null) {
                    Intent intenT = new Intent(this, Parcours.class);
                    intenT.putExtra("position", position);
                    intenT.putExtra("destination", destination);
                    this.startActivity(intenT);
                } else {
                    Intent intent2 = new Intent(this, Destination.class);
                    intent2.putExtra("noeud", position);
                    this.startActivity(intent2);
                }
            }
        }
        else {

        }
    }
/** le spinner qui nous choisiras notre position **/
    public void maPosition(View v) {
        position = DataService.findNoeud(String.valueOf(spinner.getSelectedItem()));
/** si l'utilisateur vient de la vue parcours on le renvoie vers parcours.**/
        if (destination != null) {
            Intent intent = new Intent(this, Parcours.class);
            intent.putExtra("position", position);
            intent.putExtra("destination", destination);
            this.startActivity(intent);
/** ici on va vers la vue destination**/
        } else {
            Intent intent3 = new Intent(this, Destination.class);
            intent3.putExtra("noeud", position);
            this.startActivity(intent3);
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
