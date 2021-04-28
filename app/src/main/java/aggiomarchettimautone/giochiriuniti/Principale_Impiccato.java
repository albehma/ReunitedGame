package aggiomarchettimautone.giochiriuniti;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Principale_Impiccato extends Activity {

    Gioco_Impiccato play = new Gioco_Impiccato(); //parte il gioco con un'istanza della classe Gioco
    @Override //sovrascrivo il metodo onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale__impiccato); //imposto la schermata da visualizzare

        //imposto sulla schermata visualizzata i testi da vedere: la parola tratteggiata ed il punteggio
        TextView wordView = (TextView) findViewById(R.id.word);
        TextView scoreText = (TextView) findViewById(R.id.score);

        wordView.setText(play.get_display_word()); //aggiorna man mano la parola
        scoreText.setText(Integer.toString(play.get_score())); //aggiorna man mano il punteggio, la trasformo in stringa poiché
        //il punteggio è un int
    }
    //metodo per inserire una nuova lettera nella parola
    public void new_guess(View view) {

        //Imposto gli elementi da visualizzare:
        TextView wordView = (TextView) findViewById(R.id.word);
        TextView scoreText = (TextView) findViewById(R.id.score);
        //nuova lettera inserita
        TextView letter_guessed = (TextView) findViewById(R.id.new_letter);

        //definisco il metodo di inserimento della nuova lettera:
        String new_guess = letter_guessed.getText().toString();
        if (!new_guess.equals(""))  { //se la nuova lettera non è la lettera nulla
            play.inserimento_lettera(new_guess); //verifico che la lettera possa essere inserita con il metodo definito in Gioco

            wordView.setText(play.get_display_word()); //aggiorno la parola
            scoreText.setText(Integer.toString(play.get_score())); //aggiorno il punteggio
            letter_guessed.setText(""); //serve a cancellare la lettera appena scritta nel box per inserimento lettera
            //in modo da poter scrivere una nuova lettera senza dover cancellare manualmente
            //quella precedente.

            //Questo serve a capire se il gioco è completato o no. Le condizioni di vittoria sono che la parola sia
            //completata e che il punteggio sia positivo. Sia in caso di vittoria che di sconfitta compare un Toast di avviso.
            if (play.parola_completata() && play.get_score() > 0) {
                Toast.makeText(getApplicationContext(), "Congratulazioni, \n hai indovinato la parola " + play.visualizza_parola_completa().toUpperCase() + " !!!", Toast.LENGTH_LONG).show();
            }
            else if(play.parola_completata() && play.get_score() < 0)
                Toast.makeText(getApplicationContext(), "Congratulazioni, \n hai indovinato la parola " + play.visualizza_parola_completa().toUpperCase() + ", ma hai ottenuto un punteggio negativo...", Toast.LENGTH_LONG).show();
        }
    }


    //reset: permette di far ripartire il gioco da zero con una nuova parola, reimpostando entrambi i parametri
    public void reset(View view) {
        TextView wordView = (TextView) findViewById(R.id.word);
        TextView scoreText = (TextView) findViewById(R.id.score);

        play = new Gioco_Impiccato();
        wordView.setText(play.get_display_word());
        scoreText.setText(Integer.toString(play.get_score()));
    }
}

