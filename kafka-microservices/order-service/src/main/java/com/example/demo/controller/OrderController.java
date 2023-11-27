package com.example.demo.controller;

import org.apache.kafka.common.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Order;
import com.example.demo.dto.OrderEvent;
import com.example.demo.kafka.OrderProducer;

@RestController
@RequestMapping("/api/v1")

public class OrderController {
	@Autowired
	private OrderProducer orderProducer;
	
	public OrderController(OrderProducer orderProducer) {
		this.orderProducer=orderProducer;
	}
	
	@PostMapping("/orders")
	public String placeOrder(@RequestBody Order order) {
		order.setOrderId(Uuid.randomUuid().toString());
		
		OrderEvent orderEvent=new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("order status in pending status");
		orderEvent.setOrder(order);
		
		orderProducer.sendMessage(orderEvent);
		return "order placed successfully";
		
		
	}

}
