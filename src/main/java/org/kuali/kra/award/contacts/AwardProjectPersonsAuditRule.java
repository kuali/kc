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
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * This class processes audit rules (warnings) for the Report Information related
 * data of the AwardDocument.
 */
public class AwardProjectPersonsAuditRule implements DocumentAuditRule {

    private static final String CONTACTS_AUDIT_ERRORS = "contactsAuditErrors";
    private static final String CONTACTS_AUDIT_WARNINGS = "contactsAuditWarnings";    
    private static final String AWARD_UNCERTIFIED_PARAM = "awardUncertifiedKeyPersonnel";
    private List<AuditError> auditErrors;
    private List<AuditError> auditWarnings;
    public static final String AWARD_PROJECT_PERSON_LIST_ERROR_KEY = "document.awardList[0].projectPerson.auditErrors";
    public static final String ERROR_AWARD_PROJECT_PERSON_NO_PI = "error.awardProjectPerson.no.pi.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS = "error.awardProjectPerson.pi.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED = "error.awardProjectPerson.unit.details.required";
    public static final String ERROR_AWARD_PROJECT_PERSON_LEAD_UNIT_REQUIRED = "error.awardProjectPerson.lead.unit.required";
    public static final String ERROR_AWARD_PROJECT_PERSON_UNCERTIFIED = "error.awardProjectPerson.uncertified";
    
    private ParameterService parameterService;
    private AwardService awardService;
    private InstitutionalProposalService institutionalProposalService;
    
    /**
     * 
     * Constructs a AwardContactAuditRule.java. Added so unit test would not
     * need to call processRunAuditBusinessRules and therefore declare a document.
     */
    public AwardProjectPersonsAuditRule() {
        auditErrors = new ArrayList<AuditError>();
        auditWarnings = new ArrayList<AuditError>();
    }
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument)document;
        auditErrors = new ArrayList<AuditError>();
        auditWarnings = new ArrayList<AuditError>();        
        
        valid &= checkPrincipalInvestigators(awardDocument.getAward().getProjectPersons());
        valid &= checkUnits(awardDocument.getAward().getProjectPersons());
        valid &= checkLeadUnits(awardDocument.getAward().getProjectPersons());
        valid &= checkCertifiedInvestigators(awardDocument.getAward());
        
         
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
        if (auditWarnings.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(CONTACTS_AUDIT_WARNINGS, new AuditCluster(Constants.CONTACTS_PANEL_NAME,
                    auditWarnings, Constants.AUDIT_WARNINGS));            
        }
    }
    
    protected boolean checkPrincipalInvestigators(List<AwardPerson> awardPersons) {
        boolean valid = true;
        List<AwardPerson> principalInvestigators = getPrincipalInvestigators(awardPersons);
        int piCount = principalInvestigators.size();
        if ( piCount == 0 ) {
            valid = false;
            auditErrors.add(new AuditError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_NO_PI,                    
                    Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR));
        } else if ( piCount > 1 ) {
            valid = false;
            auditErrors.add(new AuditError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS,                    
                    Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR));
        }
        return valid;
    }
    
    protected boolean checkUnits(List<AwardPerson> awardPersons) {
        boolean valid = true;
        for ( AwardPerson person : awardPersons) {
            if ( (person.isPrincipalInvestigator() || person.isCoInvestigator()) && 
                    person.getUnits() != null && person.getUnits().size() == 0 ) {
                valid = false;
                auditErrors.add(new AuditError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED,
                        Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR, new String[]{person.getFullName()}));
            }
        }        
        return valid;
    }
    
    protected boolean checkLeadUnits(List<AwardPerson> awardPersons) {
        boolean valid = true;
        List<AwardPerson> principalInvestigators = getPrincipalInvestigators(awardPersons);
        if ( principalInvestigators != null && principalInvestigators.size() == 1 ) {
            List<AwardPersonUnit> piUnits = principalInvestigators.get(0).getUnits();
                int leadUnits = 0;
                for ( AwardPersonUnit unit : piUnits ) {
                    if ( unit.isLeadUnit() ) {
                        leadUnits++;
                    }
                }
                if ( leadUnits != 1 ) {
                    valid = false;
                    auditErrors.add(new AuditError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_LEAD_UNIT_REQUIRED,
                            Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR));
                }
            }
        return valid;
    }
    
    
    
    protected List<AwardPerson> getPrincipalInvestigators(List<AwardPerson> projectPersons) {
        List<AwardPerson> principalInvestigators = new ArrayList<AwardPerson>();
        for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator()) {
                principalInvestigators.add(p);
            }
        }
        return principalInvestigators; 
    }
    
    protected List<AwardPersonUnit> getPIUnits(List<AwardPerson> projectPersons) {
        List<AwardPersonUnit> units = new ArrayList<AwardPersonUnit>();
        for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator() ) {
                for(AwardPersonUnit apu: p.getUnits()) {
                    units.add(apu);
                }
            }
        }
        return units; 
    }
    
    protected boolean checkCertifiedInvestigators(Award award) {
        boolean retval = true;
        String parmVal = getParameterService().getParameterValueAsString(AwardDocument.class, AWARD_UNCERTIFIED_PARAM);
        if (StringUtils.equals(parmVal, "0")) { //do not validate uncertified investigators
            return retval;
        }
        boolean error = StringUtils.equals(parmVal, "2");
        List<DevelopmentProposal> devProposals = new ArrayList<DevelopmentProposal>();
        List<Award> awards = getAwardService().findAwardsForAwardNumber(award.getAwardNumber());
        for (Award curAward : awards) {
            List<AwardFundingProposal> fundingProposals = curAward.getFundingProposals();
            for (AwardFundingProposal fundingProposal : fundingProposals) {
                devProposals.addAll(getInstitutionalProposalService().getAllLinkedDevelopmentProposals(fundingProposal.getProposal().getProposalNumber()));
            }
        }
        for (AwardPerson person : award.getProjectPersons()) {
            boolean personFoundCheck = devProposals.isEmpty();
            for (DevelopmentProposal proposal : devProposals) {
                for (ProposalPerson propPerson : proposal.getProposalPersons()) {
                    if ((person != null && StringUtils.equals(person.getPersonId(), propPerson.getPersonId()))
                            || (person.getRolodexId() != null && ObjectUtils.equals(person.getRolodexId(), propPerson.getRolodexId()))) {
                        if (StringUtils.equals(propPerson.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE)
                                || StringUtils.equals(propPerson.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                                || StringUtils.equals(propPerson.getOptInCertificationStatus(), "Y")) {
                            personFoundCheck = true;
                            break;
                        } //otherwise they are not certified
                    }
                }
            }
            if (!personFoundCheck) {
                retval = false;
                //if the parm says to add an error and the person is not a key person(as they do not require certification)
                if (error && !person.isKeyPerson()) {
                    auditErrors.add(new AuditError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_UNCERTIFIED,
                            Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR, new String[]{person.getFullName()}));
                } else {
                    auditWarnings.add(new AuditError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_UNCERTIFIED,
                            Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR, new String[]{person.getFullName()}));
                }
            }
        }
        
        
        return retval;
    }

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KraServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    public InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KraServiceLocator.getService(InstitutionalProposalService.class);
        }
        return institutionalProposalService;
    }

    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }
    
    

}
