package com.example.CA1;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


import java.util.*;
@Service
public class TradeConsumer {

    private final JmsTemplate jmsTemplate;
    private final Map<String, Integer> portfolio = new LinkedHashMap<>(); // Portfolio storage

    public TradeConsumer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    @JmsListener(destination = "OUTQ")
    public void receiveResponse(TradeMessage response) {
        System.out.println("Received Response from OUTQ: " + response);
    }
    private String add(TradeMessage message){

       var security= message.getSecurity();
        portfolio.putIfAbsent(security, 0); // Add security if it doesn’t exist

        return  "0 Success";
    }
    private String sell(TradeMessage message){
      var  security= message.getSecurity();
      var  amount = message.getAmount();

        if (!portfolio.containsKey(security)) {
            return "1 Unknown security";
        } else if (portfolio.get(security) < amount) {
           return "2 Not enough positions";
        } else {
            portfolio.put(security, portfolio.get(security) - amount);
            return  "0 Trade successful";
        }
    }
    private String buy(TradeMessage message){
        var security= message.getSecurity();
       var amount = message.getAmount();

        if (portfolio.containsKey(security)) {
            portfolio.put(security, portfolio.get(security) + amount);
            return  "0 Trade successful";
        } else {
            return  "1 Unknown security";
        }
    }
    @JmsListener(destination = "INQ")
    public void receiveMessage(TradeMessage message) {
        System.out.println("Received Message: " + message.getType());

        String responseText;





        switch (message.getType()) {
            case "BUY":
            responseText=buy(message);
                break;

            case "SELL":
              responseText=sell(message);
                break;

            case "ADD":
                responseText=add(message);
                break;

            case "PORTFOLIO":
                responseText =  "0 " + formatPortfolio();
                break;

            default:
                responseText = "Unknown command";
        }

        TradeMessage response = new TradeMessage( "response",responseText);
        jmsTemplate.convertAndSend("OUTQ", response);
        System.out.println("Sent Response to OUTQ: " + responseText);
    }

    private String formatPortfolio() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append(" | ");
        }
        return sb.substring(0, sb.length() - 3);
    }
}
