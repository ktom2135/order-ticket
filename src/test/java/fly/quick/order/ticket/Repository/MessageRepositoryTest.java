package fly.quick.order.ticket.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fly.quick.order.ticket.BasePojo.MessageType;
import fly.quick.order.ticket.BasePojo.SendStatus;
import fly.quick.order.ticket.BasePojo.TicketAction;
import fly.quick.order.ticket.Entity.MessageEntity;
import fly.quick.order.ticket.bases.TestBase;
import fly.quick.order.ticket.messages.TicketChangeMessage;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest extends TestBase {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public  void should_save_no_send_message_success() throws JsonProcessingException {

        TicketChangeMessage ticketChangeMessage = TicketChangeMessage.builder().ticketAction(TicketAction.CHANGE)
                                                       .targetPlaneId("XXX-11-001").build();

        String content = objectMapper.writeValueAsString(ticketChangeMessage);

        MessageEntity messageEntity = MessageEntity.builder().messageType(MessageType.CHANGE_TICKET)
                                           .sendStatus(SendStatus.NO_SEND)
                                           .content(content).build();

        MessageEntity savedMessage = messageRepository.saveAndFlush(messageEntity);

        assertEquals(content, savedMessage.getContent());
        assertEquals(MessageType.CHANGE_TICKET, savedMessage.getMessageType());
        assertEquals(SendStatus.NO_SEND, savedMessage.getSendStatus());
    }
}