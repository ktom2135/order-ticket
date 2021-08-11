package fly.quick.order.ticket.Services;

import fly.quick.order.ticket.BasePojo.ShippingStatus;
import fly.quick.order.ticket.BasePojo.TicketStatus;
import fly.quick.order.ticket.Entity.TicketEntity;
import fly.quick.order.ticket.Feign.Dtos.LockSetResponseFeignDto;
import fly.quick.order.ticket.Feign.Dtos.ShippingFeign;
import fly.quick.order.ticket.MQ.TicketMessageSender;
import fly.quick.order.ticket.Model.TicketChangeModel;
import fly.quick.order.ticket.Model.TicketChangeResultModel;
import fly.quick.order.ticket.Repository.TicketRepository;
import fly.quick.order.ticket.messages.TicketChangeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TicketServiceTest {


    @Test
    public void should_return_success_given_lock_seat_success() {
        TicketRepository stubTicketRepository = Mockito.mock(TicketRepository.class);
        TicketEntity successTicket = TicketEntity.builder().status(TicketStatus.SUCCESS).build();
        TicketEntity changedTicket = TicketEntity.builder().status(TicketStatus.CHANGED).build();
        when(stubTicketRepository.findById(any())).thenReturn(Optional.of(successTicket));
        when(stubTicketRepository.saveAndFlush(any())).thenReturn(changedTicket);

        TicketMessageSender stubTicketMessageSender = Mockito.mock(TicketMessageSender.class);
        when(stubTicketMessageSender.send(any(TicketChangeMessage.class))).thenReturn(true);

        ShippingFeign stubShippingFeign = Mockito.mock(ShippingFeign.class);
        when(stubShippingFeign.lockSeat(any()))
                .thenReturn(LockSetResponseFeignDto
                        .builder()
                        .code(ShippingStatus.SEAT_LOCKED.toString())
                        .message("改签成功")
                        .build());

        TicketService ticketService = new TicketService(stubTicketRepository, stubTicketMessageSender, stubShippingFeign);


        TicketChangeModel ticketChangeModel= TicketChangeModel.builder().ticketId(123L).planeId("TC-123-134").build();
        TicketChangeResultModel ticketChangeResultModel = ticketService.change(ticketChangeModel);
        assertEquals(TicketStatus.CHANGED, ticketChangeResultModel.getStatus());

    }
}