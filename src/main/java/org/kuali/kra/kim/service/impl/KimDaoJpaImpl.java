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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.kuali.kra.kim.bo.KimQualifiedRolePerson;
import org.springframework.transaction.annotation.Transactional;

/**
 * The KIM DAO implementation.  Uses OJB.
 */
public class KimDaoJpaImpl implements KimDao {

    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    /**
     * @see org.kuali.kra.kim.service.impl.KimDao#getPersonQualifiedRoles(java.lang.Long, java.util.Map)
     */
    @Transactional
    public Collection<KimQualifiedRolePerson> getPersonQualifiedRoles(Long personId, Map<String, String> qualifiedRoleAttributes) {
        
        Entry<String, String> entry = qualifiedRoleAttributes.entrySet().iterator().next();
        String attrName = entry.getKey();
        String attrValue = entry.getValue();

        Criteria criteria = getSession().createCriteria(KimQualifiedRolePerson.class);
        
        Criteria subCrit = criteria.createCriteria("qualifiedRoleAttributes");
        //subCrit.addEqualToField("rolePersonId", "id");
        subCrit.add(Restrictions.eq("attributeName", attrName));
        subCrit.add(Restrictions.eq("attributeValue", attrValue));        
//        crit.addExists(subQuery);
        criteria.add(Restrictions.eq("personId", personId));
        
        return criteria.list();
    }
    
    public Set<Long> getQualifiedRolePersonIds(Long roleId, Map<String, String> qualifiedRoleAttributes) {
        Set<Long> personIds = new HashSet<Long>();
        
        // TODO fill me in
        return personIds;
    }
    
    /**
     * @see org.kuali.kra.kim.service.impl.KimDao#hasPermission(java.util.Collection, java.lang.Long)
     */
    @Transactional
    public boolean hasPermission(Collection<Long> roleIds, Long permissionId) {
        Long count = 0L;
        if (roleIds.size() > 0) {
            StringBuilder sb = new StringBuilder("select count(krp.active) as count from KimRolePermission krp where krp.permissionId = :permissionId and krp.roleId in (");
            for(Long roleId: roleIds) {
                sb.append(roleId);
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
            sb.append(")");
            Query query = em.createQuery(sb.toString());
            
            query.setParameter("permissionId", permissionId);            
            count = (Long) query.getSingleResult();
        }
        return count > 0;
    }
    
    private Session getSession() {
        return (Session) em.getDelegate();
    }
}
