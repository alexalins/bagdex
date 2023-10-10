package com.alexalins.bagdex.domain.util;


import java.sql.Timestamp;
import java.util.Date;

public final class DataUtil {

    public static Timestamp getCurrentDate() {
        Date dataHoraAtual = new java.util.Date();
        return  new Timestamp(dataHoraAtual.getTime());
    }
}
