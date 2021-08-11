package fly.quick.order.ticket.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.quick.order.ticket.BasePojo.StatusCode;
import fly.quick.order.ticket.ControllerDtos.ChangeTicketRequestDto;
import fly.quick.order.ticket.Entity.TicketEntity;
import fly.quick.order.ticket.Repository.TicketRepository;
import fly.quick.order.ticket.bases.TestBase;
import org.aspectj.apache.bcel.util.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import wiremock.org.eclipse.jetty.util.DateCache;

import java.util.Date;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TicketControllerTest extends TestBase {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TicketRepository ticketRepository;

    @Test
    public void should_return_correct_ticket_given_an_exist_ticket_id_when_the_ticket_exist() throws Exception {

        TicketEntity ticketEntity = TicketEntity.builder()
                                                .userId(123L)
                                                .createdAt(new Date())
                                                .status(StatusCode.SUCCESS)
                                                .meterNo("0001-11-001")
                                                .build();

        TicketEntity savedTicketEntity = ticketRepository.saveAndFlush(ticketEntity);

        mockMvc.perform(get("/flight/tickets/"+savedTicketEntity.getId()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.userId", is(ticketEntity.getUserId().intValue())));
    }
}