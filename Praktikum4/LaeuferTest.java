package de.hsd.medien.se.hsdchess.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LaeuferTest {

    @Test
    void laeuferSollteGueltigErstelltWerden() {
        Laeufer l = new Laeufer("weiß", "c1");
        assertEquals("c1", l.getPosition());
        assertTrue(l.istImSpiel());
    }

    @Test
    void laeuferSollteDiagonalZiehenKoennen() {
        Laeufer l = new Laeufer("weiß", "c1");
        l.zieheNach("f4"); // noch nicht implementiert → Test schlägt fehl
        assertEquals("f4", l.getPosition());
    }

    @Test
    void laeuferDarfNichtHorizontalZiehen() {
        Laeufer l = new Laeufer("weiß", "c1");
        assertThrows(IllegalArgumentException.class, () -> l.zieheNach("f1"));
    }

    @Test
    void laeuferDarfNichtVertikalZiehen() {
        Laeufer l = new Laeufer("weiß", "c1");
        assertThrows(IllegalArgumentException.class, () -> l.zieheNach("c5"));
    }

    @Test
    void laeuferSollteNichtVomBrettZiehenKoennen() {
        Laeufer l = new Laeufer("weiß", "h8");
        assertThrows(IllegalArgumentException.class, () -> l.zieheNach("i9"));
    }
}
