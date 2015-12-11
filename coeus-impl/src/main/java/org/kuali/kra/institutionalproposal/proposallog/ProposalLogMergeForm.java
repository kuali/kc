/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.institutionalproposal.proposallog;

import org.kuali.rice.kns.web.struts.form.KualiForm;

import java.util.List;

public class ProposalLogMergeForm extends KualiForm {

    private static final long serialVersionUID = 1414700067134931878L;
    
    private String proposalLogNumber;
    private String institutionalProposalNumber;
    
    private String proposalLogTypeCode;
    private String proposalLogTypeCodeDescription;
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

	public String getProposalLogTypeCodeDescription() {
		return proposalLogTypeCodeDescription;
	}

	public void setProposalLogTypeCodeDescription(String proposalLogTypeCodeDescription) {
		this.proposalLogTypeCodeDescription = proposalLogTypeCodeDescription;
	}
    
}
