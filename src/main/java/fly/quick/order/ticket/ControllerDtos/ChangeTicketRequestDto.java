package fly.quick.order.ticket.ControllerDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTicketRequestDto {
    private String targetPlanId;
    private Date targetPlanFlyAt;
}
