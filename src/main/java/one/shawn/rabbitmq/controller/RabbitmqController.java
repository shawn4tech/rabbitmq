package one.shawn.rabbitmq.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.shawn.rabbitmq.model.RabbitMessage;
import one.shawn.rabbitmq.service.RabbitProducer;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {
	@Autowired
	private RabbitProducer producer;
	
	@PostMapping("/message")
	public String postMessage(@Valid @RequestBody RabbitMessage message) {
		producer.sendMessage(message);
		return "Success";
	}
}
