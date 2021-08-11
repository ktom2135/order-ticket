package fly.quick.order.ticket.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.quick.order.ticket.ControllerDtos.ChangeTicketRequestDto;
import fly.quick.order.ticket.bases.TestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TicketControllerTest extends TestBase {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void should_change_ticket_success_given_services_return_ok() throws Exception {

        ChangeTicketRequestDto request  = ChangeTicketRequestDto
                .builder()
                .targetPlanId("TC002")
                .build();
        String json = objectMapper.writeValueAsString(request);


        mockMvc.perform(post("/flight/tickets/TC001/change")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
    }
}