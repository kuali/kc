/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.kim.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.kra.kim.bo.KimNamespace;
import org.kuali.kra.kim.bo.KimPermission;
import org.kuali.kra.kim.bo.KimPerson;
import org.kuali.kra.kim.bo.KimPersonQualifiedRoleAttribute;
import org.kuali.kra.kim.bo.KimQualifiedRolePerson;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.kim.bo.KimRolePermission;

public class KimDaoImpl extends PlatformAwareDaoBaseOjb implements KimDao {

    public Collection<KimQualifiedRolePerson> getPersonQualifiedRoles(Long personId, Map<String, String> qualifiedRoleAttributes) {

        Entry<String, String> entry = qualifiedRoleAttributes.entrySet().iterator().next();
        String attrName = entry.getKey();
        String attrValue = entry.getValue();

        ReportQueryByCriteria subQuery;
        Criteria subCrit = new Criteria();
        Criteria crit = new Criteria();

        subCrit.addEqualToField("rolePersonId", Criteria.PARENT_QUERY_PREFIX + "id");
        subCrit.addEqualTo("attributeName", attrName);
        subCrit.addEqualTo("attributeValue", attrValue);
        subQuery = QueryFactory.newReportQuery(KimPersonQualifiedRoleAttribute.class, subCrit);

        crit.addExists(subQuery);
        crit.addEqualTo("personId", personId);
        Query q = QueryFactory.newQuery(KimQualifiedRolePerson.class, crit);

        return getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }
    
    public boolean hasPermission(Collection<Long> roleIds, Long permissionId) {

       // long startTime = System.currentTimeMillis();
        boolean hasPermission = false;
        if (roleIds.size() > 0) {
            Criteria crit = new Criteria();
            crit.addIn("roleId", roleIds);
            crit.addEqualTo("permissionId", permissionId);
            Query q = QueryFactory.newQuery(KimRolePermission.class, crit);
    
            hasPermission = getPersistenceBrokerTemplate().getCount(q) > 0;
        }
       // long endTime = System.currentTimeMillis();
      // System.out.println("Fast Has Permission Time: " + (endTime - startTime) + " [" + roleIds.size() + "]");
        return hasPermission;
    }

    public Long getNamespaceId(String namespaceName) {
        Long namespaceId = -1L;
        
        Criteria crit = new Criteria();
        crit.addEqualTo("name", namespaceName);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(KimNamespace.class, crit);
        q.setAttributes(new String[] { "id" });

        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        while (iter.hasNext()) {
            Object[] row = (Object[]) iter.next();
            namespaceId = ((BigDecimal) row[0]).longValue();
        }
        return namespaceId;
    }

    public Long getPermissionId(Long namespaceId, String permissionName) {
        Long permissionId = -1L;
        
        Criteria crit = new Criteria();
        crit.addEqualTo("namespaceId", namespaceId);
        crit.addEqualTo("name", permissionName);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(KimPermission.class, crit);
        q.setAttributes(new String[] { "id" });

        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        while (iter.hasNext()) {
            Object[] row = (Object[]) iter.next();
            permissionId = ((BigDecimal) row[0]).longValue();
        }
        return permissionId;
    }

    public Long getPersonId(String username) {
        Long personId = -1L;
        
        Criteria crit = new Criteria();
        crit.addEqualTo("username", username);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(KimPerson.class, crit);
        q.setAttributes(new String[] { "id" });

        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        while (iter.hasNext()) {
            Object[] row = (Object[]) iter.next();
            personId = ((BigDecimal) row[0]).longValue();
        }
        return personId;
    }

    public Long getRoleId(String roleName) {
        Long roleId = -1L;
        
        Criteria crit = new Criteria();
        crit.addEqualTo("name", roleName);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(KimRole.class, crit);
        q.setAttributes(new String[] { "id" });

        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        while (iter.hasNext()) {
            Object[] row = (Object[]) iter.next();
            roleId = ((BigDecimal) row[0]).longValue();
        }
        return roleId;
    }
}
