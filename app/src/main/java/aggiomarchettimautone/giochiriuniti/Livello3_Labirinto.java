package aggiomarchettimautone.giochiriuniti;
/**
Gestisco il gioco del terzo livello del labirinto
Utilizzo una classe di supporto (LABIRINTO) che mi gestisce le proprietà di ogni casella così da
poter salvare al suo interno le coordinate dello schermo per lo spostamento e gestirlo in maniera
più facile. Gestisco quindi i pulsanti di movimento e se posso o meno eseguire un determinato
spostamento nel mio labirinto
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Livello3_Labirinto extends AppCompatActivity {

    int r ;  //imposto variabili di riga e colonna
    int c ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creo una variabile matrice di tipo Labirinto e inserisco al suo interno tutte le coordinate
        // della posizione della casella e il booleano per definire la possibilità di passaggio
        final Labirinto lab1[][] = new Labirinto[5][4];
        lab1[0][0] = new Labirinto(90,120,true);
        lab1[1][0] = new Labirinto(267,120,true);
        lab1[2][0] = new Labirinto(445,120,false);
        lab1[3][0] = new Labirinto(622,120,true);
        lab1[4][0] = new Labirinto(800,120,true);
        lab1[0][1] = new Labirinto(90,360,false);
        lab1[1][1] = new Labirinto(267,360,true);
        lab1[2][1] = new Labirinto(445,360,true);
        lab1[3][1] = new Labirinto(622,360,true);
        lab1[4][1] = new Labirinto(800,360,false);
        lab1[0][2] = new Labirinto(90,600,false);
        lab1[1][2] = new Labirinto(267,600,true);
        lab1[2][2] = new Labirinto(445,600,false);
        lab1[3][2] = new Labirinto(622,600,true);
        lab1[4][2] = new Labirinto(800,600,true);
        lab1[0][3] = new Labirinto(90,840,true);
        lab1[1][3] = new Labirinto(267,840,true);
        lab1[2][3] = new Labirinto(445,840,false);
        lab1[3][3] = new Labirinto(622,840,false);
        lab1[4][3] = new Labirinto(800,840,true);
        /*
        Utilizzo una variabile di tipo Gioca_Labirinto per gestire il movimento e capire se è
        possibile o meno una determinata mossa. Passo la variabile lab1 di tipo Labirinto come parametro
         */
        final Gioca_Labirinto play = new Gioca_Labirinto(lab1);
        //setto il giocatore al punto di partenza
        r=0;
        c=0;
        setContentView(R.layout.activity_livello3__labirinto);
        //Gestisco azioni sui pulsanti

        // in caso di movimento verso l'alto controllo se questo è possibile usando la posizione corrente
        // e il metodo up della classe Gioca_Labirinto
        Button up = (Button)findViewById(R.id.up);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se possibile la mossa, setto l'immagine del personaggio alla posizione della mossa eseguita
                if(play.up(r,c)) {
                    ImageView man = (ImageView) findViewById(R.id.Image);
                    man.setX(lab1[r-1][c].giveX()); //prendo le variabili delle coordinate dalla matrice creata in precedenza alle coordinate della mossa scelta
                    man.setY(lab1[r-1][c].giveY());
                    r--;
                }
                //controllo se sono arrivato alle coordinate segnate come punto di arrivo, annunciandone in caso affermativo la vittoria
                if (r == 4 && c == 3)
                {
                    //Messaggio di vincita
                    Toast toast = Toast.makeText(getApplicationContext(),"HAI VINTO!!!",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        // in caso di movimento verso il basso, controllo se questo è possibile usando la posizione corrente
        // e il metodo down della classe Gioca_Labirinto
        Button down = (Button)findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se possibile la mossa, setto l'immagine del personaggio alla posizione della mossa eseguita
                if(play.down(r,c)) {
                    ImageView man = (ImageView) findViewById(R.id.Image);
                    man.setX(lab1[r+1][c].giveX()); //prendo le variabili delle coordinate dalla matrice creata in precedenza alle coordinate della mossa scelta
                    man.setY(lab1[r+1][c].giveY());
                    r++;
                }
                //controllo se sono arrivato alle coordinate segnate come punto di arrivo, annunciandone in caso affermativo la vittoria
                if (r == 4 && c == 3)
                {
                    //Messaggio di vincita
                    Toast toast = Toast.makeText(getApplicationContext(),"HAI VINTO!!!",Toast.LENGTH_SHORT);
                    toast.show();                }
            }
        });
        // in caso di movimento verso sinistra, controllo se questo è possibile usando la posizione corrente
        // e il metodo left della classe Gioca_Labirinto
        Button left = (Button)findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se possibile la mossa, setto l'immagine del personaggio alla posizione della mossa eseguita
                if(play.left(r,c)) {
                    ImageView man = (ImageView) findViewById(R.id.Image);
                    man.setX(lab1[r][c-1].giveX()); //prendo le variabili delle coordinate dalla matrice creata in precedenza alle coordinate della mossa scelta
                    man.setY(lab1[r][c-1].giveY());
                    c--;
                }
                //controllo se sono arrivato alle coordinate segnate come punto di arrivo, annunciandone in caso affermativo la vittoria
                if (r == 4 && c == 3)
                {
                    //Messaggio di vincita
                    Toast toast = Toast.makeText(getApplicationContext(),"HAI VINTO!!!",Toast.LENGTH_SHORT);
                    toast.show();                }
            }
        });
        // in caso di movimento verso destra, controllo se questo è possibile usando la posizione corrente
        // e il metodo right della classe Gioca_Labirinto
        Button right = (Button)findViewById(R.id.right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se possibile la mossa, setto l'immagine del personaggio alla posizione della mossa eseguita
                if(play.right(r,c)) {
                    ImageView man = (ImageView) findViewById(R.id.Image);
                    man.setX(lab1[r][c+1].giveX()); //prendo le variabili delle coordinate dalla matrice creata in precedenza alle coordinate della mossa scelta
                    man.setY(lab1[r][c+1].giveY());
                    c++;
                }
                //controllo se sono arrivato alle coordinate segnate come punto di arrivo, annunciandone in caso affermativo la vittoria
                if (r == 4 && c == 3)
                {
                    //Messaggio di vincita
                    Toast toast = Toast.makeText(getApplicationContext(),"HAI VINTO!!!",Toast.LENGTH_SHORT);
                    toast.show();                }
            }
        });

    }

}
