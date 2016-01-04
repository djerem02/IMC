package insset.imc;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Principale extends AppCompatActivity {

    private final String defaut = "Cliquez sur le bouton 'Calculer' pour obtenir votre IMC";
    private final String megaString = "Votre poids est correcte.";

    private Button calculer = null;
    private Button raz = null;
    private EditText poids = null;
    private EditText taille = null;
    private RadioGroup group = null;
    private TextView result = null;
    private CheckBox mega = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);

        calculer = (Button) findViewById(R.id.calcul);
        raz = (Button) findViewById(R.id.raz);
        taille = (EditText) findViewById(R.id.taille);
        poids = (EditText) findViewById(R.id.poids);
        mega = (CheckBox) findViewById(R.id.fonction);
        group = (RadioGroup) findViewById(R.id.group);
        result = (TextView) findViewById(R.id.result);

        calculer.setOnClickListener(calculerListener);
        raz.setOnClickListener(razListener);
        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);
        mega.setOnClickListener(checkedListener);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private View.OnClickListener calculerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mega.isChecked()) {
                String t = taille.getText().toString();
                String p = poids.getText().toString();
                float tValue = Float.valueOf(t);
                if (tValue <= 100) {
                    Toast.makeText(Principale.this, "Hého,tu es un nain ou quoi?", Toast.LENGTH_SHORT).show();
                } else {
                    float pValue = Float.valueOf(p);

                    //Calcul IMC
                    tValue=tValue/100;
                    tValue = (float) Math.pow(tValue, 2);
                    float imc = pValue / tValue;
                    result.setText("Votre IMC est " + String.valueOf(imc));
                }//fin else
            }//fin if checked
            else {
                result.setText(megaString);
            }
        }// fin onClick
    };

    private View.OnClickListener razListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            poids.getText().clear();
            taille.getText().clear();
            result.setText(defaut);
        }
    };

    private View.OnClickListener checkedListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!((CheckBox)v).isChecked() && result.getText().equals(megaString))
                result.setText(defaut);
        }
    };

}//fin Class Principale