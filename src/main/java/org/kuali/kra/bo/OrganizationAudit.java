/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class OrganizationAudit extends KraPersistableBusinessObjectBase {

	private String fiscalYear;
	private String organizationId;
	private boolean auditAccepted;
	private String auditComment;
	private Organization organization;

	public OrganizationAudit(){
		super();
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public boolean getAuditAccepted() {
		return auditAccepted;
	}

	public void setAuditAccepted(boolean auditAccepted) {
		this.auditAccepted = auditAccepted;
	}

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("fiscalYear", getFiscalYear());
		hashMap.put("organizationId", getOrganizationId());
		hashMap.put("auditAccepted", getAuditAccepted());
		hashMap.put("auditComment", getAuditComment());
		return hashMap;
	}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
