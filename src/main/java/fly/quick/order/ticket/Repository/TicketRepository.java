package fly.quick.order.ticket.Repository;

import fly.quick.order.ticket.Entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository< TicketEntity,Long> {
}