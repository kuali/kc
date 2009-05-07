/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.impl.KualiConfigurationServiceImpl;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * Provides credit split support
 */
public class AwardCreditSplitBean implements Serializable {
    static final String PERSON_TOTALS_KEY = "personTotalsKey";
    
    private static final long serialVersionUID = 1330497293834315534L;
    private static Logger LOGGER = Logger.getLogger(KualiConfigurationServiceImpl.class);
    private static final String PARM_TYPE_CODE = "D";
    private static final String YES = "Y";
    private static final String AWARD_CREDIT_SPLIT_PARM_NAME = "award.creditsplit.enabled";
    private static final KualiDecimal ZERO_VALUE = new KualiDecimal(0);
    
    private AwardDocument awardDocument;
    private transient Collection<InvestigatorCreditType> investigatorCreditTypes;
    
    public AwardCreditSplitBean(AwardForm awardForm) {
        this.awardDocument = awardForm.getAwardDocument();
    }
    
    public AwardCreditSplitBean(AwardDocument awardDocument) {
        this.awardDocument = awardDocument;
    }
    
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        if(investigatorCreditTypes == null || investigatorCreditTypes.size() == 0) {
            investigatorCreditTypes = loadInvestigatorCreditTypes();
        }
        return investigatorCreditTypes;
    }

    /**
     * This method returns the map of credit types to award personnel totals
     * @param personKey
     * @return
     */
    public Map<String, KualiDecimal> getPersonsTotalsMap() {
        return calculateCreditSplitTotals().get(PERSON_TOTALS_KEY);
    }
    
    public AwardPerson getProjectPerson(int index) {
        return getAward().getProjectPersons().get(index);
    }
    
    /**
     * This method prepares all project personnel, and their units, with empty credit splits
     * for any InvestigatorCreditTypes that aren't already represented.
     *  
     * @return
     */
    public List<AwardPerson> getProjectPersons() {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        List<AwardPerson> projectPersons = getAward().getProjectPersons();
        for(AwardPerson p: projectPersons) {
            createDefaultCreditSplitMapForProjectPerson(creditTypes, p);
            
            for(AwardPersonUnit apu: p.getUnits()) {
                createDefaultCreditSplitMapForPersonUnit(creditTypes, apu);
            }
        }
        return getAward().getProjectPersons();
    }

    /**
     * @return The totals map which contains the unit totals 
     */
    public Map<String, Map<String, KualiDecimal>> getUnitTotalsMap() {
        return calculateCreditSplitTotals();
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
            LOGGER.warn(e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Apply calculation total rules
     */
    public boolean recalculateCreditSplit() {
        boolean noErrors = true;
        if(isAwardCreditsLimitApplicable()) {
            Map<String, Map<String, KualiDecimal>> totalsMap = calculateCreditSplitTotals();
            noErrors = checkIfPersonTotalsAreValid(totalsMap);
            noErrors &= checkIfPersonUnitsTotalsAreValid(totalsMap);
        }
        
        return noErrors;
    }
    
    /**
     * This method fetches the Award Crddit Splits enabled system parm
     * @param parmName
     * @return
     */
    protected String fetchParameterValue(String parmName) {
        return getConfigurationService().getParameterValue(Award.AWARD_NAMESPACE_CODE, PARM_TYPE_CODE, parmName);
    }
    
    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    /**
     * @return
     */
    protected KualiConfigurationService getConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
    /**
     * This is  called to generate a map of the new credit split totals.
     *
     * @param document
     * @return Map
     * 
     */
    Map<String, Map<String, KualiDecimal>> calculateCreditSplitTotals() {
        Map<String, Map<String, KualiDecimal>> allCreditSplitTotals = new HashMap<String, Map<String, KualiDecimal>>();
        calculatePersonTotals(allCreditSplitTotals);
        calculatePersonUnitTotals(allCreditSplitTotals);
        
        return allCreditSplitTotals;
    }
    
    /**
     * @return
     */
    Award getAward() {
        return awardDocument.getAward();
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
    private void calculatePersonTotals(Map<String, Map<String, KualiDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        Map<String, KualiDecimal> personCreditSplitTotalMap = initializePersonCreditSplitTotalMap(allCreditSplitTotals);        
        for (AwardPerson projectPerson : getProjectPersons()) {
            for (InvestigatorCreditType creditType : creditTypes) {                
                calculatePersonTotalForCreditSplitType(projectPerson, creditType, personCreditSplitTotalMap);
            }
        }
    }

    /*
     * @param allCreditSplitTotals
     */
    private void calculatePersonUnitTotals(Map<String, Map<String, KualiDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        for (AwardPerson projectPerson : getProjectPersons()) {
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
    
    private boolean  checkIfPersonTotalsAreValid(Map<String, Map<String, KualiDecimal>> totalsMap) {
        AwardPersonCreditSplitRule rule = new AwardPersonCreditSplitRuleImpl();
        AwardPersonCreditSplitRuleEvent event = new AwardPersonCreditSplitRuleEvent(awardDocument, totalsMap.get(PERSON_TOTALS_KEY));
        return rule.checkAwardPersonCreditSplitTotals(event);
    }
    
    private boolean checkIfPersonUnitsTotalsAreValid(Map<String, Map<String, KualiDecimal>> totalsMap) {        
        AwardPersonUnitCreditSplitRule rule = new AwardPersonUnitCreditSplitRuleImpl();
        
        boolean success = true;
        for(AwardPerson projectPerson: getAward().getProjectPersons()) {
            AwardPersonUnitCreditSplitRuleEvent event = new AwardPersonUnitCreditSplitRuleEvent(awardDocument, projectPerson, 
                                                                                                totalsMap.get(getPersonKey(projectPerson)));
            success &= rule.checkAwardPersonUnitCreditSplitTotals(event);
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
}