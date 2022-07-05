package command.center.controller;


import command.center.service.ClientServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard-list")
public class DashboardController {

    @Autowired
    ClientServiceService clientServiceService;


    @GetMapping()
    public Map getDashBoardData() {
        return clientServiceService.getDashBoardData();
    }

}
