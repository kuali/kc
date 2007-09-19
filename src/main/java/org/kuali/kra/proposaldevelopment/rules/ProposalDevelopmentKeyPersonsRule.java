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
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;

/**
 * Implementation of business rules required for the Key Persons Page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>.
 *
 * @see org.kuali.core.rules.BusinessRule
 * @author $Author: lprzybyl $
 * @version $Revision: 1.8 $
 */
public class ProposalDevelopmentKeyPersonsRule extends ResearchDocumentRuleBase implements AddKeyPersonRule { 
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentKeyPersonsRule.class);
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        return true;
    }

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) document;
        boolean retval = true;
        
        retval &= hasPrincipalInvestigator(pd);
                
        return retval;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule#processAddKeyPersonBusinessRules(ProposalDevelopmentDocument,ProposalPerson)
     */
    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person) {
        boolean retval = true;

        if (!(isPrincipalInvestigator(person) && hasPrincipalInvestigator(document))) {
            LOG.debug("error.principalInvestigator.limit");
            // reportErrorWithPrefix("newProposalPerson", "proposalPerson", "error.principalInvestigators.limit");
            retval = false;
        }

        retval &= validateInvestigator(person);
        
        if (!isBlank(person.getProposalPersonRoleId())) {
            LOG.debug("error.missingPersonRole");
            // reportErrorWithPrefix("newProposalPerson", "proposalPerson", "error.missingPersonRole");
        }
        
        return retval;
    }
        
    protected boolean validateInvestigator(ProposalPerson person) {
        boolean retval = true;
        
        if (!isInvestigator(person)) {
            return retval;
        }

        retval &= validateInvestigatorUnits(person);
        
        return retval;
    }
    
    protected boolean validateInvestigatorUnits(ProposalPerson person) {
        boolean retval = true;
        
        if (person.getUnits().size() > 0) {
            LOG.debug("error.investigatorUnits.limit");
            // reportErrorWithPrefix("newProposalPerson", "proposalPerson", "error.investigatorUnits.limit");
        }
        
        for (ProposalPersonUnit unit : person.getUnits()) {
            if (!isBlank(unit.getUnitNumber())) {
                LOG.debug("error.investigatorUnits.limit");
                // reportErrorWithPrefix("newProposalPerson", "proposalPerson", "error.investigatorUnits.limit");
            }
        }
        
        return retval;
    }

    protected boolean isPrincipalInvestigator(ProposalPerson person) {
        return PRINCIPAL_INVESTIGATOR_ROLE.equals(person.getProposalPersonRoleId());
    }

    protected boolean isCoInvestigator(ProposalPerson person) {
        return CO_INVESTIGATOR_ROLE.equals(person.getProposalPersonRoleId());
    }
    
    protected boolean isInvestigator(ProposalPerson person) {
        return isPrincipalInvestigator(person) || isCoInvestigator(person);
    }
        
    protected boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
        boolean retval = false;

        for (Iterator<ProposalPerson> person_it = document.getProposalPersons().iterator();
             person_it.hasNext() && !retval;) {
            ProposalPerson person = person_it.next();
            retval |= isPrincipalInvestigator(person);
        }
        
        return retval;
    }
    
    /**
     * @see org.kuali.kra.rules.ResearchDocumentRuleBase#reportError(String, String, String...)
     */
    protected void reportErrorWithPrefix(String errorPathPrefix, String propertyName, String errorKey, String... errorParams) {
        GlobalVariables.getErrorMap().addToErrorPath(errorPathPrefix);
        super.reportError(propertyName, errorKey, errorParams);
        GlobalVariables.getErrorMap().removeFromErrorPath(errorPathPrefix);        
    }
}
