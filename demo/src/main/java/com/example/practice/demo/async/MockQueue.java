package com.example.practice.demo.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 伪造消息队列
 */
@Data
@Component
@Slf4j
public class MockQueue {

    /*下单消息*/
    private String placeOrder;

    /*完成订单消息*/
    private String completeOrder;

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(() -> {
            log.info("接到下单请求" + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("下单请求处理完毕，" + placeOrder);
        }).start();
    }
}
