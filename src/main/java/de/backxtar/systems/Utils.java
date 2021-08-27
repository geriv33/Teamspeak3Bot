package de.backxtar.systems;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        return sdfDate.format(date);
    }
}
