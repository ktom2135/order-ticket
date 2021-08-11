package fly.quick.order.ticket.Repository;

import fly.quick.order.ticket.BasePojo.TicketStatus;
import fly.quick.order.ticket.Entity.TicketEntity;
import fly.quick.order.ticket.bases.TestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TicketRepositoryTest extends TestBase {
    @Autowired
    TicketRepository ticketRepository;

    @Test
    public void should_save_ticket_success_given_ticket_status_is_changed(){
        TicketEntity changedTicket = TicketEntity.builder()
                                                 .userId(1234L)
                                                 .status(TicketStatus.CHANGED).build();

        TicketEntity savedTicket = ticketRepository.saveAndFlush(changedTicket);
        assertNotNull(savedTicket);
        assertEquals(TicketStatus.CHANGED, savedTicket.getStatus());
    }
}