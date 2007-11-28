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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddInstituteAttachmentRule;

public class AddInstituteAttachmentEvent extends NarrativeEventBase{
    /**
     * Constructs an AddInstituteAttachmentEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param institute
     */
    public AddInstituteAttachmentEvent(String errorPathPrefix, ProposalDevelopmentDocument document, Narrative institute) {
        super("adding institute attachment to document " + getDocumentId(document), errorPathPrefix, document, institute);
    }

    /**
     * Constructs an AddInstituteAttachmentEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param document
     * @param institute
     */
    public AddInstituteAttachmentEvent(String errorPathPrefix, Document document, Narrative institute) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document, institute);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddInstituteAttachmentRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddInstituteAttachmentRule) rule).processAddInstituteAttachmentBusinessRules(this);
    }

}
