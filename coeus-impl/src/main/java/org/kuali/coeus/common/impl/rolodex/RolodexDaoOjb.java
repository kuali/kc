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
package org.kuali.coeus.common.impl.rolodex;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.springframework.dao.DataIntegrityViolationException;
import org.springmodules.orm.ojb.OjbOperationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * OJB Implementation of <code>{@link RolodexDao}</code>
 * 
 */
public class RolodexDaoOjb extends LookupDaoOjb implements RolodexDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(RolodexDaoOjb.class);
    
    @Override
    public List<? extends BusinessObject> getNonOrganizationalRolodexResults(Map fieldValues, boolean usePrimaryKeys) {
        Collection searchResults = new ArrayList();
        Long matchingResultsCount = null;
        Criteria criteria = getNonOrganizationalRolodexCriteria(Rolodex.class, fieldValues, usePrimaryKeys);
        
         try {
            Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Rolodex.class);
            if (searchResultsLimit != null) {
                matchingResultsCount = new Long(getPersistenceBrokerTemplate().getCount(QueryFactory.newQuery(Rolodex.class, criteria)));
                LookupUtils.applySearchResultsLimit(Rolodex.class, criteria, getDbPlatform());
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

    protected Criteria getNonOrganizationalRolodexCriteria(Class businessObjectClass, Map fieldValues, boolean usePrimaryKeys) {
        Criteria retval;
        
        if (usePrimaryKeys) {
            retval = getCollectionCriteriaFromMapUsingPrimaryKeysOnly(businessObjectClass, fieldValues);
        }
        else {
            retval = getCollectionCriteriaFromMap(checkBusinessObjectClass(businessObjectClass), fieldValues);   
        }  
        
        retval.addNotNull("firstName");
        retval.addNotNull("lastName");
        //retval.addNotEqualToField("active", "No");\
        retval.addNotEqualTo("active", Boolean.FALSE);
        
        LOG.info("NonOrganizationalQuery" + retval);
        
        return retval;
    }

    
    private PersistableBusinessObject checkBusinessObjectClass(Class businessObjectClass) {
        if (businessObjectClass == null) {
            throw new IllegalArgumentException("BusinessObject class passed to RolodexDaoOjb findCollectionBySearchHelper... method was null");
        }
        PersistableBusinessObject businessObject = null;
        try {
            businessObject = (PersistableBusinessObject) businessObjectClass.newInstance();
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("RolodexDaoOjb could not get instance of " + businessObjectClass.getName(), e);
        }
        catch (InstantiationException e) {
            throw new RuntimeException("RolodexDaoOjb could not get instance of " + businessObjectClass.getName(), e);
        }
        return businessObject;
    }
}
