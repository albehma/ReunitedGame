package aggiomarchettimautone.giochiriuniti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;

/**
 * Classe principale
 */
public class Principale_Puzzle15 extends AppCompatActivity {
    private static String[] nextTurnArray; //definisco un array di tipo String globale
    private static Integer turnCount = 0; //definisco la variabile  globale che utilizzo per contare il numero di turni

    /**
     * Ottieni il metodo per NextTurnArray.
     *
     * @return Restituzione dell'array come campo globale.
     */
    public static String[] getNextTurnArray() {
        return nextTurnArray;
    }

    /**
     * Imposto il metodo per NextTurnArray.
     *
     * @param nextTurnArray Imposto il valore per l'array come campo globale.
     */
    public static void setNextTurnArray(String[] nextTurnArray) {
        Principale_Puzzle15.nextTurnArray = nextTurnArray;
    }

    /**
     * Metodo principale.
     *
     * @param savedInstanceState Paramentro parametro.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale__puzzle15);

        final String[] randomArray; //Definisco il mio array casuale
        TextView CountField = findViewById(R.id.countShow); //Associo a una variabile di tipo TextView il countShow definito in activity_main
        Button reloadBtn = findViewById(R.id.reloadBtn); //Associo a una variabile di tipo Button il reloadBtn definito in activity_main

        //Costruisco il mio array di confronto
        String[] startArray = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "0"};


        if (getNextTurnArray() == null) {

            // Creo e ritorno il mio array randomizzato
            randomArray = RandomizeArray(startArray);

        } else {

            randomArray = getNextTurnArray(); //L'array ottenuto è l'array randomizzato
            turnCount += 1; //Incremento il numero di turni
            CountField.setText("Count of turns: " + String.valueOf(turnCount)); //Stampo il numero di turni

            //Controllo se l'array randomizzato è uguale all'array di confronto
            if (Arrays.equals(startArray, randomArray)) {
                CountField.setText("You win!!! You make " + String.valueOf(turnCount) + " turns"); //Nel caso che entrambi coincidano stampo "You win + numero di turni"
                reloadBtn.setVisibility(View.VISIBLE); //Rendo visibile il pulsante di reload
                reloadBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish(); //Se viene premuto il pulsante di reload termino l'activity
                        turnCount = -1; //Resetto il contatore dei turni
                        RandomizeArray(randomArray); //Randomizzo il mio array di nuovo
                        startActivity(getIntent());//Riinizio l'activity
                    }

                });
            }
        }

        // Generazione del campo di gioco e restituzione dell'ID del pulsante zero.
        String zeroBtnID = GameFieldGenerator(randomArray);

        // Impostazione dei 4 tasti selezionabili e risistemazione dell'array dopo il tasto premuto.
        SetPressedKeys(zeroBtnID, randomArray);


    }

    /**
     * Crea il nuovo array randomizzato per inizializzare il gioco.
     *
     * @param startArray Array di partenza.
     * @return Ritorna il mio array randomizzato.
     */
    public String[] RandomizeArray(String[] startArray) {

        String[] randomArray = startArray;
        Collections.shuffle(Arrays.asList(randomArray));

        return randomArray;
    }

