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

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.core.util.GlobalVariables.getAuditErrorMap;
import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PAGE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PANEL_ANCHOR;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PANEL_NAME;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_PERSON_KEY;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_LOWBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_INVESTIGATOR_UNITS_UPBOUND;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.keyvalue.DefaultMapEntry;
import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;


/**
 * Rules that invoke audit mode for KeyPersonnel
 */
public class KeyPersonnelAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelAuditRule.class);
    
    /**
     * 
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) document;
        boolean retval = true;

        if (!hasPrincipalInvestigator(pd)) {
            retval = false;

            getAuditErrors().add(new AuditError(PROPOSAL_PERSON_KEY, ERROR_INVESTIGATOR_LOWBOUND, KEY_PERSONNEL_PAGE + "." + KEY_PERSONNEL_PANEL_ANCHOR));
        }

        // Include normal save document business rules
        retval &= new ProposalDevelopmentKeyPersonsRule().processCustomSaveDocumentBusinessRules(pd);
        
        for (ProposalPerson person : pd.getProposalPersons()) {
            retval &= validateInvestigator(person);
        }                    
        
        retval &= validateCreditSplit((ProposalDevelopmentDocument) document);
        
        return retval;

    }
       
    /**
     * @see KeyPersonnelService#isPrincipalInvestigator(ProposalPerson)
     */
    private boolean isPrincipalInvestigator(ProposalPerson person) {
        return getKeyPersonnelService().isPrincipalInvestigator(person);
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
           getAuditErrorMap().put("keyPersonnelAuditErrors", new AuditCluster(KEY_PERSONNEL_PANEL_NAME, auditErrors, AUDIT_ERRORS));
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
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = auditErrors = new ArrayList<AuditError>();
        
        if (!getAuditErrorMap().containsKey("keyPersonnelAuditErrors")) {
            getAuditErrorMap().put("keyPersonnelAuditErrors", new AuditCluster(KEY_PERSONNEL_PANEL_NAME, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get("keyPersonnelAuditErrors")).getAuditErrorList();
        }
        
        return auditErrors;
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
}
