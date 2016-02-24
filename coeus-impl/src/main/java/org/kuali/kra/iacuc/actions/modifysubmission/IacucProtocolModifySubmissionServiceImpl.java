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
package org.kuali.kra.iacuc.actions.modifysubmission;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.processBillable.IacucProtocolProcessBillableService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.iacuc.notification.IacucProtocolAssignReviewerNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRequestBean;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewTypeBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IacucProtocolModifySubmissionServiceImpl extends IacucProtocolProcessBillableService implements IacucProtocolModifySubmissionService{

    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private IacucProtocolOnlineReviewService protocolOnlineReviewService;
    private KcNotificationService kcNotificationService;
    private CommitteeServiceBase committeeService;
    private static final String NEXT_ACTION_ID_KEY = "actionId";


    public void modifySubmission(ProtocolDocumentBase protocolDocument, IacucProtocolModifySubmissionBean bean, List<ProtocolReviewerBeanBase> beans) throws Exception {
        ProtocolSubmissionBase submission = protocolDocument.getProtocol().getProtocolSubmission();
        submission.setSubmissionTypeCode(bean.getSubmissionTypeCode());
        submission.setSubmissionTypeQualifierCode(bean.getSubmissionQualifierTypeCode());
        setSchedule(submission, bean.getCommitteeId(), bean.getScheduleId());
        processBillable(protocolDocument.getProtocol(), bean.isBillable());
        
        String existingReviewType = submission.getProtocolReviewTypeCode();
        String newReviewType = bean.getProtocolReviewTypeCode();
        
       
        if (!existingReviewType.equals(newReviewType)) {
            //if new Review Type is different than old review type, get the proper review type from DB and add to submission, 
            //and update code
            proccessNewReviewType(submission, newReviewType);
        }
        assignReviewers(submission, beans, bean);
        addNewAction((IacucProtocol)protocolDocument.getProtocol(), bean, submission.getSubmissionStatusCode());
        
        // if a committee has not been assigned, then submission status is 'pending' else its 'submitted to committee", 
        // TODO this should perhaps be encoded as a drools rule
        if(submission.getCommittee() != null) {
            submission.setSubmissionStatusCode(IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        }
        else {
            submission.setSubmissionStatusCode(IacucProtocolSubmissionStatus.PENDING);
        }

        documentService.saveDocument(protocolDocument);
    }
    
    private void addNewAction(IacucProtocol protocol, IacucProtocolModifySubmissionBean actionBean, String prevSubmissionStatusCode) {
        ProtocolActionBase lastAction = protocol.getLastProtocolAction();
        ProtocolActionBase newAction = new IacucProtocolAction();
        // deep copy will replaced the last action with the new one after save
        newAction.setActionId(protocol.getNextValue(NEXT_ACTION_ID_KEY));
        newAction.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setProtocolActionTypeCode(IacucProtocolActionType.MODIFY_PROTOCOL_SUBMISSION);
        newAction.setSubmissionIdFk(lastAction.getSubmissionIdFk());
        newAction.setSubmissionNumber(lastAction.getSubmissionNumber());
        newAction.setProtocolNumber(protocol.getProtocolNumber());
        newAction.setProtocolId(protocol.getProtocolId());
        newAction.setSequenceNumber(protocol.getSequenceNumber());
        newAction.setPrevSubmissionStatusCode(prevSubmissionStatusCode);
        newAction.setComments("ModifyProtocolSubmission");
        protocol.getProtocolActions().add(newAction);

    }
    
    public void setCommitteeService(CommitteeServiceBase committeeService) {
        this.committeeService = committeeService;
    }
    
    public void setSchedule(ProtocolSubmissionBase submission, String committeeId, String scheduleId) {
        if (!setCommittee(submission, committeeId)) {
            submission.setScheduleId(null);
            submission.setScheduleIdFk(null);
            submission.setCommitteeSchedule(null);
        }
        else {
            CommitteeScheduleBase schedule = committeeService.getCommitteeSchedule(submission.getCommittee(), scheduleId);
            if (schedule == null) {
                submission.setScheduleId(null);
                submission.setScheduleIdFk(null);
                submission.setCommitteeSchedule(null);
                updateDefaultSchedule(submission);
            }
            else {
                submission.setScheduleId(schedule.getScheduleId());
                submission.setScheduleIdFk(schedule.getId());
                submission.setCommitteeSchedule(schedule);
                updateDefaultSchedule(submission);
            }
        }
    }
    
    protected void updateDefaultSchedule(ProtocolSubmissionBase submission) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolIdFk", submission.getProtocolId().toString());
        List<IacucCommitteeScheduleMinute> minutes = (List<IacucCommitteeScheduleMinute>) businessObjectService.findMatching(IacucCommitteeScheduleMinute.class, fieldValues);
        if (!minutes.isEmpty()) {
            for (CommitteeScheduleMinuteBase minute : minutes) {
                if (submission.getScheduleIdFk() == null) {
                    minute.setScheduleIdFk(CommitteeScheduleBase.DEFAULT_SCHEDULE_ID);
                } else {
                    minute.setScheduleIdFk(submission.getScheduleIdFk());
                }
            }
            businessObjectService.save(minutes);
        }
    }
    
    public boolean setCommittee(ProtocolSubmissionBase submission, String committeeId) {
        CommitteeBase committee = committeeService.getCommitteeById(committeeId);
        if (committee == null) {
            submission.setCommitteeId(null);
            submission.setCommitteeIdFk(null);
            submission.setCommittee(null);
            return false;
        }
        else {
            submission.setCommitteeId(committee.getCommitteeId());
            submission.setCommitteeIdFk(committee.getId());
            submission.setCommittee(committee);
            return true;
        }
    }
    
    protected void removeReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase protocolReviewBean,String annotation) {
        //We need to send the notification prior to the online review being removed in order to satisfy the kim role recipients requirements
        ProtocolOnlineReviewDocumentBase onlineReviewDocument = 
            protocolOnlineReviewService.getProtocolOnlineReviewDocument(protocolReviewBean.getPersonId(), protocolReviewBean.getNonEmployeeFlag(), protocolSubmission);
        if (onlineReviewDocument != null) {   
            ProtocolBase protocol = protocolSubmission.getProtocol();
            ProtocolOnlineReviewBase protocolOnlineReview = onlineReviewDocument.getProtocolOnlineReview();
            protocolReviewBean.setNotificationRequestBean(new IacucProtocolNotificationRequestBean((IacucProtocol)protocol, (IacucProtocolOnlineReview) protocolOnlineReview, IacucProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", null, null));
            protocolReviewBean.setActionFlag(ProtocolReviewerBeanBase.REMOVE);
            IacucProtocolAssignReviewerNotificationRenderer renderer = new IacucProtocolAssignReviewerNotificationRenderer((IacucProtocol) protocol, "removed");
            IacucProtocolNotificationContext context = new IacucProtocolNotificationContext((IacucProtocol)protocol, (IacucProtocolOnlineReview)protocolOnlineReview, IacucProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", renderer);
            getKcNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
        }
        
        protocolOnlineReviewService.removeOnlineReviewDocument(protocolReviewBean.getPersonId(), protocolReviewBean.getNonEmployeeFlag(), protocolSubmission, annotation);
    }
    
    public void assignReviewers(ProtocolSubmissionBase protocolSubmission, List<ProtocolReviewerBeanBase> protocolReviewerBeans, IacucProtocolModifySubmissionBean protocolModifySubmissionBean) throws Exception  {
        if (protocolSubmission != null) {
            for (ProtocolReviewerBeanBase bean : protocolReviewerBeans) {
                if (StringUtils.isNotBlank(bean.getReviewerTypeCode())) {
                    if (!protocolOnlineReviewService.isProtocolReviewer(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission)) {
                        
                        createReviewer(protocolSubmission, bean, protocolModifySubmissionBean);
                    } else {
                        updateReviewer(protocolSubmission, bean, protocolModifySubmissionBean);
                        bean.setActionFlag(ProtocolReviewerBeanBase.UPDATE);
                    }
                } else {
                    //need to check if this person is currently a reviewer...
                    if (protocolOnlineReviewService.isProtocolReviewer(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission)) {
                        removeReviewer(protocolSubmission,bean,"REVIEW REMOVED FROM ASSIGN REVIEWERS ACTION.");
                    }
                }
            }
           
            businessObjectService.save(protocolSubmission); 
        }
    }
    
    protected void updateReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase protocolReviewerBean, IacucProtocolModifySubmissionBean protocolModifySubmissionBean) {
        ProtocolReviewer reviewer = protocolOnlineReviewService.getProtocolReviewer(protocolReviewerBean.getPersonId(), protocolReviewerBean.getNonEmployeeFlag(), protocolSubmission);
        reviewer.setReviewerTypeCode(protocolReviewerBean.getReviewerTypeCode());
        IacucProtocolOnlineReview iacucProtocolOnlineReview = null;
        for (ProtocolOnlineReviewBase onlineReview : reviewer.getProtocolOnlineReviews()) {
            if (onlineReview.getSubmissionIdFk().equals(protocolSubmission.getSubmissionId())) {
                iacucProtocolOnlineReview = (IacucProtocolOnlineReview)onlineReview;
            }
        }
        if ((iacucProtocolOnlineReview != null) && !DateUtils.isSameDay(iacucProtocolOnlineReview.getDeterminationReviewDateDue(), protocolModifySubmissionBean.getDueDate())) {
            iacucProtocolOnlineReview.setDeterminationReviewDateDue(protocolModifySubmissionBean.getDueDate());
            businessObjectService.save(iacucProtocolOnlineReview);
        }
        businessObjectService.save(reviewer);
    }
    
    protected void createReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase protocolReviewerBean, IacucProtocolModifySubmissionBean protocolModifySubmissionBean) throws WorkflowException {
        String principalId = protocolReviewerBean.getPersonId();
        boolean nonEmployeeFlag = protocolReviewerBean.getNonEmployeeFlag();
        String reviewerTypeCode = protocolReviewerBean.getReviewerTypeCode();
        ProtocolReviewer reviewer = protocolOnlineReviewService.createProtocolReviewer(principalId, nonEmployeeFlag, reviewerTypeCode, protocolSubmission);
        ProtocolPersonBase protocolPerson = protocolSubmission.getProtocol().getPrincipalInvestigator();
        String protocolNumber = protocolSubmission.getProtocol().getProtocolNumber();
        String description = protocolOnlineReviewService.getProtocolOnlineReviewDocumentDescription(protocolNumber, protocolPerson.getLastName());
        String explanation = Constants.EMPTY_STRING;
        String organizationDocumentNumber = Constants.EMPTY_STRING;
        String routeAnnotation = "Online Review Requested by PI during protocol submission.";
        boolean initialApproval = false;
        Date dateRequested = null;
        Date dateDue = null;
        String sessionPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        ProtocolOnlineReviewDocumentBase document =  protocolOnlineReviewService.createProtocolOnlineReviewDocument(protocolSubmission, reviewer, 
              description, explanation, organizationDocumentNumber, dateRequested, dateDue, sessionPrincipalId);
        protocolSubmission.getProtocolOnlineReviews().add(document.getProtocolOnlineReview());
        documentService.routeDocument(document, routeAnnotation, new ArrayList<AdHocRouteRecipient>());
        
        //send notification now that the online review has been created.
        IacucProtocol protocol = (IacucProtocol) protocolSubmission.getProtocol();
        ProtocolOnlineReviewBase protocolOnlineReview = document.getProtocolOnlineReview();
        protocolReviewerBean.setNotificationRequestBean(new IacucProtocolNotificationRequestBean(protocol, (IacucProtocolOnlineReview) protocolOnlineReview, IacucProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", null, null));
        protocolReviewerBean.setActionFlag(ProtocolReviewerBeanBase.CREATE);
        IacucProtocolAssignReviewerNotificationRenderer renderer = new IacucProtocolAssignReviewerNotificationRenderer(protocol, "added");
        IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, (IacucProtocolOnlineReview) protocolOnlineReview, IacucProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", renderer);
        getKcNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
        ((IacucProtocolOnlineReview) protocolOnlineReview).setDeterminationReviewDateDue(protocolModifySubmissionBean.getDueDate());
    }
    
    protected void proccessNewReviewType(ProtocolSubmissionBase submission, String newReviewType) {
        Map fieldValues = new HashMap();
        fieldValues.put("PROTOCOL_REVIEW_TYPE_CODE", newReviewType);
        ProtocolReviewTypeBase newType = (ProtocolReviewTypeBase) this.businessObjectService.findByPrimaryKey(IacucProtocolReviewType.class, fieldValues);
        submission.setProtocolReviewType(newType);
        submission.setProtocolReviewTypeCode(newType.getReviewTypeCode());
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
   
    
    public void setKcNotificationService(KcNotificationService notificationService) {
        this.kcNotificationService = notificationService;
    }
   
    public KcNotificationService getKcNotificationService() {
        return kcNotificationService;
    }
   
    /**
     * Set the ProtocolBase Online Review Service.
     * @param protocolOnlineReviewService protocolOnlineReviewService.
     */
    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = (IacucProtocolOnlineReviewService) protocolOnlineReviewService;
    }

}
