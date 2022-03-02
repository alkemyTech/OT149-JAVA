package com.alkemy.ong.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Where(clause = "is_active = true")
@SQLDelete(sql = "UPDATE testimonials SET is_active=false WHERE id = ?")
@NoArgsConstructor
@Table(name = "testimonials")
public class Testimonial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String image;
	private String content;
	@Column(nullable = false, updatable = false)
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate createdAt;
	@LastModifiedDate
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate updatedAt;
	private boolean isActive = true;
}
