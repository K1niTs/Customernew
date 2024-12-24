package org.example.messaging;

import org.example.DTO.PreOrderDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PreOrderConsumer {

    private final AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    public PreOrderConsumer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Transactional
    @RabbitListener(queues = "orderStatus.requests.queue",ackMode = "AUTO")
    public void receivePreOrderRequest(PreOrderDTO preOrderDTO) {
        try {
            System.out.println("Received PreOrder request: " + preOrderDTO);

            if (preOrderDTO.getStatus() == null || preOrderDTO.getId() == null) {
                System.err.println("Invalid PreOrder data: " + preOrderDTO);
                return;
            }

            sendPreOrderUpdate(preOrderDTO);

        } catch (Exception e) {
            System.err.println("Error processing PreOrder request: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void sendPreOrderUpdate(PreOrderDTO preOrderDTO) {
        try {
            amqpTemplate.convertAndSend(exchangeName, "orderStatus.update", preOrderDTO);
            System.out.println("Sent PreOrder update: " + preOrderDTO);
        } catch (Exception e) {
            System.err.println("Error sending update message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
