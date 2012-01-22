/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class processes AwardPersonCreditSplitRules
 */
public class AwardPersonCreditSplitAuditRule implements DocumentAuditRule {
    private static final String PERSON_TOTALS_KEY = "personTotalsKey";
    
    private static final long serialVersionUID = 1330497293834315534L;
    private static final String YES = "Y";
    private static final String AWARD_CREDIT_SPLIT_PARM_NAME = "award.creditsplit.enabled";
    private static final KualiDecimal ZERO_VALUE = new KualiDecimal(0);
    private static final KualiDecimal MAX_VALUE = new KualiDecimal(100.00);
    private static final KualiDecimal MAX_TOTAL_VALUE = new KualiDecimal(100.00);
    
    public static final String AWARD_CREDIT_SPLIT_LIST_ERROR_KEY = "document.awardList[0].projectPersons.awardPersonCreditSplits";
    public static final String AWARD_PERSON_CREDIT_SPLIT_ERROR_MSG_KEY = "error.award.person.credit.split.error";
    public static final String AWARD_UNIT_CREDIT_SPLIT_LIST_ERROR_KEY = "document.awardList[0].projectPersons.awardPersonUnitCreditSplits";
    public static final String AWARD_PERSON_UNIT_CREDIT_SPLIT_ERROR_MSG_KEY = "error.award.person.unit.credit.split.error";
    private static final String CONTACTS_AUDIT_ERRORS = "contactsCreditSplitAuditErrors";
    
    private transient Collection<InvestigatorCreditType> investigatorCreditTypes;
    private transient ParameterService parameterService;
    private List<AuditError> auditErrors;
    
