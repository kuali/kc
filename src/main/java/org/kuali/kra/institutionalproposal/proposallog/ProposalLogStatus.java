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
package org.kuali.kra.institutionalproposal.proposallog;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;

public class ProposalLogStatus extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private String proposalLogStatusCode; 
    private String description; 
    
    public ProposalLogStatus() { 

    } 
    
    public String getProposalLogStatusCode() {
        return proposalLogStatusCode;
    }

    public void setProposalLogStatusCode(String proposalLogStatusCode) {
        this.proposalLogStatusCode = proposalLogStatusCode;
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
        hashMap.put("proposalLogStatusCode", this.getProposalLogStatusCode());
        hashMap.put("description", this.getDescription());
        return hashMap;
    }
    
}