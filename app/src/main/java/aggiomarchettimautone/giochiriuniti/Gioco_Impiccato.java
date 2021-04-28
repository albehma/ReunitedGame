package aggiomarchettimautone.giochiriuniti;

public class Gioco_Impiccato {

    //definisco varibili statiche: voglio che siano visibili a tutte le istanze della classe
    //per poter essere aggiornata di volta in volta; le varibili sono di tipo Punteggio e ParolaDaIndovinare
    static Punteggio_Impiccato score;
    static ParolaDaIndovinare_Impiccato wordToGuess; //variabile della parola che verrà scelta casualmente da GeneratoreParole

    Gioco_Impiccato(){
        score = new Punteggio_Impiccato(); //definisco un nuovo punteggio = 0
        wordToGuess = new ParolaDaIndovinare_Impiccato(GeneratoreParole_Impiccato.generaParola()); //estraggo una parola
    }

    int get_score(){
        return score.getPunteggio();
    } //definisco il metodo della classe per visualizzare il punteggio, getPunteggio() definito in Punteggio

    String visualizza_parola_completa(){
        return wordToGuess.return_parola();
    } //come suggerisce il nome, serve a visualizzare la parola completa, return_parola definito in ParolaDaIndovinare

    String get_display_word(){
        return wordToGuess.mostra_parola();
    } //meotodo per visualizzare lo stato corrente del gioco, definito in ParolaDaIndovinare

    boolean parola_completata(){
        return wordToGuess.parolaCompletata();
    } //meotdo per capire se il gioco è finito: anche questo è definito in ParolaDaIndovinare

    /*inserimento_lettera: questo metodo usa cinque metodi della classe ParolaDaIndovinare. Ovvero con i primi due
     *controlla se la lettera si trova nella lettera e se non è stata già selezionata, se queste due condizioni
     * sono verificate, allora usa il terzo metodo per inseire la lettera nella/e posizione/e giusta/e.
     * Successivamente aggiorna il punteggio con +10 punti.
     * Infine l'ultimo metodo si usa quando almeno una delle due condizioni prima specificate non sono
     * soddisfatte: in tal caso diminuisce il punteggio del giocatore di 10 punti.
     */
    boolean inserimento_lettera(String lettera_scelta) {
        if (wordToGuess.letteraSiTrovaNellaParola(lettera_scelta) && !wordToGuess.letteraGiaSelezionata(lettera_scelta)) {
            wordToGuess.insert_letter(lettera_scelta);
            score.risposta_esatta();
            return true;
        } else {
            if(wordToGuess.letteraSiTrovaNellaParola(lettera_scelta)){
                return true;
            }
            else {
                score.risposta_sbagliata();
                return false; }
        }
    }
}
