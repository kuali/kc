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
package org.kuali.kra.proposaldevelopment.rules;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PAGE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PANEL_ANCHOR;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PANEL_NAME;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_KEY;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_PERSON_KEY;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_LOWBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_UNITS_UPBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_YNQ_INCOMPLETE;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections.keyvalue.DefaultMapEntry;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rules.CreditSplitValidator.CreditSplitAuditError;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

import edu.emory.mathcs.backport.java.util.AbstractMap.SimpleEntry;


/**
 * Rules that invoke audit mode for KeyPersonnel
 */
public class KeyPersonnelAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelAuditRule.class);
    
    /**
     * 
     * @see org.kuali.rice.kns.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) document;
        boolean retval = true;

        if (!hasPrincipalInvestigator(pd)) {
            retval = false;

            getAuditErrors().add(new AuditError(PRINCIPAL_INVESTIGATOR_KEY , ERROR_INVESTIGATOR_LOWBOUND, KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR));
        }
        // Include normal save document business rules
        retval &= new ProposalDevelopmentKeyPersonsRule().processCustomSaveDocumentBusinessRules(pd);
        
        for (ProposalPerson person : pd.getProposalPersons()) {
            retval &= validateInvestigator(person);
        }                    
        
        retval &= validateCreditSplit((ProposalDevelopmentDocument) document);
        
        retval &= validateYesNoQuestions((ProposalDevelopmentDocument) document);
        
        return retval;

    }
       
    /**
     * Yes/No questions have to be submitted to Grants.gov on document route. If the submitter has not completed the certifications,
     * errors should be displayed in audit mode. This does validation by iterating over each investigator until the PI is found.
     * The PI Yes/No questions are then checked for completeness. 
     * 
     * @param document
     * @return boolean true if the submitter has not completed the certifications
     */
    private boolean validateYesNoQuestions(ProposalDevelopmentDocument document) {
        boolean retval = true;
        
        for (ProposalPerson investigator : document.getProposalPersons()) {
            if(investigator.getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE) || investigator.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE)){
                retval = validateYesNoQuestions(investigator);
            }
            if(investigator.getProposalPersonRoleId().equals(KEY_PERSON_ROLE) && isNotBlank(investigator.getOptInCertificationStatus()) && investigator.getOptInCertificationStatus().equals("Y")){
                retval = validateYesNoQuestions(investigator);
            }
        }
        
        if (!retval) {
            addAuditError(ERROR_YNQ_INCOMPLETE);
        }
        
        return retval;
    }
    
    /**
     * Yes/No questions have to be submitted to Grants.gov on document route. If the submitter has not completed the certifications,
     * errors should be displayed in audit mode.<br/> 
     * <br/>
     * This method differs from <code>{@link #validateYesNoQuestions(ProposalDevelopmentDocument)}</code> that it refers to a specific person.
     * If any one of the Yes/No Questions is not completed, then this check will fail.
     * 
     * 
     * @param investigator Proposal Investigator
     * @return true if the given PI's Yes/No Questions are completed
     */
    private boolean validateYesNoQuestions(ProposalPerson investigator) {
        boolean retval = true;
        
        for (ProposalPersonYnq question : investigator.getProposalPersonYnqs()) {
           
            retval &= isNotBlank(question.getAnswer());
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
       
       List<AuditError> auditErrors = new ArrayList<AuditError>();
       LOG.info("validating units for " + person.getPersonId() + " " + person.getFullName());
       
       if (person.getUnits().size() < 1) {
           LOG.info("error.investigatorUnits.limit");
           auditErrors.add(new AuditError(PROPOSAL_PERSON_KEY,ERROR_INVESTIGATOR_UNITS_UPBOUND , KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR));
       }
       
       for (ProposalPersonUnit unit : person.getUnits()) {
           if (isBlank(unit.getUnitNumber())) {
               LOG.trace("error.investigatorUnits.limit");
               auditErrors.add(new AuditError(PROPOSAL_PERSON_KEY,ERROR_INVESTIGATOR_UNITS_UPBOUND , KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR));
           }
           
           retval &= validateUnit(unit);
       }
       
       if (auditErrors.size() > 0) {
          GlobalVariables.getAuditErrorMap().put("keyPersonnelAuditErrors", new AuditCluster(KEY_PERSONNEL_PANEL_NAME, auditErrors, AUDIT_ERRORS));
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

       LOG.debug("Validating " + source);
       LOG.debug("validateUnit = " + retval);
       
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
     * Locate in Spring the <code>{@link KualiConfigurationService}</code> singleton instance
     * 
     * @return KualiConfigurationService
     */
    private KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!GlobalVariables.getAuditErrorMap().containsKey("keyPersonnelAuditErrors")) {
           GlobalVariables.getAuditErrorMap().put("keyPersonnelAuditErrors", new AuditCluster(KEY_PERSONNEL_PANEL_NAME, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get("keyPersonnelAuditErrors")).getAuditErrorList();
        }
        
        return auditErrors;
    }
    
    /**
     * Delegates to <code>{@link #addAuditError(String, String...)}</code>
     * 
     * Convenience method for adding an <code>{@link AuditError}</code> with just a <code>messageKey</code>.<br/>
     * 
     * @param messageKey
     * @see CreditSplitAuditError
     * @see AuditError
     * @see GlobalVariables#getAuditErrorMap()
     * @see #addAuditError(String, String...)
     */
    private void addAuditError(String messageKey) {
        addAuditError(messageKey, null);
    }

    /**
     * Convenience method for adding an <code>{@link AuditError}</code> with just a <code>messageKey</code>.<br/>
     * <br/>
     * The <code>{@link AuditError}</code> that is added is.<br/>
     * 
     * @param messageKey
     * @see CreditSplitAuditError
     * @see AuditError
     * @see GlobalVariables#getAuditErrorMap()
     */
    private void addAuditError(String messageKey, String ... params) {
        AuditError error = new AuditError(PROPOSAL_PERSON_KEY, messageKey, KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR);

        boolean found = false;
        for (AuditError ae : getAuditErrors()) {
            found |= ae.getMessageKey().equals(messageKey);
        }
        
        if (!found) {          
            getAuditErrors().add(error);
        }
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
        
        if (getKeyPersonnelService().isCreditSplitEnabled()) {
            CreditSplitValidator validator = CreditSplitValidator.getInstance();
            retval &= validator.validate(document);
        }
        
        return retval;
    }
}
