package iss.ad.project.webchart.controller;

import iss.ad.project.webchart.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DashboardController {
    private final LogService logService;

    @Autowired
    public DashboardController(LogService logService){
        this.logService = logService;
    }

    @GetMapping("/")
    public RedirectView redirectToDashboard() {
        return new RedirectView("/dashboard");
    }
    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }
    
}
