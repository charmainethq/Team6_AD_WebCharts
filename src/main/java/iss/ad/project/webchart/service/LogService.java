package iss.ad.project.webchart.service;

import iss.ad.project.webchart.model.LogEntry;
import iss.ad.project.webchart.repo.LogRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    // check if length is exactly 1 easier than using Optional?
    public List<LogEntry> getUniqueLogEntry(String name, int taskId, int modelId) {
        return logRepo.findByNameAndTaskIdAndModelId(name, taskId, modelId);
    }


    public Map<String, Map<String, Integer>> fetchBacktracksPerUserForAllModelsTasks() {
        Map<String, Map<String, Integer>> modelTaskBacktracks = new HashMap<>();

        for (int modelId = 1; modelId <= 2; modelId++) {
            for (int taskId = 1; taskId <= 3; taskId++) {
                List<LogEntry> entries = logRepo.findByModelIdAndTaskId(modelId, taskId);

                // Sort entries by name and orderValue
                entries.sort(Comparator.comparing(LogEntry::getName).thenComparing(LogEntry::getOrderValue));

                Map<String, Integer> backtracks = new HashMap<>();
                String previousName = null;
                int previousLayer = 0;

                for (LogEntry entry : entries) {
                    if (!entry.getName().equals(previousName)) {
                        previousLayer = entry.getLayer();
                        previousName = entry.getName();
                    } else {
                        if (previousLayer == 2 && entry.getLayer() == 1 || previousLayer == 3 && entry.getLayer() == 2 ) {
                            backtracks.put(entry.getName(), backtracks.getOrDefault(entry.getName(), 0) + 1);
                        }
                        previousLayer = entry.getLayer();
                    }
                }

                String key = "model" + modelId + "task" + taskId;
                modelTaskBacktracks.put(key, backtracks);
            }
        }
        return modelTaskBacktracks;
    }

    public Map<String, Double> computeModelAverages(Map<String, Map<String, Integer>> modelTaskBacktracks) {
        Map<String, Double> modelAverages = new HashMap<>();

        for (int modelId = 1; modelId <= 2; modelId++) {
            Map<String, Integer> modelTotalBacktracks = new HashMap<>();
            Map<String, Integer> modelTaskCount = new HashMap<>();

            for (int taskId = 1; taskId <= 3; taskId++) {
                String key = "model" + modelId + "task" + taskId;

                modelTaskBacktracks.get(key).forEach((user, backtracks) -> {
                    modelTotalBacktracks.put(user, modelTotalBacktracks.getOrDefault(user, 0) + backtracks);
                    modelTaskCount.put(user, modelTaskCount.getOrDefault(user, 0) + 1);
                });
            }

            for (String user : modelTotalBacktracks.keySet()) {
                double average = (double) modelTotalBacktracks.get(user) / modelTaskCount.get(user);
                modelAverages.put("model" + modelId + user, average);
            }
        }

        return modelAverages;
    }


}
