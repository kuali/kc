package org.kuali.coeus.sys.framework.controller.rest.audit;

import java.util.List;

public interface RestAuditLoggerFactory {

	RestAuditLogger getNewAuditLogger(Class<?> dataObjectClass, List<String> propertiesToLog);
}
