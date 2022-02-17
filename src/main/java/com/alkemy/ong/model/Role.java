package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET is_active = false WHERE id = ? ")
@Where(clause = "is_active = true")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate modifiedDate;

    @Column(name = "is_active")
    private Boolean isActive = Boolean.TRUE;
}
