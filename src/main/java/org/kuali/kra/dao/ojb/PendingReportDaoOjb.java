package org.kuali.kra.dao.ojb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.dao.PendingReportDao;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;

/**
 * OJB implementation of PendingReportDao using OJB Report Query (see http://db.apache.org/ojb/docu/guides/query.html#Report+Queries)
 */
public class PendingReportDaoOjb extends BaseReportDaoOjb implements PendingReportDao {

    public List<PendingReportBean> queryForPendingSupport(String personId) throws WorkflowException {
        List<PendingReportBean> data = new ArrayList<PendingReportBean>();
        for(InstitutionalProposalPerson ipPerson: executePendingSupportQuery(personId)) {
            lazyLoadProposal(ipPerson);
            PendingReportBean bean = buildPendingReportBean(ipPerson);
            if(bean != null)  {
                data.add(bean);
            }
        }
        return data;
    }

    private PendingReportBean buildPendingReportBean(InstitutionalProposalPerson ipPerson) throws WorkflowException {
        InstitutionalProposal proposal = ipPerson.getInstitutionalProposal();
        PendingReportBean bean = null;
        if(shouldDataBeIncluded(proposal.getInstitutionalProposalDocument()) && proposal.isActiveVersion()) {
            bean = new PendingReportBean(ipPerson);
        }
        return bean;
    }

    @SuppressWarnings("unchecked")
    private Collection<InstitutionalProposalPerson> executePendingSupportQuery(String personId) {
        return getBusinessObjectService().findMatching(InstitutionalProposalPerson.class, Collections.singletonMap("personId", personId));
    }

    private void lazyLoadProposal(InstitutionalProposalPerson ipPerson) {
        if(ipPerson.getInstitutionalProposal() == null) {
            ServiceHelper svcHelper = ServiceHelper.getInstance();
            Map<String, Object> searchParams = new HashMap<String, Object>();
            searchParams.put("proposalNumber", ipPerson.getProposalNumber());
            searchParams.put("sequenceNumber", ipPerson.getSequenceNumber());
//            Map searchParms = svcHelper.buildCriteriaMap(new String[]{"proposalNumber", "sequenceNumber"},
//                                                                           new Object[]{ipPerson.getProposalNumber(), ipPerson.getSequenceNumber()});
            InstitutionalProposal proposal = (InstitutionalProposal) getBusinessObjectService().findMatching(InstitutionalProposal.class, searchParams).iterator().next();
            ipPerson.setInstitutionalProposal(proposal);
        }
    }

}
