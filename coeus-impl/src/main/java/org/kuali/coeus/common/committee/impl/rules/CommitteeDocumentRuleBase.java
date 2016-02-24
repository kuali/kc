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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.committee.impl.bo.*;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.lookup.keyvalue.CommitteeIdValuesFinderBase;
import org.kuali.coeus.common.committee.impl.rule.AddCommitteeMembershipRoleRule;
import org.kuali.coeus.common.committee.impl.rule.AddCommitteeMembershipRule;
import org.kuali.coeus.common.committee.impl.rule.event.*;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.coeus.common.framework.custom.KcDocumentBaseAuditRule;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the main business rule class for the CommitteeBase Document.  It
 * is responsible for customized workflow related business rule checking such
 * saving, routing, etc.  All committee specific actions, e.g. adding members,
 * this class will act as a controller and forward the rules checking to 
 * another class within this package.
 */
@SuppressWarnings("unchecked")
public abstract class CommitteeDocumentRuleBase extends KcTransactionalDocumentRuleBase implements KcBusinessRule, AddCommitteeMembershipRule, AddCommitteeMembershipRoleRule, DocumentAuditRule {
    
    private static final String PROPERTY_NAME_TERM_START_DATE = "document.committeeList[0].committeeMemberships[%1$s].termStartDate";
    private static final String PROPERTY_NAME_TERM_END_DATE = "document.committeeList[0].committeeMemberships[%1$s].termEndDate";
    private static final String PROPERTY_NAME_ROLE_CODE_ADD = "committeeHelper.newCommitteeMembershipRoles[%1$s].membershipRoleCode";
    private static final String PROPERTY_NAME_ROLE_CODE = "document.committeeList[0].committeeMemberships[%1$s].membershipRoles[%2$s].membershipRoleCode";
    private static final String PROPERTY_NAME_ROLE_START_DATE = "document.committeeList[0].committeeMemberships[%1$s].membershipRoles[%2$s].startDate";
    private static final String PROPERTY_NAME_ROLE_END_DATE = "document.committeeList[0].committeeMemberships[%1$s].membershipRoles[%2$s].endDate";
    private static final String PROPERTY_NAME_RESEARCH_AREA_CODE = "committeeHelper.newCommitteeMembershipExpertise[%1$s].researchAreaCode";
    private static final Log LOG = LogFactory.getLog(CommitteeDocumentRuleBase.class);

    private static final String SEPERATOR = ".";
    private static final String PROPERTY_NAME_INACTIVE_AREAS_OF_EXPERTISE_PREFIX = "document.committeeList[0].committeeMemberships[%1$s].areasOfExpertise.inactive";
    private static final String COMMITTEE_ID_FIELD = "document.committeeList[0].committeeId";
    private static final String COMMITTEE_NAME_FIELD = "document.committeeList[0].committeeName";
    private static final String COMMITTEE_HOME_UNIT_NUMBER_FIELD = "document.committeeList[0].homeUnitNumber";
    private static final String REVIEW_TYPE_ERROR_PROPERTY_NAME = "document.committeeList[0].reviewTypeCode";
    
    private static final String INACTIVE_RESEARCH_AREAS_PREFIX = "document.committeeList[0].committeeResearchAreas.inactive";

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        
        retval &= super.processCustomRouteDocumentBusinessRules(document);
        
