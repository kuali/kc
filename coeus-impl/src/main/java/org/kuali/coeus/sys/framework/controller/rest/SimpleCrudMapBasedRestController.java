package org.kuali.coeus.sys.framework.controller.rest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class SimpleCrudMapBasedRestController<T extends PersistableBusinessObject> extends SimpleCrudRestControllerBase<T, Map<String, Object>> {

	private static final Collection<String> IGNORED_FIELDS = Stream.of("versionNumber", "objectId", "updateUser", "updateTimestamp").collect(Collectors.toList());

	private List<String> exposedProperties;

	public SimpleCrudMapBasedRestController() { }
	
	public SimpleCrudMapBasedRestController(
			Class<T> dataObjectClazz, String primaryKeyColumn, List<String> exposedProperties,
			String writePermissionNamespace, String writePermissionName) {
		super(dataObjectClazz, primaryKeyColumn, writePermissionNamespace, writePermissionName);
		this.exposedProperties = exposedProperties;
	}
	
	@Override
	protected Object getPrimaryKeyIncomingObject(Map<String, Object> dataObject) {
		return dataObject.get(getPrimaryKeyColumn());
	}

	@Override
	protected Collection<Map<String, Object>> translateAllDataObjects(Collection<T> dataObjects) {
		 return dataObjects.stream()
			.map(WrapDynaBean::new)
			.map(this::createMapFromPropsOnBean)
			.collect(Collectors.toList());
	}

	@Override
	protected Map<String, Object> convertDataObject(T dataObject) {
		DynaBean dynaBean = new WrapDynaBean(dataObject);
		return createMapFromPropsOnBean(dynaBean);
	}
	
	protected Map<String, Object> createMapFromPropsOnBean(DynaBean dynaBean) {
		return getExposedProperties().stream()
				.map(name -> CollectionUtils.entry(name, dynaBean.get(name)))
				.collect(CollectionUtils.nullSafeEntriesToMap());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T translateInputToDataObject(Map<String, Object> input) {
		T newDataObject = this.getNewDataObject();
		WrapDynaBean dynaBean = new WrapDynaBean(newDataObject);
		getExposedProperties().forEach(name -> dynaBean.set(name, input.get(name)));
		return (T) dynaBean.getInstance();
	}

	@Override
	protected void updateDataObjectFromInput(T existingDataObject,
			Map<String, Object> input) {
		WrapDynaBean dynaBean = new WrapDynaBean(existingDataObject);
		getExposedProperties().forEach(name -> dynaBean.set(name, input.get(name)));
	}

	public List<String> getExposedProperties() {
		if (org.apache.commons.collections4.CollectionUtils.isEmpty(exposedProperties)) {
			exposedProperties = getDefaultProperties();
		}

		return exposedProperties;
	}

	public void setExposedProperties(List<String> exposedProperties) {
		this.exposedProperties = exposedProperties;
	}

	public List<String> getDefaultProperties() {
		final List<String> fields = getPersistenceVerificationService().persistableFields(getDataObjectClazz());

		return fields.stream().filter(field -> !IGNORED_FIELDS.contains(field)).collect(Collectors.toList());
	}
}
