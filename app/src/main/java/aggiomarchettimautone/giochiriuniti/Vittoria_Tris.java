package aggiomarchettimautone.giochiriuniti;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Vittoria_Tris extends AppCompatActivity {

    //classe che annuncia la vittoria di uno dei due giocatori
    final Context context = this;
    //Definisco un bottone per poter tornare indietro
    Button b;
    //Utile per poter scrivere il nome del vincitore
    TextView nomeVincitore;
    //Immagine di vittoria
    private ImageView miaImmagine;
    @Override //sovrascrivo il metodo onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //imposto la schermata di vittoria (definita nel file .xml)
        setContentView(R.layout.activity_vittoria__tris);

        //imposto l'immagine del trofeo
        miaImmagine = (ImageView) findViewById(R.id.imageView);
        miaImmagine.setImageResource(R.drawable.premio);
        //Tramite questa chiamata posso recuperare il nome del vincitore
        Intent intent = getIntent();
        String vincitore = intent.getStringExtra("vincitore");
        //Ed impostarlo sulla schermata
        nomeVincitore = (TextView)findViewById(R.id.textView4);
        //Infine inizializzo il bottone per poter tornare indietro
        b = (Button)findViewById(R.id.goBack1);
        nomeVincitore.setText(vincitore);
        //Definisco cosa succede se clicco sul tasto prima definito
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Grazie agli Intent posso tornare indietro alla pagina iniziale.
                Intent i = new Intent(context, Menu_Tris.class);
                startActivity(i);
            }
        });
    }
}
