package aggiomarchettimautone.giochiriuniti;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.*;

public class Gioco_Tris extends Activity {

    //variabili utili per lo sviluppo del codice
    TextView t;
    String p1;
    String p2;
    //definizione di 2 array per controllare le mosse del giocatore x e o
    int[] checkedp1 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[] checkedp2 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    //Definisco una gridview: è una visualizzazione a griglia, molto utile per definire il gioco del tris
    GridView gridView;
    //variabile statica affinché sia visibile a tutte le istanze della classe
    static int i;
    //Context utile al fine di poter utilizzare l'intent
    final Context context = this;
    //Definisco un array di Stringhe "vuote" per confrontare le varie caselle del gioco
    static final String[] values;
    static {
        values = new String[]{"", "", "", "", "", "", "", "", ""};
    }

    @SuppressLint("WrongConstant") //richiesto da AndroidStudio
    @Override //sovrascrivo il metodo onCreate
    protected void onCreate(Bundle savedInstanceState) {
        for (int k = 0; k < 9; ++k)
        {
            checkedp1[k] = -1;
            checkedp2[k] = -1;
        }
        //inizializzo i = 0
        i = 0;

        super.onCreate(savedInstanceState);
        //imposto la schermata di gioco
        setContentView(R.layout.grid_view__tris);

        //Riprendo le informazioni della schermata precedente, dove erano stati decisi i simboli x e o per i 2 giocatori
        Intent intent = getIntent();
        p1 = intent.getStringExtra("Player 1");
        p2 = intent.getStringExtra("Player 2");
        //verifico che i 2 giocatori non abbiano scelto lo stesso simbolo
        if(p1.equalsIgnoreCase(p2))
        {
            Toast.makeText(Gioco_Tris.this, "Entrambi i giocatori non possono usare lo stesso simboolo", 2000).show();
            finish();
        }
        //altrimenti il gioco può iniziare
        else {
            gridView = findViewById(R.id.gridview );
            /*
             * È possibile utilizzare questo adattatore per fornire views per AdapterView.
             * Ci permette di collegare la griglia con i txt che verranno inseriti su questa
             * I parametri che prende sono: <String> perché andremo ad inserire stringhe,
             * (attuale classe, layout dove metteremo le stringhe, e ciò che useremo per le stringhe
             * ovvero l'array symbol (vuoto) prima definito.
             */
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.game_view__tris, values);
            //setAdapter = imposta i dati dentro la view passata come parametro
            gridView.setAdapter(adapter);
            //definisco cosa succede ogni volta che si clicca su una posizione
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                /*
                 * Metodo onItemClick:
                 * Metodo di callBack da invocare quando un oggetto in quest AdapterView viene
                 * chiamato.
                 * Parametri:
                 * AdapterView: l'adapter view contenente l'item cliccato
                 * view: la view specifica dentro l'adapter
                 * position: int, è la posizione dell'oggetto cliccato nella view (cioè quella che
                 * tocco col dito proprio)
                 * id: long, sarebbe l'ID dell'oggetto cliccato
                 * */
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //definisco un flag int per tenere traccia delle posizioni che hanno o no un simbolo
                    int flag = 0;
                    //txt è l'id dato a grid_view
                    t = (TextView) view.findViewById(R.id.txt);
                    /*Lo faccio per 9 volte perché io prima preimposto questa cosa di far apparire
                     *il Toast, poi se ci clicco davvero compare.
                     */
                    for (int j = 0; j < 9; ++j) {
                        //cosa succede se clicco su una posizione che già ha un simbolo
                        if (checkedp1[position] == 1 || checkedp2[position] == 1) {
                            flag = 1;
                            Toast.makeText(context, "Posizione già occupata", 2000).show();
                            break;
                        }
                    }
                    //se invece la posizione è libera:
                    if (flag == 0) {
                        //in questo modo stabilisco i turni (i pari tocca ad P1, i dispari a P2)
                        if (i % 2 == 0) {
                            //Turni pari: tocca a p1 ed imposto il simbolo sulla posizione occupata uguale a quello di p1
                            t.setText(p1);
                            //flaggo la posizione scelta con il valore 1
                            checkedp1[position] = 1;

                        } else {
                            //se è un turno dispari tocca a p2 ed eseguo le stesse operazioni
                            t.setText(p2);
                            checkedp2[position] = 1;
                        }
                        //controllo se qualcuno ha vinto, il metodo check è definito sotto
                        check(checkedp1, checkedp2);
                        //passo al turno successivo
                        i = i + 1;

                    }
                }
            });
        }
    }
    //metodo che controlla se qualcuno ha vinto: per farlo valuto tutte le posizioni che devono essere occupate
    //dallo stesso simbolo secondo le regole del tris per entrambi i giocatori. Se qualcuno ha vinto parte la schermata
    //di vittoria attraverso gli Intent. Il metodo putExtra in questo caso serve a tenere memoria del giocatore vincitore.
    public void check(int p1[], int p2[]) {
        //variabili: winner per far apparire chi ha vinto
        //flag: valore intero che fa scattare l'intent nel caso di vittoria di uno dei 2 giocatori
        String winner = null;
        int flag = 0;
        if((p1[0]==1)&& (p1[4]==1) &&(p1[8]==1))
        {
            flag=1;
            winner="Player 1";
        }
        else if((p1[2]==1)&& (p1[4]==1) &&(p1[6]==1))
        {
            flag=1;
            winner="Player 1";
        }
        else if((p1[0]==1)&& (p1[3]==1) &&(p1[6]==1))
        {
            flag=1;
            winner="Player 1";
        }
        else if((p1[1]==1)&& (p1[4]==1) &&(p1[7]==1))
        {
            flag=1;
            winner="Player 1";
        }
        else if((p1[2]==1)&& (p1[5]==1) &&(p1[8]==1))
        {
            flag=1;
            winner="Player 1";
        }
        else if((p1[0]==1)&& (p1[1]==1) &&(p1[2]==1))
        {
            flag=1;
            winner="Player 1";
        }
        else if((p1[3]==1)&& (p1[4]==1) &&(p1[5]==1))
        {
            flag=1;
            winner="Player 1";
        }
        else if((p1[6]==1)&& (p1[7]==1) &&(p1[8]==1))
        {
            flag=1;
            winner="Player 1";
        }

        //player2
        if((p2[0]==1)&& (p2[4]==1) &&(p2[8]==1))
        {
            flag=1;
            winner="Player 2";
        }
        else if((p2[2]==1)&& (p2[4]==1) &&(p2[6]==1))
        {
            flag=1;
            winner="Player 2";
        }
        else if((p2[0]==1)&& (p2[3]==1) &&(p2[6]==1))
        {
            flag=1;
            winner="Player 2";
        }
        else if((p2[1]==1)&& (p2[4]==1) &&(p2[7]==1))
        {
            flag=1;
            winner="Player 2";
        }
        else if((p2[2]==1)&& (p2[5]==1) &&(p2[8]==1))
        {
            flag=1;
            winner="Player 2";
        }
        else if((p2[0]==1)&& (p2[1]==1) &&(p2[2]==1))
        {
            flag=1;
            winner="Player 2";
        }
        else if((p2[3]==1)&& (p2[4]==1) &&(p2[5]==1))
        {
            flag=1;
            winner="Player 2";
        }
        else if((p2[6]==1)&& (p2[7]==1) &&(p2[8]==1))
        {
            flag=1;
            winner="Player 2";
        }

        if(flag==1)
        {
            Intent vic = new Intent(context, Vittoria_Tris.class);
            vic.putExtra("vincitore", winner);
            startActivity(vic);
        }
        else if (i==8) //vuol dire che sono finiti i turni e nessuno ha vinto, quindi parte la schermata di pareggio.
        {
            Intent par = new Intent(context, Pareggio_Tris.class);
            startActivity(par);
        }
    }
}