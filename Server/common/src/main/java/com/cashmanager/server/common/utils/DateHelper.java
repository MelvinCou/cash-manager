package com.cashmanager.server.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateHelper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     *
     * @param date the date to format (yyyy-MM-dd HH:mm:ss)
     * @param yearMonthOnly true if the date is only year and month, false otherwise
     * @return the date formatted
     * @throws IllegalArgumentException if the date is not in the correct format
     */
    public static LocalDateTime fromString(String date, boolean yearMonthOnly) throws IllegalArgumentException {
        String newDate = yearMonthOnly ? date.concat("-01 00:00:00") : date;
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(newDate, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("The date is not in the correct format: " + formatter);
        }
        return dateTime;
    }

    /**
     *
     * @return the current date with the time set to 00:00:00
     */
    public static LocalDateTime nowYearMonthOnly() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(now.getYear(), now.getMonth(),
                1, 0, 0);
    }
}
