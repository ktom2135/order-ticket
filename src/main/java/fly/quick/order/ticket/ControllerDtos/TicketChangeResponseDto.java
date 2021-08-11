package fly.quick.order.ticket.ControllerDtos;

import fly.quick.order.ticket.Model.TicketChangeResultModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketChangeResponseDto {

    private String message;
    private String code;
}

