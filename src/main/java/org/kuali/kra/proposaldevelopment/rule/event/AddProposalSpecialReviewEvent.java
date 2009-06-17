/*
 * Copyright 2006-2009 The Kuali Foundation
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

import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

public class AddProposalSpecialReviewEvent extends ProposalSpecialReviewEventBase{
    /**
     * Constructs an AddProposalSpecialReviewEvent with the given errorPathPrefix, document, and proposalSpecialReview.
     * 
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSpecialReview
     */
    public AddProposalSpecialReviewEvent(String errorPathPrefix, ProposalDevelopmentDocument document, ProposalSpecialReview proposalSpecialReview) {
        super("adding proposal special review to document " + getDocumentId(document), errorPathPrefix, document, proposalSpecialReview);
    }

    /**
     * Constructs an AddProposalSpecialReviewEvent with the given errorPathPrefix, document, and proposalSpecialReview.
     * 
     * @param errorPathPrefix
     * @param document
     * @param proposalSpecialReview
     */
    public AddProposalSpecialReviewEvent(String errorPathPrefix, Document document, ProposalSpecialReview proposalSpecialReview) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document, proposalSpecialReview);
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddProposalSpecialReviewRule.class;
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProposalSpecialReviewRule) rule).processAddProposalSpecialReviewBusinessRules(this);
    }

}
