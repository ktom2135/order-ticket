package fly.quick.order.ticket.Feign.Dtos;

import fly.quick.order.ticket.BasePojo.ShippingStatus;
import fly.quick.order.ticket.Feign.ShippingFeignClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class ShippingFeign {

    final ShippingFeignClient shippingClient;

    public LockSetResponseFeignDto lockSeat(LockSeatRequestFeignDto lockSeatRequestFeignDto) {
        try {
            return shippingClient.lockSeat(lockSeatRequestFeignDto);
        }
        catch (Exception ex){
            return LockSetResponseFeignDto.builder().shippingStatus(ShippingStatus.TIMEOUT).build();
        }
    }
}

