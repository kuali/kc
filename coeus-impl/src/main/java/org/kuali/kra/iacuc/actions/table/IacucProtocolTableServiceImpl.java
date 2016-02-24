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
package org.kuali.kra.iacuc.actions.table;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class IacucProtocolTableServiceImpl implements IacucProtocolTableService {
    
    private ProtocolActionService protocolActionService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    protected ProtocolVersionService protocolVersionService;

    
    public DocumentService getDocumentService() {
        return documentService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    
    
    public ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }
    
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }


    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
        
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    
    
    @Override
    public CommitteeScheduleBase getNextScheduleForCommittee(CommitteeScheduleBase currentSchedule) {
        CommitteeScheduleBase retVal = null;
        if( (null != currentSchedule) && (null != currentSchedule.getCommitteeIdFk()) ) {
            currentSchedule.refreshReferenceObject("committee");
            CommitteeBase committee = currentSchedule.getParentCommittee();
            if((null != committee)) {
                List<CommitteeScheduleBase> schedules = committee.getCommitteeSchedules();
                if(null != schedules) {
                    // sort will use the schedule's comparison method which orders by date
                    Collections.sort(schedules);
                    // iterate through the schedules until we find the schedule corresponding to the current
                    for(CommitteeScheduleBase schedule:schedules) {
                        if (StringUtils.equals(schedule.getScheduleId(), currentSchedule.getScheduleId())) {
                            // found it, now check if next schedule exists, and if so get it
                            int indexOfSchedule = schedules.indexOf(schedule);                            
                            if( (indexOfSchedule + 1) < schedules.size() ) {
                                retVal = schedules.get(indexOfSchedule + 1);
                            }
                        }
                    }
                }
            }
        }
        return retVal;
    }
    
    
    
    // bump the submission to the next schedule (if any) for the same committee, and move the associated minutes (if any)
    private void bumpSubmissionToNextSchedule(IacucProtocolSubmission submission){
        CommitteeScheduleBase originalSchedule = submission.getCommitteeSchedule();
        CommitteeScheduleBase nextSchedule = getNextScheduleForCommittee(originalSchedule);
        if(null != nextSchedule) {
            // update submission's links to point to next schedule
            submission.setScheduleId(nextSchedule.getScheduleId());
            submission.setScheduleIdFk(nextSchedule.getId());
            submission.setCommitteeSchedule(nextSchedule);
        
            // the minutes if any for this protocol in the original schedule should be moved to next schedule 
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("protocolIdFk", submission.getProtocolId().toString());
            fieldValues.put("scheduleIdFk", originalSchedule.getId().toString());
            List<IacucCommitteeScheduleMinute> minutes = (List<IacucCommitteeScheduleMinute>) businessObjectService.findMatching(IacucCommitteeScheduleMinute.class, fieldValues);
            if (!minutes.isEmpty()) {
                // move the minutes to the next schedule and save it so as update the inverse FK links from the minutes
                nextSchedule.getCommitteeScheduleMinutes().addAll(minutes);
                getBusinessObjectService().save(nextSchedule);
            }
        }
    }
    

    @Override
    public IacucProtocolDocument tableProtocol(IacucProtocol protocol, IacucProtocolTableBean actionBean) throws Exception {
        IacucProtocolSubmission submission = (IacucProtocolSubmission) protocol.getProtocolSubmission();
        // bump to next schedule
        bumpSubmissionToNextSchedule(submission);
        // add a new protocol action for "tabled", update protocol status and save
        IacucProtocolAction protocolAction = new IacucProtocolAction(protocol, submission, IacucProtocolActionType.TABLED);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        getProtocolActionService().updateProtocolStatus(protocolAction, protocol);
        getDocumentService().saveDocument(protocol.getProtocolDocument());    
        return getNewProtocolDocument(protocol);
    }
        
    /**
     * This method is to create a new protocol document for the user to edit so they 
     * can re-submit at a later time.
     */
    private IacucProtocolDocument getNewProtocolDocument(IacucProtocol protocol) throws Exception {
        documentService.cancelDocument(protocol.getProtocolDocument(), "Protocol document cancelled - protocol has been returned for revisions.");        
        IacucProtocolDocument newProtocolDocument = (IacucProtocolDocument) getProtocolVersionService().versionProtocolDocument(protocol.getProtocolDocument());
        newProtocolDocument.getProtocol().setProtocolSubmission(null);
        newProtocolDocument.getProtocol().setApprovalDate(null);
        newProtocolDocument.getProtocol().setLastApprovalDate(null);
        newProtocolDocument.getProtocol().setExpirationDate(null);
        newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
        newProtocolDocument.getProtocol().refreshReferenceObject("protocolSubmission");
        documentService.saveDocument(newProtocolDocument);             
        return newProtocolDocument;
    }

    public ProtocolVersionService getProtocolVersionService() {
        return protocolVersionService;
    }
    
    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

}
