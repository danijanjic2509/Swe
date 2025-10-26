package de.hsd.medien.se.hsdchess.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für {@link Turm}.
 * <p>
 * Diese Klasse enthält 5 Testfälle, die das Verhalten der Turm-Klasse prüfen.
 * Entwickelt nach dem TDD-Ansatz (RED → GREEN → REFACTOR).
 */
class TurmTest {

    /**
     * Testfall 1:
     * Prüft, ob Konstruktor Farbe, Position und Status korrekt setzt.
     */
    @Test
    void TurmKonstruktorSollteFarbePositionUndZustandRichtigSetzen() {
        Turm turm = new Turm(true, "A1");

        assertTrue(turm.istWeiß(), "Farbe sollte weiß sein");
        assertEquals("A1", turm.getPosition(), "Position sollte A1 sein");
        assertFalse(turm.istGeschlagen(), "Turm sollte nicht geschlagen sein");
        assertEquals(0, turm.getZugAnzahl(), "Zuganzahl sollte 0 sein");
    }

    /**
     * Testfall 2:
     * Prüft, ob der Turm bei einem regelkonformen horizontalen Zug
     * seine Position korrekt ändert und die Zuganzahl erhöht.
     */
    @Test
    void TurmSollteBeiHorizontalemZugPositionAendernUndZugAnzahlErhoehen() {
        Turm turm = new Turm(true, "A1");
        turm.zieheNach("H1");

        assertEquals("H1", turm.getPosition(), "Position sollte nach H1 gewechselt sein");
        assertEquals(1, turm.getZugAnzahl(), "Zuganzahl sollte 1 betragen");
    }

    /**
     * Testfall 3:
     * Prüft, ob der Turm bei einem diagonalen Zug eine Exception wirft.
     */
    @Test
    void TurmSollteBeiDiagonalemZugEineExceptionWerfen() {
        Turm turm = new Turm(true, "A1");

        assertThrows(IllegalArgumentException.class,
                () -> turm.zieheNach("B2"),
                "Diagonaler Zug sollte nicht erlaubt sein");
    }

    /**
     * Testfall 4:
     * Prüft, ob ein geschlagener Turm sich nicht mehr bewegen kann.
     */
    @Test
    void TurmSollteSichNichtBewegenWennErGeschlagenIst() {
        Turm turm = new Turm(true, "D2");
        turm.schlage();

        assertTrue(turm.istGeschlagen(), "Turm sollte als geschlagen markiert sein");
        assertThrows(IllegalArgumentException.class,
                () -> turm.zieheNach("D5"),
                "Geschlagener Turm darf sich nicht bewegen");
    }

    /**
     * Testfall 5:
     * Prüft, ob ungültige Positionsangaben korrekt abgefangen werden.
     */
    @Test
    void TurmSollteBeiUngueltigerPositionsNotationExceptionWerfen() {
        Turm turm = new Turm(true, "A1");

        assertThrows(IllegalArgumentException.class,
                () -> turm.zieheNach(null),
                "Null als Position sollte nicht erlaubt sein");

        assertThrows(IllegalArgumentException.class,
                () -> turm.zieheNach("AA"),
                "Falsches Positionsformat sollte nicht erlaubt sein");
    }
}