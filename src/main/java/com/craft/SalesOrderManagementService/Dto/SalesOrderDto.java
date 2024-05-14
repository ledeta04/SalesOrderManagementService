package com.craft.SalesOrderManagementService.Dto;

import com.craft.SalesOrderManagementService.Model.SalesOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesOrderDto {
	
	private Customer customer;
	private SalesOrder salesOrder;
	private String message;
	private String statusCode;

}
