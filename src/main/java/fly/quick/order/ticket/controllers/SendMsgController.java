package fly.quick.order.ticket.Controllers;

import fly.quick.order.ticket.MQ.MessageSender;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "测试RabbitMQ")
@RestController
@RequestMapping("/message")
public class SendMsgController {

    private static Logger log = LoggerFactory.getLogger(SendMsgController.class);

    @Autowired
    private MessageSender messageSender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public boolean send(String msg) {
        try {
            messageSender.send(msg);
        } catch (AmqpException e) {
            log.error("发送消息异常：{}", e);
            return false;
        }
        return true;
    }
}