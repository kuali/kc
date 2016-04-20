/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.namespace.QName;

import com.google.common.base.CaseFormat;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.kuali.coeus.sys.framework.config.KcConfigurer;
import org.kuali.coeus.sys.framework.controller.rest.CustomEditors.CustomSqlDateEditor;
import org.kuali.coeus.sys.framework.controller.rest.CustomEditors.CustomSqlTimestampEditor;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.persistence.PersistenceVerificationService;
import org.kuali.coeus.sys.framework.rest.DataDictionaryValidationException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnauthorizedAccessException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorHandlingUtilService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.framework.config.module.ModuleConfigurer;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.data.CompoundKey;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import static org.kuali.coeus.sys.framework.util.CollectionUtils.entriesToMap;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;

public abstract class SimpleCrudRestControllerBase<T, R> extends RestController implements InitializingBean, BeanNameAware {

	private static final String DELIMETER = ":";
	private static final String ALLOW_MULTI_PARM = "_allowMulti";
	private static final String SCHEMA_PARM = "_schema";
	private static final String BLUEPRINT_PARM = "_blueprint";
	protected static final String SYNTHETIC_FIELD_PK = "_primaryKey";
	private static final Log LOG = LogFactory.getLog(SimpleCrudRestControllerBase.class);

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
	@Qualifier("restAuditLoggerFactory")
	private RestAuditLoggerFactory restAuditLoggerFactory;

	@Autowired
	@Qualifier("autoRegisterMapping")
	private RestSimpleUrlHandlerMapping autoRegisterMapping;

	@Autowired
	@Qualifier("dataDictionaryService")
	private DataDictionaryService dataDictionaryService;

	@Value("classpath:org/kuali/coeus/sys/framework/controller/rest/SimpleCrudRestControllerBlueprintTemplate.md")
	private Resource blueprintTemplate;

	//this is all of the module configurers for the kc monolith.  It is used to get the base servlet path for the
	//module this bean belongs to.  Set this as object type so spring doesn't try to do special Collection wiring
	@Autowired
	@Qualifier("moduleConfigurers")
	private Object moduleConfigurers;

	private String beanName;

	private boolean registerMapping = true;

	private BeanWrapper beanWrapper;

	private Class<T> dataObjectClazz;
	
	private String primaryKeyColumn;
	
	private String writePermissionTemplateNamespace;
	
	private String writePermissionTemplateName;

	private Map<String, String> writePermissionTemplateQualifiers;

	private String readPermissionTemplateNamespace;

	private String readPermissionTemplateName;

	private Map<String, String> readPermissionTemplateQualifiers;

	private String camelCasePluralName;

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Collection<R> getAll(@RequestParam(required=false) Map<String,String> parameters) {
		assertUserHasReadAccess();
		return translateAllDataObjects(doGetAll(parameters));
	}

	@RequestMapping(method=RequestMethod.GET, params={SCHEMA_PARM})
	public @ResponseBody Map<String, Object> getSchema() {
		return getSchemaMap();
	}

	public Map<String, Object> getSchemaMap() {

		final Map<String, Object> schema = new HashMap<>();
		schema.put("primaryKey", getPrimaryKeyColumn());
		schema.put("columns", getExposedProperties());
		return schema;
	}

	@RequestMapping(value="/{code}", method=RequestMethod.GET)
	public @ResponseBody R get(@PathVariable String code) {
		assertUserHasReadAccess();

		T dataObject = getFromDataStore(code);
		if (dataObject == null) {
			throw new ResourceNotFoundException("not found for key " + code);
		}
		return convertDataObjectToDto(dataObject);
	}

	@RequestMapping(method=RequestMethod.GET, params={BLUEPRINT_PARM})
	public @ResponseBody Resource getBlueprint(HttpServletResponse response) {

		response.setContentType("text/markdown");
		response.setHeader("Content-Disposition", "attachment;filename=" + CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(this.getCamelCasePluralName()) + ".md");

		return getBlueprintResource();
	}

