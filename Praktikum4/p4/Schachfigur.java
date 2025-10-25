package de.hsd.medien.se.hsdchess.domain;

public class Schachfigur
{
    String schachFigurTyp;
    SchachFigurFarben schachFigurFarbe;
    char xPositionFigur;
    int yPositionFigur;

    
    String xFeld = "abcdefgh";
    String yFeld = "12345678";
    
    public Schachfigur(SchachFigurFarben p_schachFigurFarbe, String p_FigurenTyp,char p_xStartPosition, int p_yStartPosition)
    { 
        schachFigurFarbe = p_schachFigurFarbe;
        schachFigurTyp = p_FigurenTyp;
        xPositionFigur = Character.toLowerCase(p_xStartPosition);
        yPositionFigur = p_yStartPosition;
        checkAusgangsPosition(xPositionFigur, yPositionFigur);
    }

    void checkAusgangsPosition(char p_xKoordinate, int p_yKoordinate)
    {
        // erlaubt: b1 oder g1
        if (!((p_xKoordinate == 'b' || p_xKoordinate == 'g') && p_yKoordinate == 1))
        {
            throw new IllegalArgumentException(
                "Ungültige Startposition! Das Pferd darf nur bei b1 und g1 starten."
            );
        }
    }

    String[] aktuellePosition (String p_xKoordinate, String p_yKoordinate)
    {
        String[] positonFigur = {p_xKoordinate, p_yKoordinate};
        return positonFigur;
    }

    public boolean onBoard(String[] p_aktuellePosition)
    {
        char xPositionAktuell = p_aktuellePosition[0].charAt(0);
        int yPositionAktuell = Integer.parseInt(p_aktuellePosition[1]);

        return (xFeld.indexOf(xPositionAktuell) != -1 &&
                yPositionAktuell >= 1 && yPositionAktuell <= 8);
    }

    public void defeated()
    {
        this.xPositionFigur = '/';
        this.yPositionFigur = 0;
    }
}
