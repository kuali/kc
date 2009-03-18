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
package org.kuali.kra.committee.rules;

import java.sql.Date;
import java.util.List;

import org.apache.axis.utils.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRule;
import org.kuali.kra.committee.rule.SaveCommitteeMembershipRule;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent;
import org.kuali.kra.committee.rule.event.SaveCommitteeMembershipEvent;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * 
 * This class contains the rules to validate a <code>{@link CommitteeMembership}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeMembershipRule extends CommitteeDocumentRule 
                                     implements AddCommitteeMembershipRule,
                                                SaveCommitteeMembershipRule {

    final String PROPERTY_NAME_PREFIX = "document.committeeList[0].committeeMemberships[";
    final String PROPERTY_NAME_TERM_END_DATE = "].termEndDate";
    final String PROPERTY_NAME_NEW_ROLE_PREFIX = "membershipRolesHelper.newCommitteeMembershipRoles[";
    final String PROPERTY_NAME_ROLE_PREFIX = "].membershipRoles[";
    final String PROPERTY_NAME_ROLE_CODE = "].membershipRoleCode";
    final String PROPERTY_NAME_ROLE_START_DATE = "].startDate";
    final String PROPERTY_NAME_ROLE_END_DATE = "].endDate";

    /**
     * Process the validation rules for an <code>{@link AddCommitteeMembershipEvent}</code>.
     * 
     * @param addProtocolParticipantEvent
     * @return <code>true</code> if valid, <code>false</code> otherwise
     */
    public boolean processAddCommitteeMembershipBusinessRules(AddCommitteeMembershipEvent addCommitteeMembershipEvent) {
        final String PROPERTY_NAME_PERSON_NAME = "membershipHelper.newCommitteeMembership.personName";
        boolean isValid = true;
        CommitteeMembership committeeMembership = addCommitteeMembershipEvent.getCommitteeMembership();
        if ( (StringUtils.isEmpty(committeeMembership.getPersonId())) && (StringUtils.isEmpty(committeeMembership.getRolodexId())) ) { 
            isValid = false;
            reportError(PROPERTY_NAME_PERSON_NAME, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_NOT_SPECIFIED);
        } else if (isDuplicate((CommitteeDocument) addCommitteeMembershipEvent.getDocument(), committeeMembership)){
            isValid = false;
            reportError(PROPERTY_NAME_PERSON_NAME, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_DUPLICATE);
        }
        return isValid;
    }
    
    /**
     * Check if the <code>{@link CommitteeMembership}</code> is already part of the <code {@link Committee}</code>.
     * 
     * @param committeeDocument to which the committee member is added
     * @param newCommitteeMembership which should be added to the committee.
     * @return <code>true</code> if it is a duplicate, <code>false</code> otherwise
     */
    private boolean isDuplicate(CommitteeDocument committeeDocument, CommitteeMembership newCommitteeMembership) {
        boolean isDuplicate = false;
        List<CommitteeMembership> committeeMemberships = committeeDocument.getCommittee().getCommitteeMemberships();
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            if (committeeMembership.equals(newCommitteeMembership)) {
                isDuplicate = true;
            }
        }
        return isDuplicate;        
    }
    
    /**
     * @see org.kuali.kra.committee.rules.CommitteeDocumentRule#processSaveCommitteeMembershipBusinessRules(org.kuali.kra.committee.rule.event.SaveCommitteeMembershipEvent)
     */
    @Override
    public boolean processSaveCommitteeMembershipBusinessRules(SaveCommitteeMembershipEvent saveCommitteeMembershipEvent) {
        boolean isValid = true;
        //CommitteeMembership committeeMembership = saveCommitteeMembershipEvent.getCommitteeMembership();
        CommitteeDocument committeeDocument = (CommitteeDocument)saveCommitteeMembershipEvent.getDocument();
        List<CommitteeMembership> committeeMemberships = committeeDocument.getCommittee().getCommitteeMemberships();
        
        for (CommitteeMembership committeeMembership : committeeMemberships) {
            isValid &= isValidTermStartEndDates(committeeMembership, committeeMemberships.indexOf(committeeMembership));
            isValid &= isValidRoles(committeeMembership, committeeMemberships.indexOf(committeeMembership));
        }
        return isValid;
    }

    /**
     * Verify the Term Start and Term End dates
     * 
     * Date validation is done by the data dictionary.  
     * Validate that Term End date is greater than or equal to the Term Start date.
     * 
     * @param committeeMembership - the committeeMembership which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @return <code>true</code> if the term start and end dates are valid, <code>false</code> otherwise
     */
    private boolean isValidTermStartEndDates(CommitteeMembership committeeMembership, int membershipIndex) {
        boolean isValid = true;
        if ((committeeMembership.getTermStartDate() != null) && (committeeMembership.getTermEndDate() != null)) {
            if (!isValidStartEndDate(committeeMembership.getTermStartDate(), committeeMembership.getTermEndDate())) {
                isValid = false;
                reportError(PROPERTY_NAME_PREFIX + membershipIndex + PROPERTY_NAME_TERM_END_DATE, 
                            KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_TERM_END_DATE_BEFORE_TERM_START_DATE);
            }
        }
       return isValid;
    }
    
    /**
     * Verify Roles
     * 
     * @param committeeMembership - the committeeMembership which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @return <code>true</code> if the roles are valid, <code>false</code> otherwise
     */
    private boolean isValidRoles(CommitteeMembership committeeMembership, int membershipIndex) {
        boolean isValid = true;
        List<CommitteeMembershipRole> membershipRoles = committeeMembership.getMembershipRoles();

        isValid &= membershipRolesExists(membershipRoles, membershipIndex);
        
        for (CommitteeMembershipRole membershipRole : membershipRoles) {
            isValid &= isValidRoleStartEndDates(membershipRole, membershipIndex, membershipRoles.indexOf(membershipRole));
            isValid &= roleDatesWithinTermDates(committeeMembership, membershipRole, membershipIndex, membershipRoles.indexOf(membershipRole));
        }
        return isValid;
    }

    /**
     * Verify that a role exists
     * 
     * @param membershipRoles - list of CommitteeMembershipRoles for the committeeMembership
     * @param membershipIndex - the index position of the committeeMembership
     * @return <code>true</code> if a CommitteeMembershipRole exists, <code>false</code> otherwise
     */
    private boolean membershipRolesExists(List<CommitteeMembershipRole> membershipRoles, int membershipIndex) {
        boolean isValid = true;
        if (membershipRoles.isEmpty()) {
            isValid = false;
            reportError(PROPERTY_NAME_NEW_ROLE_PREFIX + membershipIndex + PROPERTY_NAME_ROLE_CODE, 
                    KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_MISSING);
        }
        return isValid;
    }

    /**
     * Verify the Role Start and Role End dates
     * 
     * Date validation is done by the data dictionary.  
     * Validate that Role End date is greater than or equal to the Role Start date.
     * 
     * @param membershipRole - the membershipRole which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @param roleIndex - the index position of the membershipRole
     * @return <code>true</code> if the role start and end dates are valid, <code>false</code> otherwise
     */
    private boolean isValidRoleStartEndDates(CommitteeMembershipRole membershipRole, int membershipIndex, int roleIndex) {
        boolean isValid = true;
        if ((membershipRole.getStartDate() != null) && (membershipRole.getEndDate() != null)) {
            if (!isValidStartEndDate(membershipRole.getStartDate(), membershipRole.getEndDate())) {
                isValid = false;
                reportError(PROPERTY_NAME_PREFIX + membershipIndex + PROPERTY_NAME_ROLE_PREFIX + roleIndex + PROPERTY_NAME_ROLE_END_DATE, 
                            KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_BEFORE_ROLE_START_DATE);
            }
        }
       return isValid;
    }

    /**
     * Verify that the role dates are within the term period
     * 
     * @param committeeMembership - the committeeMembership of whom the membershipRole is to be validated
     * @param membershipRole - the membershipRole which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @param indexOf - the index position of the membershipRole
     * @return <code>true</code> if the role dates are within the term period, <code>false</code> otherwise
     */
    private boolean roleDatesWithinTermDates(CommitteeMembership committeeMembership, CommitteeMembershipRole membershipRole,
            int membershipIndex, int roleIndex) {
        boolean isValid = true;
        if ((committeeMembership.getTermStartDate() != null) && (committeeMembership.getTermEndDate() != null) && 
                (membershipRole.getStartDate() != null) && (membershipRole.getEndDate() != null)) {
            if (!isWithinPeriod(membershipRole.getStartDate(), committeeMembership.getTermStartDate(), committeeMembership.getTermEndDate())) {
                isValid = false;
                reportError(PROPERTY_NAME_PREFIX + membershipIndex + PROPERTY_NAME_ROLE_PREFIX + roleIndex + PROPERTY_NAME_ROLE_START_DATE, 
                        KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_OUTSIDE_TERM);
            }
            if (!isWithinPeriod(membershipRole.getEndDate(), committeeMembership.getTermStartDate(), committeeMembership.getTermEndDate())) {
                isValid = false;
                reportError(PROPERTY_NAME_PREFIX + membershipIndex + PROPERTY_NAME_ROLE_PREFIX + roleIndex + PROPERTY_NAME_ROLE_END_DATE, 
                        KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_OUTSIDE_TERM);
            }
        }
        return isValid;
    }
    
    private boolean noRoleOverlap(CommitteeMembership committeeMembership, CommitteeMembershipRole membershipRole,
            int membershipIndex, int roleIndex) {
        boolean isValid = true;
        // TODO: cniesen - continue implementation 
        return isValid;
    }

    /**
     * Verify that end date is after start date.
     * 
     * @param startDate
     * @param endDate
     * @return <code>true</code> if endDate is on or after startDate, <code>false</code> otherwise
     */
    private boolean isValidStartEndDate(Date startDate, Date endDate) {
        if (endDate.before(startDate)) {
            return false;
        } else {
            return true;
        }
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
        if ((date.before(periodStart)) || (date.after(periodEnd))) {
            return false;
        } else {
            return true;
        }
    }
    
}
