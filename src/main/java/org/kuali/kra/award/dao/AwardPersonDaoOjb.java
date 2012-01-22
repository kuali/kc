/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.dao.LookupDao;
import org.kuali.rice.krad.util.OjbCollectionAware;

public class AwardPersonDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, AwardPersonDao {
    private LookupDao lookupDao;
    private DataDictionaryService dataDictionaryService;
    
    @SuppressWarnings("unchecked")
    public List<AwardPerson> getAwardPersons(Map<String, String> fieldValues) {
        Query query = new QueryByCriteria(AwardPerson.class, getCollectionCriteriaFromMap(fieldValues));
        return new ArrayList<AwardPerson>(super.getPersistenceBrokerTemplate().getCollectionByQuery(query));
    }
    
    /**
     * @param dataDictionaryService
     */
    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }
    
    /**
     * @param lookupDao
     */
    public void setLookupDao(LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }
    
    /*
     * 
     * Builds up criteria object based on the object and map.
     * This method is copied from lookDaoOjb, but not published in lookupdao, so can't access it directly.
     * @param businessObject
     * @param formProps
     * @return
     */ 
    private Criteria getCollectionCriteriaFromMap(Map<String, String> fieldValues) {
        AwardPerson awardPerson = new AwardPerson();
        Criteria criteria = new Criteria();
        for(String fieldName: fieldValues.keySet()) {
            String fieldValue = (String) fieldValues.get(fieldName);
            if (!lookupDao.createCriteria(awardPerson, fieldValue, fieldName, isCaseSensitive(awardPerson,  fieldName), false, criteria)) {
                continue;
            }
        }
        return criteria;
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
}
