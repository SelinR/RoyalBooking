package com.epam.royalbooking.dto;

import com.epam.royalbooking.entities.Order;

public class PreOrder extends Order {
    private String dateRange;

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }
}
