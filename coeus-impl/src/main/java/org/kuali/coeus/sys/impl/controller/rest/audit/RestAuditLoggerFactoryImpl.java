package org.kuali.coeus.sys.impl.controller.rest.audit;

import java.util.List;

import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerDao;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("restAuditLoggerFactory")
public class RestAuditLoggerFactoryImpl implements RestAuditLoggerFactory {

	@Autowired
	@Qualifier("restAuditLoggerDao")
	private RestAuditLoggerDao restAuditLoggerDao;
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	@Override
	public RestAuditLogger getNewAuditLogger(Class<?> dataObjectClass, List<String> propertiesToLog) {
		return new RestAuditLoggerImpl(globalVariableService.getUserSession().getPrincipalName(), 
				dataObjectClass, propertiesToLog, restAuditLoggerDao);
	}

	public RestAuditLoggerDao getRestAuditLoggerDao() {
		return restAuditLoggerDao;
	}

	public void setRestAuditLoggerDao(RestAuditLoggerDao restAuditLoggerDao) {
		this.restAuditLoggerDao = restAuditLoggerDao;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

}
