package com.craft.SalesOrderManagementService.Utility;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SalesOrderKeyGenerator implements IdentifierGenerator{
	
//	 /**
//	 * 
//	 */
	private static final long serialVersionUID = 1L;
	private static final String PREFIX = "ORDER";
	private static final AtomicInteger counter = new AtomicInteger(789011);

	    @Override
	    public Serializable generate(SharedSessionContractImplementor session, Object object) {
	    	
	    	int value =  counter.incrementAndGet();
			String formattedValue = String.format("%06d", value);
	    	
	        return PREFIX + formattedValue;
	    }

}