        return retval;
    }

    @Override
    public boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean valid = true;
        
        valid &= validateCommitteeId((CommitteeDocumentBase) document);
        valid &= validateUniqueCommitteeId((CommitteeDocumentBase) document);
        valid &= validateUniqueCommitteeName((CommitteeDocumentBase) document);
        valid &= validateHomeUnit((CommitteeDocumentBase) document);
        valid &= validateCommitteeTypeSpecificData((CommitteeDocumentBase) document);
        valid &= validateCommitteeMemberships((CommitteeDocumentBase) document);
        valid &= processScheduleRules((CommitteeDocumentBase) document);
        
        return valid;
    }
    
    
    

    /**
     * This method will validate the committee data w.r.t constraints that are specific to committee type that was chosen.
     * Currently these constraints are 1. (for IRB only) all research areas that are chosen must be active, and
     * 2. the proper review type corresponding to the committee type is chosen.
     * @param document
     * @return
     */
    private boolean validateCommitteeTypeSpecificData(CommitteeDocumentBase document) {
        boolean valid = true;
        // delegate actual validation logic to the business logic wrapper
        valid &= validateCommitteeResearchAreas(document.getCommittee());
        // delegate actual validation logic to the business logic wrapper
        valid &= validateReviewType(document.getCommittee());
        return valid;
    }
    
    
    
    /**
     * Verify that the committee id is not the DEFAULT_CORRESPONDENCE_TEMPLATE constant.  
     * This value is reserved for the default protocol correspondence template.
     * @param document CommitteeBase Document
     * @return true if valid; otherwise false
     */
    private boolean validateCommitteeId(CommitteeDocumentBase document) {
        CommitteeBase committee = document.getCommittee();
        if (StringUtils.equalsIgnoreCase(committee.getCommitteeId(), Constants.DEFAULT_CORRESPONDENCE_TEMPLATE)) {
            reportError(COMMITTEE_ID_FIELD, KeyConstants.ERROR_COMMITTEE_INVALID_ID);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Verify that we are not saving a committee with a duplicate CommitteeBase ID.
     * In other words, each committee must have a unique CommitteeBase ID.
     * @param document CommitteeBase Document
     * @return true if valid; otherwise false
     */
    private boolean validateUniqueCommitteeId(CommitteeDocumentBase document) {

        CommitteeBase committee = document.getCommittee();
        boolean valid = true;
        if (committee.getSequenceNumber() == 1 && (document.getDocumentHeader().getWorkflowDocument().isInitiated() || document.getDocumentHeader().getWorkflowDocument().isSaved())) {
            if (getCommitteeIds(document.getDocumentNumber()).contains(committee.getCommitteeId())) {
                valid = false;
            } else {
                // TODO : when committeeId & docstatuscode are populated properly, then the following is not needed.
                try {
                    for (CommitteeDocumentBase workflowCommitteeDocument : getCommitteesDocumentsFromWorkflow(document.getDocumentNumber())) {

                        CommitteeBase workflowCommittee = workflowCommitteeDocument.getCommittee();
                        LOG.info("get doc content for doc " + workflowCommitteeDocument.getDocumentNumber());
                        // There is no conflict if we are only modifying the same committee.

                        //if (!StringUtils.equals(workflowCommitteeDocument.getDocumentNumber(), document.getDocumentNumber())) {

                            // We have a conflict if we find a different committee in the database
                            // and it has the same ID as the committee we are trying to save
                            // while it's not a older version (lower sequence number) of this committee.

                            if (StringUtils.equals(workflowCommittee.getCommitteeId(), committee.getCommitteeId())
                                    && (workflowCommittee.getSequenceNumber() >= committee.getSequenceNumber())) {
                                valid = false;
                            }
                        //}
                    }
                }
                catch (WorkflowException e) {
                    LOG.info(e.getMessage());
                }
            }
        }
        if (!valid) {
            reportError(COMMITTEE_ID_FIELD, KeyConstants.ERROR_COMMITTEE_DUPLICATE_ID);            
        }
        return valid;
    }
    
    private List<CommitteeDocumentBase> getCommitteesDocumentsFromWorkflow(String docNumber) throws WorkflowException {

        List<CommitteeDocumentBase> documents = (List<CommitteeDocumentBase>) KcServiceLocator.getService(BusinessObjectService.class).findAll(getCommitteeDocumentBOClassHook());
        List<CommitteeDocumentBase> result = new ArrayList<CommitteeDocumentBase>();
        for (CommitteeDocumentBase commDoc : documents) {
            // documents that have not been approved
            if ((commDoc.getCommitteeList() == null || commDoc.getCommitteeList().size() == 0) && StringUtils.isBlank(commDoc.getCommitteeId()) && !StringUtils.equals(commDoc.getDocumentNumber(), docNumber)) {
                // Need this step to retrieve workflow document
    
                CommitteeDocumentBase workflowCommitteeDoc = (CommitteeDocumentBase) KcServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(commDoc.getDocumentNumber());
                // Get XML of workflow document
                String content = KcServiceLocator.getService(RouteHeaderService.class).getContent(
                        workflowCommitteeDoc.getDocumentHeader().getWorkflowDocument().getDocumentId()).getDocumentContent();
    
                // Create committee from XML and add to the document
                workflowCommitteeDoc.getCommitteeList().add(populateCommitteeFromXmlDocumentContents(content));
                if (!workflowCommitteeDoc.getDocumentHeader().getWorkflowDocument().isCanceled()) {
                    result.add(workflowCommitteeDoc);
                }
            }
        }
        return result;
    }
    
    protected abstract Class<? extends CommitteeDocumentBase> getCommitteeDocumentBOClassHook();

    /*
     * get a list of committeeIds that are in approved or saved committee docs.
     */
    private List<String> getCommitteeIds(String docNumber) {

        List<String> result = new ArrayList<String>();
        List<CommitteeDocumentBase> committeeDocss = (List<CommitteeDocumentBase>) KcServiceLocator.getService(BusinessObjectService.class).findAll(getCommitteeDocumentBOClassHook());
        for (CommitteeDocumentBase committeeDoc : committeeDocss) {
            if (StringUtils.isNotBlank(committeeDoc.getCommitteeId()) && !result.contains(committeeDoc.getCommitteeId())
                    && StringUtils.isNotBlank(committeeDoc.getDocStatusCode()) && !committeeDoc.getDocStatusCode().equals(KewApiConstants.ROUTE_HEADER_CANCEL_CD)
                    && !StringUtils.equals(committeeDoc.getDocumentNumber(), docNumber)) {
                result.add(committeeDoc.getCommitteeId());
            }
        }
        return result;
    }

    /*
     * Create a CommitteeBase object and populate it from the xml.
     */
    private CommitteeBase populateCommitteeFromXmlDocumentContents(String xmlDocumentContents) {
        CommitteeBase committee = null;
        if (!StringUtils.isEmpty(xmlDocumentContents)) {
            
            committee = (CommitteeBase) getBusinessObjectFromXML(xmlDocumentContents, getCommitteeBOClassHook().getName());
            
        }
        return committee;
    }
    
    protected abstract Class<? extends CommitteeBase> getCommitteeBOClassHook();

    /**
     * Retrieves substring of document contents from maintainable tag name. Then use xml service to translate xml into a business
     * object.
     */
    private PersistableBusinessObject getBusinessObjectFromXML(String xmlDocumentContents, String objectTagName) {
        String objXml = StringUtils.substringBetween(xmlDocumentContents, "<" + objectTagName + ">", "</" + objectTagName + ">");
        objXml = "<" + objectTagName + ">" + objXml + "</" + objectTagName + ">";
        PersistableBusinessObject businessObject = (PersistableBusinessObject) KRADServiceLocator.getXmlObjectSerializerService().fromXml(objXml);
        return businessObject;
    }

    /**
     * Verify that we are not saving a committee with a duplicate CommitteeBase Name.
     * In other words, each committee must have a unique CommitteeBase Name.
     * @param document CommitteeBase Document
     * @return true if valid; otherwise false
     */
    private boolean validateUniqueCommitteeName(CommitteeDocumentBase document) {
        CommitteeBase committee = document.getCommittee();
        CommitteeIdValuesFinderBase committeeIdValuesFinder = getNewCommitteeIdValuesFinderInstanceHook();
        List<KeyValue> committeeIdNamePairList = committeeIdValuesFinder.getKeyValues();
        for (KeyValue committeeIdNamePair : committeeIdNamePairList) {
            if (StringUtils.equalsIgnoreCase(committeeIdNamePair.getValue(), committee.getCommitteeName()) 
                    && StringUtils.isNotBlank((String) committeeIdNamePair.getKey()) 
                    && !StringUtils.equalsIgnoreCase((String) committeeIdNamePair.getKey(), committee.getCommitteeId())) {
                reportError(COMMITTEE_NAME_FIELD, KeyConstants.ERROR_COMMITTEE_DUPLICATE_NAME);            
                return false;
            }
        }
        
        return true;
    }

    protected abstract CommitteeIdValuesFinderBase getNewCommitteeIdValuesFinderInstanceHook();
    

    /**
     * Verify that the unit number if is valid.  We can ignore a blank
     * home unit number since it is a required field and that business logic
     * will flag a blank value as invalid.
     * @param document the CommitteeBase document
     * @return true if valid; otherwise false
     */
    private boolean validateHomeUnit(CommitteeDocumentBase document) {
        
        boolean valid = true;
        
        String homeUnitNumber = document.getCommittee().getHomeUnitNumber();
        if (!StringUtils.isBlank(homeUnitNumber)) {
            UnitService unitService = KcServiceLocator.getService(UnitService.class);
            Unit homeUnit = unitService.getUnit(homeUnitNumber);
            if (homeUnit == null) {
                valid = false;
                reportError(COMMITTEE_HOME_UNIT_NUMBER_FIELD, KeyConstants.ERROR_INVALID_UNIT, homeUnitNumber);
            }
        }
        
        return valid;
    }
    
    private boolean validateCommitteeMemberships(CommitteeDocumentBase committeeDocument) {
        boolean isValid = true;
        List<CommitteeMembershipBase> committeeMemberships = committeeDocument.getCommittee().getCommitteeMemberships(); 
        
        for(CommitteeMembershipBase committeeMembership : committeeMemberships) {
            int membershipIndex = committeeMemberships.indexOf(committeeMembership); 
            isValid &= isValidTermStartEndDates(committeeMembership, membershipIndex);
            isValid &= isValidRoles(committeeMembership, membershipIndex);
            isValid &= hasExpertise(committeeMembership, membershipIndex);
            // To keep the errors more comprehensible the role overlap check is done after other errors are resolved
            if (isValid) {
                isValid &= hasNoTermOverlap(committeeMemberships, committeeMembership, membershipIndex);
            }
            isValid &= checkResearchAreasForCommitteeMember(committeeMembership, membershipIndex);
        }
        return isValid;
    }
    
    /**
     * Check that the person does not have other entries whose term overlap.
     * (A member may not have the same role for overlapping time periods.)
     *
     * Checks are done only against records that are ahead of this one since these
     * have passes validations and therefore have valid term dates.
     * This method also displays the appropriate error message.
     * 
     * @param committeeMemberships - the committee memberships of the current committee
     * @param committeeMembership - the committee membership which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @return <code>true</code> if the role does not overlap with another role, <code>false</code> otherwise
     */
    private boolean hasNoTermOverlap(List<CommitteeMembershipBase> committeeMemberships, CommitteeMembershipBase committeeMembership, int membershipIndex) {
        boolean isValid = true;

        for (int i=0; i < membershipIndex; i++) {
            CommitteeMembershipBase tmpMember = committeeMemberships.get(i);
            if (tmpMember.isSamePerson(committeeMembership) 
                    && committeeMembership.getTermStartDate() != null && committeeMembership.getTermEndDate() != null
                    && tmpMember.getTermStartDate() != null && tmpMember.getTermEndDate() != null) {
                if (isWithinPeriod(committeeMembership.getTermStartDate(), tmpMember.getTermStartDate(), tmpMember.getTermEndDate())) {
                    isValid = false;
                    reportError(String.format(PROPERTY_NAME_TERM_START_DATE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_DUPLICATE,
                            tmpMember.getFormattedTermStartDate(), tmpMember.getFormattedTermEndDate());
                } else if (isWithinPeriod(committeeMembership.getTermEndDate(), tmpMember.getTermStartDate(), tmpMember.getTermEndDate())) {
                    isValid = false;
                    reportError(String.format(PROPERTY_NAME_TERM_END_DATE, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_DUPLICATE,
                            tmpMember.getFormattedTermStartDate(), tmpMember.getFormattedTermEndDate());
                } 
            }
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
    private boolean isValidTermStartEndDates(CommitteeMembershipBase committeeMembership, int membershipIndex) {
        boolean isValid = true;
        
        if (committeeMembership.getTermStartDate() != null && committeeMembership.getTermEndDate() != null 
                && committeeMembership.getTermEndDate().before(committeeMembership.getTermStartDate())) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_TERM_END_DATE, membershipIndex), 
                    KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_TERM_END_DATE_BEFORE_TERM_START_DATE);
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
    private boolean isValidRoles(CommitteeMembershipBase committeeMembership, int membershipIndex) {
        boolean isValid = true;
        List<CommitteeMembershipRole> membershipRoles = committeeMembership.getMembershipRoles();

        isValid &= hasRoles(committeeMembership, membershipIndex);
        
        for (CommitteeMembershipRole membershipRole : membershipRoles) {
            int roleIndex = membershipRoles.indexOf(membershipRole);
            isValid &= isValidRoleStartEndDates(membershipRole, membershipIndex, roleIndex);
            isValid &= roleDatesWithinTermDates(committeeMembership, membershipRole, membershipIndex, roleIndex);
            // To keep the errors more comprehensible the role overlap check is done after other errors are resolved
            if (isValid) {
                isValid &= hasNoRoleOverlap(committeeMembership, membershipRole, membershipIndex, roleIndex);
            }
        }
        
        return isValid;
    }

    /**
     * Verify that the committee membership has at least one role assigned.
     *  
     * This method also displays the appropriate error message.
     * 
     * @param committeeMembership - the committeeMembership which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @return <code>true</code> when the committee membership has at least one role assigned, <code>false</code> otherwise
     */
    private boolean hasRoles(CommitteeMembershipBase committeeMembership, int membershipIndex) {
        boolean hasExpertise = true;

        if (committeeMembership.getMembershipRoles().isEmpty()) {
            hasExpertise = false;
            reportError(String.format(PROPERTY_NAME_ROLE_CODE_ADD, membershipIndex), KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_MISSING);
        }
                
        return hasExpertise;
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
        
        if (membershipRole.getStartDate() != null && membershipRole.getEndDate() != null 
                && membershipRole.getEndDate().before(membershipRole.getStartDate())) {
            isValid = false;
            reportError(String.format(PROPERTY_NAME_ROLE_END_DATE, membershipIndex, roleIndex), 
                        KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_BEFORE_ROLE_START_DATE);
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
    private boolean roleDatesWithinTermDates(CommitteeMembershipBase committeeMembership, CommitteeMembershipRole membershipRole,
            int membershipIndex, int roleIndex) {
        boolean isValid = true;
        
        if ((committeeMembership.getTermStartDate() != null) && (committeeMembership.getTermEndDate() != null) 
                && (membershipRole.getStartDate() != null) && (membershipRole.getEndDate() != null)) {
            if (hasDateOutsideCommitteeMembershipTerm(committeeMembership, membershipRole.getStartDate())) {
                isValid = false;
                reportError(String.format(PROPERTY_NAME_ROLE_START_DATE, membershipIndex, roleIndex), 
                        KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_OUTSIDE_TERM);
            }
            if (hasDateOutsideCommitteeMembershipTerm(committeeMembership, membershipRole.getEndDate())) {
                isValid = false;
                reportError(String.format(PROPERTY_NAME_ROLE_END_DATE, membershipIndex, roleIndex), 
                        KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_OUTSIDE_TERM);
            }
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
     * Check that the role does not have other entries whose time periods overlap.
     * (A member may not have the same role for overlapping time periods.)
     * 
     * This method also displays the appropriate error message.
     * 
     * @param committeeMembership - the committeeMembership of whom the membershipRole is to be validated
     * @param membershipRole - the membershipRole which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @param indexOf - the index position of the membershipRole
     * @return <code>true</code> if the role does not overlap with another role, <code>false</code> otherwise
     */
    private boolean hasNoRoleOverlap(CommitteeMembershipBase committeeMembership, CommitteeMembershipRole membershipRole,
            int membershipIndex, int roleIndex) {
        boolean isValid = true;

        for (CommitteeMembershipRole tmpRole : committeeMembership.getMembershipRoles()) {
            if (roleIndex != committeeMembership.getMembershipRoles().indexOf(tmpRole) 
                    && membershipRole.getMembershipRoleCode().equals(tmpRole.getMembershipRoleCode())) {
                if (isWithinPeriod(membershipRole.getStartDate(), tmpRole.getStartDate(), tmpRole.getEndDate()) 
                        || isWithinPeriod(membershipRole.getEndDate(), tmpRole.getStartDate(), tmpRole.getEndDate())) {
                    isValid = false;
                    reportError(String.format(PROPERTY_NAME_ROLE_CODE, membershipIndex, roleIndex), 
                            KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_DUPLICATE);
                } 
            }
        }
        
        return isValid;
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
    
    /**
     * Verify that the committee membership has at least one expertise assigned.
     *  
     * @param committeeMembership - the committeeMembership which contains the to be validated data
     * @param membershipIndex - the index position of the committeeMembership
     * @return <code>true</code> when the committee membership has at least one expertise assigned, <code>false</code> otherwise
     */
    private boolean hasExpertise(CommitteeMembershipBase committeeMembership, int membershipIndex) {
        boolean hasExpertise = true;

        if (committeeMembership.getMembershipExpertise().isEmpty()) {
            hasExpertise = false;
            reportError(String.format(PROPERTY_NAME_RESEARCH_AREA_CODE, membershipIndex), 
                    KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_MISSING);
        }
                
        return hasExpertise;
    }
    
    
    /**
     * This method will check if all the research areas that have been added to a committee member as 'area of expertise' are indeed active.
     * It is declared public because it will be invoked from the action class for committee members as well.
     * @param document
     * @return
     */
    public boolean checkResearchAreasForCommitteeMember(CommitteeMembershipBase committeeMember, int membershipIndex) {
        
        boolean inactiveFound = false;
        StringBuffer inactiveResearchAreaIndices = new StringBuffer();
        
        List<CommitteeMembershipExpertiseBase> cmes = committeeMember.getMembershipExpertise();
        // iterate over all the research areas for this committee member looking for inactive research areas
        if(CollectionUtils.isNotEmpty(cmes)) {
            int raIndex = 0;
            for (CommitteeMembershipExpertiseBase cme : cmes) {
                if(cme.getResearchArea() !=null && !(cme.getResearchArea().isActive())) {
                    inactiveFound = true;
                    inactiveResearchAreaIndices.append(raIndex).append(SEPERATOR);
                }
                raIndex++;
            }
        }
        // if we found any inactive research areas in the above loop, report as a single error key suffixed by the list of indices of the inactive areas
        if(inactiveFound) { 
            String committeeMemberInactiveAreasOfExpertiseErrorPropertyKey = 
                String.format(PROPERTY_NAME_INACTIVE_AREAS_OF_EXPERTISE_PREFIX, membershipIndex)+ SEPERATOR + inactiveResearchAreaIndices.toString();
            reportError(committeeMemberInactiveAreasOfExpertiseErrorPropertyKey, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_INACTIVE);
        }
        
        return !inactiveFound;
    }

    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        return new KcDocumentBaseAuditRule().processRunAuditBusinessRules(document);
    }
    
    @Override
    public boolean processAddCommitteeMembershipBusinessRules(AddCommitteeMembershipEvent addCommitteeMembershipEvent) {
        return new CommitteeMembershipRule().processAddCommitteeMembershipBusinessRules(addCommitteeMembershipEvent);
    }
    
    @Override
    public boolean processAddCommitteeMembershipRoleBusinessRules(AddCommitteeMembershipRoleEvent addCommitteeMembershipRoleEvent) {
        return new CommitteeMembershipRule().processAddCommitteeMembershipRoleBusinessRules(addCommitteeMembershipRoleEvent);
    }

    @Override
    public boolean processRules(KcDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }
    
    /*
     * A few schedules related rules.
     */
    private boolean processScheduleRules(CommitteeDocumentBase committeeDocument) {
        boolean retval = true;
        
        
        CommitteeScheduleTimeEvent scheduleTimeEvent = new CommitteeScheduleTimeEvent(Constants.EMPTY_STRING, committeeDocument, null, committeeDocument.getCommittee().getCommitteeSchedules(), ErrorType.HARDERROR);
        retval &= scheduleTimeEvent.getRule().processRules(scheduleTimeEvent);
        
        CommitteeScheduleDateConflictEvent scheduleDateConfliceEvent = new CommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING, committeeDocument, null, committeeDocument.getCommittee().getCommitteeSchedules(), ErrorType.HARDERROR);
        retval &= scheduleDateConfliceEvent.getRule().processRules(scheduleDateConfliceEvent);
        
        CommitteeScheduleDeadlineEvent scheduleDeadlineEvent = new CommitteeScheduleDeadlineEvent(Constants.EMPTY_STRING, committeeDocument, null, committeeDocument.getCommittee().getCommitteeSchedules(), ErrorType.HARDERROR);
        retval &= scheduleDeadlineEvent.getRule().processRules(scheduleDeadlineEvent);
        return retval;

    }
    
    
    /**
     * This method will check if all the research areas that have been added to the committee are indeed active.
     * It is declared public because it will be invoked from the action class for committee as well.
     * @param document
     * @return
     */
    public boolean validateCommitteeResearchAreas(CommitteeBase committee) {
        boolean inactiveFound = false;
        StringBuffer inactiveResearchAreaIndices = new StringBuffer();
        
        // iterate over all the research areas for the committee BO looking for inactive research areas
        List<CommitteeResearchAreaBase> cras = committee.getCommitteeResearchAreas();
        if(CollectionUtils.isNotEmpty(cras)) {
            int raIndex = 0;
            for (CommitteeResearchAreaBase cra : cras) {
                if(!(cra.getResearchAreas().isActive())) {
                    inactiveFound = true;
                    inactiveResearchAreaIndices.append(raIndex).append(SEPERATOR);
                }
                raIndex++;
            }
        }
        // if we found any inactive research areas in the above loop, report as a single error key suffixed by the list of indices of the inactive areas
        if(inactiveFound) { 
            String committeeResearchAreaInactiveErrorPropertyKey = INACTIVE_RESEARCH_AREAS_PREFIX + SEPERATOR + inactiveResearchAreaIndices.toString();
            KcServiceLocator.getService(ErrorReporter.class).reportError(committeeResearchAreaInactiveErrorPropertyKey, KeyConstants.ERROR_COMMITTEE_RESEARCH_AREA_INACTIVE);
        }
        
        return !inactiveFound;
    }
    
    // check that the review type corresponding to the committee type is non-null (not performing XOR check) 
    public boolean validateReviewType(CommitteeBase committee) {
        boolean valid = true;        
        if(StringUtils.isBlank(committee.getReviewTypeCode())) {
            // add error message
            KcServiceLocator.getService(ErrorReporter.class).reportError(REVIEW_TYPE_ERROR_PROPERTY_NAME, KeyConstants.ERROR_COMMITTEE_REVIEW_TYPE_REQUIRED);
            valid = false;        
        }
        return valid;
    }

}
