/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.contacts;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

/**
 * This class processes AwardPersonCreditSplitRules
 */
public class AwardPersonCreditSplitAuditRule implements DocumentAuditRule {
    private static final String PERSON_TOTALS_KEY = "personTotalsKey";
    
    private static final long serialVersionUID = 1330497293834315534L;
    private static final String YES = "Y";
    private static final String AWARD_CREDIT_SPLIT_PARM_NAME = "award.creditsplit.enabled";
    private static final ScaleTwoDecimal ZERO_VALUE = new ScaleTwoDecimal(0);
    private static final ScaleTwoDecimal MAX_VALUE = new ScaleTwoDecimal(100.00);
    private static final ScaleTwoDecimal MAX_TOTAL_VALUE = new ScaleTwoDecimal(100.00);
    
    public static final String AWARD_CREDIT_SPLIT_LIST_ERROR_KEY = "document.awardList[0].projectPersons.awardPersonCreditSplits";
    public static final String AWARD_PERSON_CREDIT_SPLIT_ERROR_MSG_KEY = "error.award.person.credit.split.error";
    public static final String AWARD_UNIT_CREDIT_SPLIT_LIST_ERROR_KEY = "document.awardList[0].projectPersons.awardPersonUnitCreditSplits";
    public static final String AWARD_PERSON_UNIT_CREDIT_SPLIT_ERROR_MSG_KEY = "error.award.person.unit.credit.split.error";
    
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
            Map<String, Map<String, ScaleTwoDecimal>> totalsMap = calculateCreditSplitTotals(awardDocument);
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
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
        
    /**
     * This is  called to generate a map of the new credit split totals.
     *
     * @param document
     * @return Map
     * 
     */
    Map<String, Map<String, ScaleTwoDecimal>> calculateCreditSplitTotals(AwardDocument awardDocument) {
        Map<String, Map<String, ScaleTwoDecimal>> allCreditSplitTotals = new HashMap<String, Map<String, ScaleTwoDecimal>>();
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
                                                            Map<String, ScaleTwoDecimal> personCreditSplitTotalMap) {
        String creditTypeCode = creditType.getCode();
        ScaleTwoDecimal personsTotalCredit = personCreditSplitTotalMap.get(creditTypeCode);

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
    private void calculatePersonTotals(AwardDocument awardDocument, Map<String, Map<String, ScaleTwoDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        Map<String, ScaleTwoDecimal> personCreditSplitTotalMap = initializePersonCreditSplitTotalMap(allCreditSplitTotals);
        for (AwardPerson projectPerson : getProjectPersons(awardDocument)) {
            for (InvestigatorCreditType creditType : creditTypes) {                
                calculatePersonTotalForCreditSplitType(projectPerson, creditType, personCreditSplitTotalMap);
            }
        }
    }

    /*
     * @param allCreditSplitTotals
     */
    private void calculatePersonUnitTotals(AwardDocument awardDocument, Map<String, Map<String, ScaleTwoDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        for (AwardPerson projectPerson : getProjectPersons(awardDocument)) {
            String personKey = getPersonKey(projectPerson);
            Map<String, ScaleTwoDecimal> personUnitCreditTotals = allCreditSplitTotals.get(personKey);
            
            if (personUnitCreditTotals == null) {
                personUnitCreditTotals = new HashMap<String, ScaleTwoDecimal>();
                allCreditSplitTotals.put(personKey, personUnitCreditTotals);
            }

            for (InvestigatorCreditType creditType : creditTypes) {                
                String creditTypeCode = creditType.getCode();
                ScaleTwoDecimal totalCredit = personUnitCreditTotals.get(creditTypeCode);
                personUnitCreditTotals.put(creditTypeCode, totalCredit != null ? totalCredit : ZERO_VALUE);
            }

            calculateUnitCreditSplitTotals(projectPerson, personUnitCreditTotals);
        }
    }
    
    /*
     * @param projectPerson
     * @param personUnitCreditTotals
     */
    private void calculateUnitCreditSplitTotals(AwardPerson projectPerson, Map<String, ScaleTwoDecimal> personUnitCreditTotals) {
        if(projectPerson.isKeyPerson() && projectPerson.getUnits().size() == 0) {
            handleKeyPersonWithNoUnits(personUnitCreditTotals);
        } else {
            for (AwardPersonUnit unit : projectPerson.getUnits()) {
                for (CreditSplit creditSplit : unit.getCreditSplits()) {
                    ScaleTwoDecimal totalCredit = personUnitCreditTotals.get(creditSplit.getInvCreditTypeCode());

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
    private void handleKeyPersonWithNoUnits(Map<String, ScaleTwoDecimal> personUnitCreditTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        for(InvestigatorCreditType creditType: creditTypes) {
            personUnitCreditTotals.put(creditType.getCode(), MAX_VALUE);
        }
    }

    private boolean checkIfPersonTotalsAreValid(AwardDocument awardDocument, Map<String, Map<String, ScaleTwoDecimal>> totalsMap) {
        int errorCount = 0; 
        for(InvestigatorCreditType creditType: loadInvestigatorCreditTypes()) {
            if(creditType.addsToHundred()) {
                ScaleTwoDecimal value = totalsMap.get(PERSON_TOTALS_KEY).get(creditType.getCode());
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
    
    private boolean checkIfPersonUnitsTotalsAreValid(AwardDocument awardDocument, Map<String, Map<String, ScaleTwoDecimal>> totalsMap) {
        boolean success = true;
        for(AwardPerson person: awardDocument.getAward().getProjectPersons()) {
            int errorCount = 0;
            Map<String, ScaleTwoDecimal> totalsByCreditSplitType = totalsMap.get(getPersonKey(person));
            for(InvestigatorCreditType creditType: loadInvestigatorCreditTypes()) {
                if(creditType.addsToHundred()) {
                    ScaleTwoDecimal value = totalsByCreditSplitType.get(creditType.getCode());
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

    private String getPersonKey(AwardPerson projectPerson) {
        String personKey = "";
        if (projectPerson != null) {
            if (projectPerson.getIsRolodexPerson()) {
                personKey = projectPerson.getRolodex().getOrganization();
            } else {
                personKey = projectPerson.getFullName();
            }
        }
        return personKey;
    }

    /*
     * @param allCreditSplitTotals
     * @return
     */
    private Map<String, ScaleTwoDecimal> initializePersonCreditSplitTotalMap(Map<String, Map<String, ScaleTwoDecimal>> allCreditSplitTotals) {
        Map<String, ScaleTwoDecimal> personCreditTypeTotals = allCreditSplitTotals.get(PERSON_TOTALS_KEY);
        
        if (personCreditTypeTotals == null) {
            personCreditTypeTotals = new HashMap<String, ScaleTwoDecimal>();
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
        	AuditCluster existingErrors = (AuditCluster) GlobalVariables.getAuditErrorMap().get(Constants.CONTACTS_AUDIT_ERROR_KEY_NAME);
            if (existingErrors == null) {
                GlobalVariables.getAuditErrorMap().put(Constants.CONTACTS_AUDIT_ERROR_KEY_NAME, new AuditCluster(Constants.CONTACTS_PANEL_NAME,
                                                                                              auditErrors, Constants.AUDIT_ERRORS));
            } else {
                existingErrors.getAuditErrorList().addAll(auditErrors);
            }
        }
    }
}
