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
package org.kuali.kra.irb.actions.notification;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        return KcServiceLocator.getService(BusinessObjectService.class);
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
