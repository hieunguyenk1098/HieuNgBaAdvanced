package com.vti.finalexam.entity;

import com.vti.finalexam.entity.enumtype.DepartmentType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "total_member")
    private Long totalMember;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDate createdDate;

    @OneToMany(mappedBy = "department")
    private List<Account> accountList;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalMember() {
        return totalMember;
    }

    public void setTotalNumber(Long totalMember) {
        this.totalMember = totalMember;
    }

    public DepartmentType getType() {
        return type;
    }

    public void setType(DepartmentType type) {
        this.type = type;
    }

    public LocalDate getCreatedDate() { return createdDate; }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
