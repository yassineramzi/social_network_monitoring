package com.socialnetworkmonitoring.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class HeaderUtil {
	 	private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

	    private static final String APPLICATION_NAME = "seaeventApp";

	    private HeaderUtil() {
	    }

	    public static HttpHeaders createAlert(String message, String param) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("X-seaeventApp-alert", message);
	        headers.add("X-seaeventApp-params", param);
	        return headers;
	    }

	    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
	        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
	    }

	    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
	        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
	    }

	    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
	        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
	    }

	    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
	        log.error("Entity processing failed, {}", defaultMessage);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("X-seaeventApp-error", "error." + errorKey);
	        headers.add("X-seaeventApp-params", entityName);
	        return headers;
	    }
}
