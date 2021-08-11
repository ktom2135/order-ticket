package fly.quick.order.ticket.Controllers;

import fly.quick.order.ticket.BasePojo.TicketStatus;
import fly.quick.order.ticket.ControllerDtos.TicketChangeRequestDto;
import fly.quick.order.ticket.ControllerDtos.TicketChangeResponseDto;
import fly.quick.order.ticket.ControllerDtos.TicketResponseDto;
import fly.quick.order.ticket.Model.TicketChangeModel;
import fly.quick.order.ticket.Model.TicketChangeResultModel;
import fly.quick.order.ticket.Model.TicketModel;
import fly.quick.order.ticket.Services.TicketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "机票操作API")
@RequestMapping("/flight")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("tickets/{tid}/change")
    public ResponseEntity<TicketChangeResponseDto> change(Long tid, @RequestBody TicketChangeRequestDto request) {
        TicketChangeModel model = TicketChangeModel.builder().planeId(request.getPlaneId()).build();

        TicketChangeResultModel ticketChangeResultModel = ticketService.change(tid, model);
        TicketChangeResponseDto response = null;
        if(ticketChangeResultModel.status == TicketStatus.SUCCESS) {
             response = TicketChangeResponseDto.builder().code("SUCCESS").message("改签成功，将在60分钟内出票").build();
        }
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/tickets/{tid}")
    public ResponseEntity<TicketResponseDto> getTicketById(@PathVariable Long tid) {

        TicketModel ticketModel = ticketService.getTicketById(tid);

        TicketResponseDto ticketResponseDto = null;

        if (ticketModel != null) {
            ticketResponseDto = new TicketResponseDto(ticketModel);

            return ResponseEntity.ok().body(ticketResponseDto);
        }

        return ResponseEntity.notFound().build();
    }
}