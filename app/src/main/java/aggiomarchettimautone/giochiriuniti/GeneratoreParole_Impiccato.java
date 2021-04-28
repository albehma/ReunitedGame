package aggiomarchettimautone.giochiriuniti;

import java.util.Random;
/*
 * Questa classe è indispensabile al fine di ottenere ogni volta una parola (si spera)
 * diversa; il numero di parole a disposizione può aumentare in qualsiasi momento
 * semplicemente aggiungendo nell'array parole le nuova parole.
 * La selezione viene fatta utilizzando la classe Random; un oggetto di questo tipo
 * estrae un intero casuale e prende la parola relativa alla posizione uguale all'intero
 * estratto all'interno dell'array.
 */
public class GeneratoreParole_Impiccato {
    public static String generaParola()
    {
        String[] parole = {"pianoforte", "strumento", "smartphone", "veicolo", "sinusoidale", "tangente", "algoritmo", "motocicletta", "studente", "sportivo", "manufatturiera", "condizionatore", "ventilatore", "matlab", "scienze", "pokemon", "erbivoro", "appartamento", "salutare", "trasformata", "orgoglio", "annusare", "spiccare", "finestra"};
        Random selezioneParola = new Random();
        int casella_selezionata = selezioneParola.nextInt(parole.length);
        return parole[casella_selezionata];
    }
}