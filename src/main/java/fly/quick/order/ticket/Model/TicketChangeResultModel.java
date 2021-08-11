package fly.quick.order.ticket.Model;

import fly.quick.order.ticket.BasePojo.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketChangeResultModel {
    public TicketStatus status;
}
