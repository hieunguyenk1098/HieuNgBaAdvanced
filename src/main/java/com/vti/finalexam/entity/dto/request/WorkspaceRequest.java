package com.vti.finalexam.entity.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WorkspaceRequest {

    @NotNull
    @NotBlank
    private String workspaceName;

}
