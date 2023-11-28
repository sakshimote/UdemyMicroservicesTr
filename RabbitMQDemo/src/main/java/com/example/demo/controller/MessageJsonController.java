package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;
import com.example.demo.publisher.RabbitMQJsonProducer;


@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
	
	private RabbitMQJsonProducer jsonProducer;
	
	
	public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
		this.jsonProducer=jsonProducer;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
		jsonProducer.sendMessage(user);
		return ResponseEntity.ok("json message sent to rabbitmq.....");
	}
	

}
