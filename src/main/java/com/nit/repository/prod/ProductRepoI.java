package com.nit.repository.prod;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.model.prod.Product;

public interface ProductRepoI extends JpaRepository<Product, Integer> 
{

}
