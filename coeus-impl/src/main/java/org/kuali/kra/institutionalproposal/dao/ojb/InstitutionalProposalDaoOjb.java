/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.dao.InstitutionalProposalDao;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.core.framework.persistence.jdbc.dao.PlatformAwareDaoBaseJdbc;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Default implementation of the institutional proposal dao
 */
public class InstitutionalProposalDaoOjb extends PlatformAwareDaoBaseOjb implements InstitutionalProposalDao {
    /**
     * Finds the proposal for the award & returns only the id
     * @param award the award to find the proposal id for
     * @return hte proposal id
     */
    @Override
    public Long getProposalId(Award award) {
        Criteria crit = new Criteria();
        crit.addEqualTo("proposalNumber", award.getProposalNumber());
        ReportQueryByCriteria q = QueryFactory.newReportQuery(InstitutionalProposal.class, crit);
        q.setAttributes(new String[] { "proposalId" });
        Iterator<Object> resultsIter = getPersistenceBrokerTemplate().getIteratorByQuery(q);
        if (!resultsIter.hasNext()) {
            return null;
        }
        final Long proposalId = (Long)resultsIter.next();
        while (resultsIter.hasNext()) {
            resultsIter.next(); // exhaust the iterator so the result set can be returned
        }
        return proposalId;
    }
}
