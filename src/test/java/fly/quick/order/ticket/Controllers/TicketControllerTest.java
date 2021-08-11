package fly.quick.order.ticket.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.quick.order.ticket.BasePojo.ChangeTicketStatus;
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
        TicketChangeResultModel changeTicketSucessModel = TicketChangeResultModel
                .builder().status(ChangeTicketStatus.CHANGED).build();
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


    @Test
    public void should_change_ticket_fail_given_target_plane_fly_at_before_now() throws Exception {

        Long ticketId = 123L;
        TicketChangeResultModel changeTicketSucessModel = TicketChangeResultModel
                .builder()
                .status(ChangeTicketStatus.TARGET_PLAN_TIME_NO_VALID).build();
        Mockito.when(ticketService.change(any()))
               .thenReturn(changeTicketSucessModel);

        ChangeTicketRequestDto request = ChangeTicketRequestDto
                .builder()
                .targetPlanId("TC001")
                .targetPlanFlyAt(new Date(System.currentTimeMillis()))
                .build();
        String requestJson = objectMapper.writeValueAsString(request);


        mockMvc.perform(post("/flight/tickets/" + ticketId + "/change")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.code", is("TARGET_PLAN_TIME_NO_VALID")))
               .andExpect(jsonPath("$.message", is("改签失败，目标航班已起飞")));
    }

    @Test
    public void should_change_ticket_fail_given_shipping_service_unavailable() throws Exception {

        Long ticketId = 123L;
        TicketChangeResultModel changeTicketSucessModel = TicketChangeResultModel
                .builder()
                .status(ChangeTicketStatus.SHIPPING_SERVICE_UNAVAILABLE ).build();
        Mockito.when(ticketService.change(any()))
               .thenReturn(changeTicketSucessModel);

        ChangeTicketRequestDto request = ChangeTicketRequestDto
                .builder()
                .targetPlanId("TC001")
                .targetPlanFlyAt(new Date(System.currentTimeMillis() + 10000))
                .build();
        String requestJson = objectMapper.writeValueAsString(request);


        mockMvc.perform(post("/flight/tickets/" + ticketId + "/change")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.code", is("SHIPPING_SERVICE_UNAVAILABLE")))
               .andExpect(jsonPath("$.message", is("改签失败，无法锁定改签座位")));
    }
}