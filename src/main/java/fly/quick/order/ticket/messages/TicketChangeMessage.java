package fly.quick.order.ticket.messages;

import fly.quick.order.ticket.BasePojo.TicketAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketChangeMessage {
    private TicketAction ticketAction;
    private String targetPlaneId;
}

