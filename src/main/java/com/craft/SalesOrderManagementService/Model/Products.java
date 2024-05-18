package com.craft.SalesOrderManagementService.Model;



import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
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
	@Min(1)
	private int quantity;
	@Min(10)
	private double price;

}
