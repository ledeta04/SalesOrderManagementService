package com.craft.SalesOrderManagementService.Service;

import java.util.List;

import com.craft.SalesOrderManagementService.Dto.Customer;
import com.craft.SalesOrderManagementService.Dto.DeleteSalesOrderDto;
import com.craft.SalesOrderManagementService.Dto.SalesOrderDto;
import com.craft.SalesOrderManagementService.Model.SalesOrder;

public interface SalesOrderService {
	
	public List<SalesOrder> createNewSalesOrder(SalesOrder salesOrder);
	public SalesOrderDto updateExistingSalesOrderById(String orderId , SalesOrder salesOrder);
	public SalesOrder retrieveOrderDetailsById(String orderId);
	public DeleteSalesOrderDto deleteSalesOrderById(String orderId);
	public List<Customer>  getAllCustomersFromSalesOrder();
	

}
