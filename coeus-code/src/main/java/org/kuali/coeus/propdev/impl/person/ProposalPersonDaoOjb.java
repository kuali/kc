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
package org.kuali.coeus.propdev.impl.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.dao.LookupDao;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * This class created to get a list of proposal persons with a case-insensitive search on name.
 */

@Component("proposalPersonDao")
public class ProposalPersonDaoOjb  extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProposalPersonDao {

    @Autowired
    @Qualifier("lookupDao")
    private LookupDao lookupDao;

    protected LookupDao getLookupDao(){return lookupDao;}

    @Override
    @SuppressWarnings("unchecked")
    public List<ProposalPerson> getProposalPersonsByName(String partialName) {
        Map<String, String> fieldValues = new HashMap<String,String>();
        fieldValues.put("fullName", partialName);
        Query query = new QueryByCriteria(ProposalPerson.class, getCollectionCriteriaFromMap(fieldValues));
        return new ArrayList<ProposalPerson>(super.getPersistenceBrokerTemplate().getCollectionByQuery(query));
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
        ProposalPerson proposalPerson = new ProposalPerson();
        Criteria criteria = new Criteria();
        for(String fieldName: fieldValues.keySet()) {
            String fieldValue = (String) fieldValues.get(fieldName);
            if (!lookupDao.createCriteria(proposalPerson, fieldValue, fieldName, true, false, criteria)) {
                continue;
            }
        }
        return criteria;
    }
}
