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
package org.kuali.kra.coi.notesandattachments.attachments;

public class FinancialEntityAttachmentSummary {
    
    private String key;
    private String description;
    private String linkId;
    
    public FinancialEntityAttachmentSummary(String key, String description, String linkId) {
        this.key = key;
        this.description = description;
        this.linkId = linkId;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLinkId() {
        return linkId;
    }
    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }
}
