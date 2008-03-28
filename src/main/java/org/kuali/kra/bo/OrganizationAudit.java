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
