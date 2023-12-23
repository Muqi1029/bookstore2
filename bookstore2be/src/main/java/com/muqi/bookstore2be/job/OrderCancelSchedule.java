package com.muqi.bookstore2be.job;

import com.muqi.bookstore2be.domain.AllOrder;
import com.muqi.bookstore2be.service.AllOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderCancelSchedule {
    @Autowired
    private AllOrderService allOrderService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void cancelUnpaidOrders() {
        List<AllOrder> unpaidOrders = allOrderService.getUnpaidOrders();
        unpaidOrders.forEach(order -> {
            if (order.getCreateTime().plusMinutes(30).isBefore(LocalDateTime.now())) {
                allOrderService.cancelOrder(order.getOrderId());
            }
        });
    }
}
