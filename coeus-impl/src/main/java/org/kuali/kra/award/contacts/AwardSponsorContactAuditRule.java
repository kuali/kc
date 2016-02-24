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

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
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
public class AwardSponsorContactAuditRule implements DocumentAuditRule {

    private static final String AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY = "document.awardList[0].sponsorContact.auditErrors";
    private static final String ERROR_AWARD_NO_SPONSOR_CONTACTS = "error.awardSponsorContact.none";
    private static final String ERROR_INVALID_COUNTRY_CODE = "error.invalid.countryCode";
    private List<AuditError> auditErrors;

    
    /**
     * 
     * Constructs a AwardSponsorContactAuditRule.java. Added so unit test would not
     * need to call processRunAuditBusinessRules and therefore declare a document.
     */
    public AwardSponsorContactAuditRule() {
        auditErrors = new ArrayList<AuditError>();
    }
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument)document;
        auditErrors = new ArrayList<AuditError>();
        
        valid &= checkForAtLeastOneSponsorContact(awardDocument.getAward().getSponsorContacts());

        valid &= checkForValidCountryCode(awardDocument.getAward().getSponsorContacts());

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
    
    protected boolean checkForAtLeastOneSponsorContact(List<AwardSponsorContact> sponsorContacts) {
        if (sponsorContacts.isEmpty()) {
            auditErrors.add(new AuditError(AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY, ERROR_AWARD_NO_SPONSOR_CONTACTS,                    
                    Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR));
            return false;
        } else {
            return true;
        }
    }


    /**
     * Verifies that the {@link AwardSponsorContact#getCountryCode} is not <code>null</code>. This results in a stacktrace
     * when printing an award notice.
     *
     * @param sponsorContacts {@link List} of {@link AwardSponsorContact} instances to check countryCode on.
     * @return true if all {@link AwardSponsorContact} instances have valid country codes, false otherwise.
     */
    protected boolean checkForValidCountryCode(final List<AwardSponsorContact> sponsorContacts) {
        if (sponsorContacts.isEmpty()) {
            return true;
        }
        
        for (final AwardSponsorContact contact : sponsorContacts) {
            if (contact.getRolodex() != null && contact.getRolodex().getCountryCode() == null) {
                auditErrors.add(new AuditError(AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY, ERROR_INVALID_COUNTRY_CODE,                    
                                               Constants.MAPPING_AWARD_CONTACTS_PAGE + "." + Constants.CONTACTS_PANEL_ANCHOR));
                return false; // it only takes one
            }
        }
        return true;
    }
}
