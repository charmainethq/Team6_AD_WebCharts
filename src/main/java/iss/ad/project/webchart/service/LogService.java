package iss.ad.project.webchart.service;

import iss.ad.project.webchart.model.LogEntry;
import iss.ad.project.webchart.repo.LogRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogRepo logRepo;
    @Autowired
    public LogService(LogRepo logRepo){
        this.logRepo = logRepo;
    }

    public List<String> getDistinctNames() {
        return logRepo.findDistinctNames();
    }
    
    public List<Integer> getDistinctByTaskId(){
    	return logRepo.findDistinctTaskId();
    }
    
    public List<Integer> getDistinctByModelId(){
    	return logRepo.findDistinctModelId();
    }
    
    // check if length is exactly 1 easier than using Optional?
    public List<LogEntry> getUniqueLogEntry(String name, int taskId, int modelId) {
        return logRepo.findByNameAndTaskIdAndModelId(name, taskId, modelId);
    }
    
    public List<LogEntry> getAll(){
        return logRepo.findAll();

    }
}
