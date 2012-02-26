/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProposalStatus extends KraPersistableBusinessObjectBase {

    public static final Integer PENDING = 1;

    public static final Integer FUNDED = 2;

    private static final long serialVersionUID = 1L;

    private Integer proposalStatusCode;

    private String description;

    public ProposalStatus() {
    }

    public Integer getProposalStatusCode() {
        return proposalStatusCode;
    }

    public void setProposalStatusCode(Integer proposalStatusCode) {
        this.proposalStatusCode = proposalStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean equals(Integer other) {
        return proposalStatusCode.intValue() == other.intValue();
    }
}