    public AwardPersonCreditSplitAuditRule() {
        auditErrors = new ArrayList<AuditError>();
    }
  
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        if(investigatorCreditTypes == null || investigatorCreditTypes.size() == 0) {
            investigatorCreditTypes = loadInvestigatorCreditTypes();
        }
        return investigatorCreditTypes;
    }

    /**
     * This method prepares all project personnel, and their units, with empty credit splits
     * for any InvestigatorCreditTypes that aren't already represented.
     *  
     * @return
     */
    public List<AwardPerson> getProjectPersons(AwardDocument awardDocument) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        List<AwardPerson> projectPersons = awardDocument.getAward().getProjectPersons();
        for(AwardPerson p: projectPersons) {
            createDefaultCreditSplitMapForProjectPerson(creditTypes, p);
            
            for(AwardPersonUnit apu: p.getUnits()) {
                createDefaultCreditSplitMapForPersonUnit(creditTypes, apu);
            }
        }
        return awardDocument.getAward().getProjectPersons();
    }

    /**
     * Determines whether credit limits are applicable
     * @return
     */
    public boolean isAwardCreditsLimitApplicable() {
        try {
            String parmValue = fetchParameterValue(AWARD_CREDIT_SPLIT_PARM_NAME);
            return parmValue.equalsIgnoreCase(YES);
        } catch(IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * Apply calculation total rules
     */
    public boolean recalculateCreditSplit(AwardDocument awardDocument) {
        boolean noErrors = true;
        if(isAwardCreditsLimitApplicable()) {
            Map<String, Map<String, KualiDecimal>> totalsMap = calculateCreditSplitTotals(awardDocument);
            noErrors = checkIfPersonTotalsAreValid(awardDocument, totalsMap);
            noErrors &= checkIfPersonUnitsTotalsAreValid(awardDocument, totalsMap);
        }
        
        return noErrors;
    }
    
    /**
     * This method fetches the Award Credit Splits enabled system parm
     * @param parmName
     * @return
     */
    protected String fetchParameterValue(String parmName) {
        return this.getParameterService().getParameterValueAsString(AwardDocument.class, parmName);
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
        
    /**
     * This is  called to generate a map of the new credit split totals.
     *
     * @param document
     * @return Map
     * 
     */
    Map<String, Map<String, KualiDecimal>> calculateCreditSplitTotals(AwardDocument awardDocument) {
        Map<String, Map<String, KualiDecimal>> allCreditSplitTotals = new HashMap<String, Map<String, KualiDecimal>>();
        calculatePersonTotals(awardDocument, allCreditSplitTotals);
        calculatePersonUnitTotals(awardDocument, allCreditSplitTotals);
        
        return allCreditSplitTotals;
    }
    
    @SuppressWarnings("unchecked")
    Collection<InvestigatorCreditType> loadInvestigatorCreditTypes() {
        Map<String,String> valueMap = new HashMap<String, String>();
        valueMap.put("active", "true");
        return getBusinessObjectService().findMatching(InvestigatorCreditType.class, valueMap);
    }

    /**
     * @param projectPerson
     * @param creditType
     * @param personCreditSplitTotalMap
     */
    private void calculatePersonTotalForCreditSplitType(AwardPerson projectPerson, InvestigatorCreditType creditType, 
                                                            Map<String, KualiDecimal> personCreditSplitTotalMap) {
        String creditTypeCode = creditType.getInvCreditTypeCode();
        KualiDecimal personsTotalCredit = personCreditSplitTotalMap.get(creditTypeCode);

        if (personsTotalCredit == null) {
            personsTotalCredit = ZERO_VALUE;                    
        }
        
        for (CreditSplit personCreditSplit : projectPerson.getCreditSplits()) {
            if (personCreditSplit.getInvCreditTypeCode().equals(creditTypeCode)) {
                personCreditSplitTotalMap.put(creditTypeCode, personsTotalCredit.add(personCreditSplit.getCredit()));
            }
        }
    }
    
    /**
     * @param award
     * @param creditTypes
     * @param allCreditSplitTotals
     */
    private void calculatePersonTotals(AwardDocument awardDocument, Map<String, Map<String, KualiDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        Map<String, KualiDecimal> personCreditSplitTotalMap = initializePersonCreditSplitTotalMap(allCreditSplitTotals);        
        for (AwardPerson projectPerson : getProjectPersons(awardDocument)) {
            for (InvestigatorCreditType creditType : creditTypes) {                
                calculatePersonTotalForCreditSplitType(projectPerson, creditType, personCreditSplitTotalMap);
            }
        }
    }

    /*
     * @param allCreditSplitTotals
     */
    private void calculatePersonUnitTotals(AwardDocument awardDocument, Map<String, Map<String, KualiDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        for (AwardPerson projectPerson : getProjectPersons(awardDocument)) {
            String personKey = getPersonKey(projectPerson);
            Map<String, KualiDecimal> personUnitCreditTotals = allCreditSplitTotals.get(personKey);
            
            if (personUnitCreditTotals == null) {
                personUnitCreditTotals = new HashMap<String, KualiDecimal>();
                allCreditSplitTotals.put(personKey, personUnitCreditTotals);
            }

            for (InvestigatorCreditType creditType : creditTypes) {                
                String creditTypeCode = creditType.getInvCreditTypeCode();
                KualiDecimal totalCredit = personUnitCreditTotals.get(creditTypeCode);
                personUnitCreditTotals.put(creditTypeCode, totalCredit != null ? totalCredit : ZERO_VALUE);
            }

            calculateUnitCreditSplitTotals(projectPerson, personUnitCreditTotals);
        }
    }
    
    /*
     * @param projectPerson
     * @param personUnitCreditTotals
     */
    private void calculateUnitCreditSplitTotals(AwardPerson projectPerson, Map<String, KualiDecimal> personUnitCreditTotals) {
        if(projectPerson.isKeyPerson() && projectPerson.getUnits().size() == 0) {
            handleKeyPersonWithNoUnits(personUnitCreditTotals);
        } else {
            for (AwardPersonUnit unit : projectPerson.getUnits()) {
                for (CreditSplit creditSplit : unit.getCreditSplits()) {
                    KualiDecimal totalCredit = personUnitCreditTotals.get(creditSplit.getInvCreditTypeCode());

                    if (totalCredit == null) {
                        totalCredit = ZERO_VALUE;
                    }
                    personUnitCreditTotals.put(creditSplit.getInvCreditTypeCode(), totalCredit.add(creditSplit.getCredit()));
                }
            }
        }
    }

    /**
     * A keyPerson may have no associated unit. To satisfy the validation checks, we apply this workaround to set the unit credit split type totals to 100.00 
     * @param personUnitCreditTotals
     */
    private void handleKeyPersonWithNoUnits(Map<String, KualiDecimal> personUnitCreditTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        for(InvestigatorCreditType creditType: creditTypes) {
            personUnitCreditTotals.put(creditType.getInvCreditTypeCode(), MAX_VALUE);
        }
    }

    private boolean checkIfPersonTotalsAreValid(AwardDocument awardDocument, Map<String, Map<String, KualiDecimal>> totalsMap) {
        int errorCount = 0; 
        for(InvestigatorCreditType creditType: loadInvestigatorCreditTypes()) {
            if(creditType.addsToHundred()) {
                KualiDecimal value = totalsMap.get(PERSON_TOTALS_KEY).get(creditType.getInvCreditTypeCode());
                if(value == null) {
                    break;   // value may not have been initialized yet, so we don't want to block save
                }
                if(!MAX_TOTAL_VALUE.subtract(value).isZero()) {
                    auditErrors.add(new AuditError(AWARD_CREDIT_SPLIT_LIST_ERROR_KEY, AWARD_PERSON_CREDIT_SPLIT_ERROR_MSG_KEY,
                            Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR, new String[]{creditType.getDescription()}));

                    errorCount++;
                }
            }
        }
        
        return errorCount == 0;
    }
    
    private boolean checkIfPersonUnitsTotalsAreValid(AwardDocument awardDocument, Map<String, Map<String, KualiDecimal>> totalsMap) {        
        boolean success = true;
        for(AwardPerson person: awardDocument.getAward().getProjectPersons()) {
            int errorCount = 0;
            Map<String, KualiDecimal> totalsByCreditSplitType = totalsMap.get(getPersonKey(person));
            for(InvestigatorCreditType creditType: loadInvestigatorCreditTypes()) {
                if(creditType.addsToHundred()) {
                    KualiDecimal value = totalsByCreditSplitType.get(creditType.getInvCreditTypeCode());
                    if(value == null) {
                        break;   // value may not have been initialized yet, so we don't want to block save
                    }
                    if(!MAX_TOTAL_VALUE.subtract(value).isZero()) {
                        auditErrors.add(new AuditError(AWARD_UNIT_CREDIT_SPLIT_LIST_ERROR_KEY, AWARD_PERSON_UNIT_CREDIT_SPLIT_ERROR_MSG_KEY,
                                Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR, new String[]{creditType.getDescription(), person.getFullName()}));

                        errorCount++;
                    }
                }
            }
            
            success &= (errorCount == 0);
        }
        return success;
        

    }

    /*
     * @param creditTypes
     * @param apu
     */
    private void createDefaultCreditSplitMapForPersonUnit(Collection<InvestigatorCreditType> creditTypes, AwardPersonUnit apu) {
        Map<InvestigatorCreditType, AwardPersonUnitCreditSplit> personUnitCreditMap = new HashMap<InvestigatorCreditType, AwardPersonUnitCreditSplit>();
        for(AwardPersonUnitCreditSplit apuCreditSplit: apu.getCreditSplits()) {
            personUnitCreditMap.put(apuCreditSplit.getInvestigatorCreditType(), apuCreditSplit);
        }
         
        for(InvestigatorCreditType creditType: creditTypes) {
            if(personUnitCreditMap.get(creditType) == null) {
                apu.add(new AwardPersonUnitCreditSplit(creditType, ZERO_VALUE));
            }
        }
    }

    /*
     * @param creditTypes
     * @param projectPerson
     */
    private void createDefaultCreditSplitMapForProjectPerson(Collection<InvestigatorCreditType> creditTypes, AwardPerson projectPerson) {
        Map<InvestigatorCreditType, AwardPersonCreditSplit> personCreditMap = new HashMap<InvestigatorCreditType, AwardPersonCreditSplit>();
        for(AwardPersonCreditSplit creditSplit: projectPerson.getCreditSplits()) {
            personCreditMap.put(creditSplit.getInvestigatorCreditType(), creditSplit);
        }
        
        for(InvestigatorCreditType creditType: creditTypes) {
            if(personCreditMap.get(creditType) == null) {
                projectPerson.add(new AwardPersonCreditSplit(creditType, ZERO_VALUE));
            }
        }
    }
    
    /**
     * This method...
     * @param projectPerson
     * @return
     */
    private String getPersonKey(AwardPerson projectPerson) {
        return projectPerson.getFullName().toString();
    }

    /*
     * @param allCreditSplitTotals
     * @return
     */
    private Map<String, KualiDecimal> initializePersonCreditSplitTotalMap(Map<String, Map<String, KualiDecimal>> allCreditSplitTotals) {
        Map<String, KualiDecimal> personCreditTypeTotals = allCreditSplitTotals.get(PERSON_TOTALS_KEY);
        
        if (personCreditTypeTotals == null) {
            personCreditTypeTotals = new HashMap<String, KualiDecimal>();
            allCreditSplitTotals.put(PERSON_TOTALS_KEY, personCreditTypeTotals);
        }
        return personCreditTypeTotals;
    }   

    public boolean processRunAuditBusinessRules(Document document) {
        AwardDocument awardDocument = (AwardDocument) document;
        boolean valid = recalculateCreditSplit(awardDocument);
        reportAndCreateAuditCluster();
        return valid;
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(CONTACTS_AUDIT_ERRORS, new AuditCluster(Constants.CONTACTS_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }
}