    /**
     * Genera il nuovo campo di gioco con l'array randomizzato.
     *
     * @param randomArray Array di elementi randomizzati per la creazione di campi di gioco.
     * @return Ritorna l'ID del buttone 0.
     */
    public String GameFieldGenerator(String[] randomArray) {

        Integer numberButton = 0;
        Integer firstIndex = 1;
        String zeroBtnID = "0";

        //Attraverso un doppio ciclo for inizializzo il mio campo di gioco di dimensione 4x4
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; ) {
                Integer secondIndex = j + 1;

                String buttonID = "c" + (String.valueOf(firstIndex)) + "x" + (String.valueOf(secondIndex));
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                Button btnID = findViewById(resID);

                btnID.setText(randomArray[numberButton]);

                if (Integer.valueOf(randomArray[numberButton]) == 0) {
                    btnID.setVisibility(View.INVISIBLE);//Rendo invisibile il bottone con numero 0
                    zeroBtnID = buttonID;//Salvo l'id del bottone 0
                }
                numberButton++;
                j++;
            }
            firstIndex++;
        }
        return zeroBtnID;
    }

    /**
     * Impostazione dei pulsanti.
     *
     * @param zeroBtnID   ID per zeroBtn
     * @param randomArray Array per impostare i bottoni cliccabili.
     */
    public void SetPressedKeys(String zeroBtnID, final String[] randomArray) {

        String aroundButtonH1 = "0";
        String aroundButtonW1 = "0";
        String aroundButtonH2 = "0";
        String aroundButtonW2 = "0";

        //Controllo la posizione del zeroBtn e verifico i bottoni circostanti
        if (!zeroBtnID.equals("0")) {
            String firstIndex = String.valueOf(zeroBtnID.charAt(1));
            String secondIndex = String.valueOf(zeroBtnID.charAt(3));

            Integer aroundCellH1 = Integer.valueOf(secondIndex) - 1;
            Integer aroundCellH2 = Integer.valueOf(secondIndex) + 1;
            Integer aroundCellW1 = Integer.valueOf(firstIndex) - 1;
            Integer aroundCellW2 = Integer.valueOf(firstIndex) + 1;

            if (aroundCellH1 < 5 && aroundCellH1 > 0) {
                aroundButtonH1 = "c" + firstIndex + "x" + aroundCellH1;
            }
            if (aroundCellH2 < 5 && aroundCellH2 > 0) {
                aroundButtonH2 = "c" + firstIndex + "x" + aroundCellH2;
            }
            if (aroundCellW1 < 5 && aroundCellW1 > 0) {
                aroundButtonW1 = "c" + aroundCellW1 + "x" + secondIndex;
            }
            if (aroundCellW2 < 5 && aroundCellW2 > 0) {
                aroundButtonW2 = "c" + aroundCellW2 + "x" + secondIndex;
            }
        }

        final String aroundButtons[] = new String[]{aroundButtonW1, aroundButtonH1, aroundButtonW2, aroundButtonH2};

        //Cambio posizione al bottone premuto.
        for (int n = 0; n < aroundButtons.length; ) {

            Integer stringID = getResources().getIdentifier(aroundButtons[n], "id", getPackageName());
            Button ButtonNumID = findViewById(stringID);

            if (!aroundButtons[n].equals("0")) {

                final int keyIndex = n;

                ButtonNumID.setOnClickListener(new View.OnClickListener() {

                    /**
                     * Override onClick method for ClickListener
                     * @param v View v
                     */
                    @Override
                    public void onClick(View v) {

                        ChangePosition(aroundButtons[keyIndex], randomArray);
                    }
                });
            }
            n++;
        }
    }

    /**
     * Metodo per cambiare posizione dei bottoni.
     *
     * @param pressedKeyName Nome del bottone premuto.
     * @param randomArray    Array principale per la modifica, quando è stato premuto un tasto qualsiasi.
     */
    public void ChangePosition(String pressedKeyName, String[] randomArray) {

        String valuePressedKey;
        Integer zeroKeyPos = null;
        Integer pressedKeyPos = null;

        Integer pressedID = getResources().getIdentifier(pressedKeyName, "id", getPackageName());
        Button pressedBtn = findViewById(pressedID);

        valuePressedKey = String.valueOf(pressedBtn.getText());//Mi salvo il valore del bottone premuto

        //Tramite il ciclo for e relative condizioni if ricompongo il nuovo campo di gioco con le posizioni cambiate
        Integer posNum = 0;
        for (int n = 0; n < randomArray.length; n++) {
            if (randomArray[n].equals(valuePressedKey)) {
                pressedKeyPos = posNum;
            }
            if (randomArray[n].equals("0")) {
                zeroKeyPos = posNum;
            }
            posNum++;
        }

        if (pressedKeyPos != null && zeroKeyPos != null) {
            String temp = randomArray[pressedKeyPos];
            randomArray[pressedKeyPos] = randomArray[zeroKeyPos];
            randomArray[zeroKeyPos] = temp;
        }

        Principale_Puzzle15.setNextTurnArray(randomArray);
        recreate();
    }
}