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
        return new RedirectView("/dashboard");
    }
    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }


    @GetMapping("/backtracks")
    public String getBacktracks(Model model) throws JsonProcessingException {
        Map<String, Map<String, Integer>> modelTaskBacktracks = logService.fetchBacktracksPerUserForAllModelsTasks();
        String jsonBacktracks = objectMapper.writeValueAsString(modelTaskBacktracks);
        model.addAttribute("modelTaskBacktracksJson", jsonBacktracks);
        System.out.println(jsonBacktracks);

        // Printing the modelTaskBacktracks
//        for (Map.Entry<String, Map<String, Integer>> modelTaskEntry : modelTaskBacktracks.entrySet()) {
//            String modelTaskKey = modelTaskEntry.getKey();
//            Map<String, Integer> userBacktracks = modelTaskEntry.getValue();
//            System.out.println("ModelTask combination: " + modelTaskKey);
//            for (Map.Entry<String, Integer> userEntry : userBacktracks.entrySet()) {
//                String userName = userEntry.getKey();
//                Integer backtrackCount = userEntry.getValue();
//                System.out.println("User: " + userName + " | Backtracks: " + backtrackCount);
//            }
//            System.out.println("-------------------------------");
//        }
        return "dashboard";
    }
}
