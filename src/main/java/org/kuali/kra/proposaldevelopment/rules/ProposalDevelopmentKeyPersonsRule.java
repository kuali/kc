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
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_FLAG;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_LOWBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_UPBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_UNITS_UPBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_MISSING_PERSON_ROLE;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

/**
 * Implementation of business rules required for the Key Persons Page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>.
 *
 * @see org.kuali.core.rules.BusinessRule
 * @author $Author: lprzybyl $
 * @version $Revision: 1.14 $
 */
public class ProposalDevelopmentKeyPersonsRule extends ResearchDocumentRuleBase implements AddKeyPersonRule { 
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentKeyPersonsRule.class);

    /**
     * @see ResearchDocumentRuleBase#processCustomSaveDocumentBusinessRules(Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        return true;
    }

    /**
     * Rule invoked upon saving persons to a 
     * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>
     *
     * @param document ProposalDevelopmentDocument being saved
     * @return boolean
     */
    public boolean processSaveKeyPersonBusinessRules(ProposalDevelopmentDocument document) {
        boolean retval = true;
        int pi_cnt = 0;
        for (ProposalPerson person : document.getProposalPersons()) {
            if (isPrincipalInvestigator(person)) {
                pi_cnt = 0;
            }
        }

        if (pi_cnt < 2) {
            retval = false;
            reportErrorWithPrefix("newProposalPerson", "newProposalPerson", ERROR_INVESTIGATOR_UPBOUND);            
        }        

        return retval;
    }

    /**
     * Validate the following
     * <ul>
     *   <li>There must be at least one principal investigator</li>
     *   <li>All investigators must have a Unit #</li>
     *   <li>Principal Investigator Lead Unit should correspond to the Proposal Development Document Lead Unit</li>
     *   <li>All <code>{@link ProposalPerson}</code> instances must have a role.
     *   <li>If Credit Split is enabled:
     *     <ul>
     *       <li>% for all investigators and all credit types must add up to 100%</li>
     *       <li>Unit totals must add up to 100%</li>
     *       <li>% effort must be between 0.0 and 1.0</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param document ProposalDevelopmentDocument to process.
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) document;
        boolean retval = true;
        
        if (!hasPrincipalInvestigator(pd)) {
            retval = false;
            reportErrorWithPrefix("newProposalPerson", "newProposalPerson", ERROR_INVESTIGATOR_LOWBOUND);
        }

        retval &= processSaveKeyPersonBusinessRules(pd);

        int personIndex = 0;
        for (ProposalPerson person : pd.getProposalPersons()) {
            retval &= validateInvestigator(person);
            
            if (!isBlank(person.getProposalPersonRoleId())) {
                LOG.debug("error.missingPersonRole");
                String personProperty = "document.proposalPersons[" + personIndex + "]";
                reportErrorWithPrefix(personProperty + "*", personProperty, ERROR_MISSING_PERSON_ROLE);
            }
            personIndex++;
        }                    
        
        retval &= validateCreditSplit((ProposalDevelopmentDocument) document);

        return retval;
    }
    
    protected boolean validateCreditSplit(ProposalDevelopmentDocument document) {
        boolean retval = true;
        boolean creditSplitEnabled = getConfigurationService().getIndicatorParameter(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, CREDIT_SPLIT_ENABLED_RULE_NAME);
        
        if (creditSplitEnabled) {
            // retval &= new CreditSplitValidator().validate(document);
        }
        
        return retval;
    }


    /**
     * Validate the following
     * 
     * <ul>
     *   <li>One principal investigator at a time</li>
     *   <li>0 or more Key Persons or Co-Investigators are allowed</li>
     * </ul>
     * @see org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule#processAddKeyPersonBusinessRules(ProposalDevelopmentDocument,ProposalPerson)
     */
    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person) {
        boolean retval = true;

        if (isPrincipalInvestigator(person) && hasPrincipalInvestigator(document)) {
            LOG.debug("error.principalInvestigator.limit");
            reportErrorWithPrefix("newProposalPerson*", "newProposalPerson", ERROR_INVESTIGATOR_UPBOUND);
            retval = false;
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
        
        if (person.getUnits().size() < 1) {
            LOG.debug("error.investigatorUnits.limit");
            reportErrorWithPrefix("newProposalPerson", "newProposalPerson", ERROR_INVESTIGATOR_UNITS_UPBOUND);
        }
        
        for (ProposalPersonUnit unit : person.getUnits()) {
            if (isBlank(unit.getUnitNumber())) {
                LOG.debug("error.investigatorUnits.limit");
                reportErrorWithPrefix("newProposalPerson", "newPproposalPerson", ERROR_INVESTIGATOR_UNITS_UPBOUND);
            }
        }
        
        return retval;
    }

    public boolean isPrincipalInvestigator(ProposalPerson person) {
        return PRINCIPAL_INVESTIGATOR_ROLE.equals(person.getProposalPersonRoleId());
    }

    public boolean isCoInvestigator(ProposalPerson person) {
        return CO_INVESTIGATOR_ROLE.equals(person.getProposalPersonRoleId());
    }
    
    public boolean isInvestigator(ProposalPerson person) {
        return isPrincipalInvestigator(person) || isCoInvestigator(person);
    }
        
    public boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
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

    private KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
    }
}
