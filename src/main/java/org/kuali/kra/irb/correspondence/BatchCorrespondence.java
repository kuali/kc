/*
 * Copyright 2006-2010 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.actions.ProtocolActionType;

public class BatchCorrespondence extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private String batchCorrespondenceTypeCode; 
    private String description; 
    private Integer defaultTimeWindow; 
    private String finalActionTypeCode; 
    private String finalActionCorrespType;
    
    private List<BatchCorrespondenceDetail> batchCorrespondenceDetails;
    
    private ProtocolCorrespondenceType protocolCorrespondenceType;
    private ProtocolActionType protocolActionType;
    
    public BatchCorrespondence() {
        setBatchCorrespondenceDetails(new ArrayList<BatchCorrespondenceDetail>());
    } 
    
    public String getBatchCorrespondenceTypeCode() {
        return batchCorrespondenceTypeCode;
    }

    public void setBatchCorrespondenceTypeCode(String batchCorrespondenceTypeCode) {
        this.batchCorrespondenceTypeCode = batchCorrespondenceTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDefaultTimeWindow() {
        return defaultTimeWindow;
    }

    public void setDefaultTimeWindow(Integer defaultTimeWindow) {
        this.defaultTimeWindow = defaultTimeWindow;
    }

    public String getFinalActionTypeCode() {
        return finalActionTypeCode;
    }

    public void setFinalActionTypeCode(String finalActionTypeCode) {
        this.finalActionTypeCode = finalActionTypeCode;
    }

    public String getFinalActionCorrespType() {
        return finalActionCorrespType;
    }

    public void setFinalActionCorrespType(String finalActionCorrespType) {
        this.finalActionCorrespType = finalActionCorrespType;
    }

    public List<BatchCorrespondenceDetail> getBatchCorrespondenceDetails() {
        List<BatchCorrespondenceDetail> result = this.batchCorrespondenceDetails;
        Collections.sort(result);
        return result;
    }

    public void setBatchCorrespondenceDetails(List<BatchCorrespondenceDetail> batchCorrespondenceDetails) {
        this.batchCorrespondenceDetails = batchCorrespondenceDetails;
    }

    public ProtocolCorrespondenceType getProtocolCorrespondenceType() {
        return protocolCorrespondenceType;
    }

    public void setProtocolCorrespondenceType(ProtocolCorrespondenceType protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public ProtocolActionType getProtocolActionType() {
        return protocolActionType;
    }

    public void setProtocolActionType(ProtocolActionType protocolActionType) {
        this.protocolActionType = protocolActionType;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("BatchCorresponcenceTypeCode", this.getBatchCorrespondenceTypeCode());
        hashMap.put("description", this.getDescription());
        hashMap.put("defaultTimeWindow", this.getDefaultTimeWindow());
        hashMap.put("finalActionTypeCode", this.getFinalActionTypeCode());
        hashMap.put("finalActionCorrespType", this.getFinalActionCorrespType());
        return hashMap;
    }
    
}