package com.example.demo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.User;
@Service
public class RabbitMQJsonProducer {
	
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	
	@Value("${rabbitmq.routing.json.key}")
	private String jsonRoutingKey;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
	private RabbitTemplate rabbitmqTemplate;
	
	private RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
		
		this.rabbitmqTemplate=rabbitTemplate;
		
	}
	
	public void sendMessage(User user) {
		LOGGER.info(String.format("json message sent -> %s", user.toString()));
		rabbitmqTemplate.convertAndSend(exchange,jsonRoutingKey,user);
	}
	

}
