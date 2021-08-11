package fly.quick.order.ticket.ControllerDtos;

import fly.quick.order.ticket.BasePojo.TicketStatus;
import fly.quick.order.ticket.Model.TicketModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponseDto {
    private Long id;
    private Long userId;
    private Date createdAt;
    private TicketStatus status;
    private String meterNo;

    public TicketResponseDto(TicketModel ticketModel) {
        id = ticketModel.getId();
        userId = ticketModel.getUserId();
        createdAt = ticketModel.getCreatedAt();
        status = ticketModel.getStatus();
        meterNo = ticketModel.getMeterNo();
    }
}
