package org.kuali.coeus.sys.impl.controller.rest.audit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLog;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerDao;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("restAuditLoggerDao")
public class RestAuditLoggerDaoImpl implements RestAuditLoggerDao {
	
	private static final Log LOG = LogFactory.getLog(RestAuditLoggerDaoImpl.class);

	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public List<RestAuditLog> getAuditLogsForDataObject(Class<?> dataObjectClass) {
		return getRestAuditLogsFromDatabase(dataObjectClass).stream()
				.map(dataObject -> {
					return new RestAuditLog(dataObject.getUsername(),
							dataObject.getDate().toInstant(),
							dataObject.getClassName(),
							transformJsonToMap(dataObject.getId(), dataObject.getAdded()),
							transformJsonToMap(dataObject.getId(), dataObject.getModified()),
							transformJsonToMap(dataObject.getId(), dataObject.getDeleted()));
				}).collect(Collectors.toList());
	}

	protected List<RestAuditLogDataObject> getRestAuditLogsFromDatabase(Class<?> dataObjectClass) {
		return dataObjectService.findMatching(RestAuditLogDataObject.class, 
					QueryByCriteria.Builder.forAttribute("className", dataObjectClass.getCanonicalName()).build())
				.getResults();
	}
	
	protected List<Map<String, Object>> transformJsonToMap(Long id, String json) {
		try {
			return objectMapper.readValue(json, new TypeReference<ArrayList<HashMap<String,Object>>>() {});
		} catch (IOException e) {
			LOG.error("Error deserializing audit log json for " + id + " -- \'" + json + "\'", e);
			return new ArrayList<>();
		}
	}

	@Override
	public void saveAuditLog(Class<?> dataObjectClass, RestAuditLog log) {
		try {
			RestAuditLogDataObject dataObject = new RestAuditLogDataObject();
			dataObject.setUsername(log.getUsername());
			dataObject.setDate(Date.from(log.getDate()));
			dataObject.setClassName(dataObjectClass.getCanonicalName());
			dataObject.setAdded(transformMapToJson(log.getAdded()));
			dataObject.setModified(transformMapToJson(log.getModified()));
			dataObject.setDeleted(transformMapToJson(log.getDeleted()));
			saveAuditLog(dataObject);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected void saveAuditLog(RestAuditLogDataObject dataObject) {
		dataObjectService.save(dataObject);
	}

	protected String transformMapToJson(final List<Map<String, Object>> changes)
			throws IOException, JsonGenerationException, JsonMappingException {
		if (changes == null || changes.size() == 0) {
			return null;
		} else {
			return objectMapper.writeValueAsString(changes);
		}
	}

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

}
