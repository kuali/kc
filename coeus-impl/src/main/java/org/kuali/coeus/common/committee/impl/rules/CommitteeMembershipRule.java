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
package org.kuali.coeus.common.committee.impl.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.rule.AddCommitteeMembershipRoleRule;
import org.kuali.coeus.common.committee.impl.rule.AddCommitteeMembershipRule;
import org.kuali.coeus.common.committee.impl.rule.event.AddCommitteeMembershipEvent;
import org.kuali.coeus.common.committee.impl.rule.event.AddCommitteeMembershipRoleEvent;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;

import java.sql.Date;
import java.util.List;

/**
 * 
 * This class contains the rules to validate a <code>{@link CommitteeMembershipBase}</code>
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class CommitteeMembershipRule extends KcTransactionalDocumentRuleBase
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
        
        CommitteeMembershipBase committeeMembership = addCommitteeMembershipEvent.getCommitteeMembership();
        
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
        
        CommitteeDocumentBase<?, ?, ?> committeeDocument = (CommitteeDocumentBase) addCommitteeMembershipRoleEvent.getDocument();
        CommitteeMembershipBase committeeMembership = committeeDocument.getCommittee().getCommitteeMemberships().get(membershipIndex);
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
    private boolean hasDateOutsideCommitteeMembershipTerm(CommitteeMembershipBase committeeMembership, Date date) {
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
