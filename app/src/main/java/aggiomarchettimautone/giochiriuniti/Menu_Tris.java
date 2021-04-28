package aggiomarchettimautone.giochiriuniti;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Menu_Tris extends AppCompatActivity {
    //variabili varie che mi serviranno durante lo sviluppo del codice:
    //Bottone di inizio gioco (porta alla griglia)
    Button play;
    //Gruppo di pulsanti per selezionare x oppure o
    RadioGroup rg1;
    RadioGroup rg2;
    //pulsanti x e o
    RadioButton rb1;
    RadioButton rb2;
    //Tasto per procedere nel gioco (porta alla selezione personaggio)
    Button continua;
    //Stringhe che identificano i giocatori 1 e 2
    String p1;
    String p2;
    @Override //sovrascrivo il metodo onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //imposto la grafica da visualizzare
        setContentView(R.layout.activity_menu__tris);

        //Ritorno al menu principale
        Button menu = (Button)findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToMenu = new Intent(Menu_Tris.this,Menu_Principale.class);
                startActivity(returnToMenu);
            }
        });
        
        //Inizializzo il tasto continua con l'ID nel file xml
        continua = (Button)findViewById(R.id.button);
        //definisco cosa succede quando clicco sul bottone continua:
        continua.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //si apre la nuova activity selezione personaggio
                setContentView(R.layout.select_player__tris);
                //inizializzo i vari pulsanti con gli ID definiti nell'XML
                play = (Button)findViewById(R.id.play4);
                rg1 = (RadioGroup)findViewById(R.id.RadioGroup1);
                rg2 = (RadioGroup)findViewById(R.id.radioGroup2);

                //definisco cosa succede cliccando su play
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Differenzio la scelta del presonaggio x da quello o, tramite la definizione di interi
                        //grazie al metodo getCheckedRadioButtonId()
                        int scelta1 = rg1.getCheckedRadioButtonId();
                        rb1 = (RadioButton)findViewById(scelta1);
                        //cambio la stringa del player 1 ottenendo il carattere scelto e trasformandoo in stringa
                        p1 = rb1.getText().toString();

                        //faccio le stesse operazioni fatte per il player 1
                        int scelta2 = rg2.getCheckedRadioButtonId();
                        rb2 = (RadioButton)findViewById(scelta2);
                        p2 = rb2.getText().toString();

                        //Definisco l'intent per aprire una nuova schermata, in particolare quella del Gioco
                        Intent i = new Intent(Menu_Tris.this, Gioco_Tris.class);
                        //putExtra: indispensabile per portarmi nella schermata di gioco le scelte fatte dai
                        //giocatori x e o nella schermata precedente.
                        i.putExtra("Player 1", p1);
                        i.putExtra("Player 2", p2);
                        //inizio l'activity gioco
                        startActivity(i);
                    }
                });

            }
        });
    }
}