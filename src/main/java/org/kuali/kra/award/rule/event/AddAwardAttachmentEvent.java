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
package org.kuali.kra.award.rule.event;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.award.rule.AddAwardAttachmentRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AddAwardAttachmentEvent extends AwardAttachmentEventBase {

    public AddAwardAttachmentEvent(String description, String errorPathPrefix, AwardDocument document, AwardAttachment attachment) {
        super(description, errorPathPrefix, document, attachment);
    }
    
   public AddAwardAttachmentEvent(String description, String errorPathPrefix, AwardDocument document) {
        super(description, errorPathPrefix, document);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddAwardAttachmentRule.class;
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.krad.rules.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddAwardAttachmentRule) rule).processsAddAttachmentRule(this);
    }
    
}
