package com.epam.royalbooking.services;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void testIsOrderValid() {
        LocalDate localDateNow = LocalDate.now();
        LocalDate localDateTomorrow = localDateNow.plusDays(1);
    }
}
