package fly.quick.order.ticket.Model;

import fly.quick.order.ticket.BasePojo.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketModel {

    private Long id;
    private Long userId;
    private Date createdAt;
    private TicketStatus status;
    private String meterNo;

}
