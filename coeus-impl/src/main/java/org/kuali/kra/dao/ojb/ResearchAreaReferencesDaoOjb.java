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
package org.kuali.kra.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
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
}
