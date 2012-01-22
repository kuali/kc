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
package org.kuali.kra.proposaldevelopment.hierarchy.dao.ojb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalBudgetStatus;
import org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.util.OjbCollectionAware;

/**
 * This class...
 */
public class ProposalHierarchyDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProposalHierarchyDao {
    public List<String> getHierarchyChildProposalNumbers(String proposalNumber) {
        List<String> retval = new ArrayList<String>();
        
        ReportQueryByCriteria query = createHierarchyChildProposalNumberQuery(proposalNumber);
        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
        
        while(iter.hasNext()) {
            Object[] result = (Object[])iter.next();
            retval.add((String)result[0]);
        }
        return retval;
    }
    
    public List<ProposalBudgetStatus> getHierarchyChildProposalBudgetStatuses(String proposalNumber) {
        Criteria crit = new Criteria();
        crit.addIn("proposalNumber", getHierarchyChildProposalNumbers(proposalNumber));
        QueryByCriteria statusQuery = new QueryByCriteria(ProposalBudgetStatus.class, crit);
        
        return new ArrayList<ProposalBudgetStatus>((Collection<ProposalBudgetStatus>)getPersistenceBrokerTemplate().getCollectionByQuery(statusQuery));
    }
    
    private ReportQueryByCriteria createHierarchyChildProposalNumberQuery(String proposalNumber) {
        Criteria crit = new Criteria();
        crit.addEqualTo("hierarchyParentProposalNumber", proposalNumber);
                
        ReportQueryByCriteria query = new ReportQueryByCriteria(DevelopmentProposal.class, crit);
        query.addOrderByAscending("proposalNumber");
        query.setAttributes(new String[]{ "proposalNumber" });
        return query;
    }

}
