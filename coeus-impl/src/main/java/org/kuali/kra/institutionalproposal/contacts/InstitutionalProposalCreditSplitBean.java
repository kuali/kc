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
package org.kuali.kra.institutionalproposal.contacts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InstitutionalProposalCreditSplitBean implements Serializable {

    private static final long serialVersionUID = 1033802859817554693L;

    static final String PERSON_TOTALS_KEY = "personTotalsKey";
    
    private static Log LOGGER = LogFactory.getLog(InstitutionalProposalCreditSplitBean.class);
    private static final String YES = "Y";
    private static final String PROPOSAL_CREDIT_SPLIT_PARM_NAME = "institutionalproposal.creditsplit.enabled";
    private static final ScaleTwoDecimal ZERO_VALUE = new ScaleTwoDecimal(0);
    private static final ScaleTwoDecimal MAX_VALUE = new ScaleTwoDecimal(100.00);
    
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
    public Map<String, ScaleTwoDecimal> getPersonsTotalsMap() {
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
    public Map<String, Map<String, ScaleTwoDecimal>> getUnitTotalsMap() {
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
            Map<String, Map<String, ScaleTwoDecimal>> totalsMap = calculateCreditSplitTotals();
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
    Map<String, Map<String, ScaleTwoDecimal>> calculateCreditSplitTotals() {
        Map<String, Map<String, ScaleTwoDecimal>> allCreditSplitTotals = new HashMap<String, Map<String, ScaleTwoDecimal>>();
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
     * @param institutionalProposal
     * @param creditTypes
     * @param allCreditSplitTotals
     */
    private void calculatePersonTotals(Map<String, Map<String, ScaleTwoDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        Map<String, ScaleTwoDecimal> personCreditSplitTotalMap = initializePersonCreditSplitTotalMap(allCreditSplitTotals);
        for (InstitutionalProposalPerson projectPerson : getProjectPersons()) {
            for (InvestigatorCreditType creditType : creditTypes) {                
                calculatePersonTotalForCreditSplitType(projectPerson, creditType, personCreditSplitTotalMap);
            }
        }
    }

    /*
     * @param allCreditSplitTotals
     */
    private void calculatePersonUnitTotals(Map<String, Map<String, ScaleTwoDecimal>> allCreditSplitTotals) {
        Collection<InvestigatorCreditType> creditTypes = getInvestigatorCreditTypes();
        for (InstitutionalProposalPerson projectPerson : getProjectPersons()) {
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
    private void calculateUnitCreditSplitTotals(InstitutionalProposalPerson projectPerson, Map<String, ScaleTwoDecimal> personUnitCreditTotals) {
        if(projectPerson.isKeyPerson() && projectPerson.getUnits().size() == 0) {
            handleKeyPersonWithNoUnits(personUnitCreditTotals);
        } else {
            for (InstitutionalProposalPersonUnit unit : projectPerson.getUnits()) {
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

    private boolean  checkIfPersonTotalsAreValid(Map<String, Map<String, ScaleTwoDecimal>> totalsMap) {
        InstitutionalProposalPersonCreditSplitRule rule = new InstitutionalProposalPersonCreditSplitRuleImpl();
        InstitutionalProposalPersonCreditSplitRuleEvent event = new InstitutionalProposalPersonCreditSplitRuleEvent(institutionalProposalDocument, totalsMap.get(PERSON_TOTALS_KEY));
        return rule.checkInstitutionalProposalPersonCreditSplitTotals(event);
    }
    
    private boolean checkIfPersonUnitsTotalsAreValid(Map<String, Map<String, ScaleTwoDecimal>> totalsMap) {
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
    

    private String getPersonKey(InstitutionalProposalPerson projectPerson) {
        return projectPerson.getFullName();
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
}
