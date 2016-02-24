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

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

/**
 * This class processes audit rules (warnings) for the Report Information related
 * data of the AwardDocument.
 */
public class AwardProjectPersonsAuditRule implements DocumentAuditRule {

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
    public static final String ERROR_AWARD_PRJECT_PERSON_UNIT_REQUIRED = "error.awardProjectPerson.unit.required";
    
    private ParameterService parameterService;
    private AwardService awardService;
    private InstitutionalProposalService institutionalProposalService;

    public AwardProjectPersonsAuditRule() {
        auditErrors = new ArrayList<AuditError>();
        auditWarnings = new ArrayList<AuditError>();
    }
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        AwardDocument awardDocument = (AwardDocument)document;
        auditErrors = new ArrayList<AuditError>();
        auditWarnings = new ArrayList<AuditError>();

        boolean valid = checkUnitExists(awardDocument.getAward().getProjectPersons());
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
        	AuditCluster existingErrors = (AuditCluster) GlobalVariables.getAuditErrorMap().get(Constants.CONTACTS_AUDIT_ERROR_KEY_NAME);
            if (existingErrors == null) {
                GlobalVariables.getAuditErrorMap().put(Constants.CONTACTS_AUDIT_ERROR_KEY_NAME, new AuditCluster(Constants.CONTACTS_PANEL_NAME,
                                                                                              auditErrors, Constants.AUDIT_ERRORS));
            } else {
                existingErrors.getAuditErrorList().addAll(auditErrors);
            }
        }
        if (auditWarnings.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(CONTACTS_AUDIT_WARNINGS, new AuditCluster(Constants.CONTACTS_PANEL_NAME,
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
    
    protected boolean checkCertifiedInvestigators(Award award) {

        String parmVal = getParameterService().getParameterValueAsString(AwardDocument.class, AWARD_UNCERTIFIED_PARAM);
        if (StringUtils.equals(parmVal, "0")) { //do not validate uncertified investigators
            return true;
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

        boolean retval = true;
        for (AwardPerson person : award.getProjectPersons()) {
            boolean personFoundCheck = false;
            for (DevelopmentProposal proposal : devProposals) {
                for (ProposalPerson propPerson : proposal.getProposalPersons()) {
                    if ((person != null && StringUtils.equals(person.getPersonId(), propPerson.getPersonId()))
                            || (person.getRolodexId() != null && ObjectUtils.equals(person.getRolodexId(), propPerson.getRolodexId()))) {
                        if (StringUtils.equals(propPerson.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE)
                                || StringUtils.equals(propPerson.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                                || propPerson.getOptInCertificationStatus()) {
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

    protected boolean checkUnitExists(List<AwardPerson> awardPersons) {
        boolean valid = true;
        for (AwardPerson person : awardPersons) {
        	if ((person.isKeyPerson() && person.isOptInUnitStatus()) && (person.getUnits() == null || person.getUnits().size() < 1)) {
        		valid = false;
        		String[] params = new String[1];
        		params[0] = person.getContact().getFullName();
                auditErrors.add(new AuditError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PRJECT_PERSON_UNIT_REQUIRED,
                        Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR, params));
        	}
        }
        return valid;
    }

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KcServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    public InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
        }
        return institutionalProposalService;
    }

    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }
    
    

}
