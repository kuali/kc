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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.Iterator;

import org.kuali.core.document.Document;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;

/**
 * Implementation of business rules required for the Key Persons Page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>.
 *
 * @see org.kuali.core.rules.BusinessRule
 * @author $Author: gthomas $
 * @version $Revision: 1.1 $
 */
public class ProposalDevelopmentNarrativeRule extends ResearchDocumentRuleBase implements AddNarrativeRule { 
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentKeyPersonsRule.class);

    public boolean processAddNarrativeBusinessRules(ProposalDevelopmentDocument document, Narrative narrative) {
        // TODO Auto-generated method stub
        return false;
    }
}
