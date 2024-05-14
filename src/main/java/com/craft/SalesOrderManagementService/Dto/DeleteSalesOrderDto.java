package com.craft.SalesOrderManagementService.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteSalesOrderDto {
	
	private String message;
	private String statusCode;

}
