package com.epam.royalbooking.dto;

import com.epam.royalbooking.util.DateFormatter;

import java.time.LocalDate;

public class PreOrder {
    private String dateRange;
    private LocalDate entryDate;
    private LocalDate leaveDate;
    private int bookedRoomId;

    public void setEntryAndLeaveDate() {
        entryDate = DateFormatter.createFormattedDate(dateRange, 0);
        leaveDate = DateFormatter.createFormattedDate(dateRange, 1);
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public int getBookedRoomId() {
        return bookedRoomId;
    }

    public void setBookedRoomId(int bookedRoomId) {
        this.bookedRoomId = bookedRoomId;
    }
}
