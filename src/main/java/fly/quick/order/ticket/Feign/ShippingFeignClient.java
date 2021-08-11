package fly.quick.order.ticket.Feign;

import fly.quick.order.ticket.Feign.Dtos.LockSeatRequestFeignDto;
import fly.quick.order.ticket.Feign.Dtos.LockSetResponseFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.TimeoutException;

@FeignClient(name = "shipping", url = "${feign.shipping.url}")
public interface ShippingFeignClient {

    @PostMapping("/shipping/lock-seat")
    LockSetResponseFeignDto lockSeat(@RequestBody LockSeatRequestFeignDto lockSeatRequestFeignDto) throws TimeoutException;

}