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
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.dao.LookupDao;
import org.kuali.core.dao.jpa.LookupDaoJpa;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.dao.RolodexDao;

/**
 * OJB Implementation of <code>{@link RolodexDao}</code>
 * 
 */
public class RolodexDaoJpa implements RolodexDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(RolodexDaoJpa.class);
    
    private LookupDao lookupDao;
    
    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    /**
     * @see org.kuali.kra.dao.RolodexDao#getNonOrganizationalRolodexResults(java.util.Map, boolean)
     */
    public List<? extends BusinessObject> getNonOrganizationalRolodexResults(Map fieldValues, boolean usePrimaryKeys) {
        
//        Collection searchResults = new ArrayList();
//        Long matchingResultsCount = null;
//        Criteria criteria = getNonOrganizationalRolodexCriteria(Rolodex.class, fieldValues, usePrimaryKeys);
//        
//         try {
//            Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Rolodex.class);
//            if (searchResultsLimit != null) {
//                matchingResultsCount = new Long(getPersistenceBrokerTemplate().getCount(QueryFactory.newQuery(Rolodex.class, criteria)));
//                LookupUtils.applySearchResultsLimit(Rolodex.class, criteria, getDbPlatform());
//            }
//            if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
//                matchingResultsCount = new Long(0);
//            }
//            searchResults = getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Rolodex.class, criteria));
//            
//            LOG.info("Got results " + searchResults);
//        }
//        catch (OjbOperationException e) {
//            throw new RuntimeException("RolodexDaoOjb encountered exception during executeSearch", e);
//        }
//        catch (DataIntegrityViolationException e) {
//            throw new RuntimeException("RolodexDaoOjb encountered exception during executeSearch", e);
//        }
//        return new CollectionIncomplete(searchResults, matchingResultsCount);
        
        Criteria criteria = getSession().createCriteria(Rolodex.class);
        for(Object key: fieldValues.keySet()) {            
            criteria.add(Restrictions.eq((String)key, fieldValues.get(key)));
        }
        criteria.add(Restrictions.isNotNull("firstName"));
        criteria.add(Restrictions.isNotNull("lastName"));
        
        return criteria.list();
    }
    
    /**
     * Create <code>{@link Criteria}</code> instance to search for <code>{@link NonOrganizationalRolodex}</code>
     * instances.
     * 
     * @param businessObjectClass
     * @param fieldValues
     * @param usePrimaryKeys indicates whether to simplify the search due to criteria restricted to primary keys
     * @return Criteria
     */
//    public Criteria getNonOrganizationalRolodexCriteria(Class businessObjectClass, Map fieldValues, boolean usePrimaryKeys) {
//        Criteria retval = null;
//        
//        if (usePrimaryKeys) {
//            retval = getLookupDaoJpa().getCollectionCriteriaFromMapUsingPrimaryKeysOnly(businessObjectClass, fieldValues);
//        } else {
//            retval = getLookupDaoJpa().getCollectionCriteriaFromMap(checkBusinessObjectClass(businessObjectClass), fieldValues);   
//        }  
//        
//        retval.addNotNull("firstName");
//        retval.addNotNull("lastName");
//        
//        LOG.info("NonOrganizationalQuery" + retval);
//        
//        return retval;
//    }

    
//    private PersistableBusinessObject checkBusinessObjectClass(Class businessObjectClass) {
//        if (businessObjectClass == null) {
//            throw new IllegalArgumentException("BusinessObject class passed to LookupDaoOjb findCollectionBySearchHelper... method was null");
//        }
//        PersistableBusinessObject businessObject = null;
//        try {
//            businessObject = (PersistableBusinessObject) businessObjectClass.newInstance();
//        }
//        catch (IllegalAccessException e) {
//            throw new RuntimeException("LookupDaoOjb could not get instance of " + businessObjectClass.getName(), e);
//        }
//        catch (InstantiationException e) {
//            throw new RuntimeException("LookupDaoOjb could not get instance of " + businessObjectClass.getName(), e);
//        }
//        return businessObject;
//    }

//  
//  /**
//   * @see org.kuali.kra.dao.RolodexDao#getLookupDaoOjb()
//   */
//  public LookupDaoJpa getLookupDaoJpa() {
//      return (LookupDaoJpa) getLookupDao();
//  }
    
    public LookupDao getLookupDao() {
        return lookupDao;
    }

    public void setLookupDao(LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }
    
    private Session getSession() {
        return ((Session) em.getDelegate());
    }
}
