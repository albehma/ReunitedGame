package aggiomarchettimautone.giochiriuniti;
/**
Gestisco, con quest'activity, la selezione del livello di difficolta del gioco
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Differenze extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__differenze);

        //Assegno nome al pulsante level1 e gestisco azione
        Button livelloFacile = (Button)findViewById(R.id.easy);
        livelloFacile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLevel1 = new Intent(Menu_Differenze.this,Livello_Facile_Differenze.class);
                startActivity(openLevel1);
            }
        });

        //Gestissco pressione tasto livello difficile
        Button livelloDifficile = (Button)findViewById(R.id.hard);
        livelloDifficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLevel2 = new Intent(Menu_Differenze.this,Livello_Difficile_Differenze.class);
                startActivity(openLevel2);
            }
        });

        //Ritorno al menu principale
        Button menu = (Button)findViewById(R.id.menu_principale);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToMenu = new Intent(Menu_Differenze.this,Menu_Principale.class);
                startActivity(returnToMenu);
            }
        });
    }

}

