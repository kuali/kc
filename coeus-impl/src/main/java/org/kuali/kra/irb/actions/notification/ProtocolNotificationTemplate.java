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

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.irb.actions.ProtocolActionType;

/**
 * 
 * This class is bo of protocol notification template
 */
public class ProtocolNotificationTemplate extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer notificationTemplateId;

    private String actionTypeCode;

    private String fileName;

    private byte[] notificationTemplate;

    private ProtocolActionType protocolActionType;

    private transient FormFile templateFile;

    public ProtocolNotificationTemplate() {
        super();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }

    public FormFile getTemplateFile() {
        return templateFile;
    }

    public Integer getNotificationTemplateId() {
        return notificationTemplateId;
    }

    public void setNotificationTemplateId(Integer notificationTemplateId) {
        this.notificationTemplateId = notificationTemplateId;
    }

    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public byte[] getNotificationTemplate() {
        return notificationTemplate;
    }

    public void setNotificationTemplate(byte[] notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
    }

    public ProtocolActionType getProtocolActionType() {
        return protocolActionType;
    }

    public void setProtocolActionType(ProtocolActionType protocolActionType) {
        this.protocolActionType = protocolActionType;
    }
}
