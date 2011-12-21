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
package org.kuali.kra.personmasschange.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.ProposalLogPersonMassChangeService;

/**
 * Defines the service for performing a Person Mass Change on Proposal Logs.
 */
public class ProposalLogPersonMassChangeServiceImpl implements ProposalLogPersonMassChangeService {

    @Override
    public List<ProposalLog> getProposalLogChangeCandidates(PersonMassChange personMassChange) {
        // TODO Auto-generated method stub
        return new ArrayList<ProposalLog>();
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<ProposalLog> proposalLogChangeCandidates) {
        // TODO Auto-generated method stub
    }

}