package fly.quick.order.ticket.ControllerDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTicketRequestDto {
    private String targetPlanId;
}
