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
package org.kuali.kra.coi.lookup.dao.ojb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.coi.CoiDisclosureHistory;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.lookup.dao.CoiDisclosureDao;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.util.OjbCollectionAware;


public class CoiDisclosureDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, CoiDisclosureDao {

    public List<CoiDisclosureHistory> getApprovedAndDisapprovedDisclosureHistory(String coiDisclosureNumber) {
        
        Criteria crit1 = new Criteria();
        crit1.addEqualTo("coiDisclosureNumber", coiDisclosureNumber);
        crit1.addEqualTo("disclosureStatus", CoiDisclosureStatus.APPROVED);
        
        Criteria crit2 = new Criteria();
        crit2.addEqualTo("coiDisclosureNumber", coiDisclosureNumber);
        crit2.addEqualTo("disclosureStatus", CoiDisclosureStatus.DISAPPROVED);
        
        crit1.addOrCriteria(crit2);
        
        QueryByCriteria query = QueryFactory.newQuery(CoiDisclosureHistory.class, crit1);
        query.addOrderByDescending("sequenceNumber");
        Collection history = new ArrayList();

        history = getPersistenceBrokerTemplate().getCollectionByQuery(query);
        
        return (List<CoiDisclosureHistory>) history;     
    }
    
    
}
