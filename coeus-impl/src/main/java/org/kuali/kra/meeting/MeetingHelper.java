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
package org.kuali.kra.meeting;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.meeting.*;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeScheduleTask;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;
import org.kuali.kra.protocol.correspondence.CorrespondenceTypeModuleIdConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingHelper extends MeetingHelperBase {


    private static final long serialVersionUID = -2119007932507558770L;
    
    private static final String NAMESPACE = "KC-UNT";

    public MeetingHelper(MeetingFormBase form) {
        super((MeetingForm)form);
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new CommitteeScheduleAttachments();
    }

    @Override
    protected OtherPresentBeanBase getNewOtherPresentBeanInstanceHook() {
        return new OtherPresentBean();
    }

    @Override
    protected CommScheduleActItemBase getNewCommScheduleActItemInstanceHook() {
        return new CommScheduleActItem();
    }

    @Override
    protected CommitteeScheduleMinuteBase<?, ?> getNewCommitteeScheduleMinuteInstanceHook() {
        return new CommitteeScheduleMinute();
    }

    @Override
    protected CommitteeScheduleBase<?, ?, ?, ?> getNewCommitteeScheduleInstanceHook() {
        return new CommitteeSchedule();
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, taskName, (Committee) committee) {};
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected CommitteeScheduleTaskBase getNewCommitteeScheduleTaskInstanceHook(String taskName, CommitteeBase committee, CommitteeScheduleBase committeeSchedule) {
        return new CommitteeScheduleTask(taskName, (Committee) committee, (CommitteeSchedule) committeeSchedule);
    }
    
    public boolean isAdmin() {
        return getSystemAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), NAMESPACE, RoleConstants.IRB_ADMINISTRATOR);
    }

    @Override
    protected void initPrintCorrespondence() {
        //Could be moved to MeetingHelperBase
        List<CorrespondencePrintOption> printOptions = new ArrayList<CorrespondencePrintOption>();
        Map<String, Object> values = new HashMap<String, Object>();
        List<ProtocolCorrespondenceType> correspondenceTypes = (List<ProtocolCorrespondenceType>)
                KcServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolCorrespondenceType.class,values);
        for(ProtocolCorrespondenceType correspondenceType : correspondenceTypes) {
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
