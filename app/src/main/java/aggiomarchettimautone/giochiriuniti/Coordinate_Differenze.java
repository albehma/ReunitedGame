package aggiomarchettimautone.giochiriuniti;

/**
 * Tiene in considerazione delle coordinate in pixel della posizione del pulsante della risposta
 * corretta
 */

public class Coordinate_Differenze {
    //coordinate per la posizione dell'errore sull'immagine
    private int x;
    private int y;
    //nome dell'immagine senza soluzione e con la soluzione
    private int name,nameC;

    //nameC sta ad indicare l'immagine con la correzione cerchiata
    public Coordinate_Differenze(int x, int y,int name, int nameC)
    {
        this.x=x;
        this.y=y;
        this.name = name;
        this.nameC = nameC;
    }
    //questi metodi ritornano le informazioni richieste
    public int giveX()
    {
        return x;
    }

    public int giveY()
    {
        return y;
    }
    public int giveName()
    {
        return name;
    }
    public int giveNameC()
    {
        return nameC;
    }
}
