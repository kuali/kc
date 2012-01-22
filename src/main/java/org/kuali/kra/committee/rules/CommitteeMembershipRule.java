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
package org.kuali.kra.committee.rules;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRoleRule;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRule;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipRoleEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class contains the rules to validate a <code>{@link CommitteeMembership}</code>
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class CommitteeMembershipRule extends ResearchDocumentRuleBase 
                                     implements AddCommitteeMembershipRule,
                                                AddCommitteeMembershipRoleRule {

    private final String PROPERTY_NAME_PERSON_NAME = "committeeHelper.newCommitteeMembership.personName";
    private final String PROPERTY_NAME_ROLE_CODE = "committeeHelper.newCommitteeMembershipRoles[%1$s].membershipRoleCode";
    private final String PROPERTY_NAME_ROLE_START_DATE = "committeeHelper.newCommitteeMembershipRoles[%1$s].startDate"; 
    private final String PROPERTY_NAME_ROLE_END_DATE = "committeeHelper.newCommitteeMembershipRoles[%1$s].endDate"; 
                    
    /**
     * ProcessDefinitionDefinitionDefinition the validation rules for an <code>{@link AddCommitteeMembershipEvent}</code>.
     * 
     * @param addCommitteeMembershipEvent
     * @return <code>true</code> if all validation rules are passed, <code>false</code> otherwise
     */
    public boolean processAddCommitteeMembershipBusinessRules(AddCommitteeMembershipEvent addCommitteeMembershipEvent) {
        boolean isValid = true;
        
        CommitteeMembership committeeMembership = addCommitteeMembershipEvent.getCommitteeMembership();
        
        if (StringUtils.isBlank(committeeMembership.getPersonId()) && (committeeMembership.getRolodexId() == null)) { 
            isValid = false;
            reportError(PROPERTY_NAME_PERSON_NAME, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_NOT_SPECIFIED);
        } 
        if (StringUtils.isBlank(committeeMembership.getPersonName()) && isValid) {
            isValid = false;
            reportError(PROPERTY_NAME_PERSON_NAME, KeyConstants.ERROR_COMMITTEE_MEMBERHSIP_PERSON_NO_NAME);
        }
        
        return isValid;
    }
    
    /**
     * ProcessDefinitionDefinitionDefinition the validation rules for an <code>{@link addCommitteeMembershipRoleEvent}</code>.
     * 
     * @param addCommitteeMembershipRoleEvent
     * @return <code>true</code> if all validation rules are passed, <code>false</code> otherwise
     */
    public boolean processAddCommitteeMembershipRoleBusinessRules(AddCommitteeMembershipRoleEvent addCommitteeMembershipRoleEvent) {
        boolean isValid = true;
        
        CommitteeMembershipRole membershipRole = addCommitteeMembershipRoleEvent.getCommitteeMembershipRole();

        int membershipIndex = addCommitteeMembershipRoleEvent.getMembershipIndex();
        
        CommitteeDocument committeeDocument = (CommitteeDocument) addCommitteeMembershipRoleEvent.getDocument();
        CommitteeMembership committeeMembership = committeeDocument.getCommittee().getCommitteeMemberships().get(membershipIndex);
        List<CommitteeMembershipRole> membershipRoles = committeeMembership.getMembershipRoles();

        // Verify role code
        if (StringUtils.isBlank(membershipRole.getMembershipRoleCode())) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_ROLE_CODE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_NOT_SPECIFIED);
        }
        
        // Verify role start date
        if (membershipRole.getStartDate() == null) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_ROLE_START_DATE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_NOT_SPECIFIED);
        }
        if (hasDateOutsideCommitteeMembershipTerm(committeeMembership, membershipRole.getStartDate())) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_ROLE_START_DATE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_OUTSIDE_TERM);
        }

        // Verify role end date
        if (membershipRole.getEndDate() == null) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_ROLE_END_DATE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_NOT_SPECIFIED);
        }
        if (membershipRole.getStartDate() != null && membershipRole.getEndDate() != null 
                && membershipRole.getEndDate().before(membershipRole.getStartDate())) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_ROLE_END_DATE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_BEFORE_ROLE_START_DATE);
        }
        if (hasDateOutsideCommitteeMembershipTerm(committeeMembership, membershipRole.getEndDate())) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_ROLE_END_DATE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_OUTSIDE_TERM);
        }
        
        // To keep the errors more comprehensible the role overlap check is done after other errors are resolved
        if (isValid && hasRoleOverlap(membershipRole, membershipRoles)) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_ROLE_CODE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_DUPLICATE);
        }
        
        return isValid;
    }
    
    /**
     * Check if the date is outside the committee membership term.
     * If any of the date are null the method returns false.
     * 
     * @param committeeMembership - the committeeMembership whose term we are comparing against
     * @param date - the date to be checked
     * @return <code>true</code> if the date is outside the committee membership term, <code>false</code> otherwise
     */
    private boolean hasDateOutsideCommitteeMembershipTerm(CommitteeMembership committeeMembership, Date date) {
        boolean isOutside = false;
        if ((committeeMembership.getTermStartDate() != null) && (committeeMembership.getTermEndDate() != null) && (date != null)) {
            if (date.before(committeeMembership.getTermStartDate()) || date.after(committeeMembership.getTermEndDate())) {
                isOutside = true;
            }
        }
        return isOutside;
    }
    
    /**
     * Check if the role overlaps with another role of the same role type.
     * 
     * @param role - The role to be checked
     * @param membershipRoles - The list of roles to check against
     * @return <code>true</code> if the role date overlaps with another 
     */
    private boolean hasRoleOverlap(CommitteeMembershipRole role, List<CommitteeMembershipRole> membershipRoles) {
        boolean hasOverlap = false;
        for (CommitteeMembershipRole tmpRole : membershipRoles) {
            if (role.getMembershipRoleCode().equals(tmpRole.getMembershipRoleCode())) {
                if (isWithinPeriod(role.getStartDate(), tmpRole.getStartDate(), tmpRole.getEndDate()) 
                        || isWithinPeriod(role.getEndDate(), tmpRole.getStartDate(), tmpRole.getEndDate())) { 
                    hasOverlap = true;
                }
            }
        }
        return hasOverlap;
    }

    /**
     * Verify that a date is within a period
     * 
     * @param date - the date that needs to be within the period
     * @param periodStart - the date on which the period begins
     * @param periodEnd - the date on which the period ends
     * @return <code>true</code> if date is within the period, <code>false</code> otherwise
     */
    private boolean isWithinPeriod(Date date, Date periodStart, Date periodEnd) {
        return !(date.before(periodStart) || date.after(periodEnd));
    }

}
