package com.craft.SalesOrderManagementService.Model;


import org.hibernate.annotations.GenericGenerator;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesOrder {
	
	@Id
	@GeneratedValue(generator = "salesOrderKeyGenerator")
	@GenericGenerator(name = "salesOrderKeyGenerator" , strategy = "com.craft.SalesOrderManagementService.Utility.SalesOrderKeyGenerator")
	private String orderId;
	
	private String customerId;
	
	
	Products products;
	
	private String totalAmount;
	private String status;
	private String discount;
	
	ShippingAddress shippingAddress;
	
	private String notes;
	
}
