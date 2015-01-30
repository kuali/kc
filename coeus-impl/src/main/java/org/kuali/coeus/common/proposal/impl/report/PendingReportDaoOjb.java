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
package org.kuali.coeus.common.proposal.impl.report;

import org.kuali.coeus.common.framework.print.PendingReportBean;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * OJB implementation of PendingReportDao using OJB Report Query (see http://db.apache.org/ojb/docu/guides/query.html#Report+Queries)
 */
@Component("pendingReportDao")
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
