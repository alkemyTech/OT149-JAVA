package com.alkemy.ong.model;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Where(clause = "isActive = true")
@SQLDelete(sql = "UPDATE users_table SET isActive=false WHERE id = ?")
public class Members {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	private String name;

	private String facebookUrl;
	private String instagramUrl;
	private String linkedinUrl;
	@NonNull
	private String image;
	private String description;
	@CreationTimestamp
	private LocalDateTime createAt;
	@UpdateTimestamp
	private LocalDateTime updateAt;
	@Column(name = "is_active", nullable = false)
	private boolean isActive = true;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Members members = (Members) o;
		return id != null && Objects.equals(id, members.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
