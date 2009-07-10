/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.submit;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity 
@Table(name="SUBMISSION_STATUS")
public class ProtocolSubmissionStatus extends KraPersistableBusinessObjectBase { 

    private static final long serialVersionUID = -3005754570292744646L;

    public static final String COMPLETE = "200";
    public static final String APPROVED = "203";
    public static final String EXEMPT = "204";
    public static final String DISAPPROVED = "205";
    public static final String CLOSED = "207";
    public static final String TERMINATED = "208";

    @Id 
    @Column(name="SUBMISSION_STATUS_CODE")
    private String protocolSubmissionStatusCode; 

    @Column(name="DESCRIPTION")
    private String description; 
 
    public ProtocolSubmissionStatus() { 

    } 

    public String getProtocolSubmissionStatusCode() {
        return protocolSubmissionStatusCode;
    }

    public void setProtocolSubmissionStatusCode(String protocolSubmissionStatusCode) {
        this.protocolSubmissionStatusCode = protocolSubmissionStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("submissionStatusCode", this.getProtocolSubmissionStatusCode());
        hashMap.put("description", this.getDescription());
        return hashMap;
    }
    
}