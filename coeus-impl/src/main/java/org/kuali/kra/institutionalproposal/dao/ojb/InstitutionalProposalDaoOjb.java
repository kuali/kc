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
