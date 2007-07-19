/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.document;

import java.sql.Timestamp;

import org.kuali.core.document.Copyable;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class ProposalDevelopmentDocument extends ResearchDocumentBase implements Copyable {
    
    private Integer proposalNumber;
    
    public ProposalDevelopmentDocument() {
        super();
    }
    
    public Integer getProposalNumber() {
        return proposalNumber;
    }
    public void setProposalNumber(Integer proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
}
