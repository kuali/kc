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
package org.kuali.kra.coi.lookup.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.lookup.dao.CoiDisclosureDao;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
        
    public List<CoiDisclosure> getReviewsForReviewStatuses(Collection<String> reviewStatusCodes) {
        if (reviewStatusCodes == null || reviewStatusCodes.isEmpty()) {
            return new ArrayList<CoiDisclosure>();
        }
        Criteria crit1 = new Criteria();
        for (String reviewCode : reviewStatusCodes) {
            Criteria crit2 = new Criteria();
            crit2.addEqualTo("reviewStatusCode", reviewCode);
            crit1.addOrCriteria(crit2);
        }
        
        QueryByCriteria query = QueryFactory.newQuery(CoiDisclosure.class, crit1);
        query.addOrderByDescending("sequenceNumber");
        
        Collection<CoiDisclosure> disclosures = getPersistenceBrokerTemplate().getCollectionByQuery(query);
        
        for (CoiDisclosure disclosure : disclosures) {
            List<CoiDisclProject> coiDisclProjects = disclosure.getCoiDisclProjects();
    
            CoiDisclosureEventType coiDisclosureEventType = disclosure.getCoiDisclosureEventType();
            String coiDisclosureModuleItemKey = disclosure.getModuleItemKey();
            for(CoiDisclProject coiDisclProject : coiDisclProjects)
            {
                if ( coiDisclosureEventType.getEventTypeCode().equals(coiDisclProject.getDisclosureEventType()) &&
                            coiDisclosureModuleItemKey.equals(coiDisclProject.getModuleItemKey()) )
                {
                    disclosure.setCoiDisclProjectId(coiDisclProject.getProjectId());
                    disclosure.setCoiDisclProjectTitle(coiDisclProject.getCoiProjectTitle());
                    break;
                }
            }
        }
        
        return new ArrayList<CoiDisclosure>(disclosures);     
        
    }
    
    
}
