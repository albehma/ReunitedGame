package aggiomarchettimautone.giochiriuniti;

/*
Questa classe gestisce il labirinto, specificando le coordinate di ogni casella nello schermo
e definendo se quest'ultima è attraveersabile o meno.
 */

public class Labirinto {
    //le coordinate x e y indicano lo spostamento nella casella selezionata
    private final int X;
    private final int Y;
    //la variabile free indica se la casella è attraversabile o meno
    private boolean free;
    public Labirinto(int y , int x, boolean val){
        X=x;
        Y=y;
        free = val;
    }

    //questi metodi restituiscono l'informazione richiesta
    public int giveX(){
        return X;
    }

    public int giveY(){
        return Y;
    }

    public boolean isFree(){
        return free;
    }
}
