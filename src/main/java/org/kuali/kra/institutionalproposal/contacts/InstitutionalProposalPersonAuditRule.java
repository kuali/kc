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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * This class...
 */
public class InstitutionalProposalPersonAuditRule implements DocumentAuditRule {

    private static final String CONTACTS_AUDIT_ERRORS = "contactsAuditErrors";
    private List<AuditError> auditErrors;
    public static final String PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY = "document.institutionalProposalList[0].projectPerson.auditErrors";
    public static final String ERROR_PROPOSAL_PROJECT_PERSON_NO_PI = "error.awardProjectPerson.no.pi.exists";
    public static final String ERROR_PROPOSAL_PROJECT_PERSON_MULTIPLE_PI_EXISTS = "error.awardProjectPerson.pi.exists";
    public static final String ERROR_PROPOSAL_PROJECT_PERSON_UNIT_DETAILS_REQUIRED = "error.awardProjectPerson.unit.details.required";
    public static final String ERROR_PROPOSAL_PROJECT_PERSON_LEAD_UNIT_REQUIRED = "error.awardProjectPerson.lead.unit.required";
    
    /**
     * 
     * Constructs a InstitutionalProposalContactAuditRule.java. Added so unit test would not
     * need to call processRunAuditBusinessRules and therefore declare a document.
     */
    public InstitutionalProposalPersonAuditRule() {
        auditErrors = new ArrayList<AuditError>();
    }
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument)document;
        auditErrors = new ArrayList<AuditError>();
        
        valid &= checkPrincipalInvestigators(institutionalProposalDocument.getInstitutionalProposal().getProjectPersons());
        valid &= checkUnits(institutionalProposalDocument.getInstitutionalProposal().getProjectPersons());
        valid &= checkLeadUnits(institutionalProposalDocument.getInstitutionalProposal().getProjectPersons());
        
         
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
    
    protected boolean checkPrincipalInvestigators(List<InstitutionalProposalPerson> institutionalProposalPersons) {
        boolean valid = true;
        List<InstitutionalProposalPerson> principalInvestigators = getPrincipalInvestigators(institutionalProposalPersons);
        int piCount = principalInvestigators.size();
        if ( piCount == 0 ) {
            valid = false;
            auditErrors.add(new AuditError(PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_PROPOSAL_PROJECT_PERSON_NO_PI,                    
                    Constants.MAPPING_INSTITUTIONAL_PROPOSAL_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR));
        } else if ( piCount > 1 ) {
            valid = false;
            auditErrors.add(new AuditError(PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_PROPOSAL_PROJECT_PERSON_MULTIPLE_PI_EXISTS,                    
                    Constants.MAPPING_INSTITUTIONAL_PROPOSAL_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR));
        }
        return valid;
    }
    
    protected boolean checkUnits(List<InstitutionalProposalPerson> institutionalProposalPersons) {
        boolean valid = true;
        for ( InstitutionalProposalPerson person : institutionalProposalPersons) {
            if ( (person.isPrincipalInvestigator() || person.isCoInvestigator()) && 
                    person.getUnits() != null && person.getUnits().size() == 0 ) {
                valid = false;
                auditErrors.add(new AuditError(PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_PROPOSAL_PROJECT_PERSON_UNIT_DETAILS_REQUIRED,
                        Constants.MAPPING_INSTITUTIONAL_PROPOSAL_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR, new String[]{person.getFullName()}));
            }
        }        
        return valid;
    }
    
    protected boolean checkLeadUnits(List<InstitutionalProposalPerson> institutionalProposalPersons) {
        boolean valid = true;
        List<InstitutionalProposalPerson> principalInvestigators = getPrincipalInvestigators(institutionalProposalPersons);
        if ( principalInvestigators != null && principalInvestigators.size() == 1 ) {
            List<InstitutionalProposalPersonUnit> piUnits = principalInvestigators.get(0).getUnits();
                int leadUnits = 0;
                for ( InstitutionalProposalPersonUnit unit : piUnits ) {
                    if ( unit.isLeadUnit() ) {
                        leadUnits++;
                    }
                }
                if ( leadUnits != 1 ) {
                    valid = false;
                    auditErrors.add(new AuditError(PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_PROPOSAL_PROJECT_PERSON_LEAD_UNIT_REQUIRED,
                            Constants.MAPPING_INSTITUTIONAL_PROPOSAL_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR));
                }
            }
        return valid;
    }
    
    
    
    protected List<InstitutionalProposalPerson> getPrincipalInvestigators(List<InstitutionalProposalPerson> projectPersons) {
        List<InstitutionalProposalPerson> principalInvestigators = new ArrayList<InstitutionalProposalPerson>();
        for(InstitutionalProposalPerson p: projectPersons) {
            if(p.isPrincipalInvestigator()) {
                principalInvestigators.add(p);
            }
        }
        return principalInvestigators; 
    }
    
    protected List<InstitutionalProposalPersonUnit> getPIUnits(List<InstitutionalProposalPerson> projectPersons) {
        List<InstitutionalProposalPersonUnit> units = new ArrayList<InstitutionalProposalPersonUnit>();
        for(InstitutionalProposalPerson p: projectPersons) {
            if(p.isPrincipalInvestigator() ) {
                for(InstitutionalProposalPersonUnit apu: p.getUnits()) {
                    units.add(apu);
                }
            }
        }
        return units; 
    }
}
