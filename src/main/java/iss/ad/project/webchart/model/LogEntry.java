package iss.ad.project.webchart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class LogEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    private int age;

    private Boolean gender;

    private int taskId;

    private int modelId;

    private int layer;

    private String genre;

    private int thinkTime;

    private int orderValue;

    private int successValue;

}
