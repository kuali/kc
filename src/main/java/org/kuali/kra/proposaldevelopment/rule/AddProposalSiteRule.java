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
package org.kuali.kra.proposaldevelopment.rule;

import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent;
import org.kuali.rice.kns.rule.BusinessRule;

public interface AddProposalSiteRule  extends BusinessRule {
    
    /**
     * Rule invoked upon adding a proposal location 
     * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>
     *
     * @return boolean
     */
    public boolean processAddProposalSiteBusinessRules(AddProposalSiteEvent addProposalLocationEvent);
}
