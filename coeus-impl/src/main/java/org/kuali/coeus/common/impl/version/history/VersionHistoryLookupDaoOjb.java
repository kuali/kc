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
package org.kuali.coeus.common.impl.version.history;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.dao.LookupDao;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.kuali.rice.krad.lookup.CollectionIncomplete;

import java.util.*;

@SuppressWarnings("unchecked")
public class VersionHistoryLookupDaoOjb extends LookupDaoOjb  implements VersionHistoryLookupDao {

    private DataDictionaryService dataDictionaryService;

    private LookupDao lookupDao;

    @SuppressWarnings({ "rawtypes", "deprecation" })
    public List<? extends BusinessObject> getSequenceOwnerSearchResults(Class sequenceOwnerClass,Map fieldValues, boolean usePrimaryKeys){
        Collection searchResults = new ArrayList();
        Long matchingResultsCount = null;
        Criteria criteria = getActiveOrPendingVersionCriteria(sequenceOwnerClass, fieldValues, usePrimaryKeys);
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(sequenceOwnerClass);
        if (searchResultsLimit != null) {
            matchingResultsCount = new Long(getPersistenceBrokerTemplate().getCount(QueryFactory.newQuery(sequenceOwnerClass, criteria)));
            LookupUtils.applySearchResultsLimit(sequenceOwnerClass, criteria, getDbPlatform());
        }
        if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
            matchingResultsCount = new Long(0);
        }
        searchResults = getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(sequenceOwnerClass, criteria));

        return new CollectionIncomplete(searchResults, matchingResultsCount);    
    }

    private Criteria getActiveOrPendingVersionCriteria(Class sequenceOwnerClass, Map fieldValues, boolean usePrimaryKeys) {
        Criteria retval;
        if (usePrimaryKeys) {
            retval = getCollectionCriteriaFromMapUsingPrimaryKeysOnly(sequenceOwnerClass, fieldValues);
        }else {
            retval = getCollectionCriteriaFromMap(checkBusinessObjectClass(sequenceOwnerClass), fieldValues);   
        }

        retval.addEqualTo("versionHistory.sequenceOwnerClassName", sequenceOwnerClass.getName());
        List<String> statuses = new ArrayList<String>(2);
        statuses.add("ACTIVE");
        statuses.add("PENDING");
        Criteria orCriteria = new Criteria();
        orCriteria.addIn("versionHistory.statusForOjb", statuses);
        retval.addAndCriteria(orCriteria);
        return retval;
    }
    
    private Criteria getCollectionCriteriaFromMap(PersistableBusinessObject businessObject, Map formProps) {
        Criteria criteria = new Criteria();
        Iterator propsIter = formProps.keySet().iterator();
        while (propsIter.hasNext()) {
            String propertyName = (String) propsIter.next();
            if (formProps.get(propertyName) instanceof Collection) {
                Iterator iter = ((Collection) formProps.get(propertyName)).iterator();
                while (iter.hasNext()) {
                    
                    if (!lookupDao.createCriteria(businessObject, (String) iter.next(), propertyName, 
                            isCaseSensitive(businessObject,  propertyName), false, criteria)) {
                        throw new RuntimeException("Invalid value in Collection");
                    }
                }
            } else {
                if (!lookupDao.createCriteria(businessObject, (String) formProps.get(propertyName), propertyName, 
                        isCaseSensitive(businessObject,  propertyName), false, criteria)) {
                    continue;
                }
            }
        }
        return criteria;
    }
    private PersistableBusinessObject checkBusinessObjectClass(Class businessObjectClass) {
        if (businessObjectClass == null) {
            throw new IllegalArgumentException("BusinessObject class passed to VersionHistoryLookupDaoOjb findCollectionBySearchHelper... method was null");
        }
        PersistableBusinessObject businessObject = null;
        try {
            businessObject = (PersistableBusinessObject) businessObjectClass.newInstance();
        }catch (IllegalAccessException e) {
            throw new RuntimeException("VersionHistoryLookupDaoOjb could not get instance of " + businessObjectClass.getName(), e);
        }catch (InstantiationException e) {
            throw new RuntimeException("VersionHistoryLookupDaoOjb could not get instance of " + businessObjectClass.getName(), e);
        }
        return businessObject;
    }
    /*
     * extract method for casesensitive in method getCollectionCriteriaFromMap
     */
    private boolean isCaseSensitive(PersistableBusinessObject persistBo, String  propertyName) {
        
        boolean caseInsensitive = false;
        if (dataDictionaryService.isAttributeDefined(persistBo.getClass(), propertyName)) {
            caseInsensitive = !dataDictionaryService.getAttributeForceUppercase(persistBo.getClass(), propertyName);
        }
        return caseInsensitive;
    }

    public DataDictionaryService getDataDictionaryService() {
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    public LookupDao getLookupDao() {
        return lookupDao;
    }

    public void setLookupDao(LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }
}
