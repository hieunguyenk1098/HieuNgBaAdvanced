package com.vti.finalexam.entity.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TaskRequest {
    @NotNull
    private Integer id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private Integer taskStatusId;
    private Integer workspaceId;


}
