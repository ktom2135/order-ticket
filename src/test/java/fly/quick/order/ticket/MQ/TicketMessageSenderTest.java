package fly.quick.order.ticket.MQ;

import fly.quick.order.ticket.BasePojo.TicketAction;
import fly.quick.order.ticket.messages.TicketChangeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketMessageSenderTest {

    @Test
    public void should_return_true_given_send_message_success() {

        RabbitTemplate stubTabbitTemplate = Mockito.mock(RabbitTemplate.class);
        TicketMessageSender ticketMessageSender = new TicketMessageSender(stubTabbitTemplate);
        boolean sendMessageResult = ticketMessageSender.send(TicketChangeMessage.builder()
                                                                                .ticketAction(TicketAction.CHANGE)
                                                                                .targetPlaneId("001-ac-110")
                                                                                .build());
        assertTrue(sendMessageResult);
    }
}