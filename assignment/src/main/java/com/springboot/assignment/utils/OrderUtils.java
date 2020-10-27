package com.springboot.assignment.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class OrderUtils {
	
	public static Date getExpectedDeliveryDate() {
		
		return Date.from(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.UTC));
		
	}
	
}
