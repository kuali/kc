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

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLog;


public class RestAuditLoggerDaoImplTest {

	@Test
	public void testGetAllAuditLogs() {
		final RestAuditLogDataObject auditLog = new RestAuditLogDataObject();
		auditLog.setClassName(InstituteRate.class.getCanonicalName());
		auditLog.setUsername("quickstart");
		auditLog.setDate(new Date());
		auditLog.setModified("[{\"instituteRate\":{\"new\":35.00,\"old\":4.00},\"activityTypeCode\":\"1\",\"onOffCampusFlag\":true,\"unitNumber\":\"000001\",\"rateTypeCode\":\"1\",\"id\":1551,\"rateClassCode\":\"5\",\"fiscalYear\":\"2011\",\"startDate\":\"2010-06-23\"}]");
		auditLog.setAdded("[{\"instituteRate\":13.00,\"activityTypeCode\":\"1\",\"onOffCampusFlag\":true,\"unitNumber\":\"000001\",\"rateTypeCode\":\"1\",\"id\":1551,\"rateClassCode\":\"5\",\"fiscalYear\":\"2011\",\"startDate\":\"2010-06-24\"}, {\"instituteRate\":15.00,\"activityTypeCode\":\"1\",\"onOffCampusFlag\":true,\"unitNumber\":\"000001\",\"rateTypeCode\":\"1\",\"id\":1553,\"rateClassCode\":\"5\",\"fiscalYear\":\"2011\",\"startDate\":\"2010-06-24\"}]");
		auditLog.setDeleted("[{\"instituteRate\":22.00,\"activityTypeCode\":\"1\",\"onOffCampusFlag\":true,\"unitNumber\":\"000001\",\"rateTypeCode\":\"1\",\"id\":1552,\"rateClassCode\":\"5\",\"fiscalYear\":\"2011\",\"startDate\":\"2010-06-24\"}]");
		RestAuditLoggerDaoImpl dao = new RestAuditLoggerDaoImpl() {
			protected List<RestAuditLogDataObject> getRestAuditLogsFromDatabase(Class<?> dataObjectClass) {
				return Arrays.asList(auditLog);
			}
		};
		List<RestAuditLog> logs = dao.getAuditLogsForDataObject(InstituteRate.class);
		assertEquals(1, logs.size());
		RestAuditLog newLog = logs.get(0);
		assertEquals(2, newLog.getAdded().size());
		assertEquals(13.0, newLog.getAdded().get(0).get("instituteRate"));
		assertEquals(15.0, newLog.getAdded().get(1).get("instituteRate"));
		assertEquals(1, newLog.getDeleted().size());
		assertEquals(22.0, newLog.getDeleted().get(0).get("instituteRate"));
		assertEquals(1552, newLog.getDeleted().get(0).get("id"));
		
		assertEquals(1, newLog.getModified().size());
		Map<String, Object> modifiedMap = (Map<String, Object>) newLog.getModified().get(0).get("instituteRate");
		assertEquals(35.0, modifiedMap.get("new"));
		assertEquals(4.0, modifiedMap.get("old"));
	}
	
}
