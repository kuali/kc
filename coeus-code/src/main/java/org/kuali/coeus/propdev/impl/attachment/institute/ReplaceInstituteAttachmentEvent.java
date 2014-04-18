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
package org.kuali.coeus.propdev.impl.attachment.institute;

import org.kuali.coeus.propdev.impl.attachment.institute.AddInstituteAttachmentEvent;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.institute.ReplaceInstituteAttachmentRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class ReplaceInstituteAttachmentEvent extends AddInstituteAttachmentEvent {

    public ReplaceInstituteAttachmentEvent(String errorPathPrefix, ProposalDevelopmentDocument document, Narrative narrative) {
        super(errorPathPrefix, document, narrative);
    }
    
    @Override
    public Class getRuleInterfaceClass() {
        return ReplaceInstituteAttachmentRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ReplaceInstituteAttachmentRule) rule).processReplaceInstituteAttachmentBusinessRules(this);
    }

}
