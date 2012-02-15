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
package org.kuali.kra.personmasschange.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.ProposalDevelopmentPersonMassChangeService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.PersonEditableService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Proposal Developments.
 */
public class ProposalDevelopmentPersonMassChangeServiceImpl implements ProposalDevelopmentPersonMassChangeService {

    private static final String PROPOSAL_DEVELOPMENT_FIELD = "document.personMassChange.proposalDevelopmentPersonMassChange.";
    
    private static final String INVESTIGATOR = "investigator";
    private static final String MAILING_INFORMATION = "mailingInformation";
    private static final String KEY_STUDY_PERSON = "keyStudyPerson";
    private static final String REVIEWER = "reviewer";
    
    private static final String PROPOSAL_DEVELOPMENT_INVESTIGATOR_FIELD = PROPOSAL_DEVELOPMENT_FIELD + INVESTIGATOR;
    private static final String PROPOSAL_DEVELOPMENT_MAILING_INFORMATION_FIELD = PROPOSAL_DEVELOPMENT_FIELD + MAILING_INFORMATION;
    private static final String PROPOSAL_DEVELOPMENT_KEY_STUDY_PERSON_FIELD = PROPOSAL_DEVELOPMENT_FIELD + KEY_STUDY_PERSON;
    private static final String PROPOSAL_DEVELOPMENT_REVIEWER_FIELD = PROPOSAL_DEVELOPMENT_FIELD + REVIEWER;
    
    private static final String DEVELOPMENT_PROPOSAL = "development proposal";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private BusinessObjectService businessObjectService;
    private PersonEditableService personEditableService;
    
    @Override
    public List<DevelopmentProposal> getProposalDevelopmentChangeCandidates(PersonMassChange personMassChange) {
        List<DevelopmentProposal> proposalDevelopmentChangeCandidates = new ArrayList<DevelopmentProposal>();
        
        List<DevelopmentProposal> developmentProposals = new ArrayList<DevelopmentProposal>();
        if (personMassChange.getProposalDevelopmentPersonMassChange().isInvestigator() 
                || personMassChange.getProposalDevelopmentPersonMassChange().isMailingInformation() 
                || personMassChange.getProposalDevelopmentPersonMassChange().isKeyStudyPerson() 
                || personMassChange.getProposalDevelopmentPersonMassChange().isBudgetPerson()) {
            developmentProposals.addAll(getDevelopmentProposals());
        }

        for (DevelopmentProposal developmentProposal : developmentProposals) {
            if (isProposalDevelopmentChangeCandidate(personMassChange, developmentProposal)) {
                proposalDevelopmentChangeCandidates.add(developmentProposal);
            }
        }
        
        return proposalDevelopmentChangeCandidates;
    }
    
    private List<DevelopmentProposal> getDevelopmentProposals() {
        return new ArrayList<DevelopmentProposal>(getBusinessObjectService().findAll(DevelopmentProposal.class));
    }
    
    private boolean isProposalDevelopmentChangeCandidate(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        boolean isProposalDevelopmentChangeCandidate = false;
        
        Integer mailingInformationId = developmentProposal.getMailingAddressId();
        
        List<ProposalPerson> proposalPersons = developmentProposal.getProposalPersons();
        
        String[] investigatorRoles = { ProposalPersonRole.PRINCIPAL_INVESTIGATOR, ProposalPersonRole.CO_INVESTIGATOR };
        String[] keyStudyPersonRoles = { ProposalPersonRole.KEY_PERSON };
        
        List<BudgetPerson> proposalBudgetPersons = new ArrayList<BudgetPerson>();
        for (Budget budget : getBudgets(developmentProposal)) {
            proposalBudgetPersons.addAll(budget.getBudgetPersons());
        }
        
        if (personMassChange.getProposalDevelopmentPersonMassChange().isInvestigator()) {
            isProposalDevelopmentChangeCandidate |= isProposalPersonChangeCandidate(personMassChange, proposalPersons, investigatorRoles);
        }
        if (personMassChange.getProposalDevelopmentPersonMassChange().isMailingInformation()) {
            isProposalDevelopmentChangeCandidate |= isProposalMailingInformationChangeCandidate(personMassChange, mailingInformationId);
        }
        if (personMassChange.getProposalDevelopmentPersonMassChange().isKeyStudyPerson()) {
            isProposalDevelopmentChangeCandidate |= isProposalPersonChangeCandidate(personMassChange, proposalPersons, keyStudyPersonRoles);
        }
        if (personMassChange.getProposalDevelopmentPersonMassChange().isBudgetPerson()) {
            isProposalDevelopmentChangeCandidate |= isProposalBudgetPersonChangeCandidate(personMassChange, proposalBudgetPersons);
        }
        
        return isProposalDevelopmentChangeCandidate;
    }
    
