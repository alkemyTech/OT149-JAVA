package com.alkemy.ong.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "slides")
public class Slide {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "imageUrl", nullable = false)
	private String imageUrl;

	@Lob
	@Column(name = "text", nullable = false)
	private String text;
	
	@Column(name = "slide_order", nullable = false)
	private Integer order;

	@ManyToOne
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organizationId;

	@Column(name = "created_at", nullable = false, updatable = false)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@CreationTimestamp
	private LocalDate createdAt;

	@Column(name = "updated_at")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@UpdateTimestamp
	private LocalDate updatedAt;

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

}
