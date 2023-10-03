package com.alexalins.bagdex.domain.util;


import java.util.Calendar;
import java.util.Date;

public final class DataUtil {

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        return currentDate;
    }
}
