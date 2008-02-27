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
package org.kuali.kra.dao.ojb;

import static org.kuali.core.lookup.LookupUtils.applySearchResultsLimit;
import static org.kuali.core.lookup.LookupUtils.getSearchResultsLimit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.dao.LookupDao;
import org.kuali.core.dao.ojb.LookupDaoOjb;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.lookup.CollectionIncomplete;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.dao.RolodexDao;
import org.springframework.dao.DataIntegrityViolationException;
import org.springmodules.orm.ojb.OjbOperationException;

/**
 * OJB Implementation of <code>{@link RolodexDao}</code>
 * 
 */
public class RolodexDaoOjb extends PlatformAwareDaoBaseOjb implements RolodexDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(RolodexDaoOjb.class);
    
    private LookupDao lookupDao;
    
    /**
     * @see org.kuali.kra.dao.RolodexDao#getNonOrganizationalRolodexResults(java.util.Map, boolean)
     */
    public List<? extends BusinessObject> getNonOrganizationalRolodexResults(Map fieldValues, boolean usePrimaryKeys) {
        Collection searchResults = new ArrayList();
        Long matchingResultsCount = null;
        Criteria criteria = getNonOrganizationalRolodexCriteria(Rolodex.class, fieldValues, usePrimaryKeys);
        
         try {
            Integer searchResultsLimit = getSearchResultsLimit(Rolodex.class);
            if (searchResultsLimit != null) {
                matchingResultsCount = new Long(getPersistenceBrokerTemplate().getCount(QueryFactory.newQuery(Rolodex.class, criteria)));
                applySearchResultsLimit(Rolodex.class, criteria, getDbPlatform());
            }
            if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
                matchingResultsCount = new Long(0);
            }
            searchResults = getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Rolodex.class, criteria));
            
            LOG.info("Got results " + searchResults);
        }
        catch (OjbOperationException e) {
            throw new RuntimeException("RolodexDaoOjb encountered exception during executeSearch", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new RuntimeException("RolodexDaoOjb encountered exception during executeSearch", e);
        }
        return new CollectionIncomplete(searchResults, matchingResultsCount);    
    }
    
    /**
     * @see org.kuali.kra.dao.RolodexDao#getNonOrganizationalRolodexCriteria(java.lang.Class, java.util.Map, boolean)
     */
    public Criteria getNonOrganizationalRolodexCriteria(Class businessObjectClass, Map fieldValues, boolean usePrimaryKeys) {
        Criteria retval;
        
        if (usePrimaryKeys) {
            retval = getLookupDaoOjb().getCollectionCriteriaFromMapUsingPrimaryKeysOnly(businessObjectClass, fieldValues);
        }
        else {
            retval = getLookupDaoOjb().getCollectionCriteriaFromMap(checkBusinessObjectClass(businessObjectClass), fieldValues);   
        }  
        
        retval.addNotNull("firstName");
        retval.addNotNull("lastName");
        
        LOG.info("NonOrganizationalQuery" + retval);
        
        return retval;
    }

    
    private void addNotNullCriteria(Criteria criteria, String attributeName) {
        criteria.addNotNull(attributeName);
    }

    private PersistableBusinessObject checkBusinessObjectClass(Class businessObjectClass) {
        if (businessObjectClass == null) {
            throw new IllegalArgumentException("BusinessObject class passed to LookupDaoOjb findCollectionBySearchHelper... method was null");
        }
        PersistableBusinessObject businessObject = null;
        try {
            businessObject = (PersistableBusinessObject) businessObjectClass.newInstance();
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("LookupDaoOjb could not get instance of " + businessObjectClass.getName(), e);
        }
        catch (InstantiationException e) {
            throw new RuntimeException("LookupDaoOjb could not get instance of " + businessObjectClass.getName(), e);
        }
        return businessObject;
    }

    public LookupDao getLookupDao() {
        return lookupDao;
    }
    
    /**
     * @see org.kuali.kra.dao.RolodexDao#getLookupDaoOjb()
     */
    public LookupDaoOjb getLookupDaoOjb() {
        return (LookupDaoOjb) getLookupDao();
    }

    public void setLookupDao(LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }
}
