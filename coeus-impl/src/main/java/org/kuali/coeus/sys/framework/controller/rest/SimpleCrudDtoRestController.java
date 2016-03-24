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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.WrapDynaBean;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import com.codiform.moo.curry.Translate;

public class SimpleCrudDtoRestController<T, R> extends SimpleCrudRestControllerBase<T, R> {

	private static final String CLASS = "class";

	private Class<R> dtoObjectClazz;

	@Override
	protected Object getPropertyValueFromDto(String propertyName, Object dataObject) {
		try {
			return PropertyUtils.getProperty(dataObject, propertyName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected R convertDataObjectToDto(T dataObject) {
		final R dto = Translate.to(dtoObjectClazz).from(dataObject);
		setPrimaryKeyField(dto);

		return dto;
	}

	@Override
	public T convertDtoToDataObject(R input) {
		Configuration mooConfig = new Configuration();
		mooConfig.setSourcePropertiesRequired(false);
		Moo moo = new Moo(mooConfig);
		T newDataObject = getNewDataObject();
		moo.update(input, newDataObject);
		return newDataObject;
	}

	@Override
	protected void updateDataObjectFromDto(T existingDataObject, R input) {
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
				.filter(name -> !CLASS.equals(name))
				.filter(name -> !(isPrimaryKeyDto() && PrimaryKeyDto.SYNTHETIC_FIELD_PK.equals(name)))
				.collect(Collectors.toList());
	}

	@Override
	protected List<String> getListOfTrackedProperties() {
		try {
			return Arrays.asList(Introspector.getBeanInfo(dtoObjectClazz).getPropertyDescriptors()).stream()
					.map(PropertyDescriptor::getName)
					.filter(name -> !CLASS.equals(name))
					.filter(name -> !(isPrimaryKeyDto() && PrimaryKeyDto.SYNTHETIC_FIELD_PK.equals(name)))
					.collect(Collectors.toList());
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	protected R convertObjectToDto(Object o) {
		if (dtoObjectClazz.isAssignableFrom(o.getClass())) {
			return (R) o;
		} else if (o instanceof Map){
			final WrapDynaBean dynaBean;
			try {
				dynaBean = new WrapDynaBean(dtoObjectClazz.newInstance());
			} catch (InstantiationException|IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			getExposedProperties().forEach(name -> dynaBean.set(name, ((Map)o).get(name)));
			return new Moo().translate(dynaBean.getInstance(), dtoObjectClazz);
		} else {
			throw new RuntimeException("unknown dto type " + o.getClass().getName());
		}
	}

	protected boolean isPrimaryKeyDto() {
		return PrimaryKeyDto.class.isAssignableFrom(dtoObjectClazz);
	}

	protected void setPrimaryKeyField(R dto) {
		if (dto instanceof PrimaryKeyDto) {
			((PrimaryKeyDto) dto).set_primaryKey(primaryKeyToString(getPrimaryKeyIncomingObject(dto)));
		}
	}

	public Class<R> getDtoObjectClazz() {
		return dtoObjectClazz;
	}

	public void setDtoObjectClazz(Class<R> dtoObjectClazz) {
		this.dtoObjectClazz = dtoObjectClazz;
	}
}
