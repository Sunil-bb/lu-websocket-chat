package com.example;

import java.util.concurrent.CountDownLatch;

import javax.sound.midi.Receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.example.receiver.EduReceiver;
import com.example.receiver.MedReceiver;
import com.example.receiver.PolReceiver;
import com.example.receiver.ScieReceiver;


@SpringBootApplication
public class LuCharRcApplication {
	
	
	/*public static void main(String[] args) {
		SpringApplication.run(LuCharRcApplication.class, args);
	}*/

    public static void main(String[] args) {
        SpringApplication.run(LuCharRcApplication.class, args);
    }
    
    @Bean
	MessageListenerAdapter medlistenerAdapter(MedReceiver receiverMed) {
		return new MessageListenerAdapter(receiverMed, "receiveMessage");
	}
    
    @Bean
   	MessageListenerAdapter edulistenerAdapter(EduReceiver receiverEdu) {
   		return new MessageListenerAdapter(receiverEdu, "receiveMessage");
   	}
    
    @Bean
   	MessageListenerAdapter pollistenerAdapter(PolReceiver receiverPol) {
   		return new MessageListenerAdapter(receiverPol, "receiveMessage");
   	}
    
    @Bean
   	MessageListenerAdapter scilistenerAdapter(ScieReceiver receiverSci) {
   		return new MessageListenerAdapter(receiverSci, "receiveMessage");
   	}
	
	
	@Bean
	MedReceiver receiverMed(CountDownLatch latch) {
		return new MedReceiver(latch);
	}
	
	@Bean
	EduReceiver receiverEdu(CountDownLatch latch) {
		return new EduReceiver(latch);
	}
	
	
	@Bean
	PolReceiver receiverPol(CountDownLatch latch) {
		return new PolReceiver(latch);
	}
	
	@Bean
	ScieReceiver receiverSci(CountDownLatch latch) {
		return new ScieReceiver(latch);
	}

	@Bean
	CountDownLatch latch() {
		return new CountDownLatch(1);
	}
	
	/*@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jd = new JedisConnectionFactory();
		jd.setHostName("localhost");
		jd.setPort(6379);
		
		return jd;
		
	}*/
	
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter medlistenerAdapter,MessageListenerAdapter edulistenerAdapter,MessageListenerAdapter pollistenerAdapter,MessageListenerAdapter scilistenerAdapter ) {
		
		
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(medlistenerAdapter, new PatternTopic("MEDICAL"));
		container.addMessageListener(edulistenerAdapter, new PatternTopic("EDUCATIONS"));
		container.addMessageListener(pollistenerAdapter, new PatternTopic("POLITICS"));
		container.addMessageListener(scilistenerAdapter, new PatternTopic("SCIENCE"));

		return container;
	}
}
