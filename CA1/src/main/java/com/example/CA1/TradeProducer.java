package com.example.CA1;

import com.example.CA1.TradeMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class TradeProducer {

    private final JmsTemplate jmsTemplate;

    public TradeProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(TradeMessage message) {
        jmsTemplate.convertAndSend("INQ", message);
        System.out.println("Message Sent: " + message);
    }
}
