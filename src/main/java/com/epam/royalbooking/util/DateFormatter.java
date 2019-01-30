package com.epam.royalbooking.util;

import java.time.LocalDate;

public class DateFormatter {

    /**
     * @param dateRange comes from the front-end calendar on the order page.
     *                  Entry string is of format "mm/dd/yyyy - mm/dd/yyyy".
     * @param index a part of a dateRange. Index 0 == entry date, index 1 == leave date.
     * @return Output LocalDate is of type yyyy-mm-dd.
     */
    public static LocalDate createFormattedDate(String dateRange, int index) {
        String[] dividedEntryAndLeaveDates = dateRange.split(" - ");
        String date = dividedEntryAndLeaveDates[index];
        String[] chunks = date.split("/");
        int year = Integer.parseInt(chunks[2]);
        int month = Integer.parseInt(chunks[0]);
        int day = Integer.parseInt(chunks[1]);
        return LocalDate.of(year, month, day);
    }
}
