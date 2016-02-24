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
package org.kuali.coeus.common.proposal.impl.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log LOG = LogFactory.getLog(PendingReportDaoOjb.class);

    public List<PendingReportBean> queryForPendingSupport(String personId) throws WorkflowException {
        List<PendingReportBean> data = new ArrayList<>();
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
        if(proposal !=null &&  shouldDataBeIncluded(proposal.getInstitutionalProposalDocument()) && proposal.isActiveVersion()) {
            bean = new PendingReportBean(ipPerson);
        }
        return bean;
    }

    private Collection<InstitutionalProposalPerson> executePendingSupportQuery(String personId) {
        return getBusinessObjectService().findMatching(InstitutionalProposalPerson.class, Collections.singletonMap("personId", personId));
    }

    private void lazyLoadProposal(InstitutionalProposalPerson ipPerson) {
        if(ipPerson.getInstitutionalProposal() == null) {
            Map<String, Object> searchParams = new HashMap<>();
            searchParams.put("proposalNumber", ipPerson.getProposalNumber());
            searchParams.put("sequenceNumber", ipPerson.getSequenceNumber());

            List<InstitutionalProposal> proposals = (List<InstitutionalProposal>) getBusinessObjectService().findMatching(InstitutionalProposal.class, searchParams);
            InstitutionalProposal proposal = null;
            if (!proposals.isEmpty()) {
                proposal = proposals.get(0);
            } else {
                if (LOG.isInfoEnabled()) {
                    LOG.info("institute proposal person found with out valid institutional proposal (id: " + ipPerson.getInstitutionalProposalContactId() + ")");
                }
            }
            ipPerson.setInstitutionalProposal(proposal);
        }
    }

}
