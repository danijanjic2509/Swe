package de.hsd.medien.se.hsdchess.domain;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestHelloWorld {

    private int testZaehler;

    @BeforeAll
    public void testVorbereitung () {

        this.testZaehler = 0;
        System.out.println("TESTVORBEREITUNG");

    }

    @BeforeEach
    public void erhoeheTestZaehler() {
        testZaehler++;
    }

    @Test
    public void test () {
        assertEquals (2,1+1);
    }

    @Test
    public void testVariableIstTrue() {
        boolean istWahr = true;
        assertTrue(istWahr, "Die Variable sollte true sein");
    }

    @Test
    public void testFehlerWirdGeworfen() {
        // Beispielmethode, die einen Fehler auslöst
        assertThrows(IllegalArgumentException.class, () -> {
            methodeDieFehlerWirft();
        }, "Es sollte eine IllegalArgumentException geworfen werden");
    }

    @Test
    public void testErgebnisIst42() {
        int ergebnis = berechneEtwas();
        assertEquals(42, ergebnis, "Das Ergebnis sollte 42 sein");
    }

    // Hilfsmethoden zum Testen
    private void methodeDieFehlerWirft() {
        throw new IllegalArgumentException("Fehler aufgetreten");
    }

    private int berechneEtwas() {
        return 42;
    }
    
    @AfterEach
    public void anzahlTests () {
        System.out.println(this.testZaehler+"TESTS WURDEN AUSGEFÜHRT");
    }

    @AfterAll
    public void finished () {
        System.out.println(testZaehler+"TESTS WURDEN INSGESAMT AUSGEFÜHRT");
        System.out.println("TEST DEMO BEENDET, yay");
    }
}