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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class SimpleCrudMapBasedRestController<T extends PersistableBusinessObject> extends SimpleCrudRestControllerBase<T, Map<String, Object>> {

	protected static final String SYNTHETIC_FIELD_PK = "_primaryKey";
	private static final Collection<String> IGNORED_FIELDS = Stream.of("versionNumber", "objectId", "updateUser", "updateTimestamp").collect(Collectors.toList());

	private List<String> exposedProperties;

	@Override
	protected Object getPropertyFromIncomingObject(String propertyName, Map<String, Object> dataObject) {
		return dataObject.get(propertyName);
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
		final Map<String, Object> map = getExposedProperties().stream()
				.map(name -> CollectionUtils.entry(name, dynaBean.get(name)))
				.collect(CollectionUtils.nullSafeEntriesToMap());
        map.put(SYNTHETIC_FIELD_PK, primaryKeyToString(getPrimaryKeyIncomingObject(map)));
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T translateInputToDataObject(Map<String, Object> input) {
		T newDataObject = this.getNewDataObject();
		WrapDynaBean dynaBean = new WrapDynaBean(newDataObject);
		try {
			getExposedProperties().forEach(name -> dynaBean.set(name, input.get(name)));
		} catch (IllegalArgumentException e) {
			throw new UnprocessableEntityException(e.getMessage());
		}
		return (T) dynaBean.getInstance();
	}

	@Override
	protected void updateDataObjectFromInput(T existingDataObject,
			Map<String, Object> input) {
		WrapDynaBean dynaBean = new WrapDynaBean(existingDataObject);
		try {
			getExposedProperties().forEach(name -> dynaBean.set(name, input.get(name)));
		} catch (IllegalArgumentException e) {
			throw new UnprocessableEntityException(e.getMessage());
		}
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

	@Override
	protected List<String> getListOfTrackedProperties() {
		return getExposedProperties().stream().filter(p -> !SYNTHETIC_FIELD_PK.equals(p)).collect(Collectors.toList());
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Map<String, Object> objectToDto(Object o) {
		return (Map<String, Object>) o;
	}
}
