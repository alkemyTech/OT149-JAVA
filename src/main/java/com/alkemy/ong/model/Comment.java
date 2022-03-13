package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE comments SET is_active = false WHERE id=?")
@Where(clause = "is_active=true")
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String body;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User usersId;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "news_id")
	private New newsId;

	@Column(updatable = false, nullable = false)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@UpdateTimestamp
	private Timestamp updatedAt;

	private boolean isActive = true;
}
