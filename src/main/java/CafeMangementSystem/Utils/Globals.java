package CafeMangementSystem.Utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Globals {
    public static NumberFormat numberFormat;
    static {
        numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
        numberFormat.setMaximumFractionDigits(3);
        numberFormat.setMinimumFractionDigits(3);
    }
}
