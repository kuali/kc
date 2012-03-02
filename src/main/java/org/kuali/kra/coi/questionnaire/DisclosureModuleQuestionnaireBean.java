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
package org.kuali.kra.coi.questionnaire;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

public class DisclosureModuleQuestionnaireBean extends ModuleQuestionnaireBean {
    
    public DisclosureModuleQuestionnaireBean(CoiDisclosure disclosure) {
        super(CoeusModule.COI_DISCLOSURE_MODULE_CODE, disclosure.getCoiDisclosureNumber(), null, String.valueOf(disclosure.getSequenceNumber()), 
                disclosure.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isApproved());
        setDisclosureSubItemCode(disclosure);
    }
    
    private void setDisclosureSubItemCode(CoiDisclosure disclosure) {
        String disclosureSubItemCode = null;
        // if we are locating questionnaires for an event-based disclosure, then we need to translate the event type code into the submodule code
        if( disclosure.isProposalEvent() || 
                (disclosure.isManualEvent() && StringUtils.equals(disclosure.getEventTypeCode(), CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL)) ) {
            disclosureSubItemCode = CoeusSubModule.COI_EVENT_PROPOSAL_DISCL_SUBMODULE;
        }
        else if( disclosure.isProtocolEvent() || 
                (disclosure.isManualEvent() && StringUtils.equals(disclosure.getEventTypeCode(), CoiDisclosureEventType.MANUAL_IRB_PROTOCOL)) ) {
            disclosureSubItemCode = CoeusSubModule.COI_EVENT_IRB_PROTOCOL_DISCL_SUBMODULE;
        }
        else if( disclosure.isAwardEvent() || 
                (disclosure.isManualEvent() && StringUtils.equals(disclosure.getEventTypeCode(), CoiDisclosureEventType.MANUAL_AWARD)) ) {
            disclosureSubItemCode = CoeusSubModule.COI_EVENT_AWARD_DISCL_SUBMODULE;
        }
        
        this.setModuleSubItemCode(disclosureSubItemCode);
    }

}
