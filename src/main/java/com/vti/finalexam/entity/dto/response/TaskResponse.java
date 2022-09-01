package com.vti.finalexam.entity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
public class TaskResponse {
    private Integer id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;

    private Integer workspaceId;

    private Integer statusId;

    private String statusName;



}
