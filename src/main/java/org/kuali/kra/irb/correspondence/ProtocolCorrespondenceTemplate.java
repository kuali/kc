/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.irb.correspondence;

import java.util.LinkedHashMap;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.Committee;

public class ProtocolCorrespondenceTemplate extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer protoCorrespTemplId; 
    private String protoCorrespTypeCode; 
    private Long committeeIdFk; 
    private String fileName;
    private byte[] correspondenceTemplate; 
    
    private Committee committee;
    private transient FormFile templateFile;
    
    public ProtocolCorrespondenceTemplate() {
        super();
        this.committee = new Committee();
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

    public void setCommitteeIdFk(Long committeeIdFk) {
        this.committeeIdFk = committeeIdFk;
    }

    public Long getCommitteeIdFk() {
        return committeeIdFk;
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

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }

    public FormFile getTemplateFile() {
        return templateFile;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("protoCorrespTemplId", this.getProtoCorrespTemplId());
        hashMap.put("protoCorrespTypeCode", this.getProtoCorrespTypeCode());
        hashMap.put("committeeId", this.getCommitteeIdFk());
        hashMap.put("fileName", this.getFileName());
        hashMap.put("correspondenceTemplate", this.getCorrespondenceTemplate());
        return hashMap;
    }
    
}