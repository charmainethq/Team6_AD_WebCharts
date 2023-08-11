package iss.ad.project.webchart.controller;


import iss.ad.project.webchart.service.LogService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserChartController {

    private final LogService logService;

    @Autowired
    public UserChartController(LogService logService){
        this.logService = logService;
    }
}
