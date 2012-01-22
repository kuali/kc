/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rule;

import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * The Copy Proposal Rule validates copying of a proposal.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface CopyProposalRule extends BusinessRule {
    
    /**
     * Validates the copying of a proposal.  Before a proposal can be copied,
     * we must verify that it can be copied.  This includes verification against
     * the Copy criteria, e.g. if the user wants to copy attachments but doesn't
     * the necessary permissions to do so, we must prevent the copy.
     * 
     * @param document the original proposal development document
     * @param criteria the user-specified criteria
     * @return true if the copy request is valid; otherwise false
     */
    public boolean processCopyProposalBusinessRules(ProposalDevelopmentDocument document, 
                                                    ProposalCopyCriteria criteria);
}
