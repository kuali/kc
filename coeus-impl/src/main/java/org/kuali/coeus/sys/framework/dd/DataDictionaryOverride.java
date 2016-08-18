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
package org.kuali.coeus.sys.framework.dd;


import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.api.model.Identifiable;
import org.kuali.coeus.sys.api.model.Inactivatable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.bo.PersistableAttachment;

public class DataDictionaryOverride extends KcPersistableBusinessObjectBase implements Inactivatable, Identifiable, PersistableAttachment {

    private String id;
    private byte[] attachmentContent;
    private String fileName;
    private String contentType;
    private boolean active;

    private transient FormFile overrideBeansFile;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public byte[] getAttachmentContent() {
        return attachmentContent;
    }

    @Override
    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public FormFile getOverrideBeansFile() {
        return overrideBeansFile;
    }

    public void setOverrideBeansFile(FormFile overrideBeansFile) {
        this.overrideBeansFile = overrideBeansFile;
    }
}
