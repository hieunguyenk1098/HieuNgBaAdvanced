package com.vti.finalexam.entity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "taskstatus")
public class TaskStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


}
