/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddProposalLocationRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalLocationEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProposalDevelopmentProposalLocationRule extends ResearchDocumentRuleBase implements AddProposalLocationRule {
    private static final String NEW_PROPOSAL_LOCATION = "newPropLocation";

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddProposalLocationRule#processAddProposalLocationBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalLocationEvent)
     */
    public boolean processAddProposalLocationBusinessRules(AddProposalLocationEvent addProposalLocationEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)addProposalLocationEvent.getDocument();
        ProposalLocation proposalLocation = addProposalLocationEvent.getProposalLocation();
        boolean rulePassed = true;
        String errorPath = NEW_PROPOSAL_LOCATION;

        if(StringUtils.isBlank(proposalLocation.getLocation())){
            rulePassed = false;
            reportError(errorPath+".location", KeyConstants.ERROR_REQUIRED_FOR_PROPLOCATION_NAME);
        }

        return rulePassed;
    }

}
