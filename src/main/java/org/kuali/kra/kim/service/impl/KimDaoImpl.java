/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.kra.kim.bo.KimPersonQualifiedRoleAttribute;
import org.kuali.kra.kim.bo.KimQualifiedRolePerson;
import org.kuali.kra.kim.bo.KimRolePermission;

/**
 * The KIM DAO implementation.  Uses OJB.
 */
public class KimDaoImpl extends PlatformAwareDaoBaseOjb implements KimDao {

    /**
     * @see org.kuali.kra.kim.service.impl.KimDao#getPersonQualifiedRoles(java.lang.Long, java.util.Map)
     */
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
    
    
    public Set<Long> getQualifiedRolePersonIds(Long roleId, Map<String, String> qualifiedRoleAttributes) {
        Set<Long> personIds = new HashSet<Long>();
        
        //This will be the Proposal Key
        Entry<String, String> entry = qualifiedRoleAttributes.entrySet().iterator().next();
        String attrName = entry.getKey();
        String attrValue = entry.getValue();
        
        Criteria crit = new Criteria();
        crit.addEqualTo("qualifiedRoleAttributes.attributeName", attrName);
        crit.addEqualTo("qualifiedRoleAttributes.attributeValue", attrValue);
        crit.addEqualTo("roleId", roleId);
        ReportQueryByCriteria query = QueryFactory.newReportQuery(KimQualifiedRolePerson.class, crit);
        query.setAttributes(new String[] { "personId" });
        
        PersistenceBroker pbInstance = getPersistenceBroker(true);
        Iterator iterator = pbInstance.getReportQueryIteratorByQuery(query);
        while (iterator.hasNext()) {
            Object[] result = (Object[])iterator.next();
            BigDecimal personId = (BigDecimal) result[0];
            if(personId != null) {
                personIds.add(new Long(personId.longValue()));
            }
        }
        releasePersistenceBroker(pbInstance);
        return personIds;
    }
    
    /**
     * @see org.kuali.kra.kim.service.impl.KimDao#hasPermission(java.util.Collection, java.lang.Long)
     */
    public boolean hasPermission(Collection<Long> roleIds, Long permissionId) {

        boolean hasPermission = false;
        if (roleIds.size() > 0) {
            Criteria crit = new Criteria();
            crit.addIn("roleId", roleIds);
            crit.addEqualTo("permissionId", permissionId);
            crit.addEqualTo("active", true); 
            Query q = QueryFactory.newQuery(KimRolePermission.class, crit);
    
            hasPermission = getPersistenceBrokerTemplate().getCount(q) > 0;
        }
        return hasPermission;
    }
}
