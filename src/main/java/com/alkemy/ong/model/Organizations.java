package com.alkemy.ong.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="organizations")
@SQLDelete(sql = "UPDATE organizations SET is_active=true WHERE id = ?")
@Where(clause="isActive=false")
public class Organizations {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message ="Name can't be null")
	private String name;
	@NotNull
	private String images;
	private String addres;
	private int phone;
	@NotNull(message ="Email can't be null")
	private String email;
	@NotNull
	private  String welcomeText;
	private String aboutUsText;
	@Column( name ="modified_date")
	@UpdateTimestamp
	private LocalDate modifiedDate;
	@Column(name= "created_date")
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name= "removed_date")
	private LocalDate removedDate;
	
	@Column(name ="is_active")
	private boolean isActive = Boolean.TRUE;
	
	
	public Organizations(String name, String images, String addres, int phone) {
		super();
		this.name = name;
		this.images = images;
		this.addres = addres;
		this.phone = phone;
	}

}
