/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.dao.VersionHistoryLookupDao;
import org.kuali.kra.infrastructure.KraServiceLocator;
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
//        retval.addPathClass("versionHistory", VersionHistory.class);
//        retval.addEqualToField("sequenceOwnerVersionNameValue", "versionHistory.sequenceOwnerVersionNameValue");
//        retval.addEqualToField("sequenceOwnerSequenceNumber", "versionHistory.sequenceOwnerSequenceNumber");
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
        LookupDao lookupDao = KraServiceLocator.getService(LookupDao.class);
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
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        if (dataDictionaryService.isAttributeDefined(persistBo.getClass(), propertyName)) {
            caseInsensitive = !dataDictionaryService.getAttributeForceUppercase(persistBo.getClass(), propertyName);
        }
        return caseInsensitive;
    }

}
