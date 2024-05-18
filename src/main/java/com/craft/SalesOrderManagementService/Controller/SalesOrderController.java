package com.craft.SalesOrderManagementService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.craft.SalesOrderManagementService.Model.SalesOrder;
import com.craft.SalesOrderManagementService.Service.SalesOrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class SalesOrderController {
	
	@Autowired
	SalesOrderService salesOrderService; 
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createNewSalesOrder(@RequestBody @Valid SalesOrder salesOrder){
		
		return new ResponseEntity<>(salesOrderService.createNewSalesOrder(salesOrder), HttpStatus.CREATED);
		
	}
	@PutMapping("/update/{orderId}")
	public ResponseEntity<?> updateSalesOrderById(@PathVariable("orderId")@Valid String orderId , @RequestBody SalesOrder salesOrder){
		
		return new ResponseEntity<>(salesOrderService.updateExistingSalesOrderById(orderId, salesOrder) , HttpStatus.OK);
	}
	
	@GetMapping("/retrieve/{orderId}")
	public ResponseEntity<?> getSalesOrderById(@PathVariable("orderId")@Valid String orderId ){
		
		return new ResponseEntity<>(salesOrderService.retrieveOrderDetailsById(orderId) , HttpStatus.OK);
	}
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<?> deleteSalesOrderById(@PathVariable("orderId")@Valid String orderId){
		
		return new ResponseEntity<>(salesOrderService.deleteSalesOrderById(orderId) , HttpStatus.OK);
	}
	@GetMapping("/getAll")
	
	@CircuitBreaker(name = "getAllCustomerDetails" , fallbackMethod = "getAllCustomerDetailsFallbackMethod")
	public ResponseEntity<?> getCustomerDetails(){
		
		return new ResponseEntity<>(salesOrderService.getAllCustomersFromSalesOrder(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> getAllCustomerDetailsFallbackMethod(Exception exception){
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