    private boolean isProposalPersonChangeCandidate(PersonMassChange personMassChange, List<ProposalPerson> proposalPersons, String... personRoles) {
        boolean isProposalPersonChangeCandidate = false;
        
        for (ProposalPerson proposalPerson : proposalPersons) {
            if (isProposalPersonInRole(proposalPerson, personRoles)) {
                if (isPersonIdMassChange(personMassChange, proposalPerson.getPersonId()) 
                        || isRolodexIdMassChange(personMassChange, proposalPerson.getRolodexId())) {
                    isProposalPersonChangeCandidate = true;
                    break;
                }
            }
        }
        
        return isProposalPersonChangeCandidate;
    }
    
    private boolean isProposalPersonInRole(ProposalPerson proposalPerson, String... personRoles) {
        boolean isProposalPersonInRole = false;
        
        for (String personRole : personRoles) {
            if (StringUtils.equals(proposalPerson.getProposalPersonRoleId(), personRole)) {
                isProposalPersonInRole = true;
                break;
            }
        }
        
        return isProposalPersonInRole;
    }
    
    private boolean isProposalMailingInformationChangeCandidate(PersonMassChange personMassChange, Integer mailingInformationId) {
        return isRolodexIdMassChange(personMassChange, mailingInformationId);
    }
    
    private boolean isProposalBudgetPersonChangeCandidate(PersonMassChange personMassChange, List<BudgetPerson> proposalBudgetPersons) {
        boolean isProposalBudgetPersonChangeCandidate = false;
        
        for (BudgetPerson proposalBudgetPerson : proposalBudgetPersons) {
            if (isPersonIdMassChange(personMassChange, proposalBudgetPerson.getPersonId())) {
                isProposalBudgetPersonChangeCandidate = true;
                break;
            }
        }
        
        return isProposalBudgetPersonChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<DevelopmentProposal> proposalDevelopmentChangeCandidates) {
        for (DevelopmentProposal proposalDevelopmentChangeCandidate : proposalDevelopmentChangeCandidates) {
            if (proposalDevelopmentChangeCandidate.getProposalDocument().getPessimisticLocks().isEmpty()) {
                performProposalInvestigatorPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
                performProposalMailingInfoPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
                performProposalKeyStudyPersonPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
                performProposalBudgetPersonPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
            } else {
                if (personMassChange.getProposalDevelopmentPersonMassChange().isInvestigator()) {
                    reportWarning(PROPOSAL_DEVELOPMENT_INVESTIGATOR_FIELD, proposalDevelopmentChangeCandidate);
                } else if (personMassChange.getProposalDevelopmentPersonMassChange().isMailingInformation()) {
                    reportWarning(PROPOSAL_DEVELOPMENT_MAILING_INFORMATION_FIELD, proposalDevelopmentChangeCandidate);
                } else if (personMassChange.getProposalDevelopmentPersonMassChange().isKeyStudyPerson()) {
                    reportWarning(PROPOSAL_DEVELOPMENT_KEY_STUDY_PERSON_FIELD, proposalDevelopmentChangeCandidate);
                } else if (personMassChange.getProposalDevelopmentPersonMassChange().isBudgetPerson()) {
                    reportWarning(PROPOSAL_DEVELOPMENT_REVIEWER_FIELD, proposalDevelopmentChangeCandidate);
                }
            }
        }        
    }
    
