package com.example.CA1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ca1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ca1Application.class, args);
	}

	@Bean
	CommandLineRunner testMessaging(ApplicationContext context) {
		return args -> {
			TradeProducer producer = context.getBean(TradeProducer.class);



			TradeMessage[] messages = new TradeMessage[] {
					new TradeMessage("ADD", "Mahsa"),
					new TradeMessage("BUY", "Mahsa", 100),
					new TradeMessage("ADD", "AAPL"),
					new TradeMessage("BUY", "AAPL", 50),
					new TradeMessage("PORTFOLIO"),
					new TradeMessage("SELL", "AAPL", 30),    // SELL message

					new TradeMessage("SELL", "AAPL", 30),    // SELL message
					new TradeMessage("PORTFOLIO")
			};

			// Loop through the messages array and send each message
			for (TradeMessage message : messages) {
				System.out.println("Sending message: " + message);
				producer.sendMessage(message);
			}
		};
	}
}
