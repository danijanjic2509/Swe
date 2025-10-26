package de.hsd.medien.se.hsdchess.domain;

/**
 * Diese Klasse implementiert die Schachfigur Turm.
 * Der Turm darf sich nur horizontal oder vertikal bewegen.
 */
public class Turm {

    private final boolean istWeiß;
    private String position;
    private boolean geschlagen;
    private int zugAnzahl;

    public Turm(boolean istWeiß, String position) {
        if (position == null || !isValidPosition(position)) {
            throw new IllegalArgumentException("Ungültige Startposition: " + position);
        }
        this.istWeiß = istWeiß;
        this.position = normalizePosition(position);
        this.geschlagen = false;
        this.zugAnzahl = 0;
    }

    /**
     * Bewegt den Turm auf eine neue Position, falls der Zug regelkonform ist.
     *
     * @param neuePosition Zielposition (z. B. "H1")
     * @throws IllegalArgumentException bei ungültiger Positionsnotation, identischer Position,
     *                                  wenn die Figur geschlagen ist oder der Zug für einen Turm unzulässig ist
     */
    public void zieheNach(String neuePosition) {
        if (geschlagen) {
            throw new IllegalArgumentException("Turm wurde geschlagen und kann sich nicht bewegen.");
        }
        if (neuePosition == null || !isValidPosition(neuePosition)) {
            throw new IllegalArgumentException("Ungültige Positionsangabe: " + neuePosition);
        }

        String ziel = normalizePosition(neuePosition);

        if (ziel.equals(this.position)) {
            throw new IllegalArgumentException("Zielposition ist identisch zur Startposition: " + ziel);
        }

        char startSpalte = this.position.charAt(0);
        char zielSpalte = ziel.charAt(0);
        int startReihe = this.position.charAt(1) - '0';
        int zielReihe = ziel.charAt(1) - '0';

        boolean gleicheSpalte = startSpalte == zielSpalte;
        boolean gleicheReihe = startReihe == zielReihe;

        // Turm darf sich nur horizontal oder vertikal bewegen (exakt eine Richtung ändern)
        if (!(gleicheSpalte ^ gleicheReihe)) {
            throw new IllegalArgumentException("Ungültiger Zug für Turm: " + position + " → " + ziel);
        }

        this.position = ziel;
        this.zugAnzahl++;
    }

    public void schlage() {
        this.geschlagen = true;
    }

    public boolean istWeiß() {
        return istWeiß;
    }

    public String getPosition() {
        return position;
    }

    public boolean istGeschlagen() {
        return geschlagen;
    }

    public int getZugAnzahl() {
        return zugAnzahl;
    }

    // Hilfsfunktionen

    /** Prüft, ob eine Positionsangabe dem Muster A1..H8 entspricht. */
    private static boolean isValidPosition(String pos) {
        if (pos == null || pos.length() != 2) return false;
        char col = Character.toUpperCase(pos.charAt(0));
        char row = pos.charAt(1);
        return (col >= 'A' && col <= 'H') && (row >= '1' && row <= '8');
    }

    /** Normalisiert die Positionsdarstellung (z. B. 'a1' → 'A1'). */
    private static String normalizePosition(String pos) {
        return Character.toUpperCase(pos.charAt(0)) + String.valueOf(pos.charAt(1));
    }
}