package fly.quick.order.ticket.Services;

import fly.quick.order.ticket.Entity.TicketEntity;
import fly.quick.order.ticket.Model.TicketChangeModel;
import fly.quick.order.ticket.Model.TicketChangeResultModel;
import fly.quick.order.ticket.Model.TicketModel;
import fly.quick.order.ticket.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

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

    public TicketChangeResultModel change(Long tid, TicketChangeModel model) {
        return null;
    }
}
