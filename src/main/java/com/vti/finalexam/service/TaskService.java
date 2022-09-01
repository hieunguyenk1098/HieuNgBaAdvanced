package com.vti.finalexam.service;

import com.vti.finalexam.entity.TaskEntity;
import com.vti.finalexam.entity.TaskStatusEntity;
import com.vti.finalexam.entity.dto.request.TaskRequest;
import com.vti.finalexam.entity.dto.response.TaskResponse;
import com.vti.finalexam.repository.TaskRepository;
import com.vti.finalexam.repository.TaskStatusRepository;
import com.vti.finalexam.repository.WorkspaceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;


    public List<TaskResponse> getTasksByWorkspace(Integer workspaceId) {
        List<TaskResponse> responses = new ArrayList<>();
        List<TaskEntity> tasksByWorkspace = taskRepository.findByWorkspaceId(workspaceId);
        for (TaskEntity tasksEntity : tasksByWorkspace) {
            TaskResponse response = new TaskResponse();
            BeanUtils.copyProperties(tasksEntity, response);
            response.setWorkspaceId(workspaceId);
            response.setStatusId((tasksEntity.getTaskStatus().getId()));
            response.setStatusName(tasksEntity.getWorkspace().getName());
            responses.add(response);

        }
        return responses;
    }


    public TaskResponse updateTaskResponse(Integer id, TaskRequest request) {
        Optional<TaskEntity> taskResponseById = taskRepository.findById(id);
       if (taskResponseById.isPresent()) {
           TaskEntity task = taskResponseById.get();

            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setDueDate(request.getDueDate());
            task.setStartDate(request.getStartDate());

            TaskStatusEntity status = taskStatusRepository.getReferenceById(request.getTaskStatusId());
            task.setTaskStatus(status);

            taskRepository.save(task);

            TaskResponse response = new TaskResponse();
            BeanUtils.copyProperties(task, response);
            return response;
        }
        return null;
    }


    public TaskEntity addTask(TaskEntity taskEntity) {
        TaskEntity task = new TaskEntity();
        task.setTitle(taskEntity.getTitle());
        task.setDescription(taskEntity.getDescription());
        task.setDueDate(taskEntity.getDueDate());
        task.setStartDate(taskEntity.getStartDate());

//        WorkspaceEntity workspace = workspaceRepository.getReferenceById(taskEntity.getWorkspace().getId());
//        task.setWorkspace(taskEntity.getWorkspace());

        return taskRepository.save(task);
    }
}
