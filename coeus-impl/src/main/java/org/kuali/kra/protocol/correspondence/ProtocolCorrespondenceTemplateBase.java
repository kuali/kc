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
package org.kuali.kra.protocol.correspondence;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

public abstract class ProtocolCorrespondenceTemplateBase extends KcPersistableBusinessObjectBase implements Comparable<ProtocolCorrespondenceTemplateBase> {

    private static final long serialVersionUID = 1L;

    private Integer protoCorrespTemplId;

    private String protoCorrespTypeCode;

    private String committeeId;

    private String fileName;

    private byte[] correspondenceTemplate;

    private transient FormFile templateFile;

    public ProtocolCorrespondenceTemplateBase() {
        super();
    }

    public Integer getProtoCorrespTemplId() {
        return protoCorrespTemplId;
    }

    public void setProtoCorrespTemplId(Integer protoCorrespTemplId) {
        this.protoCorrespTemplId = protoCorrespTemplId;
    }

    public String getProtoCorrespTypeCode() {
        return protoCorrespTypeCode;
    }

    public void setProtoCorrespTypeCode(String protoCorrespTypeCode) {
        this.protoCorrespTypeCode = protoCorrespTypeCode;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getCorrespondenceTemplate() {
        return correspondenceTemplate;
    }

    public void setCorrespondenceTemplate(byte[] correspondenceTemplate) {
        this.correspondenceTemplate = correspondenceTemplate;
    }

    public CommitteeBase getCommittee() {
        return getCommitteeService().getCommitteeById(committeeId);
    }

    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }

    public FormFile getTemplateFile() {
        return templateFile;
    }

    public int compareTo(ProtocolCorrespondenceTemplateBase arg) {
        return this.getCommittee().getCommitteeName().compareTo(arg.getCommittee().getCommitteeName());
    }

    private CommitteeServiceBase getCommitteeService() {
        return KcServiceLocator.getService(getCommitteeServiceClassHook());
    }
    
    protected abstract Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook();
}
