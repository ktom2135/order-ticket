package fly.quick.order.ticket.Feign.Dtos;

import fly.quick.order.ticket.BasePojo.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LockSetResponseFeignDto {
    private String code;
    private String message;
}


