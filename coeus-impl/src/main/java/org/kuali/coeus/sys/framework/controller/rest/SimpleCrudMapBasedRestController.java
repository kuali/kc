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

import org.kuali.coeus.sys.framework.controller.rest.CustomEditors.CustomSqlDateEditor;
import org.kuali.coeus.sys.framework.controller.rest.CustomEditors.CustomSqlTimestampEditor;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.TypeMismatchException;

public class SimpleCrudMapBasedRestController<T> extends SimpleCrudRestControllerBase<T, Map<String, Object>> {

	private static final Collection<String> IGNORED_FIELDS = Stream.of("versionNumber", "objectId", "updateUser", "updateTimestamp").collect(Collectors.toList());

	private List<String> exposedProperties;

	@Override
	protected Object getPropertyValueFromDto(String propertyName, Map<String, Object> dataObject) {
		return dataObject.get(propertyName);
	}

	@Override
	protected Map<String, Object> convertDataObjectToDto(T dataObject) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(dataObject);
		return createMapFromPropsOnBean(beanWrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T convertDtoToDataObject(Map<String, Object> input) {
		T newDataObject = this.getNewDataObject();
		BeanWrapper beanWrapper = new BeanWrapperImpl(newDataObject);
        beanWrapper.registerCustomEditor(java.sql.Timestamp.class, new CustomSqlTimestampEditor());
        beanWrapper.registerCustomEditor(java.sql.Date.class, new CustomSqlDateEditor());

		try {
			getExposedProperties().forEach(name -> {
                final Object val = input.get(name);
                if (val != null || (val == null && !isPrimitive(name))) {
                    beanWrapper.setPropertyValue(name, translateValue(name, val != null ? val.toString() : null));
                }
            });
		} catch (IllegalArgumentException e) {
			getExposedProperties().forEach(name -> beanWrapper.setPropertyValue(name, input.get(name)));
		} catch (TypeMismatchException e) {
			throw new UnprocessableEntityException(e.getMessage());
		}
		return (T) beanWrapper.getWrappedInstance();
	}

	@Override
	protected void updateDataObjectFromDto(T existingDataObject,
			Map<String, Object> input) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(existingDataObject);
		try {
			getExposedProperties().forEach(name -> beanWrapper.setPropertyValue(name, input.get(name)));
		} catch (TypeMismatchException e) {
			throw new UnprocessableEntityException(e.getMessage());
		}
	}

	@Override
	protected List<String> getListOfTrackedProperties() {
		return getExposedProperties().stream().filter(p -> !SYNTHETIC_FIELD_PK.equals(p)).collect(Collectors.toList());
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Map<String, Object> convertObjectToDto(Object o) {
		return (Map<String, Object>) o;
	}

	protected Map<String, Object> createMapFromPropsOnBean(BeanWrapper dynaBean) {
		final Map<String, Object> map = getExposedProperties().stream()
				.map(name -> CollectionUtils.entry(name, dynaBean.getPropertyValue(name)))
				.collect(CollectionUtils.nullSafeEntriesToMap());
		map.put(SYNTHETIC_FIELD_PK, primaryKeyToString(getPrimaryKeyIncomingObject(map)));
		return map;
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
