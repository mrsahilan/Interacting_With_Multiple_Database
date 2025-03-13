package com.nit.repository.promotions;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.model.promotions.Offers;

public interface OffersRepoI extends JpaRepository<Offers, Integer>
{

}
