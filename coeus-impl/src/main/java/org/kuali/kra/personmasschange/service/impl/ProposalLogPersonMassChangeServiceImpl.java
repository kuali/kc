/*
 * Copyright 2005-2014 The Kuali Foundation
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