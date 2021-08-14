package fly.quick.order.ticket.ControllerDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketChangeRequestDto {
    private String planeId;
    private Date targetPlaneFlyAt;
}
