/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rule.event;

import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.SaveInstituteAttachmentsRule;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class SaveInstituteAttachmentsEvent extends NarrativeEventBase{
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

    @Override
    public Class getRuleInterfaceClass() {
        return SaveInstituteAttachmentsRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SaveInstituteAttachmentsRule) rule).processSaveInstituteAttachmentsBusinessRules(this);
    }
    

}
