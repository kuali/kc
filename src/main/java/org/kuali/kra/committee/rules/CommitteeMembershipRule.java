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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.AddCommitteeMembershipExpertiseRule;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRoleRule;
import org.kuali.kra.committee.rule.AddCommitteeMembershipRule;
import org.kuali.kra.committee.rule.SaveCommitteeMembershipRule;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipExpertiseEvent;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipRoleEvent;
import org.kuali.kra.committee.rule.event.SaveCommitteeMembershipEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class contains the rules to validate a <code>{@link CommitteeMembership}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeMembershipRule extends ResearchDocumentRuleBase 
                                     implements AddCommitteeMembershipRule,
                                                AddCommitteeMembershipRoleRule,
                                                AddCommitteeMembershipExpertiseRule,
                                                SaveCommitteeMembershipRule {

    final String PROPERTY_NAME_PERSON_NAME = "membershipHelper.newCommitteeMembership.personName";
    final String PROPERTY_NAME_PREFIX = "document.committeeList[0].committeeMemberships[";
    final String PROPERTY_NAME_TERM_END_DATE = "].termEndDate";
    final String PROPERTY_NAME_NEW_ROLE_PREFIX = "membershipRolesHelper.newCommitteeMembershipRoles[";
    final String PROPERTY_NAME_ROLE_PREFIX = "].membershipRoles[";
    final String PROPERTY_NAME_ROLE_CODE = "].membershipRoleCode";
    final String PROPERTY_NAME_ROLE_START_DATE = "].startDate";
    final String PROPERTY_NAME_ROLE_END_DATE = "].endDate";
    final String PROPERTY_NAME_NEW_EXPERTISE_PREFIX = "membershipExpertiseHelper.newCommitteeMembershipExpertise[";
    final String PROPERTY_NAME_RESEARCH_AREA_CODE = "].researchAreaCode";

    /**
     * Process the validation rules for an <code>{@link AddCommitteeMembershipEvent}</code>.
     * 
     * @param addProtocolParticipantEvent
     * @return <code>true</code> if all validation rules are passed, <code>false</code> otherwise
     */
    public boolean processAddCommitteeMembershipBusinessRules(AddCommitteeMembershipEvent addCommitteeMembershipEvent) {
        boolean isValid = true;
        
        CommitteeMembership committeeMembership = addCommitteeMembershipEvent.getCommitteeMembership();
        CommitteeDocument committeeDocument = (CommitteeDocument) addCommitteeMembershipEvent.getDocument();
        List<CommitteeMembership> committeeMemberships = committeeDocument.getCommittee().getCommitteeMemberships();
        
        if ( (StringUtils.isEmpty(committeeMembership.getPersonId())) && (StringUtils.isEmpty(committeeMembership.getRolodexId())) ) { 
            isValid = false;
            reportError(PROPERTY_NAME_PERSON_NAME, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_NOT_SPECIFIED);
        } else if (committeeMemberships.contains(committeeMembership)){
            isValid = false;
            reportError(PROPERTY_NAME_PERSON_NAME, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_DUPLICATE);
        }
        return isValid;
    }
    
    /**
     * Process the validation rules for an <code>{@link addCommitteeMembershipRoleEvent}</code>.
     * 
     * @param addCommitteeMembershipRoleEvent
     * @return <code>true</code> if all validation rules are passed, <code>false</code> otherwise
     */
    public boolean processAddCommitteeMembershipRoleBusinessRules(AddCommitteeMembershipRoleEvent addCommitteeMembershipRoleEvent) {
        boolean isValid = true;
        
        CommitteeMembershipRole membershipRole = addCommitteeMembershipRoleEvent.getCommitteeMembershipRole();
        String membershipRoleCode = membershipRole.getMembershipRoleCode();

        int membershipIndex = addCommitteeMembershipRoleEvent.getMembershipIndex();
        
        CommitteeDocument committeeDocument = (CommitteeDocument) addCommitteeMembershipRoleEvent.getDocument();
        CommitteeMembership committeeMembership = committeeDocument.getCommittee().getCommitteeMemberships().get(membershipIndex);
        List<CommitteeMembershipRole> membershipRoles = committeeMembership.getMembershipRoles();

        if (StringUtils.isBlank(membershipRoleCode)) {
            isValid = false;
            reportError(PROPERTY_NAME_NEW_ROLE_PREFIX + membershipIndex + PROPERTY_NAME_ROLE_CODE, 
                    KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_NOT_SPECIFIED);
//        } else if (!isValidMembershipRoleCode(membershipRoleCode)) {
//            isValid = false;
        } else if (membershipRoles.contains(membershipRole)) {
            isValid = false;
            reportError(PROPERTY_NAME_NEW_ROLE_PREFIX + membershipIndex + PROPERTY_NAME_ROLE_CODE, 
                    KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_DUPLICATE);
        }
        
        return isValid;
    }


    /**
     * Process the validation rules for an <code>{@link AddCommitteeMembershipExpertiseEvent}</code>.
     * 
     * @param addCommitteeMembershipExpertiseEvent
     * @return <code>true</code> if all validation rules are passed, <code>false</code> otherwise
     */
    public boolean processAddCommitteeMembershipExpertiseBusinessRules(
            AddCommitteeMembershipExpertiseEvent addCommitteeMembershipExpertiseEvent) {
        boolean isValid = true;
        
        CommitteeMembershipExpertise membershipExpertise = addCommitteeMembershipExpertiseEvent.getCommitteeMembershipExpertise();
        String researchAreaCode = membershipExpertise.getResearchAreaCode();
        
        int membershipIndex = addCommitteeMembershipExpertiseEvent.getMembershipIndex();
        
        CommitteeDocument committeeDocument = (CommitteeDocument) addCommitteeMembershipExpertiseEvent.getDocument();
        CommitteeMembership committeeMembership = committeeDocument.getCommittee().getCommitteeMemberships().get(membershipIndex);
        List<CommitteeMembershipExpertise> membershipExpertises = committeeMembership.getMembershipExpertise();

        if (StringUtils.isBlank(researchAreaCode)) {
            isValid = false;
            reportError(PROPERTY_NAME_NEW_EXPERTISE_PREFIX + membershipIndex + PROPERTY_NAME_RESEARCH_AREA_CODE, 
                    KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_NOT_SPECIFIED);
//        } else if (!isValidMembershipRoleCode(membershipRoleCode)) {
//            isValid = false;
        } else if (membershipExpertises.contains(membershipExpertise)) {
            isValid = false;
            reportError(PROPERTY_NAME_NEW_EXPERTISE_PREFIX + membershipIndex + PROPERTY_NAME_RESEARCH_AREA_CODE, 
                    KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_DUPLICATE);
        }
        
        return isValid;
    }
    
    /**
     * Process the validation rules for an <code>{@link SaveCommitteeMembershipEvent}</code>.
     * 
     * @param saveProtocolParticipantEvent
     * @return <code>true</code> if all validation rules are passed, <code>false</code> otherwise
     */
    public boolean processSaveCommitteeMembershipBusinessRules(SaveCommitteeMembershipEvent saveCommitteeMembershipEvent) {
        boolean isValid = true;
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
     * This method also displays the appropriate error message.
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
            int roleIndex = membershipRoles.indexOf(membershipRole);
            isValid &= isValidRoleStartEndDates(membershipRole, membershipIndex, roleIndex);
            isValid &= roleDatesWithinTermDates(committeeMembership, membershipRole, membershipIndex, roleIndex);
            // To keep the errors more comprehensible the role overlap check is done after other errors are resolved
            if (isValid) {
                isValid &= noRoleOverlap(committeeMembership, membershipRole, membershipIndex, roleIndex);
            }
        }
        return isValid;
    }

    /**
     * Verify that a role exists
     * 
     * This method also displays the appropriate error message.
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
     * Verify the Role Start and Role End dates.
     * 
     * Date validation is done by the data dictionary.  
     * Validate that Role End date is greater than or equal to the Role Start date.
     * 
     * This method also displays the appropriate error message.
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
     * Verify that the role dates are within the term period.
     * 
     * This method also displays the appropriate error message.
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
    
    /**
     * Check that the role does not have other entries whose time periods overlap.
     * (A member may not have the same role for overlapping time periods.)
     * 
     * @param committeeMembership - the committeeMembership of whom the membershipRole is to be validated
     * @param membershipRole - the membershipRole which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @param indexOf - the index position of the membershipRole
     * @return <code>true</code> if the role does not overlap with another role, <code>false</code> otherwise
     */
    private boolean noRoleOverlap(CommitteeMembership committeeMembership, CommitteeMembershipRole membershipRole,
            int membershipIndex, int roleIndex) {
        boolean isValid = true;
        List<CommitteeMembershipRole> membershipRoles = committeeMembership.getMembershipRoles();

        for (CommitteeMembershipRole tmpRole : membershipRoles) {
            isValid &= !hasOverlapingRolePeriods(membershipRole, tmpRole, membershipIndex, roleIndex);
        }
        
        return isValid;
    }

    /**
     * Check if the start or end date of the checkRole is within the period of the otherRole.
     * 
     * This method also displays the appropriate error message.
     * 
     * @param checkRole - the role whose time start and end date is being verified
     * @param otherRole - the role against which the verification is performed
     * @return <code>true</code> if role periods overlap, <code>false</code> otherwise
     */
    private boolean hasOverlapingRolePeriods(CommitteeMembershipRole checkRole, CommitteeMembershipRole otherRole, int membershipIndex, int roleIndex) {
        boolean isOverlaping = false;
        
        if (!checkRole.equals(otherRole)) {
            if (isWithinPeriod(checkRole.getStartDate(), otherRole.getStartDate(), otherRole.getEndDate())) {
                reportError(PROPERTY_NAME_PREFIX + membershipIndex + PROPERTY_NAME_ROLE_PREFIX + roleIndex + PROPERTY_NAME_ROLE_START_DATE, 
                        KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_DUPLICATE);
                isOverlaping = true;
            }
            if (isWithinPeriod(checkRole.getEndDate(), otherRole.getStartDate(), otherRole.getEndDate())) {
                reportError(PROPERTY_NAME_PREFIX + membershipIndex + PROPERTY_NAME_ROLE_PREFIX + roleIndex + PROPERTY_NAME_ROLE_END_DATE, 
                        KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_DUPLICATE);
                isOverlaping = true;
            }
        }
        return isOverlaping;
    }
    
    /**
     * Verify that end date is after start date.
     * 
     * @param startDate
     * @param endDate
     * @return <code>true</code> if endDate is on or after startDate, <code>false</code> otherwise
     */
    private boolean isValidStartEndDate(Date startDate, Date endDate) {
        return !endDate.before(startDate);
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
