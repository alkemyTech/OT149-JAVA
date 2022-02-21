package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    private String photo;

    @Column(name ="created_at",updatable=false, nullable=false)
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name="updated_at",nullable=false)
    @UpdateTimestamp
    private LocalDate modifiedDate;

    @Column(name="is_active")
    private boolean isActive = Boolean.TRUE;


    @ManyToMany(fetch= FetchType.EAGER)
	private Collection<Role> roleId = new ArrayList<>();
}
