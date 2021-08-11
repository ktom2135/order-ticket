package fly.quick.order.ticket.Services;

import fly.quick.order.ticket.BasePojo.TicketAction;
import fly.quick.order.ticket.BasePojo.TicketStatus;
import fly.quick.order.ticket.Entity.TicketEntity;
import fly.quick.order.ticket.Feign.Dtos.LockSeatRequestFeignDto;
import fly.quick.order.ticket.Feign.Dtos.LockSetResponseFeignDto;
import fly.quick.order.ticket.Feign.Dtos.ShippingFeign;
import fly.quick.order.ticket.MQ.TicketMessageSender;
import fly.quick.order.ticket.Model.TicketChangeModel;
import fly.quick.order.ticket.Model.TicketChangeResultModel;
import fly.quick.order.ticket.Model.TicketModel;
import fly.quick.order.ticket.Repository.TicketRepository;
import fly.quick.order.ticket.messages.TicketChangeMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {

    final TicketRepository ticketRepository;
    final TicketMessageSender ticketMessageSender;
    final ShippingFeign shippingFeign;

    public TicketModel getTicketById(Long tid) {
        Optional<TicketEntity> ticketEntityOptional = ticketRepository.findById(tid);

        return ticketEntityOptional.map(ticketEntity -> TicketModel.builder()
                                                                   .id(ticketEntity.getId())
                                                                   .createdAt(ticketEntity.getCreatedAt())
                                                                   .userId(ticketEntity.getUserId())
                                                                   .meterNo(ticketEntity.getMeterNo())
                                                                   .status(ticketEntity.getStatus())
                                                                   .build()).orElse(null);
    }

    @Transactional
    public TicketChangeResultModel change(TicketChangeModel model) {
        TicketEntity ticketEntity = ticketRepository.findById(model.getTicketId()).get();
        ticketEntity.setStatus(TicketStatus.CHANGED);
        ticketRepository.saveAndFlush(ticketEntity);
        ticketMessageSender.send(generateTicketChangeMessage(model));
        LockSetResponseFeignDto lockSetResponseFeignDto = shippingFeign.lockSeat(generateLockSeatRequestFeignDto(model));
        return TicketChangeResultModel.builder().status(TicketStatus.CHANGED).build();
    }

    private TicketChangeMessage generateTicketChangeMessage(TicketChangeModel model) {
        return TicketChangeMessage.builder().ticketAction(TicketAction.CHANGE).build();
    }

    private LockSeatRequestFeignDto generateLockSeatRequestFeignDto(TicketChangeModel model) {
        return LockSeatRequestFeignDto.builder().ticketId(model.getTicketId()).build();
    }
}
