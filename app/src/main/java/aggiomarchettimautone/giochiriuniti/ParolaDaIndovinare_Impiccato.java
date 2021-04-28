package aggiomarchettimautone.giochiriuniti;

import java.util.Random;

public class ParolaDaIndovinare_Impiccato {
    static String parola = ""; //inizializzo la parola selezionata, è quella completa senza trattini.
    String cursore = ""; //inizializzo la stringa vuota, sulla quale verranno inserite le lettere.
    //La variabile cursore verrà quindi aggiornata di volta in volta man mano che si indovinano le lettere.

    ParolaDaIndovinare_Impiccato(String word) //costruttore: prende come parametro la parola selezionata da GeneratoreParole
    {
        parola = word; //inizializzo la parola con word che viene generata dalla classe GeneratoreParole
        for (int i=0;i<parola.length();i++) //per tutte le posizioni della parola:
            cursore = cursore + "_"; //cursore alla fine sarà = _ _ _ ..."
        Random r = new Random(); //creo un nuovo oggetto Random
        /*
         * Adesso faccio in modo che la parola all'inizio del gioco contenga già delle lettere
         * di suggerimento: in questo modo viene diminuita la difficoltà del gioco, ma dato
         * che ho impostato 0 come punteggio iniziale, è tutto equilibrato.
         */
        int i1 = r.nextInt(parola.length()-1); //scegli un int a caso tra tutte le posizioni della parola
        insert_letter(parola.substring(i1,i1+1)); //la inserisce
    }

    void insert_letter(String letter){ //prende come parametro una sola lettera
        for (int i = 0; i < parola.length(); i++) //per tutta la lunghezza della parola scelta:
        {
            char c = parola.toLowerCase().charAt(i); //seleziona tutti i caratteri della parola
            if (c == letter.toLowerCase().toCharArray()[0]) //se c corrisponde all'unica lettera presa come parametro ([0])
            {
                cursore = cursore.substring(0,i) + c + cursore.substring(i+1,cursore.length()); //la inserisce al posto giusto
            }
        }
    }
    //metodo di controllo che uso nella classe Gioco al fine di poter inserire o no la lettera
    boolean letteraSiTrovaNellaParola(String lettera) //metodo per controllare se la lettera scelta è presente nella parola;
    {                                                 //successivamente dichiarerò che se è presente, allora potrà essere
        char c;                                       //essere aggiunta nel cursore.
        c = lettera.toLowerCase().toCharArray()[0]; //trasformo la lettera in char

        for (int i=0;i < parola.length(); i++)
        {
            char d = parola.toLowerCase().charAt(i); //analizzo tutti i caratteri presenti nella parola
            if (d == c) //se c'è corrispondenza
            {
                return true; //allora è vero che la lettera è già presente;
            }
        }
        return false; //altrimenti non è già presente
    }
    //metodo di controllo che uso nella classe Gioco al fine di poter inserire o no la lettera
    boolean letteraGiaSelezionata(String letter) //metodo di controllo; se la lettera è stata già scelta in precedenza
    {                                            //restituisce true, altrimenti false.
        char c;
        c = letter.toLowerCase().toCharArray()[0]; //come prima: trasformo la lettera in un charArray

        for (int i=0;i < cursore.length(); i++) //e per tutta la lunghezza della parola verifico che non sia già stata selezionata
        {
            char w = cursore.toLowerCase().charAt(i);
            if (w == c)
            {
                return true;
            }
        }
        return false;
    }
    String mostra_parola() //questo metodo serve per visualizzare lo stato corrente del gioco:
    {
        //stringa temporanea
        String temp = "";
        //per tutta la lunghezza della stringa cursore (che sarebbe quella sulla quale sto giocando)
        for (int i = 0; i < cursore.length(); i++)
        {
            temp = temp + " " + cursore.charAt(i) + " "; //restituisci il valore di tutte le posizioni
        }
        temp = temp.substring(1,temp.length()-1);
        return temp;
    }
    String return_parola()
    {
        return parola;
    } //restituisce la parola intera

    boolean parolaCompletata(){ //metodo finale per valutare se l'utente ha vinto.
        return cursore.equals(parola);
    } //verifica che la parola corrisponda con cursore, ovvero verifica se le lettere sono state indovinate tutte.
}
