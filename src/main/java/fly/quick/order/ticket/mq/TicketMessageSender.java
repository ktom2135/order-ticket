package fly.quick.order.ticket.MQ;

import fly.quick.order.ticket.messages.TicketChangeMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketMessageSender {
    private final RabbitTemplate rabbitTemplate;

    public boolean send(TicketChangeMessage generateTicketChangeMessage) {
        try {
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNTING_KEY, generateTicketChangeMessage);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
