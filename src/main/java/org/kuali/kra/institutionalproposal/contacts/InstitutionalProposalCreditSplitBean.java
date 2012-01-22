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
package org.kuali.kra.institutionalproposal.contacts;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public class InstitutionalProposalCreditSplitBean implements Serializable {

/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1033802859817554693L;

    static final String PERSON_TOTALS_KEY = "personTotalsKey";
    
    private static Log LOGGER = LogFactory.getLog(InstitutionalProposalCreditSplitBean.class);
    private static final String PARM_TYPE_CODE = "D";
    private static final String YES = "Y";
    private static final String PROPOSAL_CREDIT_SPLIT_PARM_NAME = "institutionalproposal.creditsplit.enabled";
    private static final KualiDecimal ZERO_VALUE = new KualiDecimal(0);
    private static final KualiDecimal MAX_VALUE = new KualiDecimal(100.00);
    
    private InstitutionalProposalForm institutionalProposalForm;
    private InstitutionalProposalDocument institutionalProposalDocument;
    
    private transient Collection<InvestigatorCreditType> investigatorCreditTypes;
    private transient ParameterService parameterService;

    public InstitutionalProposalCreditSplitBean(InstitutionalProposalForm institutionalProposalForm) {
        this.institutionalProposalForm = institutionalProposalForm;
    }

    /** 
     * This constructor should only be called when no InstitutionalProposalForm is not available
     * The InstitutionalProposalForm reference is stable during a session, but InstitutionalProposalDocument is not
     * However, in rule processing, a Form may not be available, especially if from a unit test.
     * 
     * In that case, this constructor should be used. The recalculateCreditSplit(InstitutionalProposalDocument) method should 
     * be called, passing in an InstitutionalProposalDocument
     */
    public InstitutionalProposalCreditSplitBean(InstitutionalProposalDocument institutionalProposalDocument) {
        this.institutionalProposalDocument = institutionalProposalDocument;
    }
    
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        if(investigatorCreditTypes == null || investigatorCreditTypes.size() == 0) {
            investigatorCreditTypes = loadInvestigatorCreditTypes();
        }
        return investigatorCreditTypes;
    }

    /**
     * This method returns the map of credit types to award personnel totals
     * @return
     */
    public Map<String, KualiDecimal> getPersonsTotalsMap() {
        return calculateCreditSplitTotals().get(PERSON_TOTALS_KEY);
    }
    
    public InstitutionalProposalPerson getProjectPerson(int index) {
        return getProjectPersons().get(index);
    }
    
    /**
     * This method prepares all project personnel, and their units, with empty credit splits
     * for any InvestigatorCreditTypes that aren't already represented.
     *  
     * @return
     */
    public List<InstitutionalProposalPerson> getProjectPersons() {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        List<InstitutionalProposalPerson> projectPersons = getInstitutionalProposal().getProjectPersons();
        for(InstitutionalProposalPerson p: projectPersons) {
            createDefaultCreditSplitMapForProjectPerson(creditTypes, p);
            
            for(InstitutionalProposalPersonUnit apu: p.getUnits()) {
                createDefaultCreditSplitMapForPersonUnit(creditTypes, apu);
            }
        }
        return getInstitutionalProposal().getProjectPersons();
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
    public boolean isInstitutionalProposalCreditsLimitApplicable() {
        try {
            String parmValue = fetchParameterValue(PROPOSAL_CREDIT_SPLIT_PARM_NAME);
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
        if(isInstitutionalProposalCreditsLimitApplicable()) {
            Map<String, Map<String, KualiDecimal>> totalsMap = calculateCreditSplitTotals();
            noErrors = checkIfPersonTotalsAreValid(totalsMap);
            noErrors &= checkIfPersonUnitsTotalsAreValid(totalsMap);
        }
        
        return noErrors;
    }
    
    /**
     * This method fetches the InstitutionalProposal Credit Splits enabled system parm
     * @param parmName
     * @return
     */
    protected String fetchParameterValue(String parmName) {
        return this.getParameterService().getParameterValueAsString(InstitutionalProposalDocument.class, parmName);
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
    Map<String, Map<String, KualiDecimal>> calculateCreditSplitTotals() {
        Map<String, Map<String, KualiDecimal>> allCreditSplitTotals = new HashMap<String, Map<String, KualiDecimal>>();
        calculatePersonTotals(allCreditSplitTotals);
        calculatePersonUnitTotals(allCreditSplitTotals);
        
        return allCreditSplitTotals;
    }
    
    /**
     * Find the Institutional Proposal, first by looking to a form bean, if any. If no form bean exists, check if the document exists.
     * If neither exists, returned Institutional Proposal is null 
     * @return
     */
    InstitutionalProposal getInstitutionalProposal() {
        InstitutionalProposal institutionalProposal;
        if(institutionalProposalForm != null) {
            institutionalProposal = institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal();
        } else if(institutionalProposalDocument != null) {
            institutionalProposal = institutionalProposalDocument.getInstitutionalProposal();
        } else {
            institutionalProposal = null;
        }
        
        return institutionalProposal;
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
    private void calculatePersonTotalForCreditSplitType(InstitutionalProposalPerson projectPerson, InvestigatorCreditType creditType, 
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
     * @param institutionalProposal
     * @param creditTypes
     * @param allCreditSplitTotals
     */
    private void calculatePersonTotals(Map<String, Map<String, KualiDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        Map<String, KualiDecimal> personCreditSplitTotalMap = initializePersonCreditSplitTotalMap(allCreditSplitTotals);        
        for (InstitutionalProposalPerson projectPerson : getProjectPersons()) {
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
        for (InstitutionalProposalPerson projectPerson : getProjectPersons()) {
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
    private void calculateUnitCreditSplitTotals(InstitutionalProposalPerson projectPerson, Map<String, KualiDecimal> personUnitCreditTotals) {
        if(projectPerson.isKeyPerson() && projectPerson.getUnits().size() == 0) {
            handleKeyPersonWithNoUnits(personUnitCreditTotals);
        } else {
            for (InstitutionalProposalPersonUnit unit : projectPerson.getUnits()) {
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

    private boolean  checkIfPersonTotalsAreValid(Map<String, Map<String, KualiDecimal>> totalsMap) {
        InstitutionalProposalPersonCreditSplitRule rule = new InstitutionalProposalPersonCreditSplitRuleImpl();
        InstitutionalProposalPersonCreditSplitRuleEvent event = new InstitutionalProposalPersonCreditSplitRuleEvent(institutionalProposalDocument, totalsMap.get(PERSON_TOTALS_KEY));
        return rule.checkInstitutionalProposalPersonCreditSplitTotals(event);
    }
    
    private boolean checkIfPersonUnitsTotalsAreValid(Map<String, Map<String, KualiDecimal>> totalsMap) {        
        InstitutionalProposalPersonUnitCreditSplitRule rule = new InstitutionalProposalPersonUnitCreditSplitRuleImpl();
        
        boolean success = true;
        for(InstitutionalProposalPerson projectPerson: getInstitutionalProposal().getProjectPersons()) {
            InstitutionalProposalPersonUnitCreditSplitRuleEvent event = new InstitutionalProposalPersonUnitCreditSplitRuleEvent(institutionalProposalDocument, projectPerson, 
                                                                                                totalsMap.get(getPersonKey(projectPerson)));
            success &= rule.checkInstitutionalProposalPersonUnitCreditSplitTotals(event);
        }
        return success;                                                                               
    }

    /*
     * @param creditTypes
     * @param apu
     */
    private void createDefaultCreditSplitMapForPersonUnit(Collection<InvestigatorCreditType> creditTypes, InstitutionalProposalPersonUnit apu) {
        Map<InvestigatorCreditType, InstitutionalProposalPersonUnitCreditSplit> personUnitCreditMap = new HashMap<InvestigatorCreditType, InstitutionalProposalPersonUnitCreditSplit>();
        for(InstitutionalProposalPersonUnitCreditSplit apuCreditSplit: apu.getCreditSplits()) {
            personUnitCreditMap.put(apuCreditSplit.getInvestigatorCreditType(), apuCreditSplit);
        }
         
        for(InvestigatorCreditType creditType: creditTypes) {
            if(personUnitCreditMap.get(creditType) == null) {
                apu.add(new InstitutionalProposalPersonUnitCreditSplit(creditType, ZERO_VALUE));
            }
        }
    }

    /*
     * @param creditTypes
     * @param projectPerson
     */
    private void createDefaultCreditSplitMapForProjectPerson(Collection<InvestigatorCreditType> creditTypes, InstitutionalProposalPerson projectPerson) {
        Map<InvestigatorCreditType, InstitutionalProposalPersonCreditSplit> personCreditMap = new HashMap<InvestigatorCreditType, InstitutionalProposalPersonCreditSplit>();
        for(InstitutionalProposalPersonCreditSplit creditSplit: projectPerson.getCreditSplits()) {
            personCreditMap.put(creditSplit.getInvestigatorCreditType(), creditSplit);
        }
        
        for(InvestigatorCreditType creditType: creditTypes) {
            if(personCreditMap.get(creditType) == null) {
                projectPerson.add(new InstitutionalProposalPersonCreditSplit(creditType, ZERO_VALUE));
            }
        }
    }
    
    /**
     * This method...
     * @param projectPerson
     * @return
     */
    private String getPersonKey(InstitutionalProposalPerson projectPerson) {
        return projectPerson.getFullName();
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
