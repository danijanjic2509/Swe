import java.io.PrintStream;

import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestHelloWorld
{
    @Test
    public void test()
    {
        //2-1
        int zahl1 = 0;
        Assert.assertTrue(zahl1==2);
        
        //2-2
        String nullZeichenKette = null;
        //erfolgreich wenn nullZeichenkette die Länge 0 hat
        Assert.assertThrows(NullPointerException.class, () -> nullZeichenKette.length());
        
        //2-3
        Assert.assertEquals(zahl1, 42);
    }

    //3
    PrintStream ausgabe = System.out;
    int testZaehler = 0;

    //3-1
    @BeforeAll
    void printTestvorbereitung()
    {

        ausgabe.println("TESTVORBEREITUNG: " + testZaehler);
    }

    //3-2
    @BeforeEach
    void testVersuchZähler()
    {
        testZaehler +=1;
    }

    //3-3
    @AfterEach
    void ausgabeTestVersuche()
    {
        ausgabe.println(testZaehler + " Tests wurden ausgeführt.");
    }

    //3-4
    @AfterAll
    void ausgabeEndeTest()
    {
        ausgabe.println("Alle " + testZaehler +" Tests wurden beendet.");
    }
}