	public Resource getBlueprintResource() {
		String templateText;
		try {
			templateText = IOUtils.toString(blueprintTemplate.getInputStream(), "UTF-8"); ;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		templateText = templateText.replaceAll("\\$\\{resourceName\\}", WordUtils.capitalizeFully(CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(this.getCamelCasePluralName()).replaceAll("-", " ")));
		templateText = templateText.replaceAll("\\$\\{endpoint\\}", "/" + getModuleMapping() + getPath());
		templateText = templateText.replaceAll("\\$\\{sampleKey\\}", "(key)");
		templateText = templateText.replaceAll("\\$\\{sampleMatchCriteria\\}", getListOfTrackedProperties().stream().map(prop -> ("+ " + prop + " (optional) - " + getPropertyDescription(prop))).collect(Collectors.joining("\n    ", "", "\n")));
		templateText = templateText.replaceAll("\\$\\{sampleResource1\\}", Stream.concat(getExposedProperties().stream(), Stream.of(SYNTHETIC_FIELD_PK)).collect(Collectors.joining("\": \"(val)\",\"", "{\"", "\": \"(val)\"}")));
		templateText = templateText.replaceAll("\\$\\{sampleResource2\\}", Stream.concat(getExposedProperties().stream(), Stream.of(SYNTHETIC_FIELD_PK)).collect(Collectors.joining("\": \"(val)\",\"", "{\"", "\": \"(val)\"}")));
		try {
			templateText = templateText.replaceAll("\\$\\{sampleSchema\\}", new ObjectMapper().writeValueAsString(getSchemaMap()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new ByteArrayResource(templateText.getBytes(Charset.forName("UTF-8")));
	}

	@RequestMapping(value="/{code}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable String code, @Valid @RequestBody R dto) {
		assertUserHasWriteAccess();
		T dataObject = getFromDataStore(code);
		if (dataObject == null) {
			throw new ResourceNotFoundException("not found for key " + code);
		}
		RestAuditLogger logger = getAuditLogger();
		logUpdateToObject(dataObject, dto, logger);
		updateDataObjectFromDto(dataObject, dto);

		validateBusinessObject(dataObject);
		validateUpdateDataObject(dataObject);
		save(dataObject);
		logger.saveAuditLog();
	}

	@RequestMapping(method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SuppressWarnings("unchecked")
	public void update(@Valid @RequestBody Object dto) {
		assertUserHasWriteAccess();

		if (dto instanceof List) {
			((List<Object>) dto).stream().map(this::convertObjectToDto).forEach(this::doUpdate);
		} else {
			doUpdate(convertObjectToDto(dto));
		}
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@SuppressWarnings("unchecked")
	public @ResponseBody Object add(@Valid @RequestBody Object dto) {
		assertUserHasWriteAccess();

		if (dto instanceof List) {
			return ((List<Object>) dto).stream().map(this::convertObjectToDto).map(this::doAdd).collect(Collectors.toList());
		} else {
			return doAdd(convertObjectToDto(dto));
		}
	}

	@RequestMapping(value="/{code}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String code) {
		assertUserHasWriteAccess();
		doDelete(getFromDataStore(code));
	}

	@RequestMapping(method=RequestMethod.DELETE, params={ALLOW_MULTI_PARM})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll(@RequestParam(required=false) Map<String,String> parameters) {
		assertUserHasWriteAccess();
		doGetAll(parameters).stream().forEach(this::doDelete);
	}

	protected abstract R convertDataObjectToDto(T dataObject);

	protected abstract R convertObjectToDto(Object o);

	protected abstract T convertDtoToDataObject(R input);

	protected abstract Object getPropertyValueFromDto(String propertyName, R dto);

	protected abstract void updateDataObjectFromDto(T dataObject, R dto);

	protected abstract List<String> getExposedProperties();

	protected abstract List<String> getListOfTrackedProperties();

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

	protected Object getPrimaryKeyIncomingObject(R dataObject) {
		if (isCompoundPrimaryKey()) {
			final List<String> pkColumns = Arrays.asList(getPrimaryKeyColumn().split(DELIMETER));
			return new CompoundKey(pkColumns.stream()
					.map(pk -> {
						final Object val = getPropertyValueFromDto(pk, dataObject);
						if (val instanceof String && StringUtils.isBlank((String) val)) {
							throw new ResourceNotFoundException(pk + " is blank.");
						} else if (val == null) {
							throw new ResourceNotFoundException(pk + " is not present.");
						}
						return entry(pk, val);
					})
					.collect(entriesToMap()));
		} else {
			return getPropertyValueFromDto(getPrimaryKeyColumn(), dataObject);
		}
	}

	protected String getPropertyDescription(String propertyName) {
		if (isAttrDefined(propertyName)) {
			final String desc = dataDictionaryService.getAttributeDescription(getDataObjectClazz(), propertyName);
			final String summary = dataDictionaryService.getAttributeSummary(getDataObjectClazz(), propertyName);
			final String label = dataDictionaryService.getAttributeLabel(getDataObjectClazz(), propertyName);
			final String shortLabel = dataDictionaryService.getAttributeLabel(getDataObjectClazz(), propertyName);

			final Integer max = dataDictionaryService.getAttributeMaxLength(getDataObjectClazz().getName(), propertyName);
			final Integer min = dataDictionaryService.getAttributeMinLength(getDataObjectClazz().getName(), propertyName);

			final String maxInclusive = dataDictionaryService.getAttributeInclusiveMax(getDataObjectClazz().getName(), propertyName);
			final String minExclusive = dataDictionaryService.getAttributeExclusiveMin(getDataObjectClazz().getName(), propertyName);

			final String description = appendPeriod(StringUtils.isNotBlank(desc) ? desc :
					StringUtils.isNotBlank(summary) ? summary :
							StringUtils.isNotBlank(label) ? label : StringUtils.isNotBlank(shortLabel) ? shortLabel : "");

			final String maxLengthDescription = appendPeriod(max != null ? "Maximum length is " + max : "");
			final String maxValueDescription = appendPeriod(StringUtils.isNoneBlank(maxInclusive) ? "Maximum inclusive value is " + maxInclusive : "");

			final String minLengthDescription = appendPeriod(min != null ? "Minimum length is " + min : "");
			final String minValueDescription = appendPeriod(StringUtils.isNoneBlank(minExclusive) ? "Minimum enclusive value is " + minExclusive : "");

			return description + prependSpace(maxLengthDescription) +
					prependSpace(minLengthDescription) + prependSpace(maxValueDescription) +
					prependSpace(minValueDescription);
		}
		return "";
	}

	protected boolean isAttrDefined(String propertyName) {
		try {
			//some rice BOs are causing an exception while accessing the data dictionary
			return dataDictionaryService.isAttributeDefined(getDataObjectClazz(), propertyName);
		} catch (RuntimeException e) {
			LOG.info(getDataObjectClazz().getName() + "." + propertyName + " not defined in the data dictionary because of an exception.", e);
			return false;
		}
	}

	protected String appendPeriod(String s) {
		if (StringUtils.isNotBlank(s) && !StringUtils.endsWith(StringUtils.trim(s), ".")) {
			return StringUtils.trim(s) + ".";
		}
		return s;
	}

	protected String prependSpace(String s) {
		if (StringUtils.isNotBlank(s)) {
			return " " + StringUtils.trim(s);
		}
		return s;
	}

	protected String primaryKeyToString(Object pkValues) {
		final String key;
		if (pkValues instanceof CompoundKey){
			final String keyNamesStr = getPrimaryKeyColumn();
			final List<String> keyNames = Arrays.asList(keyNamesStr.split(DELIMETER));
			final Map<String, ?> keys = ((CompoundKey) pkValues).getKeys();
			if (keyNames.size() != keys.size()) {
				throw new IllegalArgumentException("compoundKey value does not contain the same number key elements in format: " + keyNamesStr);
			}
			key = keyNames.stream()
					.map(name -> keys.get(name).toString())
					.reduce((t, u) -> t + DELIMETER + u)
					.get();
		} else {
			key = pkValues.toString();
		}
		return key;
	}

    protected boolean isPrimitive(String name) {
        return beanWrapper.getPropertyType(name).isPrimitive();
    }

	protected Object translateValue(String name, String value) {
		return beanWrapper.convertIfNecessary(value, beanWrapper.getPropertyType(name));
	}

	protected Collection<R> translateAllDataObjects(Collection<T> dataObjects) {
		return dataObjects.stream()
				.map(this::convertDataObjectToDto)
				.collect(Collectors.toList());
	}

	protected void doUpdate(R dto) {
		final Object pk = getPrimaryKeyIncomingObject(dto);
		final T dataObject = getFromDataStore(pk);
		if (dataObject == null) {
			throw new ResourceNotFoundException("not found for key " + pk);
		}
		RestAuditLogger logger = getAuditLogger();
		logUpdateToObject(dataObject, dto, logger);
		updateDataObjectFromDto(dataObject, dto);

		validateBusinessObject(dataObject);
		validateUpdateDataObject(dataObject);
		save(dataObject);
		logger.saveAuditLog();
	}

	protected RestAuditLogger getAuditLogger() {
		return restAuditLoggerFactory.getNewAuditLogger(dataObjectClazz, getListOfTrackedProperties());
	}
	
	protected void logUpdateToObject(T currentObject, R newObject, RestAuditLogger logger) {
		T newDataObject = convertDtoToDataObject(newObject);
		logger.addModifiedItem(currentObject, newDataObject);
	}

	protected R doAdd(R dto) {
		final Object code = getPrimaryKeyIncomingObject(dto);
        if (code != null) {
            T existingDataObject = getFromDataStore(code);
		    if (existingDataObject != null) {
			    throw new UnprocessableEntityException("already exists for key " + code);
		    }
        }
		RestAuditLogger logger = getAuditLogger();
		T newDataObject = convertDtoToDataObject(dto);

		validateBusinessObject(newDataObject);
		validateInsertDataObject(newDataObject);
		T savedDataObject = save(newDataObject);
		logger.addNewItem(newDataObject);
		logger.saveAuditLog();
		return convertDataObjectToDto(savedDataObject);
	}

	protected void doDelete(T existingDataObject) {
		if (existingDataObject == null) {
			throw new ResourceNotFoundException("not found");
		}

		RestAuditLogger logger = getAuditLogger();
		validateDeleteDataObject(existingDataObject);

		delete(existingDataObject);
		logger.addDeletedItem(existingDataObject);
		logger.saveAuditLog();
	}

	protected Collection<T> doGetAll(Map<String, String> parameters) {
		final Collection<T> dataObjects;
		final Map<String, Object> searchCriteria = parameters != null ? parameters.entrySet().stream()
				.filter(e -> getExposedProperties().contains(e.getKey()))
				.map(entry -> {
					try {
						final Object val = translateValue(entry.getKey(), entry.getValue());
						return entry(entry.getKey(), val);
					} catch (TypeMismatchException e) {
						throw new ResourceNotFoundException(e.getMessage());
					}
				})
				.collect(entriesToMap()) : Collections.emptyMap();
		if (!CollectionUtils.isEmpty(searchCriteria)) {
			dataObjects = getMatchingFromDataStore(searchCriteria);
		} else {
			dataObjects = getAllFromDataStore();
		}

		if (dataObjects == null || dataObjects.isEmpty()) {
			throw new ResourceNotFoundException("not found" + (searchCriteria.isEmpty() ? "" : " for search criteria " + searchCriteria));
		}
		return dataObjects;
	}

	protected void validateDeleteDataObject(T dataObject) {
		throwIfErrorMessages(persistenceVerificationService.verifyRelationshipsForDelete(dataObject, Collections.emptyList()));
	}

	protected void validateUpdateDataObject(T dataObject) {
		throwIfErrorMessages(persistenceVerificationService.verifyRelationshipsForUpdate(dataObject, Collections.emptyList()));
	}

	protected void validateInsertDataObject(T dataObject) {
		throwIfErrorMessages(persistenceVerificationService.verifyRelationshipsForInsert(dataObject, Collections.emptyList()));
	}
	
	protected void validateBusinessObject(T dataObject) {
		if (!dictionaryValidationService.isBusinessObjectValid(dataObject)) {
			throwIfErrorMessages(getGlobalVariableService().getMessageMap());
		}
	}

	protected void throwIfErrorMessages(MessageMap messageMap) {
		if (messageMap != null && messageMap.hasErrors()) {
			Map<String, List<String>> errors = errorHandlingUtilService.extractErrorMessages(messageMap);
			if (errors != null && !errors.isEmpty()) {
				throw new DataDictionaryValidationException(errors);
			}
		}
	}
	
	protected Collection<T> getAllFromDataStore() {
		return getLegacyDataAdapter().findAll(getDataObjectClazz());
	}

	protected Collection<T> getMatchingFromDataStore(Map<String, ?> criteria) {
		return getLegacyDataAdapter().findMatching(getDataObjectClazz(), criteria);
	}

	protected T getFromDataStore(Object code) {
		if (isCompoundPrimaryKey() && code instanceof CompoundKey) {
			return getLegacyDataAdapter().findByPrimaryKey(getDataObjectClazz(), ((CompoundKey) code).getKeys());
		} else if (isCompoundPrimaryKey() && code instanceof String) {
			return getLegacyDataAdapter().findByPrimaryKey(getDataObjectClazz(), getCompoundKeyMap((String)code));
		} else if (code instanceof String) {
			if (StringUtils.isBlank((String) code)) {
				throw new ResourceNotFoundException(getPrimaryKeyColumn() + " is blank.");
			}
			return getLegacyDataAdapter().findBySinglePrimaryKey(getDataObjectClazz(), translateValue(getPrimaryKeyColumn(), (String) code));
		} else {
			if (code == null) {
				throw new ResourceNotFoundException(getPrimaryKeyColumn() + " is not present.");
			}

			return getLegacyDataAdapter().findBySinglePrimaryKey(getDataObjectClazz(), code);
		}
	}

	protected Map<String, Object> getCompoundKeyMap(String compoundKey) {

		if (compoundKey.contains(DELIMETER)) {
			final String[] keyNames = getPrimaryKeyColumn().split(DELIMETER);
			final String[] keyValues = compoundKey.split(DELIMETER);
			if (keyNames.length != keyValues.length) {
				throw new ResourceNotFoundException("compoundKey value does not contain the same number key elements in format: " + getPrimaryKeyColumn());
			}

			return org.kuali.coeus.sys.framework.util.CollectionUtils.zipMap(keyNames, keyValues).entrySet()
					.stream()
					.map(e -> entry(e.getKey(), translateValue(e.getKey(), e.getValue())))
					.collect(entriesToMap());
		} else {
			throw new ResourceNotFoundException("compoundKey value does not contain the same number key elements in format: " + getPrimaryKeyColumn());
		}
	}

	protected T save(T dataObject) {
        return getLegacyDataAdapter().save(dataObject);
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

	/**
	 * Uses basic english rules to convert a singular word to plural form.  Note that only the basic rules are covered
	 * here and many english words will not be handled correctly.
	 * @param singular a singular word
	 * @return a plural word
     */
	String toPlural(String singular) {

		//some rice mapped classes end with Bo or BO.  We should remove these suffixes.
		if (singular.endsWith("Bo") || singular.endsWith("BO")) {
			singular = singular.substring(0, singular.length() - 2);
		}

		if (singular.endsWith("y") && !singular.endsWith("ay") && !singular.endsWith("ey") && !singular.endsWith("iy") && !singular.endsWith("oy") && !singular.endsWith("uy")) {
			return singular.substring(0, singular.length() - 1) + "ies";
		} else if (singular.endsWith("s") || singular.endsWith("x") || singular.endsWith("z") || singular.endsWith("ch") || singular.endsWith("sh")) {
			return singular + "es";
		} else {
			return singular + "s";
		}
	}

	//this is a very roundabout way to get module path.  A better approach is preferred.
	private String getModuleMapping() {
		final KcConfigurer configurer = getModuleConfigurers().stream()
				.filter(mc -> mc instanceof KcConfigurer)
				.map(mc -> (KcConfigurer) mc)
				.filter(mc -> {
					final SimpleCrudRestControllerBase<?, ?> controller = mc.getRootResourceLoader().getService(new QName(getBeanName()));
					return controller != null && controller.getDataObjectClazz().equals(this.getDataObjectClazz());
				})
				.findFirst().get();
		return configurer.getDispatchServletMappings().stream().filter(mapping -> !mapping.contains("krad")).findFirst().get();
	}

	private String getPath() {
		return "/api/v1/" + (CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(this.getCamelCasePluralName()) + "/");
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
			final String path = getPath() + "*";
			autoRegisterMapping.setUrlMap(Collections.singletonMap(getPath(), this));
			autoRegisterMapping.registerHandler(path, this);
		}

		beanWrapper = new BeanWrapperImpl(dataObjectClazz);
        beanWrapper.registerCustomEditor(java.sql.Timestamp.class, new CustomSqlTimestampEditor());
        beanWrapper.registerCustomEditor(java.sql.Date.class, new CustomSqlDateEditor());

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
			final List<String> pks = persistenceVerificationService.pkFields(dataObjectClazz);
			primaryKeyColumn = pks.stream().sorted().reduce((t, u) -> t + DELIMETER + u).get();
		}

		return primaryKeyColumn;
	}

	public boolean isCompoundPrimaryKey() {
		return getPrimaryKeyColumn().contains(DELIMETER);
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

	public RestAuditLoggerFactory getRestAuditLoggerFactory() {
		return restAuditLoggerFactory;
	}

	public void setRestAuditLoggerFactory(RestAuditLoggerFactory restAuditLoggerFactory) {
		this.restAuditLoggerFactory = restAuditLoggerFactory;
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

	public Collection<ModuleConfigurer> getModuleConfigurers() {
		return (Collection<ModuleConfigurer>) moduleConfigurers;
	}

	public void setModuleConfigurers(Collection<ModuleConfigurer> moduleConfigurers) {
		this.moduleConfigurers = moduleConfigurers;
	}

	public String getBeanName() {
		return beanName;
	}

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public DataDictionaryService getDataDictionaryService() {
		return dataDictionaryService;
	}

	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}
}
