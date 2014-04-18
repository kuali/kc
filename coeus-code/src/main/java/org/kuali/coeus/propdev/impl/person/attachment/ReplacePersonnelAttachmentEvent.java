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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.attachment.AddPersonnelAttachmentEvent;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ReplacePersonnelAttachmentRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class ReplacePersonnelAttachmentEvent extends AddPersonnelAttachmentEvent {

    /**
     * Constructs a ReplacePersonnelAttachmentEvent with the given errorPathPrefix, document, and ProposalPersonBiography.
     * 
     * @param errorPathPrefix
     * @param document
     * @param proposalPersonBiography
     */
    public ReplacePersonnelAttachmentEvent(String errorPathPrefix, ProposalDevelopmentDocument document,
            ProposalPersonBiography proposalPersonBiography) {
        super(errorPathPrefix, document, proposalPersonBiography);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return ReplacePersonnelAttachmentRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ReplacePersonnelAttachmentRule) rule).processReplacePersonnelAttachmentBusinessRules(this);
    }
    
}
