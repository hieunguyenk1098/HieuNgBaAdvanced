package com.vti.finalexam.service;


import com.vti.finalexam.entity.WorkspaceEntity;
import com.vti.finalexam.entity.dto.AccountEntity;
import com.vti.finalexam.entity.dto.request.WorkspaceRequest;
import com.vti.finalexam.entity.dto.response.WorkspaceResponse;
import com.vti.finalexam.repository.AccountRepository;
import com.vti.finalexam.repository.TaskRepository;
import com.vti.finalexam.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService {
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;


    public WorkspaceResponse createWorkspace(WorkspaceRequest request) {

        WorkspaceEntity workspace = new WorkspaceEntity();
        workspace.setName(request.getWorkspaceName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // lấy thông tin user hiện tại đang login vào
        AccountEntity account = accountRepository.findByEmail(authentication.getName());   // lay account hien tai
        workspace.setAccount(account);

        workspaceRepository.save(workspace);
        return new WorkspaceResponse(workspace.getId(), workspace.getName(), 0L);
    }

//    public List<WorkspaceResponse> getCurrentWorkspace() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//
//        List<WorkspaceResponse> response = workspaceRepository.getWorkspaceResponseByUserEmail(email);
//
//        for (WorkspaceResponse workspaceReponse : response) {
//            int numOfTask = taskRepository.countByWorkspaceId(workspaceReponse.getId());
//            workspaceReponse.setNumberOfTask(numOfTask);
//        }
//
//        return response;
//    }


    public List<WorkspaceResponse> getCurrentWorkspace() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<WorkspaceResponse> response = workspaceRepository.getWorkspaceResponseByUserEmail(email);

        return response;
    }


}


