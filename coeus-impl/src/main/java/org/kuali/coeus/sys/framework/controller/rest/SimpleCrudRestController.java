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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rest.DataDictionaryValidationException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnauthorizedAccessException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import com.codiform.moo.curry.Translate;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entriesToMap;

public abstract class SimpleCrudRestController<T extends PersistableBusinessObject, R> extends RestController {
	
	@Autowired
	@Qualifier("businessObjectService")
	private BusinessObjectService businessObjectService;
	
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
	@Qualifier("kualiConfigurationService")
	private ConfigurationService configurationService;
	
	protected abstract Class<R> getDtoClass();
	
	protected abstract Class<T> getDoClass();
	
	protected T getNewDataObject() {
		try {
			return getDoClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("cannot create new data object", e);
		}
	}
	
	protected abstract Object getPrimaryKeyFromDto(R dataObject);
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Collection<R> getAll() {
		return (Collection<R>) Translate.to(getDtoClass()).fromEach(getAllFromDataStore());
	}
	
	@RequestMapping(value="{code}", method=RequestMethod.GET)
	public @ResponseBody R get(@PathVariable String code) {
		return Translate.to(getDtoClass()).from(getFromDataStore(code));
	}
	
	@RequestMapping(value="{code}", method=RequestMethod.PUT)
	public @ResponseBody void update(@PathVariable String code, @Valid @RequestBody R dto) {
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

		save(dataObject);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody void add(@Valid @RequestBody R dto) {
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

		save(newDataObject);
	}
	
	protected void validateBusinessObject(T dataObject) {
		if (!dictionaryValidationService.isBusinessObjectValid(dataObject)) {
			
			Map<String, List<String>> errors = globalVariableService.getMessageMap().getErrorMessages().entrySet().stream()
					.map(entry -> {
						return entry(entry.getKey().replaceFirst("^\\.", ""), entry.getValue().stream()
								.map(errorMessage -> resolveErrorMessage(errorMessage))
								.collect(Collectors.toList()));
					}).collect(entriesToMap());
			
			throw new DataDictionaryValidationException(errors);
		}
	}

	protected String resolveErrorMessage(ErrorMessage errorMessage) {
        String messageText = configurationService.getPropertyValueAsString(errorMessage.getErrorKey());
        for (int i = 0; i < errorMessage.getMessageParameters().length; i++) {
            messageText = StringUtils.replace(messageText, "{" + i + "}", errorMessage.getMessageParameters()[i]);
        }
        return messageText;    
	}
	
	protected Collection<T> getAllFromDataStore() {
		return getBusinessObjectService().findAll(getDoClass());
	}
	
	protected T getFromDataStore(Object code) {
		return getBusinessObjectService().findBySinglePrimaryKey(getDoClass(), code);
	}

	protected void save(T currentCategory) {
		getBusinessObjectService().save(currentCategory);
	}
	
	protected void assertUserHasAccess() {
		if (!permissionService.hasPermission(globalVariableService.getUserSession().getPrincipalId(), 
				Constants.MODULE_NAMESPACE_MAINTENANCE, PermissionConstants.MAINTAIN_INSTITUTE_RATES)) {
			throw new UnauthorizedAccessException();
		}
	}
	
	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
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

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
}
