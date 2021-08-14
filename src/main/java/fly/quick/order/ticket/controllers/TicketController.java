package fly.quick.order.ticket.Controllers;

import fly.quick.order.ticket.BasePojo.ChangeTicketStatus;
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
    public ResponseEntity<TicketChangeResponseDto> change(@PathVariable Long tid, @RequestBody TicketChangeRequestDto request) {
        TicketChangeModel model = TicketChangeModel.builder()
                                                   .ticketId(tid)
                                                   .targetPlaneFlyAt(request.getTargetPlaneFlyAt())
                                                   .targetPlaneId(request.getPlaneId()).build();

        TicketChangeResultModel ticketChangeResultModel = ticketService.change(model);
        TicketChangeResponseDto response = null;
        if (ticketChangeResultModel.status == ChangeTicketStatus.CHANGED) {
            response = TicketChangeResponseDto.builder().code("SUCCESS").message("改签成功，将在60分钟内出票").build();

            return ResponseEntity.ok().body(response);
        } else if(ticketChangeResultModel.status == ChangeTicketStatus.TARGET_PLAN_TIME_NO_VALID) {
            response = TicketChangeResponseDto.builder()
                                              .code("TARGET_PLAN_TIME_NO_VALID")
                                              .message("改签失败，目标航班已起飞")
                                              .build();

            return ResponseEntity.badRequest().body(response);
        } else {
            response = TicketChangeResponseDto.builder()
                                              .code("SHIPPING_SERVICE_UNAVAILABLE")
                                              .message("改签失败，无法锁定改签座位")
                                              .build();

            return ResponseEntity.badRequest().body(response);
        }
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