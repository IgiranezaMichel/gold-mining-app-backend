package com.gold_mining_app_backend.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {
public static String convertLocalDateTime(LocalDateTime ldt,String pattern){
    DateTimeFormatter dtf=DateTimeFormatter.ofPattern(pattern);
    return dtf.format(ldt);
}
}