    private void performProposalInvestigatorPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        if (personMassChange.getProposalDevelopmentPersonMassChange().isInvestigator()) {
            String[] personRoles = { ProposalPersonRole.PRINCIPAL_INVESTIGATOR, ProposalPersonRole.CO_INVESTIGATOR };
            performProposalPersonPersonMassChange(personMassChange, developmentProposal, personRoles);
        }
    }
    
    private void performProposalMailingInfoPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        if (personMassChange.getProposalDevelopmentPersonMassChange().isMailingInformation()) {
            developmentProposal.setMailingAddressId(Integer.valueOf(personMassChange.getReplacerRolodexId()));
        
            getBusinessObjectService().save(developmentProposal);
        }
    }
    
    private void performProposalKeyStudyPersonPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        if (personMassChange.getProtocolPersonMassChange().isKeyStudyPerson()) {
            String[] personRoles = { ProposalPersonRole.KEY_PERSON };
            performProposalPersonPersonMassChange(personMassChange, developmentProposal, personRoles);
        }
    }
    
    private void performProposalPersonPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal, String... personRoles) {
        for (ProposalPerson proposalPerson : developmentProposal.getProposalPersons()) {
            if (isProposalPersonInRole(proposalPerson, personRoles)) {
                if (personMassChange.getReplacerPersonId() != null) {
                    getPersonEditableService().populateContactFieldsFromPersonId(proposalPerson);
                    proposalPerson.setRolodexId(null);
                    
                    for (ProposalPersonBiography proposalPersonBiography : developmentProposal.getPropPersonBios()) {
                        if (ObjectUtils.equals(proposalPersonBiography.getProposalPersonNumber(), proposalPerson.getProposalPersonNumber())) {
                            proposalPersonBiography.setPersonId(personMassChange.getReplacerPersonId());
                            proposalPersonBiography.setRolodexId(null);
                            
                            getBusinessObjectService().save(proposalPersonBiography);
                        }
                    }
                } else if (personMassChange.getReplacerRolodexId() != null) {
                    getPersonEditableService().populateContactFieldsFromRolodexId(proposalPerson);
                    proposalPerson.setPersonId(null);
                    
                    for (ProposalPersonBiography proposalPersonBiography : developmentProposal.getPropPersonBios()) {
                        if (ObjectUtils.equals(proposalPersonBiography.getProposalPersonNumber(), proposalPerson.getProposalPersonNumber())) {
                            proposalPersonBiography.setPersonId(null);
                            proposalPersonBiography.setRolodexId(Integer.valueOf(personMassChange.getReplacerRolodexId()));
                        
                            getBusinessObjectService().save(proposalPersonBiography);
                        }
                    }
                }

                getBusinessObjectService().save(proposalPerson);
            }
        }
    }
    
    private void performProposalBudgetPersonPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        if (personMassChange.getProposalDevelopmentPersonMassChange().isBudgetPerson()) {
            for (Budget budget : getBudgets(developmentProposal)) {
                performProposalBudgetPersonPersonMassChange(personMassChange, budget);
                
                performProposalBudgetPersonnelDetailsPersonMassChange(personMassChange, budget);
            }
        }
    }
    
    private void performProposalBudgetPersonPersonMassChange(PersonMassChange personMassChange, Budget budget) {
        for (BudgetPerson budgetPerson : budget.getBudgetPersons()) {
            if (personMassChange.getReplacerPersonId() != null) {
                budgetPerson.setPersonId(personMassChange.getReplacerPersonId());
                budgetPerson.setRolodexId(null);
            } else if (personMassChange.getReplacerRolodexId() != null) {
                budgetPerson.setPersonId(null);
                budgetPerson.setRolodexId(Integer.valueOf(personMassChange.getReplacerRolodexId()));
            }
            
            getBusinessObjectService().save(budgetPerson);
        }
    }
    
    private void performProposalBudgetPersonnelDetailsPersonMassChange(PersonMassChange personMassChange, Budget budget) {
        for (BudgetPersonnelDetails budgetPersonnelDetails : budget.getBudgetPersonnelDetailsList()) {
            if (personMassChange.getReplacerPersonId() != null) {
                budgetPersonnelDetails.setPersonId(personMassChange.getReplacerPersonId());
            } else if (personMassChange.getReplacerRolodexId() != null) {
                budgetPersonnelDetails.setPersonId(personMassChange.getReplacerRolodexId());
            }
            
            for (BudgetPersonnelRateAndBase budgetPersonnelRateAndBase : budgetPersonnelDetails.getBudgetPersonnelRateAndBaseList()) {
                if (personMassChange.getReplacerPersonId() != null) {
                    budgetPersonnelRateAndBase.setPersonId(personMassChange.getReplacerPersonId());
                } else if (personMassChange.getReplacerRolodexId() != null) {
                    budgetPersonnelRateAndBase.setPersonId(personMassChange.getReplacerRolodexId());
                }
            }
            
            getBusinessObjectService().save(budgetPersonnelDetails);
        }
    }
    
    private void reportWarning(String propertyName, DevelopmentProposal developmentProposalChangeCandidate) {
        String proposalNumber = developmentProposalChangeCandidate.getProposalNumber();
        errorReporter.reportWarning(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, DEVELOPMENT_PROPOSAL, proposalNumber);
    }
    
    private List<Budget> getBudgets(DevelopmentProposal developmentProposal) {
        List<Budget> budgets = new ArrayList<Budget>();
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("PARENT_DOCUMENT_KEY", developmentProposal.getProposalDocument().getDocumentNumber());
        for (BudgetDocument<DevelopmentProposal> proposalBudgetDocument : getBusinessObjectService().findMatching(BudgetDocument.class, fieldValues)) {
            budgets.add(proposalBudgetDocument.getBudget());
        }
        
        return budgets;
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, String personId) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && StringUtils.equals(replaceePersonId, personId);
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, Integer rolodexId) {
        String replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && StringUtils.equals(replaceeRolodexId, String.valueOf(rolodexId));
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public PersonEditableService getPersonEditableService() {
        return personEditableService;
    }

    public void setPersonEditableService(PersonEditableService personEditableService) {
        this.personEditableService = personEditableService;
    }

}