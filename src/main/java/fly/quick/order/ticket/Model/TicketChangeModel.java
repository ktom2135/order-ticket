package fly.quick.order.ticket.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketChangeModel {
    private String targetPlaneId;
    private Date targetPlaneFlyAt;
    private Long ticketId;
}
