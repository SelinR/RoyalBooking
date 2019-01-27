package com.epam.royalbooking.services;

import com.epam.royalbooking.config.ApplicationConfig;
import com.epam.royalbooking.dao.OrderDaoTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class OrderServiceTest {

    @Autowired
    private static OrderService orderService;

    @Test
    public void testIsOrderValid() {
        orderService.isOrderValid(LocalDate.now(), LocalDate.now().plusDays(1), 1);
        orderService.isOrderValid(LocalDate.now(), LocalDate.now().plusDays(1), 2);
        orderService.isOrderValid(LocalDate.now(), LocalDate.now().plusDays(1), 3);
        orderService.isOrderValid(LocalDate.now(), LocalDate.now().plusDays(1), 4);
    }

    @Test
    public void testIsEntryDateBeforeOrEqualLeaveDate() {
        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);
        LocalDate pastYear = now.minusYears(1);
        LocalDate tomorrow = now.plusDays(1);
        LocalDate nextYear = now.plusYears(1);
        assertTrue(orderService.isEntryDateBeforeOrEqualLeaveDate(now, tomorrow));
        assertTrue(orderService.isEntryDateBeforeOrEqualLeaveDate(now, now));
        assertTrue(orderService.isEntryDateBeforeOrEqualLeaveDate(now, nextYear));
        assertFalse(orderService.isEntryDateBeforeOrEqualLeaveDate(now, yesterday));
        assertFalse(orderService.isEntryDateBeforeOrEqualLeaveDate(now, pastYear));
    }

    @Test
    public void testIsOrderForTodayOrInFuture() {
        LocalDate entryDate1 = LocalDate.now();
        LocalDate entryDate2 = LocalDate.now().plusDays(1);
        LocalDate entryDate3 = LocalDate.now().plusDays(5);
        LocalDate entryDate4 = LocalDate.now().minusDays(1);
        LocalDate entryDate5 = LocalDate.now().minusDays(5);
        assertTrue(orderService.isOrderForTodayOrInFuture(entryDate1));
        assertTrue(orderService.isOrderForTodayOrInFuture(entryDate2));
        assertTrue(orderService.isOrderForTodayOrInFuture(entryDate3));
        assertFalse(orderService.isOrderForTodayOrInFuture(entryDate4));
        assertFalse(orderService.isOrderForTodayOrInFuture(entryDate5));
    }

    @Test
    public void testIsRoomFreeInSelectedDays() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate inFiveDays = today.plusDays(5);
        LocalDate inAWeek = today.plusDays(7);
        LocalDate inAMonth = today.plusMonths(1);
        assertFalse(orderService.isRoomFreeInSelectedDays(today, tomorrow, 1));
        assertFalse(orderService.isRoomFreeInSelectedDays(tomorrow, inAWeek, 1));
        assertFalse(orderService.isRoomFreeInSelectedDays(tomorrow, inAWeek, 2));
        assertFalse(orderService.isRoomFreeInSelectedDays(tomorrow, inAWeek, 3));
        assertFalse(orderService.isRoomFreeInSelectedDays(today, inAWeek, 5));

    }

    @Test
    public void testCalculateTotalPrice() {

    }

    @Autowired
    public static void setOrderService(OrderService orderService) {
        OrderServiceTest.orderService = orderService;
    }
}
