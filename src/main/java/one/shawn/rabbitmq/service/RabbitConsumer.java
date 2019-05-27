package one.shawn.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import one.shawn.rabbitmq.config.RabbitConfig;
import one.shawn.rabbitmq.model.RabbitMessage;

@Service
public class RabbitConsumer {
	static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);
	  
    @RabbitListener(queues = RabbitConfig.QUEUE_MESSAGES)
    public void processOrder(RabbitMessage message) {
        logger.info("Message Received: " + message);
    }
}
