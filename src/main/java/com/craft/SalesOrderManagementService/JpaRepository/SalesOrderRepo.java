package com.craft.SalesOrderManagementService.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.craft.SalesOrderManagementService.Model.SalesOrder;

@Repository
public interface SalesOrderRepo extends JpaRepository<SalesOrder, String>{

}
