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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import com.codiform.moo.curry.Translate;

public class SimpleCrudDtoRestController<T extends PersistableBusinessObject, R> extends SimpleCrudRestControllerBase<T, R> {
	
	private Class<R> dtoObjectClazz;

	@Override
	protected Object getPropertyFromIncomingObject(String propertyName, Object dataObject) {
		try {
			return PropertyUtils.getProperty(dataObject, propertyName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected Collection<R> translateAllDataObjects(Collection<T> dataObjects) {
		final Collection<R> dtos = Translate.to(dtoObjectClazz).fromEach(dataObjects);
		dtos.forEach(this::setPrimaryKeyField);
		return dtos;
	}
	
	@Override
	protected R convertDataObject(T dataObject) {
		final R dto = Translate.to(dtoObjectClazz).from(dataObject);
		setPrimaryKeyField(dto);

		return dto;
	}

	protected void setPrimaryKeyField(R dto) {
		if (dto instanceof PrimaryKeyDto) {
			((PrimaryKeyDto) dto).set_primaryKey(primaryKeyToString(getPrimaryKeyIncomingObject(dto)));
		}
	}

	@Override
	protected T translateInputToDataObject(R input) {
		Configuration mooConfig = new Configuration();
		mooConfig.setSourcePropertiesRequired(false);
		Moo moo = new Moo(mooConfig);
		T newDataObject = getNewDataObject();
		moo.update(input, newDataObject);
		return newDataObject;
	}

	@Override
	protected void updateDataObjectFromInput(T existingDataObject, R input) {
		Configuration mooConfig = new Configuration();
		mooConfig.setSourcePropertiesRequired(false);
		Moo moo = new Moo(mooConfig);
		moo.update(input, existingDataObject);
	}

	@Override
	protected List<String> getExposedProperties() {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(dtoObjectClazz);
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
		return Arrays.asList(beanInfo.getPropertyDescriptors()).stream()
				.map(PropertyDescriptor::getName)
				.filter(name -> !"class".equals(name))
				.collect(Collectors.toList());
	}

	public Class<R> getDtoObjectClazz() {
		return dtoObjectClazz;
	}

	public void setDtoObjectClazz(Class<R> dtoObjectClazz) {
		this.dtoObjectClazz = dtoObjectClazz;
	}

	@Override
	protected List<String> getListOfTrackedProperties() {
		try {
			return Arrays.asList(Introspector.getBeanInfo(dtoObjectClazz).getPropertyDescriptors()).stream()
					.map(PropertyDescriptor::getName)
					.filter(name -> !"class".equals(name))
					.collect(Collectors.toList());
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}
}
