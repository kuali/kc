/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.actions.print;

import java.io.Serializable;

import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateBase;

/**
 * This class represents a Correspondence available for print within Protocol, Schedule and Committee
 * print panes.
 */
public class CorrespondencePrintOption implements Serializable {

    private static final long serialVersionUID = 2254998166111344060L;

    private String correspondenceTypeCode;
    private Long correspondenceId;
    private String label;
    private String description;
    private String itemKey;
    private String itemCode;
    private String subItemKey;
    private String subItemCode;
    private boolean selected = false;
    private String moduleId;

    private ProtocolCorrespondenceTemplateBase protocolCorrespondenceTemplate;

    private String scheduleId;
    private String committeeId;
    private String meetingId;
    
    
    public String getCorrespondenceTypeCode() {
        return correspondenceTypeCode;
    }
    
    public void setCorrespondenceTypeCode(String correspondenceTypeCode) {
        this.correspondenceTypeCode = correspondenceTypeCode;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Long getCorrespondenceId() {
        return correspondenceId;
    }

    public void setCorrespondenceId(Long correspondenceId) {
        this.correspondenceId = correspondenceId;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getSubItemKey() {
        return subItemKey;
    }

    public void setSubItemKey(String subItemKey) {
        this.subItemKey = subItemKey;
    }

    public String getSubItemCode() {
        return subItemCode;
    }

    public void setSubItemCode(String subItemCode) {
        this.subItemCode = subItemCode;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public ProtocolCorrespondenceTemplateBase getProtocolCorrespondenceTemplate() {
        return protocolCorrespondenceTemplate;
    }

    public void setProtocolCorrespondenceTemplate(ProtocolCorrespondenceTemplateBase protocolCorrespondenceTemplate) {
        this.protocolCorrespondenceTemplate = protocolCorrespondenceTemplate;
    }

    public String getScheduleId() {
        return scheduleId;
    }
    
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
}
