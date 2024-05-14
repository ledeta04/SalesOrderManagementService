package com.craft.SalesOrderManagementService.Service.ServiceImpl;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.craft.SalesOrderManagementService.Dto.Customer;
import com.craft.SalesOrderManagementService.Dto.DeleteSalesOrderDto;
import com.craft.SalesOrderManagementService.Dto.SalesOrderDto;
import com.craft.SalesOrderManagementService.JpaRepository.SalesOrderRepo;
import com.craft.SalesOrderManagementService.Model.Products;
import com.craft.SalesOrderManagementService.Model.SalesOrder;
import com.craft.SalesOrderManagementService.Model.ShippingAddress;
import com.craft.SalesOrderManagementService.Service.SalesOrderService;


@Service
public class SalesOrderServiceImpl implements SalesOrderService{
	
	@Autowired
	SalesOrderRepo salesOrderRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	private final String uri = "http://localhost:9094/customers/getAll";

	@Override
	public List<SalesOrder> createNewSalesOrder(SalesOrder salesOrder) {
		

		salesOrderRepo.save(salesOrder);
		
		
		return salesOrderRepo.findAll();
	}

	@Override
	public SalesOrderDto updateExistingSalesOrderById(String orderId, SalesOrder salesOrder) {

		Optional<SalesOrder> optional = salesOrderRepo.findById(orderId);
		
		if(optional.isPresent()) {
			
			SalesOrder salesOrder2 = optional.get();
			
			salesOrder2.setTotalAmount(salesOrder.getTotalAmount());
			salesOrder2.setStatus(salesOrder.getStatus());
			salesOrder2.setDiscount(salesOrder.getDiscount());
			
		
			Products products = new Products();
			
			products.setProductId(salesOrder.getProducts().getProductId());
			products.setQuantity(salesOrder.getProducts().getQuantity());
			products.setPrice(salesOrder.getProducts().getPrice());
			
			
			ShippingAddress shippingAddress = new ShippingAddress();
			
			shippingAddress.setStreet(salesOrder.getShippingAddress().getStreet());
			shippingAddress.setCity(salesOrder.getShippingAddress().getCity());
			shippingAddress.setState(salesOrder.getShippingAddress().getState());
			shippingAddress.setZipcode(salesOrder.getShippingAddress().getZipcode());
			
			salesOrderRepo.save(salesOrder2);
			
					
			SalesOrderDto salesOrderDto = new SalesOrderDto(null, salesOrder, "Successfully Updated", "3000");
	
			
			return salesOrderDto;
		
//	    Optional<SalesOrder> optional = salesOrderRepo.findById(orderId);
//
//	    if (optional.isPresent()) {
//
//	        SalesOrder existingSalesOrder = optional.get();
//
//	        // Update other properties of the sales order
//	        existingSalesOrder.setTotalAmount(salesOrder.getTotalAmount());
//	        existingSalesOrder.setStatus(salesOrder.getStatus());
//	        existingSalesOrder.setDiscount(salesOrder.getDiscount());
//
//	        // Update products
//	        List<Products> updatedProducts = new ArrayList<>();
//	        for (Products product : salesOrder.getProducts()){
//	            Products updatedProduct = new Products();
//	            updatedProduct.setProductId(product.getProductId());
//	            updatedProduct.setQuantity(product.getQuantity());
//	            updatedProduct.setPrice(product.getPrice());
//	            updatedProducts.add(updatedProduct);
//	        }
//	        existingSalesOrder.setProducts(updatedProducts);
//
//	        // Update shipping address
//	        ShippingAddress shippingAddress = new ShippingAddress();
//	        shippingAddress.setStreet(salesOrder.getShippingAddress().getStreet());
//	        shippingAddress.setCity(salesOrder.getShippingAddress().getCity());
//	        shippingAddress.setState(salesOrder.getShippingAddress().getState());
//	        shippingAddress.setZipcode(salesOrder.getShippingAddress().getZipcode());
//	        existingSalesOrder.setShippingAddress(shippingAddress);
//
//	        salesOrderRepo.save(existingSalesOrder);
//
//	        SalesOrderDto salesOrderDto = new SalesOrderDto(null, salesOrder, "Successfully Updated", "3000");
//
//	        return salesOrderDto;
					
		}
		
		
		return null;
	}

	@Override
	public SalesOrder retrieveOrderDetailsById(String orderId) {
		
	
		Optional<SalesOrder> optional = salesOrderRepo.findById(orderId);
		
		if(optional.isPresent()) {
			
			return  optional.get();
		}
		return null;

//	    SalesOrder salesOrder =	salesOrderRepo.findById(orderId).get();
//	    SalesOrderDto salesOrderDto = modelMapper.map(salesOrder, SalesOrderDto.class);
//	    
//	    Customer customer = restTemplate.getForObject("http://localhost:9094/customers/get/{orderId}", Customer.class, orderId);
//	    
//	    salesOrderDto.setCustomer(customer);
//	    
//	    return salesOrder;
		
  }

	@Override
	public DeleteSalesOrderDto deleteSalesOrderById(String orderId) {
		
		try {
			
			  Optional<SalesOrder> optional = salesOrderRepo.findById(orderId);
			    
			    if(optional.isPresent()) {
			    	
			    	salesOrderRepo.deleteById(orderId);
			    	
			    	return new DeleteSalesOrderDto("Sales Order deleted Successfully", "2000");
			    }else {
			    	
			    	return new DeleteSalesOrderDto("Sales Order Not Found", "4040");
			    }
			    			
		} catch (Exception e) {

			return new DeleteSalesOrderDto("Server Error", "5000");			
		}
  }

	@Override
	public List<Customer>  getAllCustomersFromSalesOrder() {

		HttpHeaders headers = new HttpHeaders();
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		
		ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(
				
				uri,
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<List<Customer>>() {}
				
				);
	
		
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			
			return responseEntity.getBody();
		}else {
			
			return Collections.emptyList();
		}
	}

}
