package com.example.receiver;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;

public class MedReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedReceiver.class);

    private CountDownLatch latch;

    @Autowired
    public MedReceiver(CountDownLatch latch) {
        this.latch = latch;
    }
    
    @Bean
	public MessageChannel webSocketOutputChannel() {
		DirectChannel dc= new DirectChannel();
		return dc;
	}

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        
    
        StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SEND);
		//headers.setSubscriptionId("subs1");
		headers.setDestination("/app/MEDICAL");
		Message<String> message2 = MessageBuilder.withPayload(message).setHeaders(headers).build();

		this.webSocketOutputChannel().send(message2);
		
      
        latch.countDown();
    }
}