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
package org.kuali.kra.personmasschange.service.impl;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.ProposalLogPersonMassChangeService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Defines the service for performing a Person Mass Change on Proposal Logs.
 * 
 * Person roles that might be replaced are: Principal Investigator.
 */
@Component("proposalLogPersonMassChangeService")
public class ProposalLogPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements ProposalLogPersonMassChangeService {

    private static final String PROPOSAL_LOG = "proposal log";
    private static final String PROPOSAL_LOG_WARNINGS = "propLogWarnings";
    
    @Override
    public List<ProposalLog> getProposalLogChangeCandidates(PersonMassChange personMassChange) {
        Set<ProposalLog> proposalLogChangeCandidates = new HashSet<ProposalLog>();
        
        List<ProposalLog> proposalLogs = new ArrayList<ProposalLog>();
        if (personMassChange.getProposalLogPersonMassChange().requiresChange()) {
            proposalLogs.addAll(getBusinessObjectService().findAll(ProposalLog.class));
        }

        for (ProposalLog proposalLog : proposalLogs) {
            if (isProposalLogChangeCandidate(personMassChange, proposalLog)) {
                proposalLogChangeCandidates.add(proposalLog);
            }
        }
        
        return new ArrayList<ProposalLog>(proposalLogChangeCandidates);
    }
    
    private boolean isProposalLogChangeCandidate(PersonMassChange personMassChange, ProposalLog proposalLog) {
        return isPersonIdMassChange(personMassChange, proposalLog.getPiId()) || isRolodexIdMassChange(personMassChange, proposalLog.getRolodexId());
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<ProposalLog> proposalLogChangeCandidates) {
        for (ProposalLog proposalLogChangeCandidate : proposalLogChangeCandidates) {
            if (personMassChange.getReplacerPersonId() != null) {
                KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                proposalLogChangeCandidate.setPiId(kcPerson.getPersonId());
                proposalLogChangeCandidate.setRolodexId(null);
                proposalLogChangeCandidate.setPiName(kcPerson.getFullName());
            } else if (personMassChange.getReplacerRolodexId() != null) {
                RolodexContract rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
                proposalLogChangeCandidate.setPiId(null);
                proposalLogChangeCandidate.setRolodexId(rolodex.getRolodexId());
                proposalLogChangeCandidate.setPiName(rolodex.getFullName());
            }

            getBusinessObjectService().save(proposalLogChangeCandidate);
        }
    }

    @Override
    protected String getDocumentId(PersistableBusinessObject parent) {
        return ((ProposalLog) parent).getProposalNumber();
    }

    @Override
    protected String getDocumentName() {
        return PROPOSAL_LOG;
    }

    @Override
    protected String getWarningKey() {
        return PROPOSAL_LOG_WARNINGS;
    }

}
