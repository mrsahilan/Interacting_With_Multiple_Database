package com.nit.model.promotions;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Offers 
{
	@Id
	@SequenceGenerator(name = "gen1", sequenceName="offer_seq", initialValue=100, allocationSize =1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer offerId;
	
	@Column(length = 20)
	@NonNull
	private String offerName;
	@Column(length = 20)
	@NonNull
	private String offerCode;
	@NonNull
	private Float discountPercentage;
	@NonNull
	private LocalDateTime expiryDate;
}
