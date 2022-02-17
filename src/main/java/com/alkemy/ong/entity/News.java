package com.alkemy.ong.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE news SET isActive = false WHERE id=?")
@Where(clause = "isActive=true")
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    private String content;
    @Column(nullable = false)
    private String image;
    @Column(name = "created_date", updatable = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "modified_date")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "is_active")
    private boolean isActive=Boolean.TRUE;

    /*
    @ManyToMany(mappedBy = "news", fetch = FetchType.LAZY)
    private List<Categories> categoryId = new ArrayList<>();
     */
}
