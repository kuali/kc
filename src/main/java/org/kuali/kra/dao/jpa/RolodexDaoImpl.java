/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.dao.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.dao.RolodexDao;
import org.kuali.rice.exceptions.RiceRuntimeException;
import org.springframework.transaction.annotation.Transactional;

/**
 * OJB Implementation of <code>{@link RolodexDao}</code>
 * 
 */
public class RolodexDaoImpl implements RolodexDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(RolodexDaoImpl.class);
    
    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    /**
     * @see org.kuali.kra.dao.RolodexDao#getNonOrganizationalRolodexResults(java.util.Map, boolean)
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public List<NonOrganizationalRolodex> getNonOrganizationalRolodexResults(Map<String, String> fieldValues, boolean usePrimaryKeys) {
        try {
            Criteria criteria = getSession().createCriteria(NonOrganizationalRolodex.class);
            for(Object key: fieldValues.keySet()) {            
                criteria.add(Restrictions.ilike((String)key, fieldValues.get(key)));
            }
//            criteria.add(Restrictions.isNotNull("firstName"));
//            criteria.add(Restrictions.isNotNull("lastName"));
            
            List<NonOrganizationalRolodex> results = (List<NonOrganizationalRolodex>) criteria.list();            
            
            return results;
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RiceRuntimeException(e);
        }
    }
    
    private Session getSession() {
        return ((Session) em.getDelegate());
    }
}
