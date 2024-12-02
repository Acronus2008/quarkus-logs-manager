package com.microboxlabs.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class DateUtil {
    private static final DateTimeFormatter LOG_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final Function<String, LocalDateTime> parseLogDateTime = timestamp -> LocalDateTime.parse(timestamp, LOG_DATE_FORMAT);
}
