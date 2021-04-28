package aggiomarchettimautone.giochiriuniti;

/**
 * classe per gestione dell'algoritmo di controllo delle caselle e spostamento
 * una volta indicata la matrice, vengono richiamati i metodi in base al pulsante premuto
 * e in base alla posizione attuale, anch'eesa passata per parametro, questa classe restituisce
 * un valore true in caso di mossa valida o false in caso di mossa non valida.
 */

public class Gioca_Labirinto {
    private Labirinto map[][] = new Labirinto[5][4];
    public Gioca_Labirinto(Labirinto lab[][]){
        this.map = lab ;
    }

    //metodo per movimento verso sinistra, restituisco falso se sono in una delle caselle all'estrema
    // sinistra o nel caso che la casella alla nostra sinistra sia non attraversabile

    public boolean left(int r, int c){
        if (r > 4 || c >3 )
            return false;
        else if (c == 0 )
            return false;
        else
            return map[r][c-1].isFree();
    }
    //metodo per movimento verso destra, restituisco falso se sono in una delle caselle all'estrema
    // destra o nel caso che la casella alla nostra destra sia non attraversabile

    public boolean right(int r, int c){
        if (r > 4 || c >3 )
            return false;
        else if (c == 3 )
            return false;
        else
            return map[r][c+1].isFree();
    }
    //metodo per movimento verso su, restituisco falso se sono in una delle caselle di riga = 0
    //  o nel caso che la casella sopra di noi sia non attraversabile

    public boolean up(int r, int c){
        if (r > 4 || c >3 )
            return false;
        else if (r == 0 )
            return false;
        else
            return map[r-1][c].isFree();
    }
    //metodo per movimento verso giu, restituisco falso se sono in una delle caselle di riga finale
    //  o nel caso che la casella sotto di noi sia non attraversabile

    public boolean down(int r, int c){
        if (r > 4 || c >3 )
            return false;
        else if (r == 4 )
            return false;
        else
            return map[r+1][c].isFree();
    }

}
