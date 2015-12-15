package org.kuali.coeus.sys.framework.controller.rest.audit;

import java.util.List;

public interface RestAuditLoggerDao {

	List<RestAuditLog> getAuditLogsForDataObject(Class<?> dataObjectClass);
	
	void saveAuditLog(Class<?> dataObjectClass, RestAuditLog log);
}
