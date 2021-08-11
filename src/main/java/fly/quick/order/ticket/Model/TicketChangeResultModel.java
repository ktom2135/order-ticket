package fly.quick.order.ticket.Model;

import fly.quick.order.ticket.BasePojo.ChangeTicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketChangeResultModel {
    public ChangeTicketStatus status;
}
