package iss.ad.project.webchart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import iss.ad.project.webchart.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.*;
import org.springframework.ui.Model;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class DashboardController {
    private final LogService logService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public DashboardController(LogService logService){
        this.logService = logService;
    }

    @GetMapping("/")
    public RedirectView redirectToDashboard() {
        return new RedirectView("/backtracks");
    }
    @GetMapping("/backtracks")
    public String getBacktracks(Model model) throws JsonProcessingException {
        Map<String, Map<String, Integer>> backtracks = logService.fetchBacktracksPerUserForAllModelsTasks();
        Map<String, Double> averages = logService.computeModelAverages(backtracks);

        String jsonBacktracks = objectMapper.writeValueAsString(backtracks);
        String jsonAverages = objectMapper.writeValueAsString(averages);

        model.addAttribute("modelTaskBacktracksJson", jsonBacktracks);
        model.addAttribute("modelAveragesJson", jsonAverages);

        System.out.println(jsonBacktracks);
        System.out.println(jsonAverages);

        return "dashboard";
    }


}
