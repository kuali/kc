/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.sql.Date;
import java.util.Iterator;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.util.OjbCollectionAware;

/**
 * This class...
 */
public class ProposalHierarchyDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProposalHierarchyDao {
    
    public static String[] HIERARCHY_PROPOSAL_ATTRIBUTES = {
        "proposalNumber",
        "requestedStartDateInitial",
        "requestedEndDateInitial",
        "ownedByUnit.organizationId", "ownedByUnit.unitName",
        "activityType.description",
        
        "proposalState.description",
        "proposalType.description",
        // narrative?
        // budget?
        "title"
    };

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao#getProposalSummary(java.lang.String)
     */
    public HierarchyProposalSummary getProposalSummary(String proposalNumber) {
        Criteria crit = new Criteria();
        crit.addEqualTo("proposalNumber", proposalNumber);
        
        ReportQueryByCriteria query = new ReportQueryByCriteria(DevelopmentProposal.class, crit);
        query.setAttributes(HIERARCHY_PROPOSAL_ATTRIBUTES);
        
        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
        Object[] result = (Object[])iter.next();
        HierarchyProposalSummary retval = new HierarchyProposalSummary();
        retval.setProposalNumber((String)result[0]);
        retval.setRequestedStartDateInitial((Date)result[1]);
        retval.setRequestedEndDateInitial((Date)result[2]);
        retval.setOwnedByUnitName((String)result[3] + " : " + (String)result[4]);
        retval.setActivityTypeName((String)result[5]);
        
        retval.setProposalStateName((String)result[6]);
        retval.setProposalTypeName((String)result[7]);
        retval.setNarrative("What goes here?");
        retval.setBudget("What goes here?");
        retval.setTitle((String)result[8]);
        
        return retval;
    }

}
