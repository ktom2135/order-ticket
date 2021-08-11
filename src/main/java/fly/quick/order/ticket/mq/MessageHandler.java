package fly.quick.order.ticket.MQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MessageHandler {

    private static Logger log = LoggerFactory.getLogger(MessageHandler.class);

    @RabbitListener(queues = "order-ticket-queue")
    public void handle1(String msg) {
        log.info("接受到来自RabbitMQ的消息：{}", msg);
    }
}
