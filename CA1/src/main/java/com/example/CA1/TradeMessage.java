package com.example.CA1;

import java.io.Serializable;

public class TradeMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String security;
    private int amount;
    private  String text;

    public TradeMessage() {}

    public TradeMessage(String type, String security, int amount) {
        this.type = type;
        this.security = security;
        this.amount = amount;
    }
    public TradeMessage(String type, String security) {
        this.type = type;
        this.security = security;
    }
    public TradeMessage( String type) {
      this.type=type;
    }


    public String getType() { return type; }
    public String getSecurity() { return security; }
    public int getAmount() { return amount; }
}
