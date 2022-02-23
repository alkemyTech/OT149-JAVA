package com.alkemy.ong.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@SQLDelete(sql = "UPDATE news SET isActive = false WHERE id=?")
@Where(clause = "isActive=true")
@Table(name = "news")
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    private String content;
    @Column(nullable = false)
    private String image;
    @Column(name = "created_at", updatable = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "is_active")
    private boolean isActive=Boolean.TRUE;

    @OneToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category categoryId;
}
