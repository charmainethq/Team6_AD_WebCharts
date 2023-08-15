package iss.ad.project.webchart.repo;

import iss.ad.project.webchart.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LogRepo extends JpaRepository<LogEntry, Long> {


    @Query("SELECT DISTINCT le.name FROM LogEntry le")
    List<String> findDistinctNames();
    
    @Query("SELECT le FROM LogEntry le WHERE le.name = :name AND le.taskId = :taskId AND le.modelId = :modelId")
    List<LogEntry> findByNameAndTaskIdAndModelId(@Param("name") String name, @Param("taskId") int taskId, @Param("modelId") int modelId);
    
    @Query("SELECT DISTINCT le.taskId FROM LogEntry le")
    List<Integer> findDistinctTaskId();
    
    @Query("SELECT DISTINCT le.modelId FROM LogEntry le")
    List<Integer> findDistinctModelId();
}
