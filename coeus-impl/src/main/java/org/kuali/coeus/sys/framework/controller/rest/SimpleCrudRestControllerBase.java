/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.controller.rest;

import java.lang.reflect.Modifier;
import java.util.*;

import javax.validation.Valid;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.persistence.PersistenceVerificationService;
import org.kuali.coeus.sys.framework.rest.DataDictionaryValidationException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnauthorizedAccessException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.validation.ErrorHandlingUtilService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;

public abstract class SimpleCrudRestControllerBase<T extends PersistableBusinessObject, R> extends RestController implements InitializingBean {

	@Autowired
	@Qualifier("legacyDataAdapter")
	private LegacyDataAdapter legacyDataAdapter;
	
	@Autowired
	@Qualifier("permissionService")
	private PermissionService permissionService;
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	@Autowired
	@Qualifier("dictionaryValidationService")
	private DictionaryValidationService dictionaryValidationService;
	
	@Autowired
	@Qualifier("errorHandlingUtilService")
	private ErrorHandlingUtilService errorHandlingUtilService;
	
	@Autowired
	@Qualifier("persistenceVerificationService")
	private PersistenceVerificationService persistenceVerificationService;
	
	@Autowired
	@Qualifier("autoRegisterMapping")
	private RestSimpleUrlHandlerMapping autoRegisterMapping;

	private boolean registerMapping = true;
	
	@Autowired
	@Qualifier("restAuditLoggerFactory")
	private RestAuditLoggerFactory restAuditLoggerFactory;

	private Class<T> dataObjectClazz;
	
	private String primaryKeyColumn;
	
	private String writePermissionTemplateNamespace;
	
	private String writePermissionTemplateName;

	private Map<String, String> writePermissionTemplateQualifiers;

	private String readPermissionTemplateNamespace;

	private String readPermissionTemplateName;

	private Map<String, String> readPermissionTemplateQualifiers;

	private String camelCasePluralName;

	protected Map.Entry<String, String> getWritePermission() {
		return entry(writePermissionTemplateNamespace, writePermissionTemplateName);
	}

	protected Map.Entry<String, String> getReadPermission() {
		return entry(readPermissionTemplateNamespace, readPermissionTemplateName);
	}

	protected T getNewDataObject() {
		try {
			return getDataObjectClazz().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("cannot create new data object", e);
		}
	}
	
	protected abstract Object getPrimaryKeyIncomingObject(R dataObject);
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Collection<R> getAll() {
		assertUserHasReadAccess();

		Collection<T> dataObjects = getAllFromDataStore();
		if (dataObjects == null || dataObjects.size() == 0) {
			throw new ResourceNotFoundException("not found");
		}
		return translateAllDataObjects(dataObjects);
	}
	
	protected abstract Collection<R> translateAllDataObjects(Collection<T> dataObjects);
	
	@RequestMapping(method=RequestMethod.GET, params={"schema"})
	public @ResponseBody Map<String, Object> getSchema() {
		assertUserHasReadAccess();

		Map<String, Object> schema = new HashMap<>();
		schema.put("primaryKey", getPrimaryKeyColumn());
		schema.put("columns", getExposedProperties());
		return schema;
	}
	
	protected abstract List<String> getExposedProperties();
	
	@RequestMapping(value="/{code}", method=RequestMethod.GET)
	public @ResponseBody R get(@PathVariable String code) {
		assertUserHasReadAccess();

		T dataObject = getFromDataStore(code);
		if (dataObject == null) {
			throw new ResourceNotFoundException("not found");
		}
		return convertDataObject(dataObject);
	}
	
	protected abstract R convertDataObject(T dataObject);
	
	@RequestMapping(value="/{code}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable String code, @Valid @RequestBody R dto) {
		assertUserHasWriteAccess();
		T dataObject = getFromDataStore(code);
		if (dataObject == null) {
			throw new ResourceNotFoundException("not found");
		}
		RestAuditLogger logger = getAuditLogger();
		logUpdateToObject(dataObject, dto, logger);
		updateDataObjectFromInput(dataObject, dto);
		
		validateBusinessObject(dataObject);
		validateUpdateDataObject(dataObject);
		save(dataObject);
		logger.saveAuditLog();
	}
	
	protected RestAuditLogger getAuditLogger() {
		return restAuditLoggerFactory.getNewAuditLogger(dataObjectClazz, getListOfTrackedProperties());
	}
	
	protected void logUpdateToObject(T currentObject, R newObject, RestAuditLogger logger) {
		T newDataObject = translateInputToDataObject(newObject);
		logger.addModifiedItem(currentObject, newDataObject);
	}
	
	protected abstract List<String> getListOfTrackedProperties();
	
	protected abstract T translateInputToDataObject(R input);
	
