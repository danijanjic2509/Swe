package de.hsd.medien.se.hsdchess.domain;

import org.junit.jupiter.api.Test;              // @Test, @BeforeEach, @AfterEach, ...
import static org.junit.jupiter.api.Assertions.*;  // assertTrue, assertEquals, assertThrows
import org.junit.jupiter.api.*;

public class TestHelloWorld 
{
 
// Klassenvariable für den Zähler
static int testZaehler;

//Vor ALLEN Tests
@BeforeAll
static void beforeAll()
{
    testZaehler = 0;
    System.out.println("TESTVORBEREITUNG");
}


//Vor JEDEM Test
@BeforeEach
void beforeEach()
{
    testZaehler++;
}

// --- Erster Test ---
@Test
void additionSollteFunktionieren() 
{
    int result = 2 + 2;
    assertEquals(4, result);
}

// --- Neue Tests ---
    @Test
    void variableSollteTrueSein() {
        boolean wert = true;
        assertTrue(wert);
    }

    @Test
    void sollteFehlerWerfen() {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("nur ein Beispiel");
        });
    }

    @Test
    void ergebnisSollte42Sein() {
        int result = 40 + 2;
        assertEquals(42, result);
    }

    @AfterEach
    void afterEach() {
        System.out.println(testZaehler + " Tests wurden ausgeführt.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Insgesamt " + testZaehler + " Tests ausgeführt.");
        System.out.println("TestDemo beendet.");
    }


} // End of TestHelloWorld class