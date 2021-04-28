package aggiomarchettimautone.giochiriuniti;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
Questa classe gestisce il gioco delle differenze. Appare l'immagine composta da due parti, quella sinistra
a cui manca qualcosa, e quella di destra che mostra l'immagine completa.
Avro quindi due pulsanti, uno per la differenza di destra e uno per la differenza di sinistra.
Un'altro pulsante è presente nel restante spazio dell'immagine per capire se sbaglio la differenza.
 */
public class Livello_Facile_Differenze extends AppCompatActivity {

    final int numFoto = 10;       //numero di foto totali di livello
    int i = 0;              //foto corrente
    int p = 0;            //numero di foto corrette



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livello__facile__differenze);

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
        final Coordinate_Differenze easyP[] = new Coordinate_Differenze[numFoto];
        easyP[0] = new Coordinate_Differenze(270,400,R.drawable.e11,R.drawable.e12);
        easyP[1] = new Coordinate_Differenze(270,230,R.drawable.e21,R.drawable.e22);
        easyP[2] = new Coordinate_Differenze(170,280,R.drawable.e31,R.drawable.e32);
        easyP[3] = new Coordinate_Differenze(30,200,R.drawable.e41,R.drawable.e42);
        easyP[4] = new Coordinate_Differenze(20,180,R.drawable.e51,R.drawable.e52);
        easyP[5] = new Coordinate_Differenze(80,285,R.drawable.e61,R.drawable.e62);
        easyP[6] = new Coordinate_Differenze(200,30,R.drawable.e71,R.drawable.e72);
        easyP[7] = new Coordinate_Differenze(100,360,R.drawable.e81,R.drawable.e82);
        easyP[8] = new Coordinate_Differenze(100,500,R.drawable.e91,R.drawable.e92);
        easyP[9] = new Coordinate_Differenze(150,150,R.drawable.e101,R.drawable.e102);

        //inizializzo la prima immagine e le relative coordinate della differenza
        sbagliato.setBackground(getDrawable(easyP[i].giveName()));
        corretto1.setX(easyP[i].giveX());
        corretto1.setY(easyP[i].giveY());
        //calcolo che la seconda immagine è spostata di 550 rispetto la prima, così ho le coordinate della seocnda casella di differenza
        corretto2.setX(easyP[i].giveX()+550);
        corretto2.setY(easyP[i].giveY());


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
                            sbagliato.setBackground(getDrawable(easyP[i].giveNameC()));
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
                            sbagliato.setBackground(getDrawable(easyP[i].giveName()));
                            //Aggiorno il numero di immagine corrente
                            corrente.setText("FACILE : "+(i+1)+" DI 10");
                            //sposto coordinate nuova foto
                            corretto1.setX(easyP[i].giveX());
                            corretto1.setY(easyP[i].giveY());
                            corretto2.setX(easyP[i].giveX()+550);
                            corretto2.setY(easyP[i].giveY());
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
                            Intent openRisultato = new Intent(Livello_Facile_Differenze.this,Risultato_Differenze.class);
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
                            sbagliato.setBackground(getDrawable(easyP[i].giveNameC()));
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
                            sbagliato.setBackground(getDrawable(easyP[i].giveName()));
                            corrente.setText("FACILE : "+(i+1)+" DI 10");
                            //sposto coordinate nuova foto
                            corretto1.setX(easyP[i].giveX());
                            corretto1.setY(easyP[i].giveY());
                            corretto2.setX(easyP[i].giveX()+550);
                            corretto2.setY(easyP[i].giveY());
                            corretto1.setEnabled(true);
                            corretto2.setEnabled(true);
                            sbagliato.setEnabled(true);
                            handler.postDelayed(this, 3000);
                        }
                        else if(i == numFoto)
                        {
                            Intent openRisultato = new Intent(Livello_Facile_Differenze.this,Risultato_Differenze.class);
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
                            //non incremento p
                            //mostro l'errore con l'immagine corretta
                            sbagliato.setBackground(getDrawable(easyP[i].giveNameC()));
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
                            sbagliato.setBackground(getDrawable(easyP[i].giveName()));
                            //visualizzo il numero di foto corrente
                            corrente.setText("FACILE : "+(i+1)+" DI 10");
                            //sposto coordinate nuova foto
                            corretto1.setX(easyP[i].giveX());
                            corretto1.setY(easyP[i].giveY());
                            corretto2.setX(easyP[i].giveX()+550);
                            corretto2.setY(easyP[i].giveY());
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
                            Intent openRisultato = new Intent(Livello_Facile_Differenze.this,Risultato_Differenze.class);
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

