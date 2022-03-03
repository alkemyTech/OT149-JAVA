package com.alkemy.ong.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Where(clause = "is_active = true")
@SQLDelete(sql = "UPDATE testimonials SET is_active=false WHERE id = ?")
@NoArgsConstructor
@Table(name = "testimonials")
public class Testimonial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String image;
	private String content;

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
