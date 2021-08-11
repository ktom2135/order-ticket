package fly.quick.order.ticket.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.quick.order.ticket.BasePojo.TicketStatus;
import fly.quick.order.ticket.ControllerDtos.ChangeTicketRequestDto;
import fly.quick.order.ticket.Entity.TicketEntity;
import fly.quick.order.ticket.Model.TicketChangeResultModel;
import fly.quick.order.ticket.Repository.TicketRepository;
import fly.quick.order.ticket.Services.TicketService;
import fly.quick.order.ticket.bases.TestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TicketControllerTest extends TestBase {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TicketRepository ticketRepository;

    @MockBean
    TicketService ticketService;

    @Test
    public void should_change_ticket_success_given_ticket_service_change_ticket_success() throws Exception {

        Long ticketId = 123L;
        TicketChangeResultModel changeTicketSucessModel = TicketChangeResultModel.builder().status(TicketStatus.SUCCESS).build();
        Mockito.when(ticketService.change(any()))
               .thenReturn(changeTicketSucessModel);

        ChangeTicketRequestDto request = ChangeTicketRequestDto.builder().targetPlanId("TC001").build();
        String requestJson = objectMapper.writeValueAsString(request);


        mockMvc.perform(post("/flight/tickets/" + ticketId + "/change")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code", is("SUCCESS")))
               .andExpect(jsonPath("$.message", is("改签成功，将在60分钟内出票")));
    }
}