package com.nit.runners;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nit.model.prod.Product;
import com.nit.model.promotions.Offers;
import com.nit.repository.prod.ProductRepoI;
import com.nit.repository.promotions.OffersRepoI;

@Component
public class MultiDbTestRunners implements CommandLineRunner 
{
	@Autowired
	private ProductRepoI productRepoI;
	
	@Autowired
	private OffersRepoI offersRepoI;
	
	@Override
	public void run(String... args) throws Exception 
	{
		Product pr = new Product("TV", 24000.0, 1, "Lg");
		int idValue = productRepoI.save(pr).getPid();
		System.out.println("Product is saved with this product id : " + idValue);
		
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		
		Offers of = new Offers("Diwali", "diwali1102", 34.0f, LocalDateTime.now());
		int idVal = offersRepoI.save(of).getOfferId();
		System.out.println("Offers is saved with this offers id : " + idVal);
		
	}

}
