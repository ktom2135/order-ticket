package fly.quick.order.ticket.Feign.Dtos;

import fly.quick.order.ticket.BasePojo.ShippingStatus;
import fly.quick.order.ticket.Feign.ShippingFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ShippingFeignTest {

    @Test
    void should_lock_seat_success_given_call_shipping_service_success() throws TimeoutException {
        ShippingFeignClient stubShippingFeignClient = Mockito.mock(ShippingFeignClient.class);
        when(stubShippingFeignClient.lockSeat(any())).thenReturn(LockSetResponseFeignDto
                .builder()
                .shippingStatus(ShippingStatus.SEAT_LOCKED)
                .message("改签成功")
                .build()
        );
        ShippingFeign shippingFeign = new ShippingFeign(stubShippingFeignClient);
        LockSetResponseFeignDto lockSetResponseFeignDto = shippingFeign.lockSeat(LockSeatRequestFeignDto.builder().build());
        assertEquals(ShippingStatus.SEAT_LOCKED.toString(), lockSetResponseFeignDto.getShippingStatus());
    }
}