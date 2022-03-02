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

@Data
@Entity
@NoArgsConstructor
@SQLDelete(sql = "UPDATE organizations SET is_active=false WHERE id = ?")
@Where(clause="is_active=true")
@Table(name = "organizations")
public class Organization {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;	
	private String name;
	private String images;
	private String address;
	private int phone;
	private String email;
	private  String welcomeText;
	private String aboutUsText;
	
	
	@Column( name ="updated_at", nullable = false)
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate updatedAt;
	
	@Column(name= "created_at", updatable= false ,nullable=false)
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate createdAt;	

	@Column(name ="is_active")
	private boolean isActive = Boolean.TRUE;

}
