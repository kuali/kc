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
package org.kuali.kra.protocol.actions.submit;

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.protocol.drools.brms.FactBean;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDao;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

/*
 * This class is for the condition attributes of of the protocol action.
 * i.e., the condition of protocol status, submissionstatus, action type code etc.
 */
public abstract class ProtocolActionMappingBase implements FactBean {
    
    protected static final String PROTOCOL_NUMBER = "protocolNumber";
    protected static final String SEQUENCE_NUMBER = "sequenceNumber";
    protected static final String SUBMISSION_NUMBER = "submissionNumber";
    
    protected BusinessObjectService businessObjectService;
    
    protected ProtocolDao<? extends ProtocolBase> dao;
    
    protected String submissionStatusCode;
    
    protected String submissionTypeCode;
    
    protected String protocolReviewTypeCode;
    
    protected String actionTypeCode;
    
    protected String protocolStatusCode;
    
    protected String scheduleId;
    
    protected ProtocolBase protocol;
    
    protected Integer submissionNumber;

    protected boolean allowed = false;

    public ProtocolActionMappingBase(String actionTypeCode, String submissionStatusCode, String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId, Integer submissionNumber) {
        super();
        this.actionTypeCode = actionTypeCode;
        this.submissionStatusCode = submissionStatusCode;        
        this.submissionTypeCode = submissionTypeCode;
        this.protocolReviewTypeCode = protocolReviewTypeCode;
        this.protocolStatusCode = protocolStatusCode;
        this.scheduleId = scheduleId;
        this.submissionNumber = submissionNumber;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setDao(ProtocolDao<? extends ProtocolBase> dao) {
        this.dao = dao;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }
    
    public String getProtocolSubmissionScheduleId() {
        // TODO : should not need to retrieve from DB because protocol.getProtocolSubmission() is
        // the same as the one retrieved.  The positiveFieldValues are the pk in coeus.
        // this is used in rule for null check.
        
        if (protocol.getProtocolSubmission() == null) {
            return null;
        } else {
            return protocol.getProtocolSubmission().getScheduleId();
        }        
    }
    
    /**
     * 
     * This method if this submission has committee schedule minutes
     * @return
     */
    public boolean getMinutesCount() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        fieldValues.put(SUBMISSION_NUMBER, protocol.getProtocolSubmission().getSubmissionNumber());
        
        return businessObjectService.countMatching(getCommitteeScheduleMinuteBOClassHook(), fieldValues) > 0;
    }
    
    protected abstract Class<? extends CommitteeScheduleMinuteBase> getCommitteeScheduleMinuteBOClassHook();
    
    public abstract boolean getSubmissionCount();

    public abstract boolean getSubmissionCountCond2();
    
    /**
     * 
     * This method Check if there are any pending amendmends/renewals for this protocols
     * @return
     */
    public boolean getSubmissionCountCond3() {
        return dao.getProtocolSubmissionCountFromProtocol(protocol.getProtocolNumber());
    }

    
    public abstract boolean getSubmissionCountCond4();
    
    public abstract boolean getSubmissionCountCond5();

    public abstract boolean getSubmissionCountForWithdraw(); 
    
    /**
     * This method finds number of reviewers tied to protocol submission. Implementation in lieu of following query 
     *           SELECT count(OSP$PROTOCOL_REVIEWERS.PERSON_ID)
     *           INTO li_PersonCnt
     *           FROM OSP$PROTOCOL_REVIEWERS
     *           WHERE OSP$PROTOCOL_REVIEWERS.PROTOCOL_NUMBER = AS_PROTOCOL_NUMBER
     *           AND OSP$PROTOCOL_REVIEWERS.SUBMISSION_NUMBER = AS_SUBMISSION_NUMBER; 
     * @return
     */
    public boolean getProtocolReviewerCountCond1() {       
        return protocol.getProtocolSubmission().getProtocolReviewers().size() > 0;
    }
    
    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getSubmissionStatusCode() {
        return submissionStatusCode;
    }

    public void setSubmissionStatusCode(String submissionStatusCode) {
        this.submissionStatusCode = submissionStatusCode;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }
    
    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }

    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }    
    
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    /**
     * check if user is PI
     */
    public boolean isPrincipalInvestigator() {
        Person user = GlobalVariables.getUserSession().getPerson();
        boolean isPi = false;
        if (user.getPrincipalId().equals(protocol.getPrincipalInvestigator().getPersonId())) {
            isPi = true;
        }
        return isPi;
    }
}
