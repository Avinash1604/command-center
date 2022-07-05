package command.center.controller;

import command.center.service.ZenotiClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AppointmentController {

    @Autowired
    ZenotiClientService zenotiClientService;

    @GetMapping("appointments")
    public Map getAppointments() {
        return zenotiClientService.getAppointments();
    }

}
