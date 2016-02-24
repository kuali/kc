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
package org.kuali.kra.iacuc.committee.meeting;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.meeting.*;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.document.authorization.IacucCommitteeScheduleTask;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondenceType;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;
import org.kuali.kra.protocol.correspondence.CorrespondenceTypeModuleIdConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucMeetingHelper extends MeetingHelperBase {


    private static final long serialVersionUID = -5657448017371644177L;

    private static final String NAMESPACE = "KC-UNT";
    
    public IacucMeetingHelper(MeetingFormBase form) {
        super((IacucMeetingForm)form);
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new IacucCommitteeScheduleAttachments();
    }

    @Override
    protected OtherPresentBeanBase getNewOtherPresentBeanInstanceHook() {
        return new IacucOtherPresentBean();
    }

    @Override
    protected CommScheduleActItemBase getNewCommScheduleActItemInstanceHook() {
        return new IacucCommScheduleActItem();
    }

    @Override
    protected CommitteeScheduleMinuteBase<?, ?> getNewCommitteeScheduleMinuteInstanceHook() {
        return new IacucCommitteeScheduleMinute();
    }

    @Override
    protected CommitteeScheduleBase<?, ?, ?, ?> getNewCommitteeScheduleInstanceHook() {
        return new IacucCommitteeSchedule();
    }

    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
    }

    @Override
    protected CommitteeScheduleTaskBase getNewCommitteeScheduleTaskInstanceHook(String taskName, CommitteeBase committee, CommitteeScheduleBase committeeSchedule) {
        return new IacucCommitteeScheduleTask(taskName, (IacucCommittee) committee, (IacucCommitteeSchedule) committeeSchedule);
    }
    
    public boolean isAdmin() {
        return getSystemAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IACUC_ADMINISTRATOR);
    }

    @Override
    protected void initPrintCorrespondence() {
        List<CorrespondencePrintOption> printOptions = new ArrayList<CorrespondencePrintOption>();
        Map<String, Object> values = new HashMap<String, Object>();
        List<IacucProtocolCorrespondenceType> correspondenceTypes = (List<IacucProtocolCorrespondenceType>)
                KcServiceLocator.getService(BusinessObjectService.class).findMatching(IacucProtocolCorrespondenceType.class,values);
        for(IacucProtocolCorrespondenceType correspondenceType : correspondenceTypes) {
            if(StringUtils.equals(correspondenceType.getModuleId(),CorrespondenceTypeModuleIdConstants.SCHEDULE.getCode())) {
                CorrespondencePrintOption printOption = new CorrespondencePrintOption();
                printOption.setDescription(correspondenceType.getDescription());
                printOption.setLabel(correspondenceType.getDescription());
                printOption.setCorrespondenceId(1L);
                printOption.setProtocolCorrespondenceTemplate(correspondenceType.getDefaultProtocolCorrespondenceTemplate());
                printOptions.add(printOption);
            }
        }
        setCorrespondencesToPrint(printOptions);
    }
    

}
