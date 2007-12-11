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
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.SaveInstituteAttachmentsRule;

public class SaveInstituteAttachmentsEvent extends NarrativeEventBase{
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SaveInstituteAttachmentsEvent.class);
    /**
     * Constructs an SaveInstituteAttachmentsEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     */
    public SaveInstituteAttachmentsEvent(String errorPathPrefix, ProposalDevelopmentDocument document) {
        super("Adding institute attachment to document " + getDocumentId(document), errorPathPrefix, document);
    }

    /**
     * Constructs an SaveInstituteAttachmentsEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param document
     */
    public SaveInstituteAttachmentsEvent(String errorPathPrefix, Document document) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return SaveInstituteAttachmentsRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SaveInstituteAttachmentsRule) rule).processSaveInstituteAttachmentsBusinessRules(this);
    }
    

}
