package fly.quick.order.ticket.Feign;

import fly.quick.order.ticket.Feign.Dtos.LockSeatRequestFeignDto;
import fly.quick.order.ticket.Feign.Dtos.LockSetResponseFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "shipping", url = "${feign.shipping.url}", primary = false)
public interface ShippingClient {

    @PostMapping("/shipping/lock-seat")
    LockSetResponseFeignDto lockSeat(@RequestBody LockSeatRequestFeignDto orderNumber);

}