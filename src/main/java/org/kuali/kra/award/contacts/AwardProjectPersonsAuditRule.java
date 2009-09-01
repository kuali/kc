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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class processes audit rules (warnings) for the Report Information related
 * data of the AwardDocument.
 */
public class AwardProjectPersonsAuditRule implements DocumentAuditRule {

    private static final String CONTACTS_AUDIT_ERRORS = "contactsAuditErrors";
    private List<AuditError> auditErrors;
    public static final String AWARD_PROJECT_PERSON_LIST_ERROR_KEY = "document.awardList[0].projectPerson.auditErrors";
    public static final String ERROR_AWARD_PROJECT_PERSON_NO_PI = "error.awardProjectPerson.no.pi.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS = "error.awardProjectPerson.pi.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED = "error.awardProjectPerson.unit.details.required";
    public static final String ERROR_AWARD_PROJECT_PERSON_LEAD_UNIT_REQUIRED = "error.awardProjectPerson.lead.unit.required";
    
    /**
     * 
     * Constructs a AwardContactAuditRule.java. Added so unit test would not
     * need to call processRunAuditBusinessRules and therefore declare a document.
     */
    public AwardProjectPersonsAuditRule() {
        auditErrors = new ArrayList<AuditError>();
    }
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument)document;
        auditErrors = new ArrayList<AuditError>();
        
        valid &= checkPrincipalInvestigators(awardDocument.getAward().getProjectPersons());
        valid &= checkUnits(awardDocument.getAward().getProjectPersons());
        valid &= checkLeadUnits(awardDocument.getAward().getProjectPersons());
        
         
        reportAndCreateAuditCluster();
        
        return valid;
    }
        
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(CONTACTS_AUDIT_ERRORS, new AuditCluster(Constants.CONTACTS_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
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

}
