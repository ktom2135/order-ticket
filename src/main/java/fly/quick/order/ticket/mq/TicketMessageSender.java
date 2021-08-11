package fly.quick.order.ticket.MQ;

import fly.quick.order.ticket.messages.TicketChangeMessage;
import org.springframework.stereotype.Service;

@Service
public class TicketMessageSender {
    public boolean send(TicketChangeMessage generateTicketChangeMessage) {
        return true;
    }
}
