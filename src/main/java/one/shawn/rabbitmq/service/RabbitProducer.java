package one.shawn.rabbitmq.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.shawn.rabbitmq.config.RabbitConfig;
import one.shawn.rabbitmq.model.RabbitMessage;

@Service
public class RabbitProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{
	@Autowired
	private RabbitTemplate rabbitTemplate;
	private Logger logger = LoggerFactory.getLogger(RabbitProducer.class);
	
	@PostConstruct
	private void setRabbitTemplate() {
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.setReturnCallback(this);
	}
	
	public void sendMessage(RabbitMessage message) {
		this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_MESSAGES, message);
	}

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		logger.error("Cannot deliver to the queue. Reply text: {}", replyText);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			logger.info("Send to the excchange");
		} else {
			logger.error("Cannot deliver to the exchange. Cause: {}", cause);
		}
	}
}
