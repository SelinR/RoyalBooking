package com.epam.royalbooking.services;

import com.epam.royalbooking.config.ApplicationConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * These test are supposed to be ran right after init.sql, while no changes to the db are applied.
 * Init.sql is written in a date-relative way: all dates taking there place relative to 'today', so
 * before running tests for the first time a day, init.sql should be reran.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class OrderServiceTest {
    private OrderService orderService;
    private static LocalDate today;
    private static LocalDate todayPlusThreeDays;
    private static LocalDate todayPlusFiveDays;
    private static LocalDate tomorrow;
    private static LocalDate yesterday;
    private static LocalDate pastYear;
    private static LocalDate nextYear;
    private static LocalDate inAWeek;
    private static LocalDate inAMonth;
    private static LocalDate todayPlusThirtyDays;

    @BeforeClass
    public static void setUp() {
        today = LocalDate.now();
        todayPlusThreeDays = today.plusDays(3);
        todayPlusFiveDays = today.plusDays(5);
        tomorrow = today.plusDays(1);
        yesterday = today.minusDays(1);
        todayPlusThirtyDays = today.plusDays(30);
        pastYear = today.minusYears(1);
        nextYear = today.plusYears(1);
        inAWeek = today.plusDays(7);
        inAMonth = today.plusMonths(1);
    }

    @Test
    public void testIsOrderValid() {
        assertFalse(orderService.isOrderValid(today, tomorrow, 1));
        assertFalse(orderService.isOrderValid(yesterday, tomorrow, 2));
        assertFalse(orderService.isOrderValid(today, yesterday, 3));
        assertFalse(orderService.isOrderValid(today, todayPlusThreeDays, 4));
        assertFalse(orderService.isOrderValid(today, todayPlusThreeDays, 4));
        assertTrue(orderService.isOrderValid(today.plusDays(2), todayPlusThreeDays, 1));
        assertTrue(orderService.isOrderValid(todayPlusThreeDays, todayPlusThirtyDays, 2));
        assertTrue(orderService.isOrderValid(today, today, 7));
        assertFalse(orderService.isOrderValid(todayPlusFiveDays, today.minusDays(30), 7));
        assertFalse(orderService.isOrderValid(todayPlusFiveDays, today.minusDays(3), 7));
        assertTrue(orderService.isOrderValid(todayPlusThirtyDays, nextYear, 7));
    }

    @Test
    public void testIsEntryDateBeforeOrEqualLeaveDate() {
        assertTrue(orderService.isEntryDateBeforeOrEqualLeaveDate(today, tomorrow));
        assertTrue(orderService.isEntryDateBeforeOrEqualLeaveDate(today, today));
        assertTrue(orderService.isEntryDateBeforeOrEqualLeaveDate(today, nextYear));
        assertFalse(orderService.isEntryDateBeforeOrEqualLeaveDate(today, yesterday));
        assertFalse(orderService.isEntryDateBeforeOrEqualLeaveDate(today, pastYear));
    }

    @Test
    public void testIsOrderForTodayOrInFuture() {
        assertTrue(orderService.isOrderForTodayOrInFuture(today));
        assertTrue(orderService.isOrderForTodayOrInFuture(tomorrow));
        assertTrue(orderService.isOrderForTodayOrInFuture(todayPlusFiveDays));
        assertFalse(orderService.isOrderForTodayOrInFuture(yesterday));
        assertFalse(orderService.isOrderForTodayOrInFuture(today.minusDays(5)));
    }

    @Test
    public void testIsRoomFreeInSelectedDays() {
        assertFalse(orderService.isRoomFreeInSelectedDays(today, tomorrow, 1));
        assertFalse(orderService.isRoomFreeInSelectedDays(tomorrow, inAWeek, 1));
        assertFalse(orderService.isRoomFreeInSelectedDays(tomorrow, inAWeek, 2));
        assertFalse(orderService.isRoomFreeInSelectedDays(tomorrow, inAWeek, 3));
        assertFalse(orderService.isRoomFreeInSelectedDays(today, inAWeek, 5));
        assertTrue(orderService.isRoomFreeInSelectedDays(todayPlusFiveDays, inAWeek, 1));
        assertFalse(orderService.isRoomFreeInSelectedDays(inAMonth, inAWeek, 2));
        assertTrue(orderService.isRoomFreeInSelectedDays(inAMonth, inAMonth, 3));
        assertTrue(orderService.isRoomFreeInSelectedDays(today, today, 6));
    }

    @Test(expected = RuntimeException.class)
    public void testCalculateTotalPriceForRunTimeException() {
        LocalDate today = LocalDate.now();
        orderService.calculateTotalPrice(0, today, today);
        orderService.calculateTotalPrice(-1, today, inAMonth);
    }

    @Test
    public void testCalculateTotalPrice() {
        assertEquals(50, orderService.calculateTotalPrice(1, today, today), 1);
        assertEquals(1550, orderService.calculateTotalPrice(1, today, todayPlusThirtyDays), 1);
        assertEquals(160, orderService.calculateTotalPrice(2, today, tomorrow), 1);
        assertEquals(2480, orderService.calculateTotalPrice(2, today, todayPlusThirtyDays), 1);
        assertEquals(100, orderService.calculateTotalPrice(3, today, today), 1);
        assertEquals(200, orderService.calculateTotalPrice(3, today, tomorrow), 1);
        assertEquals(3100, orderService.calculateTotalPrice(3, today, todayPlusThirtyDays), 1);
        assertEquals(1000, orderService.calculateTotalPrice(14, today, today), 1);
        assertEquals(2000, orderService.calculateTotalPrice(14, today, tomorrow), 1);
        assertEquals(31000, orderService.calculateTotalPrice(14, today, todayPlusThirtyDays), 1);
    }

    @Test
    public void testGetAllDaysBetweenIncludingLastDay() {
        List<LocalDate> daysBetweenTodayAndTodayInclusive = new ArrayList<>();
        daysBetweenTodayAndTodayInclusive.add(today);
        List<LocalDate> daysBetweenTodayAndTomorrowInclusive = new ArrayList<>();
        daysBetweenTodayAndTomorrowInclusive.add(today);
        daysBetweenTodayAndTomorrowInclusive.add(tomorrow);

        List<LocalDate> daysBetweenTodayAndFiveDaysInclusive = new ArrayList<>();
        LocalDate temporalDay = LocalDate.now();
        for (long i = today.toEpochDay(); i <= todayPlusFiveDays.toEpochDay(); i++) {
            daysBetweenTodayAndFiveDaysInclusive.add(temporalDay);
            temporalDay = temporalDay.plusDays(1);
        }

        List<LocalDate> daysBetweenTodayAndThirtyDaysInclusive = new ArrayList<>();
        temporalDay = LocalDate.now();
        for (long i = today.toEpochDay(); i <= todayPlusThirtyDays.toEpochDay(); i++) {
            daysBetweenTodayAndThirtyDaysInclusive.add(temporalDay);
            temporalDay = temporalDay.plusDays(1);
        }

        assertEquals(daysBetweenTodayAndTodayInclusive, orderService.getAllDaysBetweenIncludingLastDay(today, today));
        assertEquals(daysBetweenTodayAndTomorrowInclusive, orderService.getAllDaysBetweenIncludingLastDay(today, tomorrow));
        assertEquals(daysBetweenTodayAndFiveDaysInclusive, orderService.getAllDaysBetweenIncludingLastDay(today, todayPlusFiveDays));
        assertEquals(daysBetweenTodayAndThirtyDaysInclusive, orderService.getAllDaysBetweenIncludingLastDay(today, todayPlusThirtyDays));

        List empty = Collections.EMPTY_LIST;
        assertEquals(empty, orderService.getAllDaysBetweenIncludingLastDay(null, today));
        assertEquals(empty, orderService.getAllDaysBetweenIncludingLastDay(today, null));
        assertEquals(empty, orderService.getAllDaysBetweenIncludingLastDay(null, null));
    }

    @AfterClass
    public static void tearDown() {
        today = null;
        todayPlusThreeDays = null;
        todayPlusFiveDays = null;
        tomorrow = null;
        yesterday = null;
        todayPlusThirtyDays = null;
        pastYear = null;
        nextYear = null;
        inAWeek = null;
        inAMonth = null;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
