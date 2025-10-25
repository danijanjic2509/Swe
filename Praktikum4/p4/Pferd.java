package de.hsd.medien.se.hsdchess.domain;

public class Pferd extends Schachfigur
{
    public Pferd()
    {
        super(SchachFigurFarben.WEISS, "Pferd", 'b', 1);
        
    }

    public void moveFigure(char p_xPositionNachZug, int p_yPositionNachZug, String[] p_aktuellePosition)
    {
        char xPositionAktuell = p_aktuellePosition[0].charAt(0);
        int yPositionAktuell = Integer.parseInt(p_aktuellePosition[1]);

        int indexOfX = xFeld.indexOf(xPositionAktuell);
        int indexOfY = yFeld.indexOf(Integer.toString(yPositionAktuell));

        int indexOfXNeu = xFeld.indexOf(p_xPositionNachZug);
        int indexOfYNeu = yFeld.indexOf(Integer.toString(p_yPositionNachZug));
    }

    public boolean correctMove(int aktuelleXCord, int xCordNachZug, int aktuelleYCord, int yCordNachZug)
    {
        boolean gueltigerZug = false;

        if (aktuelleXCord == -1 || aktuelleYCord == -1 || xCordNachZug == -1 || yCordNachZug == -1)
            return false; 


        int dx = Math.abs(xCordNachZug - aktuelleXCord);
        int dy = Math.abs(yCordNachZug - aktuelleYCord);
        
        if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1))
            gueltigerZug = true;

        return gueltigerZug;
    }
}