	protected abstract void updateDataObjectFromInput(T existingDataObject, R input);
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@Valid @RequestBody R dto) {
		assertUserHasWriteAccess();
		T existingDataObject = getFromDataStore(getPrimaryKeyIncomingObject(dto));
		if (existingDataObject != null) {
			throw new UnprocessableEntityException("already exists");
		}
		
		RestAuditLogger logger = getAuditLogger();
		T newDataObject = translateInputToDataObject(dto);
		
		validateBusinessObject(newDataObject);
		validateInsertDataObject(newDataObject);
		save(newDataObject);
		logger.addNewItem(newDataObject);
		logger.saveAuditLog();
	}
	
	@RequestMapping(value="/{code}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void delete(@PathVariable String code) {
		assertUserHasWriteAccess();
		T existingDataObject = getFromDataStore(code);
		if (existingDataObject == null) {
			throw new ResourceNotFoundException("not found");
		}
		
		RestAuditLogger logger = getAuditLogger();
		validateDeleteDataObject(existingDataObject);
		
		delete(existingDataObject);
		logger.addDeletedItem(existingDataObject);
		logger.saveAuditLog();
	}
	
	protected boolean validateDeleteDataObject(T dataObject) {
		MessageMap messages = persistenceVerificationService.verifyRelationshipsForDelete(dataObject, Collections.emptyList());
		if (messages.hasErrors()) {
			throw new DataDictionaryValidationException(errorHandlingUtilService.extractErrorMessages(messages));
		}
		return true;
	}

	protected boolean validateUpdateDataObject(T dataObject) {
		final MessageMap messages = persistenceVerificationService.verifyRelationshipsForUpdate(dataObject, Collections.emptyList());
		if (messages.hasErrors()) {
			throw new DataDictionaryValidationException(errorHandlingUtilService.extractErrorMessages(messages));
		}
		return true;
	}

	protected boolean validateInsertDataObject(T dataObject) {
		final MessageMap messages = persistenceVerificationService.verifyRelationshipsForInsert(dataObject, Collections.emptyList());
		if (messages.hasErrors()) {
			throw new DataDictionaryValidationException(errorHandlingUtilService.extractErrorMessages(messages));
		}
		return true;
	}
	
	protected void validateBusinessObject(T dataObject) {
		if (!dictionaryValidationService.isBusinessObjectValid(dataObject)) {
			throwErrorMessages();
		}
	}

	protected void throwErrorMessages() {
		Map<String, List<String>> errors = errorHandlingUtilService.extractErrorMessages(getGlobalVariableService().getMessageMap());
		if (errors != null && !errors.isEmpty()) {
			throw new DataDictionaryValidationException(errors);
		}
	}
	
	protected Collection<T> getAllFromDataStore() {
		return getLegacyDataAdapter().findAll(getDataObjectClazz());
	}
	
	protected T getFromDataStore(Object code) {
		return getLegacyDataAdapter().findBySinglePrimaryKey(getDataObjectClazz(), code);
	}

	protected void save(T dataObject) {
		getLegacyDataAdapter().save(dataObject);
	}
	
	protected void delete(T dataObject) {
		getLegacyDataAdapter().delete(dataObject);
	}

	protected void assertUserHasWriteAccess() {
		if (globalVariableService.getUserSession() == null || !permissionService.hasPermissionByTemplate(globalVariableService.getUserSession().getPrincipalId(),
				getWritePermission().getKey(), getWritePermission().getValue(), getWritePermissionTemplateQualifiers())) {
			throw new UnauthorizedAccessException();
		}
	}

	protected void assertUserHasReadAccess() {
		if (globalVariableService.getUserSession() == null || !permissionService.hasPermissionByTemplate(globalVariableService.getUserSession().getPrincipalId(),
				getReadPermission().getKey(), getReadPermission().getValue(), getReadPermissionTemplateQualifiers())) {
			throw new UnauthorizedAccessException();
		}
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

	public DictionaryValidationService getDictionaryValidationService() {
		return dictionaryValidationService;
	}

	public void setDictionaryValidationService(
			DictionaryValidationService dictionaryValidationService) {
		this.dictionaryValidationService = dictionaryValidationService;
	}

	public LegacyDataAdapter getLegacyDataAdapter() {
		return legacyDataAdapter;
	}

	public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
		this.legacyDataAdapter = legacyDataAdapter;
	}

	public PersistenceVerificationService getPersistenceVerificationService() {
		return persistenceVerificationService;
	}

	public void setPersistenceVerificationService(
			PersistenceVerificationService persistenceVerificationService) {
		this.persistenceVerificationService = persistenceVerificationService;
	}

	public Class<T> getDataObjectClazz() {
		return dataObjectClazz;
	}

	public void setDataObjectClazz(
			Class<T> dataObjectClazz) {
		this.dataObjectClazz = dataObjectClazz;
	}

	public String getWritePermissionTemplateNamespace() {
		return writePermissionTemplateNamespace;
	}

	public void setWritePermissionTemplateNamespace(String writePermissionTemplateNamespace) {
		this.writePermissionTemplateNamespace = writePermissionTemplateNamespace;
	}

	public String getWritePermissionTemplateName() {
		return writePermissionTemplateName;
	}

	public void setWritePermissionTemplateName(String writePermissionTemplateName) {
		this.writePermissionTemplateName = writePermissionTemplateName;
	}

	public Map<String, String> getWritePermissionTemplateQualifiers() {
		return writePermissionTemplateQualifiers;
	}

	public void setWritePermissionTemplateQualifiers(Map<String, String> writePermissionTemplateQualifiers) {
		this.writePermissionTemplateQualifiers = writePermissionTemplateQualifiers;
	}

	public String getReadPermissionTemplateNamespace() {
		return readPermissionTemplateNamespace;
	}

	public void setReadPermissionTemplateNamespace(String readPermissionTemplateNamespace) {
		this.readPermissionTemplateNamespace = readPermissionTemplateNamespace;
	}

	public String getReadPermissionTemplateName() {
		return readPermissionTemplateName;
	}

	public void setReadPermissionTemplateName(String readPermissionTemplateName) {
		this.readPermissionTemplateName = readPermissionTemplateName;
	}

	public Map<String, String> getReadPermissionTemplateQualifiers() {
		return readPermissionTemplateQualifiers;
	}

	public void setReadPermissionTemplateQualifiers(Map<String, String> readPermissionTemplateQualifiers) {
		this.readPermissionTemplateQualifiers = readPermissionTemplateQualifiers;
	}

	public ErrorHandlingUtilService getErrorHandlingUtilService() {
		return errorHandlingUtilService;
	}

	public void setErrorHandlingUtilService(
			ErrorHandlingUtilService errorHandlingUtilService) {
		this.errorHandlingUtilService = errorHandlingUtilService;
	}

	public String getPrimaryKeyColumn() {
		if (StringUtils.isBlank(primaryKeyColumn)) {
			List<String> pks = persistenceVerificationService.pkFields(dataObjectClazz);
			if (pks.size() > 1) {
				throw new UnsupportedOperationException("compound primary keys are not supported");
			}
			primaryKeyColumn = pks.get(0);
		}

		return primaryKeyColumn;
	}

	public void setPrimaryKeyColumn(String primaryKeyColumn) {
		this.primaryKeyColumn = primaryKeyColumn;
	}

	public String getCamelCasePluralName() {
		return camelCasePluralName;
	}

	public void setCamelCasePluralName(String camelCasePluralName) {
		this.camelCasePluralName = camelCasePluralName;
	}

	public boolean isRegisterMapping() {
		return registerMapping;
	}

	public void setRegisterMapping(boolean registerMapping) {
		this.registerMapping = registerMapping;
	}

	public RestSimpleUrlHandlerMapping getAutoRegisterMapping() {
		return autoRegisterMapping;
	}

	public void setAutoRegisterMapping(RestSimpleUrlHandlerMapping autoRegisterMapping) {
		this.autoRegisterMapping = autoRegisterMapping;
	}

	/**
	 * Uses basic english rules to convert a singular word to plural form.  Note that only the basic rules are covered
	 * here and many english words will not be handled correctly.
	 * @param singular a singular word
	 * @return a plural word
     */
	private String toPlural(String singular) {
		if (singular.endsWith("y") && !singular.endsWith("ay") && !singular.endsWith("ey") && !singular.endsWith("iy") && !singular.endsWith("oy") && !singular.endsWith("uy")) {
			return singular.substring(0, singular.length() - 1) + "ies";
		} else if (singular.endsWith("s") || singular.endsWith("x") || singular.endsWith("z") || singular.endsWith("ch") || singular.endsWith("sh")) {
			return singular + "es";
		} else {
			return singular + "s";
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (Modifier.isAbstract(dataObjectClazz.getModifiers())) {
			throw new IllegalStateException("dataObjectClazz must not be abstract: " + dataObjectClazz.getName());
		}

		if (StringUtils.isBlank(camelCasePluralName)) {
			setCamelCasePluralName(toPlural(dataObjectClazz.getSimpleName()));
		}

		if (StringUtils.isBlank(writePermissionTemplateName)) {
			setWritePermissionTemplateName(PermissionConstants.WRITE_CLASS);
		}

		if (StringUtils.isBlank(writePermissionTemplateNamespace)) {
			setWritePermissionTemplateNamespace(Constants.MODULE_NAMESPACE_SYSTEM);
		}

		if (CollectionUtils.isEmpty(writePermissionTemplateQualifiers)) {
			setWritePermissionTemplateQualifiers(Collections.singletonMap(KcKimAttributes.CLASS_NAME, getDataObjectClazz().getName()));
		}

		if (StringUtils.isBlank(readPermissionTemplateName)) {
			setReadPermissionTemplateName(PermissionConstants.READ_CLASS);
		}

		if (StringUtils.isBlank(readPermissionTemplateNamespace)) {
			setReadPermissionTemplateNamespace(Constants.MODULE_NAMESPACE_SYSTEM);
		}

		if (CollectionUtils.isEmpty(readPermissionTemplateQualifiers)) {
			setReadPermissionTemplateQualifiers(Collections.singletonMap(KcKimAttributes.CLASS_NAME, getDataObjectClazz().getName()));
		}

		if (isRegisterMapping() && autoRegisterMapping != null) {
			final String path = "/api/v1/" + (CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(this.getCamelCasePluralName()) + "/*");

			autoRegisterMapping.setUrlMap(Collections.singletonMap(path, this));
			autoRegisterMapping.registerHandler(path, this);
		}
	}
}
