package com.muqi.bookstore2be.job;

import com.muqi.bookstore2be.domain.AllOrder;
import com.muqi.bookstore2be.service.AllOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class OrderCancelSchedule {
    @Autowired
    private AllOrderService allOrderService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void cancelUnpaidOrders() {
        List<AllOrder> unpaidOrders = allOrderService.getUnpaidOrders();
        unpaidOrders.forEach(order -> {
            LocalDateTime createTime = convertDateToLocalDateTime(order.getCreatetime());
            if (createTime.plusMinutes(30).isBefore(LocalDateTime.now())) {
                allOrderService.cancelOrder(order.getOrderId());
            }
        });
    }
    private LocalDateTime convertDateToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
