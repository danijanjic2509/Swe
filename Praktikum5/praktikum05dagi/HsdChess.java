package de.hsd.medien.se.hsdchess;
import com.github.lalyos.jfiglet.FigletFont;

public class HsdChess {
    public static void main(String[] args) {
        String willkommensText = "Welcome to HSD-Chess!";
        try {
            willkommensText = FigletFont.convertOneLine(willkommensText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(willkommensText);
    }
}
