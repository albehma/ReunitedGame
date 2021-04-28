package aggiomarchettimautone.giochiriuniti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.widget.ImageView;

public class Pareggio_Tris extends Activity {

    //Classe che definisce la schermata di pareggio
    final Context context = this;
    //Tasto per poter tornare indietro
    Button b;
    //Immagine di pareggio
    private ImageView miaImmagine;
    @Override //Sovrascrivo il metodo onCreate
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Imposto la schermata definita nel fil .xml
        setContentView(R.layout.activity_pareggio__tris);
        //Ed inizializzo l'immagine di pareggio
        miaImmagine = (ImageView) findViewById(R.id.imageView3);
        miaImmagine.setImageResource(R.drawable.pareggio);
        //Infine inizializzo il tasto per tornare indietro
        b = (Button) findViewById(R.id.goBack2);

        //Definisco cosa succede se clicco sul tasto per tornare indietro
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Grazie agli intent posso tornare alla pagina iniziale
                Intent i = new Intent(context, Menu_Tris.class);
                startActivity(i);
            }
        });
    }
}
