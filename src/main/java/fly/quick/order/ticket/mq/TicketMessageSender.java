package fly.quick.order.ticket.MQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.quick.order.ticket.messages.TicketChangeMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketMessageSender {

    private final ObjectMapper mapper;
    private final RabbitTemplate rabbitTemplate;

    public boolean send(TicketChangeMessage ticketChangeMessage) {
        try {
            String message = mapper.writeValueAsString(ticketChangeMessage);
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNTING_KEY, message);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
