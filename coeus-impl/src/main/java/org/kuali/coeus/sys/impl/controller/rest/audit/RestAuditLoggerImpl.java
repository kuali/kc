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
package org.kuali.coeus.sys.impl.controller.rest.audit;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLog;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerDao;
import org.kuali.coeus.sys.framework.util.CollectionUtils;

public class RestAuditLoggerImpl implements RestAuditLogger {

	private Class<?> dataObjectClass;
	private List<String> propertiesToTrack;
	private RestAuditLog restAuditLog;
	private RestAuditLoggerDao restAuditLoggerDao;
	
	public RestAuditLoggerImpl(String username, Class<?> dataObjectClass, List<String> propertiesToTrack,
			RestAuditLoggerDao restAuditLoggerDao) {
		this.dataObjectClass = dataObjectClass;
		this.propertiesToTrack = propertiesToTrack;
		this.restAuditLoggerDao = restAuditLoggerDao;
		restAuditLog = new RestAuditLog(username, dataObjectClass.getCanonicalName());
	}

	@Override
	public void addModifiedItem(Object currentItem, Object updatedItem) {
		restAuditLog.getModified().add(getUpdatedAuditLogItem(currentItem, updatedItem));
	}
	
	Map<String, Object> getUpdatedAuditLogItem(Object current, Object updated) {
		DynaBean currentDynaBean = new WrapDynaBean(current);
		DynaBean updatedDynaBean = new WrapDynaBean(updated);
		return propertiesToTrack.stream()
			.map(name -> {
				Object currentValue = currentDynaBean.get(name);
				Object newValue = updatedDynaBean.get(name);
				if (areValuesEqual(currentValue, newValue)) {
					return CollectionUtils.entry(name, currentValue);
				} else {
					Map<String, Object> diffs = Stream.of(CollectionUtils.entry("old", currentValue), CollectionUtils.entry("new", newValue)).collect(CollectionUtils.nullSafeEntriesToMap());
					return CollectionUtils.entry(name, (Object) diffs);
				}
			})
			.collect(CollectionUtils.nullSafeEntriesToMap());
	}

	protected boolean areValuesEqual(Object currentValue, Object newValue) {
			return Objects.equals(currentValue, newValue);
	}

	@Override
	public void addNewItem(Object newItem) {
		restAuditLog.getAdded().add(getAuditLogItem(newItem));
	}
	
	Map<String, Object> getAuditLogItem(Object current) {
		DynaBean currentDynaBean = new WrapDynaBean(current);
		return propertiesToTrack.stream()
			.map(name -> {
				return CollectionUtils.entry(name, currentDynaBean.get(name));
			})
			.collect(CollectionUtils.nullSafeEntriesToMap());
	}

	@Override
	public void addDeletedItem(Object deletedItem) {
		restAuditLog.getDeleted().add(getAuditLogItem(deletedItem));
	}

	@Override
	public void saveAuditLog() {
		restAuditLog.setDate(Instant.now());
		restAuditLoggerDao.saveAuditLog(dataObjectClass, restAuditLog);
		restAuditLog = new RestAuditLog(restAuditLog.getUsername(), dataObjectClass.getCanonicalName());
	}

	public Class<?> getDataObjectClass() {
		return dataObjectClass;
	}

	public void setDataObjectClass(Class<?> dataObjectClass) {
		this.dataObjectClass = dataObjectClass;
	}

	public List<String> getPropertiesToTrack() {
		return propertiesToTrack;
	}

	public void setPropertiesToTrack(List<String> propertiesToTrack) {
		this.propertiesToTrack = propertiesToTrack;
	}

	public RestAuditLog getRestAuditLog() {
		return restAuditLog;
	}

	public void setRestAuditLog(RestAuditLog restAuditLog) {
		this.restAuditLog = restAuditLog;
	}

	public RestAuditLoggerDao getRestAuditLoggerDao() {
		return restAuditLoggerDao;
	}

	public void setRestAuditLoggerDao(RestAuditLoggerDao restAuditLoggerDao) {
		this.restAuditLoggerDao = restAuditLoggerDao;
	}

}
