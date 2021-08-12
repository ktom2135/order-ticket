package fly.quick.order.ticket.Repository;

import fly.quick.order.ticket.Entity.MessageEntity;
import fly.quick.order.ticket.Entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}