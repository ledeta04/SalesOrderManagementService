package com.craft.SalesOrderManagementService.Model;



import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Products {
	
	private String productId;
	private int quantity;
	private double price;

}
