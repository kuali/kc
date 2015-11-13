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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import com.codiform.moo.curry.Translate;

import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;

public abstract class SimpleCrudRestController<T extends PersistableBusinessObject, R> extends RestController {
	
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
	
	private Class<R> dtoObjectClazz;
	
	private String primaryKeyColumn;
	
	private String writePermissionNamespace;
	
	private String writePermissionName;
	
	public SimpleCrudRestController(
			Class<T> dataObjectClazz,
			Class<R> dtoObjectClazz, String primaryKeyColumn,
			String writePermissionNamespace, String writePermissionName) {
		super();
		this.dataObjectClazz = dataObjectClazz;
		this.dtoObjectClazz = dtoObjectClazz;
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
	
	protected Object getPrimaryKeyFromDto(R dataObject) {
		return getProperty(dataObject, primaryKeyColumn);
	}
	
    private Object getProperty(Object o, String prop) {
        try {
            return PropertyUtils.getProperty(o, prop);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Collection<R> getAll() {
		Collection<T> dataObjects = getAllFromDataStore();
		if (dataObjects == null || dataObjects.size() == 0) {
			throw new ResourceNotFoundException("not found");
		}
		return Translate.to(getDtoObjectClazz()).fromEach(dataObjects);
	}
	
	@RequestMapping(value="/{code}", method=RequestMethod.GET)
	public @ResponseBody R get(@PathVariable String code) {
		T dataObject = getFromDataStore(code);
		if (dataObject == null) {
			throw new ResourceNotFoundException("not found");
		}
		return Translate.to(getDtoObjectClazz()).from(dataObject);
	}
	
	@RequestMapping(value="/{code}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable String code, @Valid @RequestBody R dto) {
		assertUserHasAccess();
		T dataObject = getFromDataStore(code);
		if (dataObject == null) {
			throw new ResourceNotFoundException("not found");
		}
		
		Configuration mooConfig = new Configuration();
		mooConfig.setSourcePropertiesRequired(false);
		Moo moo = new Moo(mooConfig);
		moo.update(dto, dataObject);
		
		validateBusinessObject(dataObject);
		validateUpdateDataObject(dataObject);
		save(dataObject);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@Valid @RequestBody R dto) {
		assertUserHasAccess();
		T existingDataObject = getFromDataStore(getPrimaryKeyFromDto(dto));
		if (existingDataObject != null) {
			throw new UnprocessableEntityException("already exists");
		}
		
		Configuration mooConfig = new Configuration();
		mooConfig.setSourcePropertiesRequired(false);
		Moo moo = new Moo(mooConfig);
		T newDataObject = getNewDataObject();
		moo.update(dto, newDataObject);
		
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

	public Class<R> getDtoObjectClazz() {
		return dtoObjectClazz;
	}

	public void setDtoObjectClazz(Class<R> dtoObjectClazz) {
		this.dtoObjectClazz = dtoObjectClazz;
	}

	public String getPrimaryKeyColumnNames() {
		return primaryKeyColumn;
	}

	public void setPrimaryKeyColumnNames(String primaryKeyColumn) {
		this.primaryKeyColumn = primaryKeyColumn;
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
	
}
