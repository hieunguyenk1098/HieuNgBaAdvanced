package com.vti.finalexam.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceResponse {
    private Integer id;

    private String workspaceName;

    private Long numberOfTask;
}
