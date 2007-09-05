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
package org.kuali.kra.proposaldevelopment.rule;

import org.kuali.core.rule.BusinessRule;

import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;


/**
 * Interface for adding key persons
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.1 $
 */
public interface AddKeyPersonRule extends BusinessRule {
    
    /**
     * Rule invoked upon adding a key person to a 
     * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>
     *
     * @return boolean
     */
    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person);
}
