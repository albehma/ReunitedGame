package aggiomarchettimautone.giochiriuniti;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Risultato_Differenze extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultato__differenze);

        Bundle extras = getIntent().getExtras();
        int corretti = extras.getInt("p");
        int tot = extras.getInt("numImm");

        TextView riepilogo = (TextView)findViewById(R.id.riepilogo);
        riepilogo.setText("Il tuo punteggio e' di: "+corretti+" su "+tot);
        Button menu = (Button)findViewById(R.id.ritorna);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMenu = new Intent(Risultato_Differenze.this,Menu_Differenze.class);
                startActivity(openMenu);
            }
        });
    }
}
