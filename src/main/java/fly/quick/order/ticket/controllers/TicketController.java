package fly.quick.order.ticket.Controllers;

import fly.quick.order.ticket.ControllerDtos.ChangeTicketRequestDto;
import fly.quick.order.ticket.ControllerDtos.ChangeTicketResponseDto;
import fly.quick.order.ticket.BasePojo.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "机票操作API")
@RequestMapping("/flight")
public class TicketController {

    @PostMapping("/tickets/{tid}/change")
    public ResponseEntity<ChangeTicketResponseDto> change(String tid, @RequestBody ChangeTicketRequestDto requestDto) {


        ChangeTicketResponseDto responseDto = ChangeTicketResponseDto.builder()
                                                               .code(StatusCode.SUCCESS)
                                                               .message("改签成功，将在60分钟内出票")
                                                               .build();
        return ResponseEntity.created(null).body(responseDto);
    }
}