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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.keyvalue.DefaultMapEntry;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.document.Document;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.DegreeType;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

import static java.util.Map.Entry;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
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
 * @version $Revision: 1.22 $
 */
public class ProposalDevelopmentKeyPersonsRule extends ResearchDocumentRuleBase implements AddKeyPersonRule, ChangeKeyPersonRule { 
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentKeyPersonsRule.class);

    /**
     * @see ResearchDocumentRuleBase#processCustomSaveDocumentBusinessRules(Document)lin-long
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
            
            int personIndex = 0;
            if (!isBlank(person.getProposalPersonRoleId()) && person.getRole() == null) {
                LOG.debug("error.missingPersonRole");
                String personProperty = "document.proposalPerson[" + personIndex + "]";
                reportErrorWithPrefix(personProperty + "*", personProperty, ERROR_MISSING_PERSON_ROLE);
                personIndex++;
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

        for (ProposalPerson person : pd.getProposalPersons()) {
            retval &= validateInvestigator(person);
        }                    
        
        retval &= validateCreditSplit((ProposalDevelopmentDocument) document);

        return retval;
    }

    /**
     * Check if credit split totals validate
     *
     * @param document <code>{@link ProposalDevelopmentDocument}</code> instance to validate
     * credit splits of
     * @boolean is the credit split valid?
     * @see CreditSplitValidator#validate(ProposalDevelopmentDocument)
     */
    protected boolean validateCreditSplit(ProposalDevelopmentDocument document) {
        boolean retval = true;
        boolean creditSplitEnabled = getConfigurationService().getIndicatorParameter(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, CREDIT_SPLIT_ENABLED_RULE_NAME);
        
        if (creditSplitEnabled) {
            retval &= new CreditSplitValidator().validate(document);
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
        
        LOG.debug("validating " + person);
        LOG.info("Person role is " + person.getRole());

        if (isPrincipalInvestigator(person) && hasPrincipalInvestigator(document)) {
            LOG.debug("error.principalInvestigator.limit");
            reportErrorWithPrefix("newProposalPerson*", "newProposalPerson", ERROR_INVESTIGATOR_UPBOUND);
            retval = false;
        }
        
        if (isBlank(person.getProposalPersonRoleId()) && person.getRole() == null) {
            LOG.debug("Tried to add person without role");
            reportErrorWithPrefix("newProposalPerson*", "newProposalPerson", ERROR_MISSING_PERSON_ROLE);
            retval = false;
        }
        
        return retval;
    }
        
    /**
     *
     * @param person <code>{@link ProposalPerson}</code> instance that is also an investigator to validate
     * @boolean investigator is valid
     * @Wsee #validateInvestigatorUnits(ProposalPerson)
     */
    protected boolean validateInvestigator(ProposalPerson person) {
        boolean retval = true;
        
        if (!isInvestigator(person)) {
            return retval;
        }

        retval &= validateInvestigatorUnits(person);
        
        return retval;
    }
    
    /**
     *
     * @param person <code>{@link ProposalPerson}</code> instance who's units we want to validate
     * @return boolean Investigator Units are valid
     */
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
            
            retval &= validateUnit(unit);
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

    /**
     * Locate in Spring the <code>{@link KualiConfigurationService}</code> singleton instance
     * 
     * @return KualiConfigurationService
     */
    private KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
    }

    /**
     * Locate in Spring the <code>{@link BusinessObjectService}</code> singleton instance
     * 
     * @return BusinessObjectService
     */
    private BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }

    /**
     * @see KeyPersonnelService#isPrincipalInvestigator(ProposalPerson)
     */
    private boolean isPrincipalInvestigator(ProposalPerson person) {
        return getKeyPersonnelService().isPrincipalInvestigator(person);
    }

    /**
     * @see KeyPersonnelService#isPrincipalInvestigator(ProposalPerson)
     */
    private boolean isCoInvestigator(ProposalPerson person) {
        return getKeyPersonnelService().isCoInvestigator(person);
    }
    
    /**
     * @see KeyPersonnelService#isPrincipalInvestigator(ProposalPerson)
     */
    private boolean isInvestigator(ProposalPerson person) {
        return getKeyPersonnelService().isInvestigator(person);
    }
        
    /**
     * @see KeyPersonnelService#isPrincipalInvestigator(ProposalPerson)
     */
    private boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
        return getKeyPersonnelService().hasPrincipalInvestigator(document);
    }

    /**
     * Locate in Spring <code>{@link KeyPersonnelService}</code> singleton  
     * 
     * @return KeyPersonnelService
     */
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }

    /**
     * Either adding a degree or unit can trigger this rule to be validated
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule#processChangeKeyPersonBusinessRules(org.kuali.kra.proposaldevelopment.bo.ProposalPerson, org.kuali.core.bo.BusinessObject)
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentKeyPersonnelAction#insertDegree(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentKeyPersonnelAction#insertUnit(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public boolean processChangeKeyPersonBusinessRules(ProposalPerson proposalPerson, BusinessObject source) {
        boolean retval = true;
        
        if (source instanceof ProposalPersonDegree) {
            retval &= validateDegree((ProposalPersonDegree) source);
        }
        else if (source instanceof ProposalPersonUnit) {
            retval &= validateUnit((ProposalPersonUnit) source);
        }
        
        return retval;
    }

    /**
     * Checks to makes sure that the unit is valid. Usually called as a result of a <code>{@link ProposalPersonUnit}</code> being added to a <code>{@link ProposalPerson}</code>.
     * 
     * @param source
     * @return boolean pass or fail
     */
    private boolean validateUnit(ProposalPersonUnit source) {
        boolean retval = true;
        
        if (source == null) {
            LOG.info("validated null unit");
            return false;
        }
        
        if (source.getUnit() == null && isBlank(source.getUnitNumber())) {
            retval = false;
        }
        
        if (isNotBlank(source.getUnitNumber()) && isInvalid(Unit.class, keyValue("unitNumber", source.getUnitNumber()))) {
            retval = false;
        }

        LOG.info("validateUnit = " + retval);
        
        return retval;
    }

    /**
     * Checks to makes sure that the degree is valid. Usually called as a result of a <code>{@link ProposalPersonDegree}</code> being added to a <code>{@link ProposalPerson}</code>.
     * 
     * @param source
     * @return boolean
     */
    private boolean validateDegree(ProposalPersonDegree source) {
        boolean retval = true;
        
        if (source == null) {
            return false;
        }

        if (isBlank(source.getDegreeCode()) && source.getDegree() == null) {
            return false;
        }
        
        if (isNotBlank(source.getDegreeCode()) && isInvalid(DegreeType.class, keyValue("degreeCode", source.getDegreeCode()))) {
            retval = false;
        }
        
        return retval;
    }
    
    /**
     * Convenience method for creating a <code>{@link SimpleEntry}</code> out of a key/value pair
     * 
     * @param key
     * @param value
     * @return SimpleImmutableEntry
     */
    private Entry<String, String> keyValue(String key, String value) {
        return new DefaultMapEntry(key, value);
    }
    
   
    /**
     * The opposite of <code>{@link #isValid(Class, SimpleEntry...)}</code>
     * 
     * @param boClass the class of the business object to validate
     * @param entries varargs array of <code>{@link SimpleEntry}</code> key/value pair instances
     * @return true if invalid; false if valid
     * @see #isValid(Class, SimpleImmutableEntry...)
     */
    private boolean isInvalid(Class<?> boClass, Entry<String, String> ... entries) {
        return !isValid(boClass, entries);
    }
    
    /**
     * Is the given code valid?  Query the database for a matching code
     * If found, it is valid; otherwise it is invalid.
     * 
     * @param boClass the class of the business object to validate
     * @param entries varargs array of <code>{@link SimpleEntry}</code> key/value pair instances
     * @return true if invalid; false if valid
     * @see #isInvalid(Class, SimpleImmutableEntry...)
     */
    private boolean isValid(Class<?> boClass, Entry<String,String> ... entries) {
        if (entries != null && entries.length > 0) {
            Map<String,String> fieldValues = new HashMap<String,String>();
            
            for (Entry<String,String> entry : entries) {
                fieldValues.put(entry.getKey(), entry.getValue());
            }

            if (getBusinessObjectService().countMatching(boClass, fieldValues) == 1) {
                return true;
            }
        }
        return false;
    }
}

