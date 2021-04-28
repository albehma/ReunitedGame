package aggiomarchettimautone.giochiriuniti;
/*
Menu principale dell'applicazione, permette di scegliere il gioco che si vuole usare.
Questa Activity gestisce dei bottoni collocati nello schermo in maniera da coprirlo completamente
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Principale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__principale);

        //Assegno nome al pulsante Labirinto e gestisco azione
        Button labirinto = (Button)findViewById(R.id.labirinto);
        labirinto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLabirinto = new Intent(Menu_Principale.this,Menu_Labirinto.class);
                //faccio partire l'activity corrispondente al pulsante premuto
                startActivity(openLabirinto);
            }
        });

        //Assegno nome al pulsante Trova le differenze e gestisco azione
        Button differenze = (Button)findViewById(R.id.differenze);
        differenze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openDifferenze = new Intent(Menu_Principale.this,Menu_Differenze.class);
                //faccio partire l'activity corrispondente al pulsante premuto
                startActivity(openDifferenze);
            }
        });

        //Assegno nome al pulsante Impiccato e gestisco azione
        Button impiccato = (Button)findViewById(R.id.impiccato);
        impiccato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openImpiccato = new Intent(Menu_Principale.this,Principale_Impiccato.class);
                //faccio partire l'activity corrispondente al pulsante premuto
                startActivity(openImpiccato);
            }
        });

        //Assegno nome al pulsante Tris e gestisco azione
        Button tris = (Button)findViewById(R.id.tris);
        tris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openTris = new Intent(Menu_Principale.this,Menu_Tris.class);
                //faccio partire l'activity corrispondente al pulsante premuto
                startActivity(openTris);
            }
        });
        //Assegno nome al pulsante Puzzle15 e gestisco azione
        Button puzzle = (Button)findViewById(R.id.puzzle);
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPuzzle = new Intent(Menu_Principale.this,Principale_Puzzle15.class);
                //faccio partire l'activity corrispondente al pulsante premuto
                startActivity(openPuzzle);
            }
        });
        //Assegno nome al pulsante Memory e gestisco azione
        Button memory = (Button)findViewById(R.id.memory);
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMemory = new Intent(Menu_Principale.this,Principale_Memory.class);
                //faccio partire l'activity corrispondente al pulsante premuto
                startActivity(openMemory);
            }
        });
    }
}
