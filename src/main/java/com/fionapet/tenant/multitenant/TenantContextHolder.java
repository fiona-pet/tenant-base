package com.fionapet.tenant.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContextHolder {
	
	public static final String DEFAULT_SCHEMA = "core";

	private static final Logger log = LoggerFactory.getLogger(TenantContextHolder.class); 
	private static ThreadLocal<String> currentSchema = new ThreadLocal<>();

	public static String getCurrentSchema() {
		String schemaName = currentSchema.get();
		if (schemaName == null) {
			return DEFAULT_SCHEMA;
		}
		return schemaName;
	}

	public static void setCurrentSchema(String schema) {
		currentSchema.set(schema);
		log.debug("Current schema = {}", schema);
	}

	public static void clear() {
		setCurrentSchema(DEFAULT_SCHEMA);
	}
	
}
