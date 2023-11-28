package com.example.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.dto.User;

@Service
public class RabbitMQJsonConsumer {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	
	@RabbitListener(queues = "${rabbitmq.queue.json.name}")
	public void consumeJsonMessage(User user) {
		
		LOGGER.info(String.format("received json message -> %s", user.toString()));
		
	}

}
