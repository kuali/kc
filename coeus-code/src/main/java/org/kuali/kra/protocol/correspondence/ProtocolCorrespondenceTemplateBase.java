/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
