package com.nit.model.prod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pid;
	@NonNull
	@Column(length = 20)
	private String pName;
	@NonNull
	private Double price;
	@NonNull
	private Integer qantity;
	@NonNull
	@Column(length = 20)
	private String vendors;
}
