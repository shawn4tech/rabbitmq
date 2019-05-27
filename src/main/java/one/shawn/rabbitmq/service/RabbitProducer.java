package one.shawn.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.shawn.rabbitmq.config.RabbitConfig;
import one.shawn.rabbitmq.model.RabbitMessage;

@Service
public class RabbitProducer {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(RabbitMessage message) {
		this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_MESSAGES, message);
	}
}
