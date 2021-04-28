package aggiomarchettimautone.giochiriuniti;

/*
 * Classe Punteggio: è una classe che definisce in che modo viene assegnato il punteggio
 * in base a come viene data la risposta; viene impostato di defaul = 0, per ogni
 * risposta esatta vengono assegnati 10 punti, per ogni risposta sbagliata ne tolgo 10.
 */
public class Punteggio_Impiccato {
    static int valore;

    public Punteggio_Impiccato()
    {
        valore = 0;
    }
    public void risposta_esatta()
    {
        valore = valore + 10;
    }
    public void risposta_sbagliata()
    {
        valore = valore - 10;
    }
    public int getPunteggio()
    {
        return valore;
    }
}
