package fly.quick.order.ticket.ControllerDtos;

import fly.quick.order.ticket.BasePojo.TicketStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class CancleTicketResponseDto implements Serializable {
    private TicketStatus code;
    private String message;
}
