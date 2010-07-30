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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.web.struts.form.KualiForm;

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
        templates.add(getTemplate("303", "ProtocolWithdrawNotification.xsl"));
        templates.add(getTemplate("116", "NotifyIrbNotification.xsl"));
        templates.add(getTemplate("105", "RequestToCloseNotification.xsl"));
        templates.add(getTemplate("106", "RequestToSuspensionNotification.xsl"));
        templates.add(getTemplate("108", "OpenEnrollmentNotification.xsl"));
        templates.add(getTemplate("114", "DataAnalysisNotification.xsl"));
        templates.add(getTemplate("115", "CloseEnrollmentNotification.xsl"));

        return templates;
    }
    
    private ProtocolNotificationTemplate getTemplate(String actionTypeCode, String fileName) {
        ProtocolNotificationTemplate template = new ProtocolNotificationTemplate();
        template.setActionTypeCode(actionTypeCode);
        template.setFileName(fileName);
        template.setNotificationTemplate(getFileContent("/org/kuali/kra/printing/stylesheet/"+fileName));
        template.refreshReferenceObject("protocolActionType");
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