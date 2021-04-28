package aggiomarchettimautone.giochiriuniti;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/*
Questa classe gestisce il gioco delle differenze. Appare l'immagine composta da due parti, quella sinistra
a cui manca qualcosa, e quella di destra che mostra l'immagine completa.
Avro quindi due pulsanti, uno per la differenza di destra e uno per la differenza di sinistra.
Un'altro pulsante è presente nel restante spazio dell'immagine per capire se sbaglio la differenza.
 */

public class Livello_Difficile_Differenze extends AppCompatActivity {

    final int numFoto = 9;       //numero di foto totali di livello
    int i = 0;              //foto corrente
    int p = 0;            //numero di foto corrette



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livello__difficile__differenze);

        //messaggi di avvertimento per risultato corretto o meno
        final Toast toastC = Toast.makeText(getApplicationContext(),"TROVATO!!!",Toast.LENGTH_SHORT);
        final Toast toastE = Toast.makeText(getApplicationContext(),"SBAGLIATO!!!",Toast.LENGTH_SHORT);
        //pulsanti che si trovano nella differenza dell'immagine sinistra e destra
        final Button corretto1 = (Button)findViewById(R.id.correct1);
        final Button corretto2 = (Button)findViewById(R.id.correct2);
        //pulsante per cogliere l'errore dell'utente nel trovare la differenza
        final Button sbagliato = (Button)findViewById(R.id.notCorrect);
        // Textview che visualizza a che numero di immagine sono arrivato sul totale
        final TextView corrente = (TextView)findViewById(R.id.immagineCorr);
        //Textview che indica qual è il mio punteggio attuale
        final TextView punteggio = (TextView)findViewById(R.id.punteggioCorr);
        //variabile per il tempo di attesa tra un immagine e l'altra nel quale viene mostrato l'errore
        final Handler handler = new Handler();

        //coordinate per foto
        //creo un vettore con N elementi, uno per immagine
        final Coordinate_Differenze hardP[] = new Coordinate_Differenze[numFoto];
        hardP[0] = new Coordinate_Differenze(270,330,R.drawable.d11,R.drawable.d12);
        hardP[1] = new Coordinate_Differenze(0,130,R.drawable.d21,R.drawable.d22);
        hardP[2] = new Coordinate_Differenze(230,155,R.drawable.d31,R.drawable.d32);
        hardP[3] = new Coordinate_Differenze(190,200,R.drawable.d41,R.drawable.d42);
        hardP[4] = new Coordinate_Differenze(170,220,R.drawable.d51,R.drawable.d52);
        hardP[5] = new Coordinate_Differenze(120,220,R.drawable.d61,R.drawable.d62);
        hardP[6] = new Coordinate_Differenze(160,540,R.drawable.d71,R.drawable.d72);
        hardP[7] = new Coordinate_Differenze(0,0,R.drawable.d81,R.drawable.d82);
        hardP[8] = new Coordinate_Differenze(0,100,R.drawable.d91,R.drawable.d92);

        //inizializzo la prima immagine e le relative coordinate della differenza
        sbagliato.setBackground(getDrawable(hardP[i].giveName()));
        corretto1.setX(hardP[i].giveX());
        corretto1.setY(hardP[i].giveY());
        //calcolo che la seconda immagine è spostata di 550 rispetto la prima, così ho le coordinate della seocnda casella di differenza
        corretto2.setX(hardP[i].giveX()+550);
        corretto2.setY(hardP[i].giveY());


        //Azione in caso di ritrovamento della differenza

        corretto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //metodo per la gestione dell'attesa tra un'immagine e la successiva
                handler.postDelayed(new Runnable() {
                    int j=0;
                    @Override
                    public void run() {
                        if(j==0)
                        {
                            p++;    //incremento il punteggio di 1
                            //visualizzo l'immagine con la correzzione
                            sbagliato.setBackground(getDrawable(hardP[i].giveNameC()));
                            //aggiorno la casella di testo col punteggio aggiornato
                            punteggio.setText("CORRETTI: "+ p);
                            //visualizzo un messaggio che mi indica la correttezza
                            toastC.show();
                            //incremento il numero dell'immagine corrente
                            i++;
                            j++;
                            //disabilito i pulsanti per evitare errori durante la visualizzazione della differenza
                            corretto1.setEnabled(false);
                            corretto2.setEnabled(false);
                            sbagliato.setEnabled(false);
                            //imposto tempo d'attesa
                            handler.postDelayed(this, 3000);
                        }
                        //controllo se ho ancora foto da giocare
                        else if(i!=numFoto){
                            //aggiorno l'immagine successiva
                            sbagliato.setBackground(getDrawable(hardP[i].giveName()));
                            //Aggiorno il numero di immagine corrente
                            corrente.setText("Difficile : "+(i+1)+" DI 9");
                            //sposto coordinate nuova foto
                            corretto1.setX(hardP[i].giveX());
                            corretto1.setY(hardP[i].giveY());
                            corretto2.setX(hardP[i].giveX()+550);
                            corretto2.setY(hardP[i].giveY());
                            //abilito i pulsanti
                            corretto1.setEnabled(true);
                            corretto2.setEnabled(true);
                            sbagliato.setEnabled(true);
                            //tempo di attesa
                            handler.postDelayed(this, 3000);
                        }
                        //se invece mi trovo all'ultima foto e non ho foto successive
                        else if(i == numFoto)
                        {
                            //salvo il punteggio e mando l'utente all'activity finale con segnalazione punteggio
                            Intent openRisultato = new Intent(Livello_Difficile_Differenze.this,Risultato_Differenze.class);
                            openRisultato.putExtra("p",p);
                            openRisultato.putExtra("numImm",numFoto);
                            startActivity(openRisultato);
                        }

                    }
                }, 10);
            }
        });

        //questa azione sul pulsante è analoga al "corretto1"
        corretto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                handler.postDelayed(new Runnable() {
                    int j=0;
                    @Override
                    public void run() {
                        if(j==0)
                        {
                            p++;    //incremento il punteggio di 1
                            sbagliato.setBackground(getDrawable(hardP[i].giveNameC()));
                            punteggio.setText("CORRETTI: "+ p);
                            toastC.show();
                            i++;
                            j++;
                            corretto1.setEnabled(false);
                            corretto2.setEnabled(false);
                            sbagliato.setEnabled(false);
                            handler.postDelayed(this, 3000);
                        }
                        else if(i!=numFoto){
                            sbagliato.setBackground(getDrawable(hardP[i].giveName()));
                            corrente.setText("Difficile : "+(i+1)+" DI 9");
                            //sposto coordinate nuova foto
                            corretto1.setX(hardP[i].giveX());
                            corretto1.setY(hardP[i].giveY());
                            corretto2.setX(hardP[i].giveX()+550);
                            corretto2.setY(hardP[i].giveY());
                            corretto1.setEnabled(true);
                            corretto2.setEnabled(true);
                            sbagliato.setEnabled(true);
                            handler.postDelayed(this, 3000);
                        }
                        else if(i == numFoto)
                        {
                            Intent openRisultato = new Intent(Livello_Difficile_Differenze.this,Risultato_Differenze.class);
                            openRisultato.putExtra("p",p);
                            openRisultato.putExtra("numImm",numFoto);
                            startActivity(openRisultato);
                        }

                    }
                }, 10);
            }
        });

        //Azione nel caso di errore sul ritrovamento

        sbagliato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(new Runnable() {
                    int j=0;
                    @Override
                    public void run() {
                        if(j==0)
                        {
                            //non incremento il punteggio
                            //mostro l'errore con l'immagine corretta
                            sbagliato.setBackground(getDrawable(hardP[i].giveNameC()));
                            //visualizzo il punteggio
                            punteggio.setText("CORRETTI: "+ p);
                            //avverto l'utente dell'errore
                            toastE.show();
                            //incremento il numero di foto corrente
                            i++;
                            j++;
                            //disabilito i pulsanti per evitare errori
                            corretto1.setEnabled(false);
                            corretto2.setEnabled(false);
                            sbagliato.setEnabled(false);
                            //tempo di attesa del programma per permettere di visualizzare l'errore
                            handler.postDelayed(this, 3000);
                        }
                        //entro se ho ancora immagini dopo l'attuale
                        else if(i!=numFoto){
                            //Aggiorno all'immagine successiva
                            sbagliato.setBackground(getDrawable(hardP[i].giveName()));
                            //visualizzo il numero di foto corrente
                            corrente.setText("Difficile : "+(i+1)+" DI 9");
                            //sposto coordinate nuova foto
                            corretto1.setX(hardP[i].giveX());
                            corretto1.setY(hardP[i].giveY());
                            corretto2.setX(hardP[i].giveX()+550);
                            corretto2.setY(hardP[i].giveY());
                            //riabilito i pulsanti
                            corretto1.setEnabled(true);
                            corretto2.setEnabled(true);
                            sbagliato.setEnabled(true);
                            //tempo di attesa
                            handler.postDelayed(this, 3000);
                        }
                        //se sono all'ultima foto
                        else if(i == numFoto)
                        {
                            //salvo il punteggio e mando l'utente all'activity finale che mostrerà il punteggio
                            Intent openRisultato = new Intent(Livello_Difficile_Differenze.this,Risultato_Differenze.class);
                            openRisultato.putExtra("p",p);
                            openRisultato.putExtra("numImm",numFoto);
                            startActivity(openRisultato);
                        }
                    }
                }, 10);
            }
        });
    }
}
