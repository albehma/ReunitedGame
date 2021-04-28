package aggiomarchettimautone.giochiriuniti;

/**
Quest'activity gestisce la selezione del livello del labirinto e il ritorno al menu principale
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Labirinto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__labirinto);
        //Pulsante livello 1 : FACILE
        Button level1 = (Button)findViewById(R.id.level1);
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLevel1 = new Intent(Menu_Labirinto.this,Livello1_Labirinto.class);
                //mando il programma all'activity corrispondente al primo livello
                startActivity(openLevel1);
            }
        });

        //Pulsante livello 2 : MEDIO
        Button level2 = (Button)findViewById(R.id.level2);
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLevel2 = new Intent(Menu_Labirinto.this,Livello2_Labirinto.class);
                //mando il programma all'activity corrispondente al secondo livello
                startActivity(openLevel2);
            }
        });

        //Pulsante livello 3 : DIFFICILE
        Button level3 = (Button)findViewById(R.id.level3);
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLevel3 = new Intent(Menu_Labirinto.this,Livello3_Labirinto.class);
                //mando il programma all'activity corrispondente al terzo livello
                startActivity(openLevel3);
            }
        });

        //Ritorno al menu principale
        Button menu = (Button)findViewById(R.id.menu_principale);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToMenu = new Intent(Menu_Labirinto.this,Menu_Principale.class);
                //ritorno al menu principale dell'applicazione
                startActivity(returnToMenu);
            }
        });
    }
}
