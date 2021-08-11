package fly.quick.order.ticket.Feign.Dtos;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockSeatRequestFeignDto {

    private Long ticketId;

}
