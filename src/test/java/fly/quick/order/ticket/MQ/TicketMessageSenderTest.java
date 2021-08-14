package fly.quick.order.ticket.MQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.quick.order.ticket.BasePojo.TicketAction;
import fly.quick.order.ticket.messages.TicketChangeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;

class TicketMessageSenderTest {

    @Test
    public void should_return_true_given_send_message_success() {

        RabbitTemplate stubTabbitTemplate = Mockito.mock(RabbitTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();
        TicketMessageSender ticketMessageSender = new TicketMessageSender(objectMapper, stubTabbitTemplate);
        boolean sendMessageResult = ticketMessageSender.send(TicketChangeMessage.builder()
                                                                                .ticketAction(TicketAction.CHANGE)
                                                                                .targetPlaneId("001-ac-110")
                                                                                .build());
        assertTrue(sendMessageResult);
    }

    @Test
    public void should_return_false_given_send_message_fail() {

        RabbitTemplate stubTabbitTemplate = Mockito.mock(RabbitTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Mockito.doThrow(AmqpException.class).when(stubTabbitTemplate).convertAndSend(anyString(), anyString(), java.util.Optional.ofNullable(any()));
        TicketMessageSender ticketMessageSender = new TicketMessageSender(objectMapper, stubTabbitTemplate);
        boolean sendMessageResult = ticketMessageSender.send(TicketChangeMessage.builder()
                                                                                .ticketAction(TicketAction.CHANGE)
                                                                                .targetPlaneId("001-ac-110")
                                                                                .build());
        assertFalse(sendMessageResult);
    }
}