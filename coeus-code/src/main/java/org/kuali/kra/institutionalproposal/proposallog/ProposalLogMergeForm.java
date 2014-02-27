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
package org.kuali.kra.institutionalproposal.proposallog;

import org.kuali.rice.kns.web.struts.form.KualiForm;

import java.util.List;

public class ProposalLogMergeForm extends KualiForm {

    private static final long serialVersionUID = 1414700067134931878L;
    
    private String proposalLogNumber;
    private String institutionalProposalNumber;
    
    private String proposalLogTypeCode;
    private String piId;
    private String rolodexId;
    private List<ProposalLog> matchedProposalLogs;
    
    public String getProposalLogNumber() {
        return proposalLogNumber;
    }
    
    public void setProposalLogNumber(String proposalLogNumber) {
        this.proposalLogNumber = proposalLogNumber;
    }
    
    public String getInstitutionalProposalNumber() {
        return institutionalProposalNumber;
    }
    
    public void setInstitutionalProposalNumber(String institutionalProposalNumber) {
        this.institutionalProposalNumber = institutionalProposalNumber;
    }
    
    public String getProposalLogTypeCode() {
        return proposalLogTypeCode;
    }

    public void setProposalLogTypeCode(String proposalLogTypeCode) {
        this.proposalLogTypeCode = proposalLogTypeCode;
    }

    public String getPiId() {
        return piId;
    }

    public void setPiId(String piId) {
        this.piId = piId;
    }

    public List<ProposalLog> getMatchedProposalLogs() {
        return matchedProposalLogs;
    }

    public void setMatchedProposalLogs(List<ProposalLog> matchedProposalLogs) {
        this.matchedProposalLogs = matchedProposalLogs;
    }

    public String getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(String rolodexId) {
        this.rolodexId = rolodexId;
    }
    
}
