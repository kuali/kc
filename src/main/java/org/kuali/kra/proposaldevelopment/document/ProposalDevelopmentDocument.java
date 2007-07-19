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
import org.kuali.core.document.TransactionalDocumentBase;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class ProposalDevelopmentDocument extends TransactionalDocumentBase implements Copyable {
    
    private Integer proposalNumber;
    
    // TODO Refactor into parent class?
    private String updateUser;
    private Timestamp updateTimestamp;
    
    public ProposalDevelopmentDocument() {
        super();
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        this.setUpdateTimestamp(((DateTimeService) KraServiceLocator.getService("dateTimeService")).getCurrentTimestamp());
        this.setUpdateUser(GlobalVariables.getUserSession().getLoggedInUserNetworkId().substring(0, 8));
    }
    
    public Integer getProposalNumber() {
        return proposalNumber;
    }
    public void setProposalNumber(Integer proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
