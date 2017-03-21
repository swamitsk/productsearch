package com.test.kogan.productsearch.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.kogan.productsearch.service.ProductLookupService;

@Path("/productsearch")
public class ProductLookupResource {
	
	ProductLookupService service = new ProductLookupService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public String getAllProductsCategories()
	{
			return service.getAllProductCategories();
	}
	
	@GET
    @Path("/weight/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductWeightPerType(@PathParam("category") String prodType){
		
		return service.getAverageProductWeight(prodType);
	}
	
	
	

}
