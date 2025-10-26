# Schritt 0 – JUnit Setup

## Ziel

In diesem Schritt wird das Testframework **JUnit 5** eingerichtet, um Unit-Tests an der Konsole auszuführen.  
Dazu wird das Modul **JUnit Platform Console Standalone (Version 1.10.0)** verwendet.

---

## Projektstruktur

Das Projekt sollte folgendermaßen aufgebaut sein:

```
projektverzeichnis/
│
├── lib/
│   └── junit-platform-console-standalone-1.10.0.jar
│
├── src/
│   └── TestHelloWorld.java
│
├── out/
│
├── compileAndRunTests.sh
├── .gitignore
└── README.md
```

---

## JUnit-Bibliothek

Die Datei **`junit-platform-console-standalone-1.10.0.jar`** muss im Unterverzeichnis  
`lib/` des Projektverzeichnisses abgelegt werden.

Download-Link:  
🔗 <https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar>

---

## Beispiel-Testklasse

Die Testklasse **`TestHelloWorld.java`** dient nur dazu, das Setup zu überprüfen:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestHelloWorld {

    @Test
    void testAddition() {
        int result = 2 + 3;
        assertEquals(5, result, "2 + 3 sollte 5 ergeben");
    }

    @Test
    void testStringNotNull() {
        String s = "JUnit funktioniert!";
        assertNotNull(s, "String darf nicht null sein");
    }
}
```

---

## Kompilieren und Ausführen der Tests

### 1️⃣ Kompilieren

```bash
javac -cp lib/junit-platform-console-standalone-1.10.0.jar -d out src/TestHelloWorld.java
```

**Erklärung:**

- `-cp` → legt den **Classpath** fest (Pfad zur JUnit-Bibliothek)
- `-d out` → legt das Ausgabeverzeichnis für die kompilierten `.class`-Dateien fest

---

### 2️⃣ Tests ausführen

```bash
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path out --scan-classpath
```

**Erklärung:**

- `--class-path out` → Pfad zu den kompilierten Klassen
- `--scan-classpath` → durchsucht automatisch alle Testklassen im Classpath

---

## Alternative: Bash-Skript

Die Schritte lassen sich mit einem Skript automatisieren:

**Datei:** `compileAndRunTests.sh`

```bash
#!/bin/bash
set -e

# Kompilieren
javac -cp lib/junit-platform-console-standalone-1.10.0.jar -d out src/TestHelloWorld.java

# Tests ausführen
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path out --scan-classpath
```

Skript ausführbar machen:

```bash
chmod +x compileAndRunTests.sh
```

Skript starten:

```bash
./compileAndRunTests.sh
```

---

## Warum die JAR-Datei nicht ins Git-Repository gehört

Die Datei `junit-platform-console-standalone-1.10.0.jar` soll **nicht** in das Git-Repository aufgenommen werden, weil:

- sie eine **externe Bibliothek** ist und **nicht zum eigenen Quellcode** gehört
- sie **groß** ist und das Repository unnötig aufbläht
- sie **leicht erneut heruntergeladen** werden kann
- sie **versionsabhängig** ist (andere Projekte können andere JUnit-Versionen benötigen)

Stattdessen wird sie durch `.gitignore` ausgeschlossen.

---

## Beispiel `.gitignore`

```gitignore
# Kompilierte Klassen
/out/

# JUnit-Bibliothek
/lib/junit-platform-console-standalone-*.jar

# Temporäre Dateien
*.class
*.log
```

---

✅ Mit diesen Schritten ist die JUnit 5 Umgebung korrekt eingerichtet, und einfache Tests können über die Konsole kompiliert und ausgeführt werden.

# Schritt 2 - Testfälle für die Figur beschreiben

| Testfall-Name (Testmethodenname)                             | Aufgerufene Methode | Parameter                                                                            | Geprüftes Verhalten                                         | Erwartetes Resultat (bei korrekter Implementierung)                                                                    | Begründung                                                             |
| ------------------------------------------------------------ | ------------------: | ------------------------------------------------------------------------------------ | ----------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| TurmSollteBeiHorizontalemZugSeinePositionAendern             |         `zieheNach` | Konstruktor: `new Turm(true, "A1")` anschließend `zieheNach("H1")`                   | Regelkonformer horizontaler Zug wird erlaubt und ausgeführt | Keine Exception; `getPosition()` → `"H1"`; `getZugAnzahl()` erhöht um 1                                                | Grundfunktion des Turms: waagrechter Zug (Funktionstest)               |
| TurmSollteBeiVertikalemZugSeinePositionAendern               |         `zieheNach` | Konstruktor: `new Turm(false, "D2")` anschließend `zieheNach("D8")`                  | Regelkonformer vertikaler Zug wird erlaubt und ausgeführt   | Keine Exception; `getPosition()` → `"D8"`; `getZugAnzahl()` erhöht um 1                                                | Grundfunktion des Turms: senkrechter Zug (Funktionstest)               |
| TurmSollteBeiDiagonalemZugEineExceptionWerfen                |         `zieheNach` | Konstruktor: `new Turm(true, "A1")` anschl. `zieheNach("B2")`                        | Diagonaler Zug ist für Turm nicht erlaubt                   | `IllegalArgumentException` wird geworfen; Position bleibt `"A1"`; `getZugAnzahl()` bleibt 0                            | Prüffall für ungültige Regelverletzung (Negative/Fehlerfall)           |
| TurmSollteSichNichtBewegenWennGeschlagen                     |         `zieheNach` | Konstruktor: `new Turm(false, "C3")`; zuerst `schlage()`, dann `zieheNach("C6")`     | Bewegungsverbot wenn Figur bereits geschlagen wurde         | `IllegalArgumentException` wird geworfen mit Hinweis auf geschlagen; Position bleibt `"C3"`; `getZugAnzahl()` bleibt 0 | Zustandsabhängiger Randfall: geschlagene Figur darf sich nicht bewegen |
| TurmSollteBeiUngueltigerPositionsNotationEineExceptionWerfen |         `zieheNach` | Konstruktor: `new Turm(true, "A1")` anschl. `zieheNach("AA")` oder `zieheNach(null)` | Validierung der Zielpositionsnotation (Länge/Format)        | `IllegalArgumentException` wird geworfen; Position bleibt `"A1"`; `getZugAnzahl()` bleibt 0                            | Robustheitstest für Eingabevalidierung (Randbereich)                   |
