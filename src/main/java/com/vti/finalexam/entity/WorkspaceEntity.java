package com.vti.finalexam.entity;

import com.vti.finalexam.entity.dto.AccountEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "workspaces")
public class WorkspaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    @ManyToOne
    private AccountEntity account;


}
