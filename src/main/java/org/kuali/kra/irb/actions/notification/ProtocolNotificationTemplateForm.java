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
package org.kuali.kra.irb.actions.notification;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class is the action form for notification template maintenance
 */
public class ProtocolNotificationTemplateForm extends KualiForm {

    private static final long serialVersionUID = 6043169784839779473L;

    private List<ProtocolNotificationTemplate> notificationTemplates;

    private boolean readOnly;

    public ProtocolNotificationTemplateForm() {
        super();
        this.readOnly = true;
        this.setNotificationTemplates(initNotificationTemplates());
    }


    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * 
     * This method to get the notification templates from db.
     * 
     * @return
     */
    public List<ProtocolNotificationTemplate> initNotificationTemplates() {
        List<ProtocolNotificationTemplate> templates = (List<ProtocolNotificationTemplate>) getBusinessObjectService().findAll(
                ProtocolNotificationTemplate.class);
        if (templates.isEmpty()) {
            // set up default data to start, so data can be populated
            templates = getDefaultTemplates();
        }
        return templates;
    }

    private List<ProtocolNotificationTemplate> getDefaultTemplates() {
        List<ProtocolNotificationTemplate> templates = new ArrayList<ProtocolNotificationTemplate>();
        templates.add(getTemplate(ProtocolActionType.WITHDRAWN, "ProtocolWithdrawNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.NOTIFY_IRB, "NotifyIrbNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.REQUEST_TO_CLOSE, "RequestToCloseNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.REQUEST_FOR_SUSPENSION, "RequestToSuspensionNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT, "OpenEnrollmentNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, "DataAnalysisNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, "CloseEnrollmentNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.IRB_ACKNOWLEDGEMENT, "IrbAcknowledgementNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.ABANDON_PROTOCOL, "AbandonProtocolNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.ASSIGN_TO_AGENDA, "AssignToAgendaReviewerNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.ASSIGN_REVIEWER, "AssignReviewerNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.REVIEW_COMPLETE, "ReviewCompleteNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.RENEWAL_REMINDER_GENERATED, "BatchCorrespondenceNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.REVIEW_REJECTED, "RejectReviewNotification.xsl"));
        templates.add(getTemplate(ProtocolActionType.FUNDING_SOURCE, "FundingSourceNotification.xsl"));

        return templates;
    }
    
    private ProtocolNotificationTemplate getTemplate(String actionTypeCode, String fileName) {
        ProtocolNotificationTemplate template = new ProtocolNotificationTemplate();
        template.setActionTypeCode(actionTypeCode);
        template.setFileName(fileName);
        template.setNotificationTemplate(getFileContent("/org/kuali/kra/irb/notification/stylesheet/"+fileName));
        template.refreshReferenceObject("protocolActionType");
        if (actionTypeCode.equals(ProtocolActionType.ASSIGN_REVIEWER)) {
            ProtocolActionType actionType = new ProtocolActionType();
            actionType.setProtocolActionTypeCode(actionTypeCode);
            actionType.setDescription("Assign Reviewer");
            template.setProtocolActionType(actionType);
        }
        if (actionTypeCode.equals(ProtocolActionType.REVIEW_COMPLETE)) {
            ProtocolActionType actionType = new ProtocolActionType();
            actionType.setProtocolActionTypeCode(actionTypeCode);
            actionType.setDescription("Review Complete");
            template.setProtocolActionType(actionType);
        }
        return template;

    }
    
    private byte[] getFileContent(String filePath) {
        try {
            InputStream inStream = this.getClass().getResourceAsStream(filePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = inStream.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); // no doubt here is 0
                // Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
            }

            return bos.toByteArray();

        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }


    public List<ProtocolNotificationTemplate> getNotificationTemplates() {
        return notificationTemplates;
    }


    public void setNotificationTemplates(List<ProtocolNotificationTemplate> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
    }

}