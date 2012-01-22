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
package org.kuali.kra.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.dao.ResearchAreaReferencesDao;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class ResearchAreaReferencesDaoOjb extends PlatformAwareDaoBaseOjb implements ResearchAreaReferencesDao {

    public boolean isResearchAreaReferencedByAnyCommittee(String researchAreaCode) {
        boolean retVal = false;
        Criteria crit = new Criteria();
        crit.addEqualTo("researchAreaCode", researchAreaCode);
        QueryByCriteria q = QueryFactory.newQuery(CommitteeResearchArea.class, crit);
        int count = getPersistenceBrokerTemplate().getCount(q);
        if(count > 0){
            retVal = true;
        }
        return retVal;
    }

    public boolean isResearchAreaReferencedByAnyCommitteeMember(String researchAreaCode) {
        boolean retVal = false;
        Criteria crit = new Criteria();
        crit.addEqualTo("researchAreaCode", researchAreaCode);
        QueryByCriteria q = QueryFactory.newQuery(CommitteeMembershipExpertise.class, crit);
        int count = getPersistenceBrokerTemplate().getCount(q);
        if(count > 0){
            retVal = true;
        }
        return retVal;
    }

    public boolean isResearchAreaReferencedByAnyProtocol(String researchAreaCode) {
        boolean retVal = false;
        Criteria crit = new Criteria();
        crit.addEqualTo("researchAreaCode", researchAreaCode);
        QueryByCriteria q = QueryFactory.newQuery(ProtocolResearchArea.class, crit);
        int count = getPersistenceBrokerTemplate().getCount(q);
        if(count > 0){
            retVal = true;
        }
        return retVal;
    }


    // this method can be used as a replacement for the helper method of the same name in the ResearchServiceImpl, if efficiency 
    // of execution of that method ever becomes a issue. This method's code is more efficient because it uses a counting 
    // query rather than retrieving an actual business object from the database. The down-side is that it is tied to OJB.
    public boolean isCurrentVersion(Committee committee) {
        boolean retVal = true;
        Criteria crit = new Criteria();
        crit.addEqualTo("committeeId", committee.getCommitteeId());
        Criteria andCrit = new Criteria();
        andCrit.addGreaterThan("sequenceNumber", committee.getSequenceNumber());
        crit.addAndCriteria(andCrit);
        QueryByCriteria q = QueryFactory.newQuery(Committee.class, crit);
        int count = getPersistenceBrokerTemplate().getCount(q);
        if(count > 0){
            retVal = false;
        }
        return retVal;
    }

}
