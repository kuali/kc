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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.persistence.PersistenceVerificationService;
import org.kuali.coeus.sys.framework.rest.DataDictionaryValidationException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnauthorizedAccessException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.validation.ErrorHandlingUtilService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;

public abstract class SimpleCrudRestControllerBase<T extends PersistableBusinessObject, R> extends RestController {
	
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
	
	private Class<T> dataObjectClazz;
	
	private String primaryKeyColumn;
	
	private String writePermissionNamespace;
	
	private String writePermissionName;
	
	public SimpleCrudRestControllerBase() { }
	
	public SimpleCrudRestControllerBase(
			Class<T> dataObjectClazz, String primaryKeyColumn,
			String writePermissionNamespace, String writePermissionName) {
		super();
		this.dataObjectClazz = dataObjectClazz;
		this.primaryKeyColumn = primaryKeyColumn;
		this.writePermissionNamespace = writePermissionNamespace;
		this.writePermissionName = writePermissionName;
	}

	protected Map.Entry<String, String> getPermission() {
		return entry(writePermissionNamespace, writePermissionName);
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
		Collection<T> dataObjects = getAllFromDataStore();
		if (dataObjects == null || dataObjects.size() == 0) {
			throw new ResourceNotFoundException("not found");
		}
		return translateAllDataObjects(dataObjects);
	}
	
	protected abstract Collection<R> translateAllDataObjects(Collection<T> dataObjects);
	
	@RequestMapping(method=RequestMethod.GET, params={"schema"})
	public @ResponseBody Map<String, Object> getSchema() {
		Map<String, Object> schema = new HashMap<>();
		schema.put("primaryKey", getPrimaryKeyColumn());
		schema.put("columns", getExposedProperties());
		return schema;
	}
	
	protected abstract List<String> getExposedProperties();
	
	@RequestMapping(value="/{code}", method=RequestMethod.GET)
	public @ResponseBody R get(@PathVariable String code) {
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
		assertUserHasAccess();
		T dataObject = getFromDataStore(code);
		if (dataObject == null) {
			throw new ResourceNotFoundException("not found");
		}
		
		updateDataObjectFromInput(dataObject, dto);
		
		validateBusinessObject(dataObject);
		validateUpdateDataObject(dataObject);
		save(dataObject);
	}
	
	protected abstract T translateInputToDataObject(R input);
	
	protected abstract void updateDataObjectFromInput(T existingDataObject, R input);
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@Valid @RequestBody R dto) {
		assertUserHasAccess();
		T existingDataObject = getFromDataStore(getPrimaryKeyIncomingObject(dto));
		if (existingDataObject != null) {
			throw new UnprocessableEntityException("already exists");
		}
		
		T newDataObject = translateInputToDataObject(dto);
		
		validateBusinessObject(newDataObject);
		validateInsertDataObject(newDataObject);
		save(newDataObject);
	}
	
	@RequestMapping(value="/{code}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void delete(@PathVariable String code) {
		assertUserHasAccess();
		T existingDataObject = getFromDataStore(code);
		if (existingDataObject == null) {
			throw new ResourceNotFoundException("not found");
		}
		
		validateDeleteDataObject(existingDataObject);
		
		delete(existingDataObject);
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

	protected void assertUserHasAccess() {
		if (globalVariableService.getUserSession() == null || !permissionService.hasPermission(globalVariableService.getUserSession().getPrincipalId(),
				getPermission().getKey(), getPermission().getValue())) {
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
	
	public String getWritePermissionNamespace() {
		return writePermissionNamespace;
	}

	public void setWritePermissionNamespace(String writePermissionNamespace) {
		this.writePermissionNamespace = writePermissionNamespace;
	}

	public String getWritePermissionName() {
		return writePermissionName;
	}

	public void setWritePermissionName(String writePermissionName) {
		this.writePermissionName = writePermissionName;
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
	
}
