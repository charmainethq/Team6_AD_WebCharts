package iss.ad.project.webchart.controller;

import iss.ad.project.webchart.model.LogEntry;
import iss.ad.project.webchart.service.LogService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DashboardController {
    private final LogService logService;

    @Autowired
    public DashboardController(LogService logService){
        this.logService = logService;
    }

//    @GetMapping("/")
//    public RedirectView redirectToDashboard() {
//        return new RedirectView("/dashboard");
//    }
    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }
    @GetMapping("/logView")
    public String LogViewForm(Model model) {
    	List<LogEntry> logs = logService.getAll();
    	model.addAttribute("logs",logs );
    	model.addAttribute("userList", logService.getDistinctNames());
    	model.addAttribute("taskList", logService.getDistinctByTaskId());
    	model.addAttribute("modelList",logService.getDistinctByModelId());
    	return "logView";
    }
    
//    @PostMapping("/logView")
//    public String LogView(@RequestParam("userName") String userName,
//                          @RequestParam("model_id") int model_id,
//                          @RequestParam("task_id") int task_id,
//                          Model model) {
//        List<LogEntry> logList = logService.getUniqueLogEntry(userName, task_id, model_id);
//        System.out.println(userName);
//        System.out.println(model_id);
//        System.out.println(task_id);
//        System.out.println("Test");
//        model.addAttribute("logList", logList);
//        return "logView";
//    }
//    @PostMapping("/logView")
//    public ResponseEntity<List<LogEntry>> LogView(@RequestParam("userName") String userName,
//                                                  @RequestParam("model_id") int model_id,
//                                                  @RequestParam("task_id") int task_id) {
//        List<LogEntry> logList = logService.getUniqueLogEntry(userName, task_id, model_id);
//        System.out.println(userName);
//        System.out.println(model_id);
//        System.out.println(task_id);
//        if (logList.isEmpty()) {
//            System.out.println("Log list is empty.");
//        } else {
//            System.out.println("Log list size: " + logList.size());
//        }
//        return ResponseEntity.ok(logList);
//    }

}
