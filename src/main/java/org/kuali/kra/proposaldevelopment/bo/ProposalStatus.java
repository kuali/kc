/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class represents a proposal status.  It maps to a separate table in
 * the database with a 1:1 relationship with the eps_proposal table.  This
 * is so the status can be updated from the Budget module or the Propsal
 * Development module without causing a wholesale collision between the two
 * documents.
 */
public class ProposalStatus extends KraPersistableBusinessObjectBase {
    
    private String proposalNumber;
    private String budgetStatusCode;
    
    public String getProposalNumber() {
        return proposalNumber;
    }
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
    public String getBudgetStatusCode() {
        return budgetStatusCode;
    }
    public void setBudgetStatusCode(String budgetStatusCode) {
        this.budgetStatusCode = budgetStatusCode;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("budgetStatusCode", getBudgetStatusCode());
        return hashMap;
    }

}
