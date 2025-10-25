package de.hsd.medien.se.hsdchess.domain;

public class Laeufer {

    private String farbe;
    private String position;
    private boolean imSpiel;
    private int zugZaehler;

    // Konstruktor
    public Laeufer(String farbe, String position)
    {
        this.farbe = farbe;
        this.position = position;
        this.imSpiel = true;
        this.zugZaehler = 0;
    }

    // Getter (für Tests nützlich)
    public String getPosition()
    {
        return position;
    }

    public boolean istImSpiel()
    {
        return imSpiel;
    }

    
    public void zieheNach(String zielPosition) {
    // Aktuelle Koordinaten der Figur
    char startSpalte = position.charAt(0);
    int startReihe = position.charAt(1) - '0';

    // Zielkoordinaten
    char zielSpalte = zielPosition.charAt(0);
    int zielReihe = zielPosition.charAt(1) - '0';

    // Berechne horizontale und vertikale Distanz
    int horizontalerAbstand = Math.abs(zielSpalte - startSpalte);
    int vertikalerAbstand = Math.abs(zielReihe - startReihe);

    // Prüfen, ob der Läufer diagonal zieht
    if (horizontalerAbstand != vertikalerAbstand) {
        throw new IllegalArgumentException("Ungültiger Zug: Der Läufer darf sich nur diagonal bewegen!");
    }

    // Überprüfen, ob das Ziel innerhalb des Schachbretts liegt (a–h, 1–8)
    if (zielSpalte < 'a' || zielSpalte > 'h' || zielReihe < 1 || zielReihe > 8) {
        throw new IllegalArgumentException("Ungültiger Zug: Zielposition liegt außerhalb des Spielfelds!");
    }

    // Wenn alles gültig ist, neue Position setzen und Zugzähler erhöhen
    this.position = zielPosition;
    this.zugZaehler++;
}


public void schlagen()
{
    this.imSpiel = false;
    this.position = null;       // Figur steht nicht mehr auf dem Brett
}

}
